package Model.Service;

import com.development.scut_cdd.View.MyShowingCard;

import java.util.List;
import java.util.Vector;

import Model.Entity.Player;
import Model.Entity.SelectedCardGroup;

public interface GameTurnService {
     public String getCurrentShowingPlayerInString();

     public void distributeCard();

     public void decideShowingOrder();
     public boolean playerShowCards(Vector<Integer> index);

     public void turnToNextPlayer();

     public SelectedCardGroup getUpperShownCardGroup();

     public void upPassCount();
     public void clearPassCount();

     void distributeCard(Player player1, Player player2, Player player3, Player player4);




     void startTurn(int room_id);
     Player getPlayer(String user_id);
     void playerShowingCards(List<MyShowingCard> list, String user_id);
     String getCurrentPlayer(int room_id);

     void robotTurn(String Robot_id,int room_id);
     Vector<String> getPlayerPosition(String player_id);

}
