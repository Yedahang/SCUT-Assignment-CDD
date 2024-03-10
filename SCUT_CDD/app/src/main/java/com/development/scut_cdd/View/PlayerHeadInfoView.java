package com.development.scut_cdd.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.development.scut_cdd.R;

public class PlayerHeadInfoView extends LinearLayout {
    private ImageView mAvatarView;
    private TextView mNameView;

    public PlayerHeadInfoView(Context context) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_player_info, this, true);
//        Button button=findViewById(R.id.testButton);
//        button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                button.setVisibility(GONE);
//            }
//        });
    }

    public PlayerHeadInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_player_info, this, true);
    }

    public PlayerHeadInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PlayerHeadInfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void init(Context context) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        inflater.inflate(R.layout.view_player_info, this, true);
//
//        mAvatarView = findViewById(R.id.player_avatar);
//        mNameView = findViewById(R.id.player_name);
    }

    public void setAvatar(int resourceId) {
        mAvatarView.setImageResource(resourceId);
    }

    public void setName(String name) {
        mNameView.setText(name);
    }
}
