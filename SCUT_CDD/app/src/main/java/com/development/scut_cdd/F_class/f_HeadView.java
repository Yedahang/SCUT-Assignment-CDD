package com.development.scut_cdd.F_class;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.development.scut_cdd.R;

public class f_HeadView extends RelativeLayout {
    public ImageButton getInformation_Button() {
        return Information_Button;
    }

    private ImageButton Information_Button;
    private TextView Player_Name;
    private Context mContext;
    private LayoutInflater Inflater;
    private View inflatedView;
    private View inflatedView_info;
    private f_InformationView informationView;
    private static AppCompatActivity appCompatActivity;

    public f_HeadView(Context context){
        super(context);
        mContext = context;
        inflatedView = Inflater.inflate(R.layout.f_head_view, this, true);
        init();

    }

    public static void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        f_HeadView.appCompatActivity = appCompatActivity;
    }

    @SuppressLint("ResourceType")
    public f_HeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        Inflater = LayoutInflater.from(context);
//         加载布局文件
        inflatedView = Inflater.inflate(R.layout.f_head_view, this, true);
        f_InformationView.inflatedView_info=Inflater.inflate(R.layout.f_info,this,false);
        init();
    }

    public void init() {
        Player_Name=findViewById(R.id.tv_playerName);
        Information_Button = findViewById(R.id.mbtn_info);
        informationView= new f_InformationView(appCompatActivity);


        setPlayer(informationView);
        f_InformationView.setAppCompatActivity(appCompatActivity);
        LayoutParams para = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        para.addRule(RelativeLayout.ALIGN_BOTTOM);
        informationView.setVisibility(GONE);
        appCompatActivity.addContentView(informationView,para);

        Information_Button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                informationView.bringToFront();
                informationView.setVisibility(VISIBLE);
            }
        });
    }

    public  void setPlayer(f_InformationView informationView) {
        this.informationView =informationView;
        Player_Name.setText(informationView.player.getNickName());
    }


    public f_InformationView getInformationView() {
        return informationView;
    }

    public void setPlayerName(String s){
        Player_Name.setText(s);
    }
}
