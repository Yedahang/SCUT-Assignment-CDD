package com.development.scut_cdd.UserState;

import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.Config;
import com.development.scut_cdd.F_class.f_HeadView;
import com.development.scut_cdd.F_class.f_SPUtils;
import com.development.scut_cdd.F_class.f_SettingView;
import com.development.scut_cdd.R;

import java.util.concurrent.Callable;

public class SinglePlayingState implements UserState{

    public SinglePlayingState() {
        CardBoardActivity.getCardBoardActivity().setContentView(R.layout.activity_card_board);
        CardBoardActivity.getCardBoardActivity().initServer();
        CardBoardActivity.getCardBoardActivity().initClient(Config.IPADDRESS);
        CardBoardActivity.getCardBoardActivity().getUiCon().initAllUI();
        CardBoardActivity.getCardBoardActivity().setPlayingSingle(true);
        CardBoardActivity.getCardBoardActivity().changeStateTo(new ReadyState());

        f_SettingView.setAppCompatActivity(CardBoardActivity.getCardBoardActivity());
        f_SettingView settingView=CardBoardActivity.getCardBoardActivity().findViewById(R.id.f_setting);

        ImageButton imageButton_info =CardBoardActivity.getCardBoardActivity().findViewById(R.id.setibt);
        imageButton_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingView.bringToFront();
                settingView.setVisibility(View.VISIBLE);

            }
        });


        //检查版本
        CardBoardActivity.getCardBoardActivity().checkVersion();
        f_HeadView.setAppCompatActivity(CardBoardActivity.getCardBoardActivity());
        f_HeadView headView = CardBoardActivity.getCardBoardActivity().findViewById(R.id.myHeadView);
        headView.bringToFront();

        CardBoardActivity.getCardBoardActivity().setIvHead(headView.getInformationView().getIvHead());

        //取出缓存
        String imageUrl = f_SPUtils.getString("imageUrl",null,CardBoardActivity.getCardBoardActivity());
        if(imageUrl != null){
            Glide.with(CardBoardActivity.getCardBoardActivity()).load(imageUrl).apply(CardBoardActivity.getCardBoardActivity().getRequestOptions()).into(headView.getInformationView().ivHead);
            ImageButton imageButton = headView.getInformation_Button();
            Glide.with(CardBoardActivity.getCardBoardActivity()).load(imageUrl).error(R.drawable.f_beach_background).into(imageButton);
        }



    }

    @Override
    public void setAction() {

    }
}
