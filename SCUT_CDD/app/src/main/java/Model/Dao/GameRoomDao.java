package Model.Dao;

import java.util.Vector;

import Model.Entity.Player;
import Model.Entity.RoomModel;

public interface GameRoomDao {
    long insert(RoomModel roomModel);
    int delete(int room_id);

    int update(RoomModel roomModel);
    int getRoomCount();
    RoomModel getRoom(int room_id);
    int getRoomPlayerNum();
    void test();




    Vector<Player> getAllPlayerInRoom(int room_id);

    void updateCurrentPlayer(int room_id,int player_index);

    void updateIsFirstRound(int room_id,boolean trueOrFalse);

    String getCurrentPlayer(int room_id);
}
