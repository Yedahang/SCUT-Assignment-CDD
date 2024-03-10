package com.development.scut_cdd.UserState;

import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.R;
import com.development.scut_cdd.util.MyAnimation;

import Controller.CardBoardCon;
import ControllerImpl.CardBoardConImpl;
import com.development.scut_cdd.Clientlayer.ClientDataGenerator;

public class ReadyState implements UserState {
    CardBoardActivity cardBoardActivity;

    //等待服务器发来开局信息
    public ReadyState() {
        CardBoardActivity.getCardBoardActivity().getUiCon().setReadyStateUI();
    }


    @Override
    public void setAction() {

    }


}
