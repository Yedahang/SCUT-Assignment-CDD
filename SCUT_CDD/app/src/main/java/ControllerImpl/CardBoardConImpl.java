package ControllerImpl;

import android.content.Context;

import Controller.CardBoardCon;
import Model.Service.CardboardService;
import Model.ServiceImpl.CardboardServiceImpl;

/** <p>牌桌控制器</p></p>*/
public class CardBoardConImpl implements CardBoardCon {
    public CardBoardConImpl(Context context) {
        cardboardService = new CardboardServiceImpl(context);
    }
    public CardBoardConImpl() {
      cardboardService = new CardboardServiceImpl();
    }
    private CardboardService cardboardService;
    public void operationEvent(int opcode){
        switch (opcode){
            case 0://初始化房间
//                cardboardService.initRoom();//此操作后发完牌
                break;
            case 1://开始对局
//                startGame();

                break;

        }
    }

    public void startGame() {
        System.out.println("CardBoardConImpl开始对局");
        cardboardService.startGameInit();//初始化玩家数据对局 进入GameTurn
    }





    public void disAllPlayerHandCards(){
        cardboardService.displayAllPlayerCard();
    }


}
