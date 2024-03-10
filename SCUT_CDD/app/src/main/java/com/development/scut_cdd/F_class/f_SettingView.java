package com.development.scut_cdd.F_class;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.development.scut_cdd.R;

public class f_SettingView extends FrameLayout {
    private ImageButton exitButton;
    private ImageButton changeMusic;
    private ImageButton changeBeat;
    private ImageButton changeRobot;
    private ImageButton rule;

    private ImageButton back_1;
    private ImageButton back_2;
    private ImageButton back_3;
    private ImageButton back_4;



    private Context mContext;
    private f_SettingView settingView;
    private static AppCompatActivity appCompatActivity;
    private Drawable onBackground = getResources().getDrawable(R.drawable.f_on);
    private Drawable offBackground = getResources().getDrawable(R.drawable.f_off);

    public f_SettingView(@NonNull Context context) {
        super(context);
        mContext = context;
        LayoutInflater Inflater = LayoutInflater.from(getContext());
        View inflatedView_info = Inflater.inflate(R.layout.f_setting, this, false);
        addView(inflatedView_info);
        settingView=this;
        init();
    }
    public f_SettingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater Inflater = LayoutInflater.from(getContext());
        View inflatedView_info = Inflater.inflate(R.layout.f_setting, this, false);
        addView(inflatedView_info);
        settingView=this;
        init();
    }

    private void init() {
        changeMusic=findViewById(R.id.mbt_music);
        changeBeat=findViewById(R.id.mbt_beat);
        changeRobot=findViewById(R.id.mbt_robot);
        rule=findViewById(R.id.mbt_playerRule);
        back_1=findViewById(R.id.changeBackground_1);
        back_2=findViewById(R.id.changeBackground_2);
        back_3=findViewById(R.id.changeBackground_3);
        back_4=findViewById(R.id.changeBackground_4);



        exitButton=findViewById(R.id.mbtn_exit);
        exitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                settingView.setVisibility(GONE);
            }

        });
        changeMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                  change(changeMusic);
            }
        });

        changeBeat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                change(changeBeat);
            }
        });

        changeRobot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                change(changeRobot);
            }
        });

        rule.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        back_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.findViewById(R.id.my_layout).setBackgroundResource(R.drawable.f_cardboard_bg1);
            }
        });

        back_2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.findViewById(R.id.my_layout).setBackgroundResource(R.drawable.f_cardboard_bg2);


            }
        });

        back_3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.findViewById(R.id.my_layout).setBackgroundResource(R.drawable.f_cardboard_bg3);


            }
        });

        back_4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.findViewById(R.id.my_layout).setBackgroundResource(R.drawable.f_cardboard_bg4);


            }
        });


    }

    private void change(ImageButton imageButton){
    Drawable currentBackground = imageButton.getBackground();

    if (currentBackground.getConstantState().equals(onBackground.getConstantState())) {
        imageButton.setBackgroundResource(R.drawable.f_off);
    } else if (currentBackground.getConstantState().equals(offBackground.getConstantState())) {
        imageButton.setBackgroundResource(R.drawable.f_on);
    }
}

    public static void setAppCompatActivity(AppCompatActivity appCompatActivity) {
       f_SettingView.appCompatActivity = appCompatActivity;
    }



}
