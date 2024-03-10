package com.development.scut_cdd.View;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.R;
import com.development.scut_cdd.UserState.SinglePlayingState;

public class StartView extends ConstraintLayout {
    private Context mContext;
    private LayoutInflater Inflater;
    private View inflate;
    private static AppCompatActivity appCompatActivity;



    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;
    public static VideoView videoView;
    protected ImageView button_logo;
    protected ImageButton button_setting;
    protected ImageButton button_rank;
    protected ImageButton button_singlePlayerGame;
    protected ImageButton button_multiPlayerGame;
    protected ImageButton button_return;
    protected ImageButton button_blueTooth;
    protected ImageButton button_wlan;
    protected InputIPView inputIPView;
    protected RegisterView registerView;
    protected BluetoothView bluetoothView;

    public static void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        StartView.appCompatActivity = appCompatActivity;
    }

    public StartView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public StartView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        /*Inflater = LayoutInflater.from(context);
        // 加载布局文件
        inflate = Inflater.inflate(R.layout.start_view, this, true);*/
        init();
    }

    public StartView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        mContext = context;
        /*Inflater = LayoutInflater.from(context);
        // 加载布局文件
        inflate = Inflater.inflate(R.layout.start_view, this, true);*/
        init();
    }

    public boolean isBluetoothEnabled()
    {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    protected void init()
    {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.start_view, this, true);


        videoView = findViewById(R.id.video);
        String videoUrl = "android.resource://" + mContext.getPackageName() + "/" + R.raw.title;
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.start();


        // logo 的监听器
        button_logo = findViewById(R.id.logo);
        button_logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 创建AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("该游戏由 华南理工大学-三人行 开发，侵权必究");
                builder.setPositiveButton("确定", null);
                builder.show();
            }
        });

        // 设置 的监听器
        button_setting = findViewById(R.id.setting);
        button_setting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("设置");
                builder.setMessage("设置");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后的操作
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 排行榜 的监听器
        button_rank = findViewById(R.id.rank);
        button_rank.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("排行榜");
                builder.setMessage("排行榜");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮后的操作
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 单机游戏 的监听器
        button_singlePlayerGame = findViewById(R.id.single_player_game);
        button_singlePlayerGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CardBoardActivity.getCardBoardActivity().changeStateTo(new SinglePlayingState());
            }
        });

        // 好友约局 的监听器
        button_multiPlayerGame = findViewById(R.id.multiplayer_game);
        button_multiPlayerGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.single_player_game).setVisibility(View.GONE);
                findViewById(R.id.multiplayer_game).setVisibility(View.GONE);
                findViewById(R.id.setting).setVisibility(View.GONE);
                findViewById(R.id.rank).setVisibility(View.GONE);
                findViewById(R.id.blueToothConnection).setVisibility(View.VISIBLE);
                findViewById(R.id.WLANConnection).setVisibility(View.VISIBLE);
                findViewById(R.id.returnLastPage).setVisibility(View.VISIBLE);

                button_logo.setEnabled(false);
                button_return.setEnabled(false);
                button_blueTooth.setEnabled(false);
                button_wlan.setEnabled(false);

                registerView = appCompatActivity.findViewById(R.id.registerView);
                registerView.restart();
                registerView.setVisibility(VISIBLE);
            }
        });

        // 返回 的监听器
        button_return = findViewById(R.id.returnLastPage);
        button_return.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                findViewById(R.id.single_player_game).setVisibility(View.VISIBLE);
                findViewById(R.id.multiplayer_game).setVisibility(View.VISIBLE);
                findViewById(R.id.setting).setVisibility(View.VISIBLE);
                findViewById(R.id.rank).setVisibility(View.VISIBLE);
                findViewById(R.id.blueToothConnection).setVisibility(View.GONE);
                findViewById(R.id.WLANConnection).setVisibility(View.GONE);
                findViewById(R.id.returnLastPage).setVisibility(View.GONE);
            }
        });

        // 蓝牙连接 的监听器
        button_blueTooth = findViewById(R.id.blueToothConnection);
        button_blueTooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("是否允许应用打开蓝牙？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                        if (bluetoothAdapter == null)
                            Toast.makeText(mContext, "当前手机设备不支持蓝牙", Toast.LENGTH_SHORT).show();
                        else {
                            //手机设备支持蓝牙，判断蓝牙是否已开启
                            if (bluetoothAdapter.isEnabled())
                            {
                                Toast.makeText(mContext, "手机蓝牙已开启", Toast.LENGTH_SHORT).show();

                                button_logo.setEnabled(false);
                                button_return.setEnabled(false);
                                button_blueTooth.setEnabled(false);
                                button_wlan.setEnabled(false);

                                bluetoothView = appCompatActivity.findViewById(R.id.bluetoothView);
                                bluetoothView.setVisibility(VISIBLE);
                            }
                            else
                            {
                                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions((Activity)mContext, new String[]{Manifest.permission.BLUETOOTH_CONNECT}, REQUEST_ENABLE_BT);
                                    return;
                                }
                                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                ((Activity) mContext).startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                            }
                        }

                    }
                });
                builder.setNegativeButton("取消", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // 热点连接 的监听器
        button_wlan = findViewById(R.id.WLANConnection);
        button_wlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("是否允许应用打开热点？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        button_logo.setEnabled(false);
                        button_return.setEnabled(false);
                        button_blueTooth.setEnabled(false);
                        button_wlan.setEnabled(false);

                        inputIPView = appCompatActivity.findViewById(R.id.inputIPView);
                        inputIPView.setVisibility(View.VISIBLE);
                    }
                });
                builder.setNegativeButton("取消", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

}
