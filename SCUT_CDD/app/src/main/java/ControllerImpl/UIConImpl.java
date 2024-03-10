package ControllerImpl;

import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.development.scut_cdd.CardBoardActivity;
import com.development.scut_cdd.Clientlayer.ClientDataGenerator;
import com.development.scut_cdd.Config;
import com.development.scut_cdd.F_class.f_HeadView;
import com.development.scut_cdd.R;
import com.development.scut_cdd.ServerLayer.GameRoomData;
import com.development.scut_cdd.UserState.ShowingState;
import com.development.scut_cdd.util.MyAnimation;

import java.util.Vector;

import Database.LocalDB;
import Model.Entity.Player;
import Model.Entity.RoomModel;
import Model.Entity.ServerAction;
import Model.Entity.UserAction;

/** <p>监控控制UI界面的更新的 线程</p>*/
public class UIConImpl  {
    int span=20;//睡眠的毫秒数
    public static UIConImpl uiCon;
    CardBoardActivity cardBoardActivity;//activity的引用
    UIHelper uiHelper;
    Vector<String> order=null;
    int count_players=0;
    public static GameRoomData gameRoomData=null;
    public static final String MESSAGE_LEFT_PASS="LeftPass";
    public static final String MESSAGE_TOP_PASS="TopPass";
    public static final String MESSAGE_RIGHT_PASS="RightPass";
    private boolean flag=true;//循环标记
    boolean justIntoRoom=false;
    public UIConImpl(CardBoardActivity cardBoardActivity) {
        this.cardBoardActivity = cardBoardActivity;
        uiHelper=new UIHelper(cardBoardActivity);
    }

    public void initAllUI(){
        uiHelper.initMyHandCardArea();
        uiHelper.initButton();
        uiHelper.initImageView();
        uiHelper.initShowingArea();
        uiHelper.initPassText();
    }

