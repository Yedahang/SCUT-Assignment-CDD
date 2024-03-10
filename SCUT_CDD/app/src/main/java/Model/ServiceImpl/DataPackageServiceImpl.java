package Model.ServiceImpl;

import android.content.Context;

import Model.Dao.GameRoomDao;
import Model.Dao.PlayerEntryDao;
import Model.DaoImpl.GameRoomDaoImpl;
import Model.DaoImpl.PlayerEntryDaoImpl;
import Model.Entity.DataPackage;
import Model.Entity.PlayerModel;
import Model.Entity.RoomModel;

public class DataPackageServiceImpl {
    GameRoomDao gameRoomDao ;
    PlayerEntryDao playerEntryDao;
    public DataPackageServiceImpl(Context context) {
    gameRoomDao=new GameRoomDaoImpl(context);
    playerEntryDao= new PlayerEntryDaoImpl(context);
    }

//    public DataPackage getDataPackage(String user_id){
//        PlayerModel playerModel = playerEntryDao.query(user_id);
//        return new DataPackage(gameRoomDao.getRoom(playerModel.getROOM_ID()),
//                playerModel,null);
//    }

}
