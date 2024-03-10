package Model.ServiceImpl.Entity_RobotServiceImpl;

import com.development.scut_cdd.ServerLayer.GameRoomData;

import java.util.Vector;

import Model.Entity.Card;
import Model.Entity.CardGroup;
import Model.Entity.Player;
import Model.Entity.SelectedCardGroup;
import Model.ServiceImpl.GameTurnServiceImpl;
import Model.ServiceImpl.MinimumAbleStrategy.MinimumAbleStrategy;

public class RobotImpl {
//    MinimumAbleStrategy minimumAbleStrategy = new MinimumAbleStrategy();
//    public void show(CardGroup currentCardGroup, SelectedCardGroup upper){
//        minimumAbleStrategy.init(currentCardGroup,upper);
//        minimumAbleStrategy.minimumAbleStrategyImplementor();
//        minimumAbleStrategy.getNext();
//
//        System.out.println(minimumAbleStrategy.toString());
//    }

      public static boolean robotTurn(Player robot, SelectedCardGroup upperscg, GameRoomData gameRoomData){
          MinimumAbleStrategy minimumAbleStrategy=new MinimumAbleStrategy();
          minimumAbleStrategy.init(robot.getOwnCardGroup(),upperscg);
          minimumAbleStrategy.getNext();
          if(minimumAbleStrategy.isGet()){
              SelectedCardGroup selectedCardGroup =new SelectedCardGroup();
              selectedCardGroup.setSelectedCardGroup(minimumAbleStrategy.getCurrentInferenceCardGroup());
              robot.setShownCards(selectedCardGroup);
              GameTurnServiceImpl gameTurnService =new GameTurnServiceImpl();
              gameTurnService.minusCards(robot);
              return true;
          }else{
              //pass
              return false;
          }
      }
      public static void robotShowMin(Player robot,GameRoomData gameRoomData){
          ;
          Vector<Card>t=new Vector<>();
          t.add(robot.getOwnCardGroup().getCards().get(0));
          SelectedCardGroup selectedCardGroup =new SelectedCardGroup();
          selectedCardGroup.setSelectedCardGroup(t);
          robot.setShownCards(selectedCardGroup);
          GameTurnServiceImpl gameTurnService =new GameTurnServiceImpl();
          gameTurnService.minusCards(robot);
      }
}