    public void handleData(GameRoomData gameRoomData){
        this.gameRoomData = gameRoomData;
        cardBoardActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (gameRoomData.getServerAction().getACTION_TYPE()){
                    case  ServerAction.ACTION_TYPE_Init_GAME://初始化对局
                        initPlayerPosition(gameRoomData);
                        for(Player p:gameRoomData.getPlayers()){
                            if(p.getUSER_ID().equals(Config.USER_ID)){
                                uiHelper.updateMyHandCardArea(p.getOwnCardGroup());
                                break;
                            }
                        }

//                        if(gameRoomData.getCurrentPlayerId().equals(LocalDB.user_id)){
//                            cardBoardActivity.changeStateTo(new ShowingState(cardBoardActivity));
//                        }else{
                        uiHelper.getMyReadyText().setVisibility(View.GONE);
                        cardBoardActivity.getDataSender().sendData(ClientDataGenerator.responseWithNoAction());
                        Log.d(Config.SOCKET_TAG,"客户端线程 处理ACTION_TYPE_Init_GAME");
//                        }

                        break;
                    case  ServerAction.ACTION_In_Game:
//                        Log.d(Config.TAG,"客户端线程 _____+____");
                        Log.d(Config.TAG,gameRoomData.toJson());
                        if(gameRoomData.getRoundCount()!=0) {
                            //不是是第一个出牌时候
//                            Debug.waitForDebugger();
                            setShowingCards(gameRoomData);
                        }
//                        Log.d(Config.TAG,"客户端线程 _____=____");
                        if(gameRoomData.getCurrentPlayerId().equals(Config.USER_ID)){
                            uiHelper.getMyShowingCardArea_my().clearMyShowingCardArea();
                            uiHelper.getMy_pass().setVisibility(View.GONE);
                            //在自己回合
                            cardBoardActivity.changeStateTo(new ShowingState(cardBoardActivity));
//                            Log.d(Config.TAG,"客户端线程 ============");
                        }else{
                            cardBoardActivity.getDataSender().sendData(ClientDataGenerator.responseWithNoAction());
                        }
                        Log.d(Config.SOCKET_TAG,"客户端线程 处理ACTION_In_Game");
                        break;
                    case ServerAction.ACTION_In_Single:
                        count_players=4;
                        //发送消息开始游戏
                        cardBoardActivity.getDataSender().sendData(ClientDataGenerator.startNewGame());
                        break;
                    case ServerAction.ACTION_NEW_JION:
//                        initPlayerPosition(gameRoomData);//更新顺序
                        if(!justIntoRoom) {
                            count_players++;
                            //新的玩家加入
//                            initPlayerPosition
                            int mypos=-1;
                            for(int i=0;i<gameRoomData.getPlayers().size();i++){
                                if(gameRoomData.getPlayers().get(i).getUSER_ID().equals(Config.USER_ID)){
                                    mypos=i;break;
                                }
                            }
                            int position = gameRoomData.getPlayers().size()-mypos-1;//User前面一位
                            switch (position) {
                                case 0:
                                    f_HeadView my = CardBoardActivity.getCardBoardActivity().findViewById(R.id.myHeadView);
                                    my.setVisibility(View.VISIBLE);
                                    my.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-1).getUSER_ID());
                                    break;
                                case 1:
                                    f_HeadView right = CardBoardActivity.getCardBoardActivity().findViewById(R.id.rightHeadView);
                                    right.setVisibility(View.VISIBLE);
                                    right.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-1).getUSER_ID());
                                    break;
                                case 2:
                                    f_HeadView top = CardBoardActivity.getCardBoardActivity().findViewById(R.id.topHeadView);
                                    top.setVisibility(View.VISIBLE);
                                    top.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-1).getUSER_ID());
                                    break;
                                case 3:
                                    f_HeadView left = CardBoardActivity.getCardBoardActivity().findViewById(R.id.leftHeadView);
                                    left.setVisibility(View.VISIBLE);
                                    left.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-1).getUSER_ID());
                                    break;
                            }
                        }else{
                             setJustIntoRoom(false);
                             int count =gameRoomData.getPlayers().size();
                            count_players=count;
                             if(count>0){
                                 f_HeadView my = CardBoardActivity.getCardBoardActivity().findViewById(R.id.myHeadView);
                                 my.setVisibility(View.VISIBLE);
                                 my.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-1).getUSER_ID());
                                 count--;
                             }
                            if(count>0){
                                f_HeadView left = CardBoardActivity.getCardBoardActivity().findViewById(R.id.leftHeadView);
                                left.setVisibility(View.VISIBLE);
                                left.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-2).getUSER_ID());
                                count--;
                            }
                            if(count>0){
                                f_HeadView top = CardBoardActivity.getCardBoardActivity().findViewById(R.id.topHeadView);
                                top.setVisibility(View.VISIBLE);
                                top.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-3).getUSER_ID());
                                count--;
                            }
                            if(count>0){

                                f_HeadView right = CardBoardActivity.getCardBoardActivity().findViewById(R.id.rightHeadView);
                                right.setVisibility(View.VISIBLE);
                                right.setPlayerName(gameRoomData.getPlayers().get(gameRoomData.getPlayers().size()-4).getUSER_ID());
                                count--;
                            }
                        }

                        break;
                }
            }
        });

    }
