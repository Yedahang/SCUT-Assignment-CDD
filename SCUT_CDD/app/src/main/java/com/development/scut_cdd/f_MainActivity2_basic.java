package com.development.scut_cdd;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;


import com.development.scut_cdd.F_class.f_CameraUtils;
import com.development.scut_cdd.F_class.f_HeadView;
import com.development.scut_cdd.F_class.f_SPUtils;
import com.development.scut_cdd.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import Model.Entity.Player;

public class f_MainActivity2_basic extends AppCompatActivity {

    private RelativeLayout container;
    private ImageButton imageButton_info;
    private ImageButton imageButtonExit;

    //图片控件
    public  ShapeableImageView ivHead;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;
    //Base64
    private String base64Pic;
    //Glide请求图片选项配置


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Player player=new Player(0,"Jack",2000);

        //检查版本
        checkVersion();
        f_HeadView.setAppCompatActivity(this);
        setContentView(R.layout.f_activity_main_activity2_basic);
        f_HeadView headView = findViewById(R.id.view_head);
        headView.getInformationView().setPlayer(player);
        headView.setPlayer(headView.getInformationView());
         ivHead=headView.getInformationView().getIvHead();

        //取出缓存
        String imageUrl = f_SPUtils.getString("imageUrl",null,this);
        if(imageUrl != null){
            Glide.with(this).load(imageUrl).apply(requestOptions).into(headView.getInformationView().ivHead);
            ImageButton imageButton = headView.getInformation_Button();
            Glide.with(this).load(imageUrl).error(R.drawable.f_beach_background).into(imageButton);
        }
    }

    private RxPermissions rxPermissions;
    /**
     * Toast提示
     *
     * @param msg
     */
    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 检查版本
     */
    //是否拥有权限
    private boolean hasPermissions = false;

    private void checkVersion() {
        //Android6.0及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            rxPermissions = new RxPermissions(this);
            //权限请求
            rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {//申请成功
                            showMsg("已获取权限");
                            hasPermissions = true;
                        } else {//申请失败
                            showMsg("权限未开启");
                            hasPermissions = false;
                        }
                    });
        } else {
            //Android6.0以下
            showMsg("无需请求动态权限");
        }
    }

    //底部弹窗
    private BottomSheetDialog bottomSheetDialog;
    //弹窗视图
    private View bottomView;
    /**
     * 更换头像
     *
     * @param view
     */
    public void changeAvatar(View view) {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomView = getLayoutInflater().inflate(R.layout.f_dialog_bottom, null);
        bottomSheetDialog.setContentView(bottomView);
        // bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundColor(Color.TRANSPARENT);
        //TextView tvTakePictures = bottomView.findViewById(R.id.tv_take_pictures);
        TextView tvOpenAlbum = bottomView.findViewById(R.id.tv_open_album);
        TextView tvCancel = bottomView.findViewById(R.id.tv_cancel);

        //拍照
//        tvTakePictures.setOnClickListener(v -> {
//            takePhoto();
//            showMsg("拍照");
//            bottomSheetDialog.cancel();
//        });
        //打开相册
        tvOpenAlbum.setOnClickListener(v -> {
            openAlbum();
            showMsg("打开相册");
            bottomSheetDialog.cancel();
        });
        //取消
        tvCancel.setOnClickListener(v -> {
            bottomSheetDialog.cancel();
        });
        bottomSheetDialog.show();
    }

    //存储拍完照后的图片
    private File outputImagePath;
    //启动相机标识
    public static final int TAKE_PHOTO = 1;
    //启动相册标识
    public static final int SELECT_PHOTO = 2;
    /**
     * 拍照
     */
    private void takePhoto() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy_MM_dd_HH_mm_ss");
        String filename = timeStampFormat.format(new Date());
        outputImagePath = new File(getExternalCacheDir(),
                filename + ".jpg");
        Intent takePhotoIntent = f_CameraUtils.getTakePhotoIntent(this, outputImagePath);
        // 开启一个带有返回值的Activity，请求码为TAKE_PHOTO
        startActivityForResult(takePhotoIntent, TAKE_PHOTO);
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        if (!hasPermissions) {
            showMsg("未获取到权限");
            checkVersion();
            return;
        }
        startActivityForResult(f_CameraUtils.getSelectPhotoIntent(), SELECT_PHOTO);
    }

    private RequestOptions requestOptions = RequestOptions.circleCropTransform()
            .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
            .skipMemoryCache(true);//不做内存缓存

    /**
     * 返回到Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //拍照后返回
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    //显示图片
                    displayImage(outputImagePath.getAbsolutePath());
                }
                break;
            //打开相册后返回
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    String imagePath = null;
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        //4.4及以上系统使用这个方法处理图片
                        imagePath = f_CameraUtils.getImageOnKitKatPath(data, this);
                    } else {
                        imagePath = f_CameraUtils.getImageBeforeKitKatPath(data, this);
                    }
                    //显示图片
                    displayImage(imagePath);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 通过图片路径显示图片
     */
    /**
     * 通过图片路径显示图片
     */
    private void displayImage(String imagePath) {
        if (!TextUtils.isEmpty(imagePath)) {

            //放入缓存
            f_SPUtils.putString("imageUrl",imagePath,this);

            //显示图片
            Glide.with(this).load(imagePath).apply(requestOptions).error(R.drawable.f_beach_background) .into(ivHead);
            f_HeadView headView = findViewById(R.id.view_head);
            ImageButton imageButton = headView.getInformation_Button();
            Glide.with(this).load(imagePath).error(R.drawable.f_beach_background).into(imageButton);
            //  压缩图片
               orc_bitmap = f_CameraUtils.compression(BitmapFactory.decodeFile(imagePath));
               headView.getInformationView().player.setHeadBitmap(orc_bitmap);
            //转Base64
            // base64Pic = BitmapUtils.bitmapToBase64(orc_bitmap);

        } else {
            showMsg("图片获取失败");
        }
    }

}
