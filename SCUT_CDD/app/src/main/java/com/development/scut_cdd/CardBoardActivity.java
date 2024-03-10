package com.development.scut_cdd;

import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.development.scut_cdd.Clientlayer.ClientDataGenerator;
import com.development.scut_cdd.Clientlayer.ClientRunnable;
import com.development.scut_cdd.Clientlayer.DataSender;
import com.development.scut_cdd.F_class.f_CameraUtils;
import com.development.scut_cdd.F_class.f_HeadView;
import com.development.scut_cdd.F_class.f_SPUtils;
import com.development.scut_cdd.ServerLayer.ServerService;
import com.development.scut_cdd.UserState.ReadyState;
import com.development.scut_cdd.UserState.UserState;
import com.development.scut_cdd.View.BluetoothView;
import com.development.scut_cdd.View.InputIPView;
import com.development.scut_cdd.View.RegisterView;
import com.development.scut_cdd.View.StartView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import Controller.GameTurnCon;
import ControllerImpl.GameTurnConImpl;
import ControllerImpl.UIConImpl;
import Model.Entity.DataPackage;
import Model.Entity.RoomModel;
import Wifi.NetworkUtils;

public class CardBoardActivity extends AppCompatActivity {

    private RelativeLayout container;
    private ImageButton imageButton_info;
    private ImageButton imageButtonExit;

    //图片控件
    public ShapeableImageView ivHead;
    //拍照和相册获取图片的Bitmap
    private Bitmap orc_bitmap;
    //Base64
    private String base64Pic;
    //Glide请求图片选项配置



    Messenger ServiceMessager;
    private static final String TAG = "wangshu";
    UserState userState;
    static CardBoardActivity cardBoardActivity;
    BlockingQueue<RoomModel> messageQueue = new LinkedBlockingQueue<>();

    DataPackage dataPackage=null;

    Vector<String> order = null;
    UIConImpl uiCon;
    DataSender dataSender;
    ClientRunnable clientRunnable;
    private ExecutorService executorService;//线程池

    private boolean isPlayingSingle=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardBoardActivity=this;

        f_HeadView.setAppCompatActivity(this);
        StartView.setAppCompatActivity(this);
        InputIPView.setAppCompatActivity(this);
        RegisterView.setAppCompatActivity(this);
        BluetoothView.setAppCompatActivity(this);



        setScreenWidth();
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//设置全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.yao_main2);


//        //创建线程池
        executorService = Executors.newFixedThreadPool(6);
        uiCon=new UIConImpl(this);




//        uiCon.initAllUI();//必须在cardboard时候用
//
//
//
//        EditText editText = findViewById(R.id.ipEdit);
//        Button serverbtn =findViewById(R.id.serverbtn);
//        serverbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initServer();
//            }
//        });
//
//        Button ok=findViewById(R.id.okip);
//        ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initClient(editText.getText().toString());
//            }
//        });
//

//
//        Button idbn = findViewById(R.id.idbtn);
//        idbn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText t=findViewById(R.id.idedit);
//                 Config.USER_ID=t.getText().toString();
//                 TextView t1=findViewById(R.id.idtext);
//                t1.setText(Config.USER_ID);
//                t1.setTextColor(Color.WHITE);
////                t1.setBackgroundColor(Color.parseColor("#FFA500"));
//            }
//        });


//        changeStateTo(new ReadyState(this));

    }





    public void initServer(){
//        Button server=findViewById(R.id.serverbtn);
        Config.IPADDRESS= NetworkUtils.getIpAddress(getApplicationContext());
//        TextView ipaddress = findViewById(R.id.sererIpAddress);
//        ipaddress.setTextColor(Color.WHITE);
//        ipaddress.setText(Config.IPADDRESS);
        //***********************************************服务器端***************************
        Intent myServiceIntent=new Intent(CardBoardActivity.this, ServerService.class);
//        bindService(myServiceIntent,myServiceConnection, Context.BIND_AUTO_CREATE);
        startService(myServiceIntent);
    }
    public void initClient(String ip){
        //*********************************************本地客户端*****************************
        clientRunnable= new ClientRunnable(this,ip);
        executorService.submit(clientRunnable);
        dataSender=new DataSender(clientRunnable);

    }
    private String intToIp(int ip) {
        return ((ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                ((ip >> 24) & 0xFF));
    }
    private String getIpAddress(String bssid) {
        String[] ipParts = bssid.split(":");
        String ipAddress = "";
        for (int i = 0; i < ipParts.length; i++) {
            if (i == ipParts.length - 1) {
                ipAddress += Integer.parseInt(ipParts[i], 16);
            } else {
                ipAddress += Integer.parseInt(ipParts[i], 16) + ".";
            }
        }
        return ipAddress;
    }

    //获取屏幕宽度
    private void setScreenWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Config.screenWidth = displayMetrics.widthPixels;

    }

    public void changeStateTo(UserState userState){
        this.userState=userState;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }


    public BlockingQueue<RoomModel> getMessageQueue() {
        return messageQueue;
    }

    public void putMessage(RoomModel s){
        try{
            messageQueue.put(s);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
//    public void pass_text_Display(ImageView imageView){
//        imageView.setVisibility(View.VISIBLE);
//        MyAnimation.zoomInAndOut(imageView);
//    }
//    public Handler mHandler = new Handler(Looper.getMainLooper()){
//
//        @Override
//        public void handleMessage( Message msg){
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    pass_text_Display((ImageView) msg.obj);
//                }
//            });
//        }
//    };

    public void setOrder(Vector<String> order) {
        this.order = order;
    }

    public Vector<String> getOrder() {
        return order;
    }

    public ClientRunnable getClientRunnable() {
        return clientRunnable;
    }

    public DataPackage getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(DataPackage dataPackage) {
        this.dataPackage = dataPackage;
    }

    public DataSender getDataSender() {
        return dataSender;
    }

    public UIConImpl getUiCon() {
        return uiCon;
    }

    public static CardBoardActivity getCardBoardActivity() {
        return cardBoardActivity;
    }

    public boolean isPlayingSingle() {
        return isPlayingSingle;
    }

    public void setPlayingSingle(boolean playingSingle) {
        isPlayingSingle = playingSingle;
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

    public void checkVersion() {
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
            f_HeadView headView = findViewById(R.id.myHeadView);
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

    public void setIvHead(ShapeableImageView ivHead) {
        this.ivHead = ivHead;
    }

    public RequestOptions getRequestOptions() {
        return requestOptions;
    }
}