//    @Override
//    public void run() {
//        while (flag){
//            if(gameRoomData!=null) {
//                switch (gameRoomData.getServerAction().getACTION_TYPE()) {
//                    case ServerAction.ACTION_TYPE_Init_GAME:
//                         //初始化手牌
//                        uiHelper.updateMyHandCardArea(gameRoomData.getPlayers().get(0).getOwnCardGroup());
//                    case MESSAGE_LEFT_PASS:
//                        ImageView bu1 = cardBoardActivity.findViewById(R.id.pass_text_left);
//                        pass_text_Display(bu1);
//
//                        break;
//                    case MESSAGE_TOP_PASS:
//                        ImageView bu2 = cardBoardActivity.findViewById(R.id.pass_text_top);
//                        pass_text_Display(bu2);
//                        break;
//                    case MESSAGE_RIGHT_PASS:
//                        ImageView bu3 = cardBoardActivity.findViewById(R.id.pass_text_right);
//                        pass_text_Display(bu3);
//                        break;
//                }
//            }
//            }
//
//        }




    private RoomModel getMessage() {
        RoomModel message = null;
        try {
            message = cardBoardActivity.getMessageQueue().take();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return message;
    }

    private void pass_text_Display(ImageView imageView){
        cardBoardActivity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        imageView.setVisibility(View.VISIBLE);
                        MyAnimation.zoomInAndOut(imageView);
                    }
                });
    }
    public void initPlayerPosition(GameRoomData gameRoomData){
        order=new Vector<>();
        int p=0;
        for(;p<4;p++){
            if(gameRoomData.getPlayers().get(p)==null){
                break;
            }
            if(gameRoomData.getPlayers().get(p).getUSER_ID().equals(Config.USER_ID)){
                order.add(Config.USER_ID);
                break;
            }
        }
//        for(int i=0;i<gameRoomData.getPlayers().size()-1;i++){
//            p = (p +1) % order.size();
//            order.add(gameRoomData.getPlayers().get(p).getUSER_ID());
//        }
        p = (p +1) % 4;
        order.add(gameRoomData.getPlayers().get(p).getUSER_ID());
        p = (p + 1) % 4;
        order.add(gameRoomData.getPlayers().get(p).getUSER_ID());
        p = (p + 1) % 4;
        order.add(gameRoomData.getPlayers().get(p).getUSER_ID());
    }
    public void setShowingCards(GameRoomData gameRoomData){
        Player previousPlayer=gameRoomData.getPreviousPlayer();
        int i=0;
        for(;i<4;i++){
            if(order.get(i).equals(gameRoomData.getNewAction().getUSER_ID())){
                break;
            };
        }
        switch (i){
            case 1:
                if(gameRoomData.getNewAction().getACTION_TYPE().equals(UserAction.ACTION_TYPE_PASS)) {
                    uiHelper.getMyShowingCardArea_right().clearMyShowingCardArea();
                    uiHelper.getRight_pass().setVisibility(View.VISIBLE);
                }else{
                    uiHelper.getMyShowingCardArea_right()
                            .updateShowingCard(previousPlayer.getShownCards());
                }
                break;
            case 2:
                if(gameRoomData.getNewAction().getACTION_TYPE().equals(UserAction.ACTION_TYPE_PASS)) {
                    uiHelper.getMyShowingCardArea_top().clearMyShowingCardArea();
                    uiHelper.getTop_pass().setVisibility(View.VISIBLE);
                }else{
                    uiHelper.getMyShowingCardArea_top()
                            .updateShowingCard(previousPlayer.getShownCards());
                }

                break;
            case 3:
                if(gameRoomData.getNewAction().getACTION_TYPE().equals(UserAction.ACTION_TYPE_PASS)) {
                    uiHelper.getMyShowingCardArea_left().clearMyShowingCardArea();
                    uiHelper.getLeft_pass().setVisibility(View.VISIBLE);
                }else{
                    uiHelper.getMyShowingCardArea_left()
                            .updateShowingCard(previousPlayer.getShownCards());
                }

                break;
        }
    }
    public void setGameRoomData(GameRoomData gameRoomData) {
        this.gameRoomData = gameRoomData;
    }

    public UIHelper getUiHelper() {
        return uiHelper;
    }

    public void setReadyStateUI(){
        uiHelper.getReadyBtn().setVisibility(View.VISIBLE);
        uiHelper.getLeaveBtn().setVisibility(View.VISIBLE);
    }
    public void setShowingStateUI(){
        uiHelper.getPass_btn().setVisibility(View.VISIBLE);
        uiHelper.getPlay_btn().setVisibility(View.VISIBLE);
    }

    public static GameRoomData getGameRoomData() {
        return gameRoomData;
    }

    public void setJustIntoRoom(boolean justIntoRoom) {
        this.justIntoRoom = justIntoRoom;
    }

    public int getCount_players() {
        return count_players;
    }
}
