package Model.DaoImpl;

import java.util.Vector;

import Database.LocalDB;
import Model.Dao.GameTurnDao;
import Model.Entity.Player;
import Model.Entity.SelectedCardGroup;

public class GameTurnDaoImpl implements GameTurnDao {

    @Override
    public Player getCurrentShowingPlayer() {
        return LocalDB.getPlayerVector().get(LocalDB.getCurrentPlayer());
    }

    @Override
    public void updateCurrentPlayer() {
        int newCurrentPlayer=(LocalDB.getCurrentPlayer()+1)%4;
        LocalDB.setCurrentPlayer(newCurrentPlayer);
    }
    @Override
    public Vector<Player> getPlayerVec() {
        return LocalDB.getPlayerVector();
    }

    @Override
    public void setCurrentPlayer(int currentTurn) {
        LocalDB.setCurrentPlayer(currentTurn);
    }

    @Override
    public SelectedCardGroup getUpperShownCardGroup() {
        return LocalDB.getUpperShownCardGroup();
    }

    @Override
    public void setUpperShownCardGroup(SelectedCardGroup upperShownCardGroup) {
      LocalDB.setUpperShownCardGroup(upperShownCardGroup);
    }

    @Override
    public int getPassCount() {
        return LocalDB.getPassCount();
    }

    @Override
    public void setPassCount(int i) {
        LocalDB.setPassCount(i);
    }

}
