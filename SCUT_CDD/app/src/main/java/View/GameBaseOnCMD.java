package View;

import ControllerImpl.CardBoardConImpl;
import ControllerImpl.GameTurnConImpl;
import ControllerImpl.GameTurnConImpl;
import View.util.ConsoleInput;

public class GameBaseOnCMD {


    public static void main(String[] args) {
//        System.out.println("进入Cardboard： 1-开始游戏 2-退出Cardboard");
//        int operation=ConsoleInput.getInputNumber();
//        CardBoardConImpl cbcontroller = new CardBoardConImpl();
//        cbcontroller.operationEvent(operation);
//
//        GameTurnConImpl gameTurnCon= new GameTurnConImpl();
//        while(true) {
//            cbcontroller.disAllPlayerHandCards();
//            gameTurnCon.outputCurrentShowingPlayer();
//            System.out.println("输入 1-出牌 2-跳过");
//            int op =ConsoleInput.getInputNumber();
//            gameTurnCon.comeIntoRound(op);
//            switch (op){
//                case 1:
//                    gameTurnCon.showCards(ConsoleInput.getSelectedCardIndexInput());break;
//                case 2:
//                    continue;
//            }
//
//        }
        int i=(-1)%4;
        System.out.println(i);
    }
}
