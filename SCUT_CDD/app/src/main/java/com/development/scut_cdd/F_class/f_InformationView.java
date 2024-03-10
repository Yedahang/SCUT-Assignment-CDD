package com.development.scut_cdd.F_class;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.development.scut_cdd.R;
import com.google.android.material.imageview.ShapeableImageView;

import Model.Entity.Player;

public class f_InformationView extends FrameLayout {
    public ImageButton exitButton;
    private f_InformationView informationView;
    public static AppCompatActivity appCompatActivity;
    private Context mContext;
    private TextView Player_Name;
    private LayoutInflater Inflater;
    public static View inflatedView_info;
    public TextView title;
    public TextView score;
    public TextView rank;
    public TextView win_or_lose;
    public TextView winRate;
    public ShapeableImageView ivHead;

    ShapeableImageView shapeableImageView;



    public Player player = new Player(0, "今天晚上吃什么", 250);

    public void setPlayer(Player player) {
        this.player = player;
        Player_Name.setText(player.getNickName());
        title.setText(player.getTitle());
        score.setText("积分: " + player.getScore() + " 分");
        rank.setText("排名: " + player.getRank() + " 名");
        win_or_lose.setText("战绩: " + player.getWinTimes() + "胜/" + player.getLoseTimes() + "负");
        winRate.setText("胜率 :" + 100 * player.winRate() + "%");
    }

    public static void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        f_InformationView.appCompatActivity = appCompatActivity;
    }

    public f_InformationView(Context context) {
        super(context);
        mContext = context;
        Inflater = LayoutInflater.from(getContext());
        inflatedView_info = Inflater.inflate(R.layout.f_info, this, false);
        addView(inflatedView_info);
        informationView = this;
        init();
    }

    public f_InformationView(Context context, AttributeSet attrs, Player player) {

        super(context);
        mContext = context;
        Inflater = LayoutInflater.from(getContext());
        inflatedView_info = Inflater.inflate(R.layout.f_info, this, false);
        shapeableImageView = findViewById(R.id.iv_head_info);
        addView(inflatedView_info);
        informationView = this;
        this.player = player;
        init();

    }

    public f_InformationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        Inflater = LayoutInflater.from(getContext());
        inflatedView_info = Inflater.inflate(R.layout.f_info, this, false);
        addView(inflatedView_info);
        informationView = this;
        init();


    }

    public ShapeableImageView getIvHead() {
        return ivHead;
    }

    public void init() {
        ivHead = findViewById(R.id.iv_head_info);
        title = findViewById(R.id.title);
        score = findViewById(R.id.tv_4);
        rank = findViewById(R.id.tv_6);
        win_or_lose = findViewById(R.id.tv_5);
        winRate = findViewById(R.id.tv_7);
        Player_Name = findViewById(R.id.tv_3);
        setPlayer(player);
        exitButton = findViewById(R.id.mbtn_exit);

        exitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                informationView.setVisibility(GONE);
//                hideContainerView();
            }

        });
    }

}