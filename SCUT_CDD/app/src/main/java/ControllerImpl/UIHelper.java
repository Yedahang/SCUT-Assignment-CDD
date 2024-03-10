package ControllerImpl;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.Clientlayer.ClientDataGenerator;
import com.development.scut_cdd.Config;
import com.development.scut_cdd.R;
import com.development.scut_cdd.ServerLayer.GameRoomData;
import com.development.scut_cdd.UserState.WaitingState;
import com.development.scut_cdd.View.MyHandCardArea;
import com.development.scut_cdd.View.MyShowingCardArea;
import com.development.scut_cdd.util.MyAnimation;

import java.util.logging.Handler;

import Database.LocalDB;
import Model.Entity.CardGroup;
import Model.Entity.SelectedCardGroup;
import Model.Service.GameRule.GameRule;
import Model.ServiceImpl.GameRule.GameRuleImpl;

public class UIHelper {
    MyHandCardArea myHandCardArea;//手牌
    CardBoardActivity cardBoardActivity;
    ImageButton readyBtn ;
    ImageButton leaveBtn;

    ImageButton play_btn;

    ImageButton pass_btn;
    Button addRobot;
    ImageView MyReadyText;

    ImageView Right_pass;
    ImageView Top_pass;
    ImageView Left_pass;
    ImageView My_pass;

    MyShowingCardArea myShowingCardArea_my;
    MyShowingCardArea myShowingCardArea_right;
    MyShowingCardArea myShowingCardArea_top;
    MyShowingCardArea myShowingCardArea_left;




    public UIHelper(CardBoardActivity cardBoardActivity) {
        this.cardBoardActivity = cardBoardActivity;
    }

