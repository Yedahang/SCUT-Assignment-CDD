package com.development.scut_cdd.View;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.development.scut_cdd.R;

public class BluetoothView extends RelativeLayout {
    private Context mContext;
    private LayoutInflater Inflater;
    private View inflate;
    protected ImageButton button_closeBluetooth;
    protected ImageButton button_refresh;
    private StartView startView;

    private static AppCompatActivity appCompatActivity;

    public static void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        BluetoothView.appCompatActivity = appCompatActivity;
    }

    public BluetoothView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        Inflater = LayoutInflater.from(context);
        // 加载布局文件
        inflate = Inflater.inflate(R.layout.bluetooth_view, this, true);

        init();
    }

    public void init()
    {
        button_refresh = findViewById(R.id.refreshBluetooth);
        // 刷新 的监听器
        button_refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(appCompatActivity, R.anim.search_animation);
                button_refresh.startAnimation(animation);
            }
        });

        button_closeBluetooth = findViewById(R.id.closeBluetooth);
        // 关闭 的监听器
        button_closeBluetooth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BluetoothView bluetoothView = appCompatActivity.findViewById(R.id.bluetoothView);
                bluetoothView.setVisibility(GONE);

                startView = appCompatActivity.findViewById(R.id.startView);
                startView.button_logo.setEnabled(true);
                startView.button_return.setEnabled(true);
                startView.button_blueTooth.setEnabled(true);
                startView.button_wlan.setEnabled(true);
            }
        });
    }
}
