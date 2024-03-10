package Model.ServiceImpl;

import android.content.Context;

import com.development.scut_cdd.Config;

import java.util.Vector;

import Database.LocalDB;
import Model.Dao.GameRoomDao;
import Model.Dao.PlayerEntryDao;
import Model.DaoImpl.GameRoomDaoImpl;
import Model.DaoImpl.PlayerEntryDaoImpl;
import Model.Entity.Player;
import Model.Entity.PlayerModel;
import Model.Entity.RoomModel;
import Model.Service.CardboardService;
import Model.Service.GameTurnService;

public class RoomServiceImpl {
    GameRoomDao gameRoomDao;
    PlayerEntryDao playerEntryDao;

    GameTurnService gameTurnService=new GameTurnServiceImpl();

    CardboardService cardboardService;
    public RoomServiceImpl(Context context) {
        gameRoomDao = new GameRoomDaoImpl(context);
        playerEntryDao = new PlayerEntryDaoImpl(context);
        cardboardService= new CardboardServiceImpl(context);
    }

    /**
     * 描述: 创建房间
     * @author 叶达杭
     * @return int 新创建的房间id
    */
    public int createARoom(String creator_id){
       int newRoomId= gameRoomDao.getRoomCount()+1;
        RoomModel roomModel =new RoomModel();
        roomModel.setROOM_ID(newRoomId);
        roomModel.setCreator_id(creator_id);
        roomModel.setPLAYER_NUM(1);
        roomModel.setFIRST_SEAT_PLAYER_ID(creator_id);
        roomModel.setFIRST_SEAT_TYPE(RoomModel.type_person);
        long temp=gameRoomDao.insert(roomModel);
        return newRoomId;
    }

    public void addActorToRoom(String user_id,int room_id,String type){
         RoomModel roomModel=gameRoomDao.getRoom(room_id);
         switch (roomModel.getPLAYER_NUM()){
             case 1:
                 roomModel.setSECOND_SEAT_PLAYER_ID(user_id);
                 roomModel.setSECOND_SEAT_TYPE(type);
                 break;
             case 2:
                 roomModel.setTHIRD_SEAT_PLAYER_ID(user_id);
                 roomModel.setTHIRD_SEAT_TYPE(type);
                 break;
             case 3:
                 roomModel.setFORTH_SEAT_PLAYER_ID(user_id);
                 roomModel.setFORTH_SEAT_TYPE(type);
                 break;
         }
         roomModel.setPLAYER_NUM(roomModel.getPLAYER_NUM()+1);
         gameRoomDao.update(roomModel);
    }


    public void startGame(int room_id){
        RoomModel roomModel=gameRoomDao.getRoom(room_id);
        Vector<String> order = roomModel.getOrderStrings();

        PlayerModel playerModel1 = new PlayerModel(roomModel.getFIRST_SEAT_PLAYER_ID(),room_id);
        playerModel1.setBottomPlayers(order);
        playerEntryDao.insert(playerModel1);

        PlayerModel playerModel2 = new PlayerModel(roomModel.getSECOND_SEAT_PLAYER_ID(),room_id);
        playerModel2.setBottomPlayers(order);
        playerEntryDao.insert(playerModel2);

        PlayerModel playerModel3 = new PlayerModel(roomModel.getTHIRD_SEAT_PLAYER_ID(),room_id);
        playerModel3.setBottomPlayers(order);
        playerEntryDao.insert(playerModel3);

        PlayerModel playerModel4 = new PlayerModel(roomModel.getFORTH_SEAT_PLAYER_ID(),room_id);
        playerModel4.setBottomPlayers(order);
        playerEntryDao.insert(playerModel4);

    }

    /**
     * 描述:玩家创建一个游戏房间
     * @author 叶达杭
     * @return void 添加说明
    */
    public int initRoom(){
        int id= createARoom(Config.USER_ID);
       addActorToRoom("Robot1",id,RoomModel.type_robot);
       addActorToRoom("Robot2",id,RoomModel.type_robot);
       addActorToRoom("Robot3",id,RoomModel.type_robot);
//       startGame(id);
        return id;
    }


}
