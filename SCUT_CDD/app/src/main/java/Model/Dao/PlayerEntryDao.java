package Model.Dao;

import Model.Entity.PlayerModel;

public interface PlayerEntryDao {
    long insert(PlayerModel _playerModel);

    int getRoomId(String user_id);
    PlayerModel query(String user_id);

    int update(PlayerModel _playerModel);


}
