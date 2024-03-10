package Controller;

import com.development.scut_cdd.View.MyShowingCard;

import java.util.List;

import Model.Entity.CardGroup;

public interface GameTurnCon {
    CardGroup getHandCard(String user_id);

    void playerShowingCards(List<MyShowingCard> list,String user_id);

}
