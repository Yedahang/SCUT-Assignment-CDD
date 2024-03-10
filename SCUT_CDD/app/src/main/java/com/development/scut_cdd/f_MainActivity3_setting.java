package com.development.scut_cdd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.development.scut_cdd.F_class.f_SettingView;
import com.development.scut_cdd.R;


public class f_MainActivity3_setting extends AppCompatActivity {


    private ImageButton imageButton_info;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        f_SettingView.setAppCompatActivity(this);
        setContentView(R.layout.f_activity_main_activity3_setting);
        f_SettingView settingView=findViewById(R.id.settingView);
        imageButton_info=findViewById(R.id.btn_setting);
        imageButton_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingView.setVisibility(View.VISIBLE);
            }
        });




    }


}