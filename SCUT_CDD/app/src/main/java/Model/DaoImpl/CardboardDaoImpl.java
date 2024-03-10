package Model.DaoImpl;

import java.util.Vector;

import Database.LocalDB;
import Database.LocalDB;
import Model.Dao.CardboardDao;
import Model.Entity.Player;

public class CardboardDaoImpl implements CardboardDao {

    @Override
    public void clearPlayerVec() {
        LocalDB.getPlayerVector().clear();
    }

    @Override
    public boolean addPlayerToVec(Player player) {
        if (LocalDB.getPlayerVector().size()==4){
            return false;
        }else {
            LocalDB.getPlayerVector().add(player);
            return true;
        }
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
    public Player getCurrentShowingPlayer() {
        return LocalDB.getPlayerVector().get(LocalDB.getCurrentPlayer());
    }

}
