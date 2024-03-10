package com.development.scut_cdd.UserState;

import static java.lang.Thread.sleep;

import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.Config;
import com.development.scut_cdd.R;
import com.development.scut_cdd.View.MyHandCardArea;
import com.development.scut_cdd.View.MyShowingCard;
import com.development.scut_cdd.View.MyShowingCardArea;
import com.development.scut_cdd.util.MyAnimation;

import ControllerImpl.UIHelper;
import Model.Entity.CardGroup;
import Model.Entity.DataPackage;
import Model.Entity.UserAction;

public class ShowingState implements UserState{
    CardBoardActivity cardBoardActivity;
    ImageButton ibt_play;//出牌按钮
    ImageButton ibt_pass;//不出按钮
    ImageView pass_text;
    MyHandCardArea myHandCardArea;
    MyShowingCardArea myShowingCardArea;
    public ShowingState(CardBoardActivity cardBoardActivity) {
        this.cardBoardActivity = cardBoardActivity;
        cardBoardActivity.getUiCon().setShowingStateUI();
    }



    private void initButton(){

        ibt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //出牌
                myShowingCardArea.updateShowingCard(myHandCardArea.getSelectedCards());
                myHandCardArea.removeShownCards();




//                cardBoardActivity.getGameTurnCon().playerShowingCards(
//                        myShowingCardArea.getShowingCardList(),LocalDB.user_id);

                cardBoardActivity.changeStateTo(new WaitingState());
//                cardBoardActivity.

            }
        });
        ibt_play.setVisibility(View.VISIBLE);

        ibt_pass = cardBoardActivity.findViewById(R.id.ibt_pass);
        ibt_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibt_play.setVisibility(View.GONE);
                ibt_pass.setVisibility(View.GONE);
                RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                para.addRule(RelativeLayout.CENTER_IN_PARENT);
                RelativeLayout myLayout = cardBoardActivity.findViewById(R.id.my_layout);
                myLayout.addView(pass_text, para);
                MyAnimation.zoomInAndOut(pass_text);
            }
        });
        ibt_pass.setVisibility(View.VISIBLE);
    }



    /**
     * 描述:初始化出牌区域的位置
     * @author 叶达杭
     * @return void
    */
    private void initMyShowingCardArea() {
        RelativeLayout myLayout = cardBoardActivity.findViewById(R.id.my_layout);
        myShowingCardArea = new MyShowingCardArea(cardBoardActivity.getApplicationContext());
        RelativeLayout.LayoutParams para = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                Config.ShowingCardAreaHeight
        );
        para.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        myLayout.addView(myShowingCardArea, para);
    }

    private void initImageView(){
        pass_text = new ImageView(cardBoardActivity.getApplicationContext());
        pass_text.setImageResource(R.drawable.buchu_text);
    }

    @Override
    public void setAction() {

    }

    private void getMyHandCardArea(){

    }
}
