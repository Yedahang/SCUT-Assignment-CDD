package Model.Dao;

import java.util.Vector;

import Model.Entity.Player;

public interface CardboardDao {
    public void clearPlayerVec();
    public boolean addPlayerToVec(Player player);

    public Vector<Player> getPlayerVec();

    public void setCurrentPlayer(int currentTurn);


    public Player getCurrentShowingPlayer();
}
