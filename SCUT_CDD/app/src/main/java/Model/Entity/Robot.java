package Model.Entity;

import android.os.Debug;

import com.development.scut_cdd.ServerLayer.GameRoomData;

import java.util.Vector;

import Model.ServiceImpl.GameTurnServiceImpl;
import Model.ServiceImpl.MinimumAbleStrategy.MinimumAbleStrategy;

public class Robot extends Player{


    public Robot(String USER_ID, String type) {
        super(USER_ID, type);
    }

   public boolean robotTurn(SelectedCardGroup upperscg, GameRoomData gameRoomData){
        MinimumAbleStrategy minimumAbleStrategy=new MinimumAbleStrategy();
        minimumAbleStrategy.init(getOwnCardGroup(),upperscg);
        minimumAbleStrategy.getNext();
        if(minimumAbleStrategy.isGet()){
            SelectedCardGroup selectedCardGroup =new SelectedCardGroup();
            selectedCardGroup.setSelectedCardGroup(minimumAbleStrategy.getCurrentInferenceCardGroup());
            this.setShownCards(selectedCardGroup);
            return true;
        }else{
            //pass
            return false;
        }

   }
   public void showDiamondThree(){
        for(Card c:getOwnCardGroup().getCards()){
            if(c.getValue()==Value.THREE&&c.getSuit()==Suit.DIAMONDS){
                SelectedCardGroup temp=new SelectedCardGroup();
                Vector<Card> v=new Vector<>();
                v.add(c);
                temp.setSelectedCardGroup(v);
                setSelCards(temp);
                setShownCards(temp);
                break;
            }
        }
       GameTurnServiceImpl gameTurnService =new GameTurnServiceImpl();
       gameTurnService.minusCards(this);
   }






}
