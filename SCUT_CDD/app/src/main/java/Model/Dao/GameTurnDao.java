package Model.Dao;

import java.util.Vector;

import Model.Entity.Player;
import Model.Entity.SelectedCardGroup;


/** <p>链接对局中所需要的数据</p>*/
public interface GameTurnDao {
    public Player getCurrentShowingPlayer();

    public Vector<Player> getPlayerVec();

    public void updateCurrentPlayer();
    public void setCurrentPlayer(int currentTurn);

    public SelectedCardGroup getUpperShownCardGroup() ;

    public void setUpperShownCardGroup(SelectedCardGroup upperShownCardGroup);

    int getPassCount();

    void setPassCount(int i);



}