    public void initMyHandCardArea(){
        RelativeLayout myLayout = cardBoardActivity.findViewById(R.id.my_layout);
        myHandCardArea= new MyHandCardArea(myLayout.getContext());
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                Config.handCardAreaHeight
        );
        params1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        myLayout.addView(myHandCardArea,params1);
        myHandCardArea.initHandCard();
    }
    public void initShowingArea(){
        myShowingCardArea_my=cardBoardActivity.findViewById(R.id.ShownAreaMy);
        myShowingCardArea_left=cardBoardActivity.findViewById(R.id.ShownAreaLeft);
        myShowingCardArea_right=cardBoardActivity.findViewById(R.id.ShownAreaRight);
        myShowingCardArea_top=cardBoardActivity.findViewById(R.id.ShownAreaTop);
    }
    public void initPassText(){
        Right_pass=cardBoardActivity.findViewById(R.id.pass_text_right);
        Top_pass=cardBoardActivity.findViewById(R.id.pass_text_top);
        Left_pass=cardBoardActivity.findViewById(R.id.pass_text_left);
        My_pass=cardBoardActivity.findViewById(R.id.pass_text_my);
    }
    public void initButton(){
        //准备按钮
        readyBtn = cardBoardActivity.findViewById(R.id.ibt_ready);
        readyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UIConImpl.getGameRoomData().getPlayers().size()
//                CardBoardActivity.getCardBoardActivity().getUiCon().getCount_players()
                //是单机游戏
                if (CardBoardActivity.getCardBoardActivity().isPlayingSingle()) {
                    cardBoardActivity.getDataSender().sendData(ClientDataGenerator.playSingle());//发生初始化数据
                    readyBtn.setVisibility(View.GONE);
                    leaveBtn.setVisibility(View.GONE);
                    MyReadyText.setVisibility(View.VISIBLE);
                    MyAnimation.zoomInAndOut(MyReadyText);
                }else {
                //非单机
                    if(CardBoardActivity.getCardBoardActivity().getUiCon().getCount_players()==4) {
                        //人满
                        cardBoardActivity.getDataSender().sendData(ClientDataGenerator.startNewGame()); //发送消息开始游戏
                        readyBtn.setVisibility(View.GONE);
                        leaveBtn.setVisibility(View.GONE);
                        MyReadyText.setVisibility(View.VISIBLE);
                        MyAnimation.zoomInAndOut(MyReadyText);
                    }else {
                        Toast.makeText(CardBoardActivity.getCardBoardActivity(), "房间人还没满", Toast.LENGTH_SHORT).show();
                    }

                }



//                    cardBoardActivity.getDataSender().sendData(ClientDataGenerator.addRobotRoRoom());
//                    cardBoardActivity.getDataSender().sendData(ClientDataGenerator.addRobotRoRoom());
//                    cardBoardActivity.getDataSender().sendData(ClientDataGenerator.addRobotRoRoom());


            }
        });

        leaveBtn=cardBoardActivity.findViewById(R.id.ibt_leave);

        //出牌按钮
        play_btn=cardBoardActivity.findViewById(R.id.ibt_play);
        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedCardGroup selectedCardGroup=myHandCardArea.getSelectedCardGroup();
                GameRule gameRule = new GameRuleImpl();
                if(UIConImpl.getGameRoomData().getPassCount()==3||gameRule.validate(UIConImpl.getGameRoomData(),selectedCardGroup)){
                    myShowingCardArea_my.updateShowingCard(myHandCardArea.getSelectedCards());
                    UIConImpl.getGameRoomData().clearPassCount();
                    myHandCardArea.removeShownCards();
                    cardBoardActivity.getDataSender().sendData(ClientDataGenerator
                            .responseWithShowing(selectedCardGroup, Config.USER_ID));
                     Log.d(Config.TAG,"客户端：出牌");
                    pass_btn.setVisibility(View.GONE);
                    play_btn.setVisibility(View.GONE);
                    cardBoardActivity.changeStateTo(new WaitingState());

                    play_btn.setVisibility(View.GONE);
                    pass_btn.setVisibility(View.GONE);
                }
            }
        });


        //跳过按钮
        pass_btn=cardBoardActivity.findViewById(R.id.ibt_pass);
        pass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                My_pass.setVisibility(View.VISIBLE);
                MyAnimation.zoomInAndOut(My_pass);
                play_btn.setVisibility(View.GONE);
                pass_btn.setVisibility(View.GONE);
                cardBoardActivity.getDataSender().sendData(ClientDataGenerator
                        .passAction());
                Log.d(Config.TAG,"客户端：跳过");
            }
        });

        Button addRobot=cardBoardActivity.findViewById(R.id.addRobot);
        addRobot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!CardBoardActivity.getCardBoardActivity().isPlayingSingle()&&CardBoardActivity.getCardBoardActivity().getUiCon().getCount_players()==4){
                    Toast.makeText(CardBoardActivity.getCardBoardActivity(), "房间满了，不能再加机器人", Toast.LENGTH_SHORT).show();
                }else {
                    cardBoardActivity.getDataSender().sendData(ClientDataGenerator.addRobotRoRoom());
                }
            }
        });
    }

    public void updateMyHandCardArea(CardGroup c){
        myHandCardArea.updateHandCardArea(c);
    }
    public void initImageView(){
        MyReadyText=cardBoardActivity.findViewById(R.id.ready_text_my);
    }


    public MyHandCardArea getMyHandCardArea() {
        return myHandCardArea;
    }

    public ImageButton getReadyBtn() {
        return readyBtn;
    }

    public ImageButton getLeaveBtn() {
        return leaveBtn;
    }

    public ImageView getMyReadyText() {
        return MyReadyText;
    }

    public CardBoardActivity getCardBoardActivity() {
        return cardBoardActivity;
    }

    public ImageButton getPlay_btn() {
        return play_btn;
    }

    public ImageButton getPass_btn() {
        return pass_btn;
    }

    public MyShowingCardArea getMyShowingCardArea_my() {
        return myShowingCardArea_my;
    }

    public MyShowingCardArea getMyShowingCardArea_right() {
        return myShowingCardArea_right;
    }

    public MyShowingCardArea getMyShowingCardArea_top() {
        return myShowingCardArea_top;
    }

    public MyShowingCardArea getMyShowingCardArea_left() {
        return myShowingCardArea_left;
    }

    public ImageView getRight_pass() {
        return Right_pass;
    }

    public ImageView getTop_pass() {
        return Top_pass;
    }

    public ImageView getLeft_pass() {
        return Left_pass;
    }

    public ImageView getMy_pass() {
        return My_pass;
    }
}
