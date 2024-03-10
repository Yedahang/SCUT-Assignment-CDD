package Model.DaoImpl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Vector;

import Database.DatabaseContract;
import Database.DatabaseHelper;
import Model.Dao.GameRoomDao;
import Model.Entity.Player;
import Model.Entity.PlayerModel;
import Model.Entity.RoomModel;

public class GameRoomDaoImpl implements GameRoomDao {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase dbR;
    private SQLiteDatabase dbW;
    public GameRoomDaoImpl(Context context) {
        dbHelper = new DatabaseHelper(context);
        dbR=dbHelper.getReadableDatabase();
        dbW=dbHelper.getWritableDatabase();
    }

    public long insert(RoomModel roomModel){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GameRoomEntry.COLUMN_ROOM_ID,roomModel.getROOM_ID());//房间ID
        values.put(DatabaseContract.GameRoomEntry.COLUMN_Creator_id,roomModel.getCreator_id());//创建者id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_PLAYER_NUM,roomModel.getPLAYER_NUM());

        values.put(DatabaseContract.GameRoomEntry.COLUMN_FIRST_SEAT_PLAYER_ID,roomModel.getFIRST_SEAT_PLAYER_ID());//第一个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_FIRST_SEAT_TYPE,roomModel.getFIRST_SEAT_TYPE());//第一个座位玩家的类型

        values.put(DatabaseContract.GameRoomEntry.COLUMN_SECOND_SEAT_PLAYER_ID,roomModel.getSECOND_SEAT_PLAYER_ID());//第2个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_SECOND_SEAT_TYPE,roomModel.getSECOND_SEAT_TYPE());//第2个座位玩家的类型

        values.put(DatabaseContract.GameRoomEntry.COLUMN_THIRD_SEAT_PLAYER_ID,roomModel.getTHIRD_SEAT_PLAYER_ID());//第3个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_THIRD_SEAT_TYPE,roomModel.getTHIRD_SEAT_TYPE());//第3个座位玩家的类型

        values.put(DatabaseContract.GameRoomEntry.COLUMN_FORTH_SEAT_PLAYER_ID,roomModel.getFORTH_SEAT_PLAYER_ID());//第4个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_FORTH_SEAT_TYPE,roomModel.getFORTH_SEAT_TYPE());//第4个座位玩家的类型
//
//        values.put(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_PLAYER,roomModel.getPREVIOUS_PLAYER());
//        values.put(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_PLAYER_OPERATION,roomModel.getPREVIOUS_PLAYER_OPERATION());
//        values.put(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER_INDEX,roomModel.getCURRENT_PLAYER());
//        values.put(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_SHOWN_CARD,roomModel.getCURRENT_PLAYER());
//        values.put(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER_OPERATION,roomModel.getCURRENT_PLAYER_OPERATION());

        return dbW.insert(DatabaseContract.GameRoomEntry.TABLE_NAME,null,values);
    }

    public int delete(int room_id){
        String selection = DatabaseContract.GameRoomEntry.COLUMN_ROOM_ID+" = ?";
        String[] selectionArgs={String.valueOf(room_id)};
        return dbR.delete(DatabaseContract.GameRoomEntry.TABLE_NAME,selection,selectionArgs);
    }

    public RoomModel getRoom(int room_id){
        String query ="SELECT * FROM "+ DatabaseContract.GameRoomEntry.TABLE_NAME+" WHERE "+
                DatabaseContract.GameRoomEntry.COLUMN_ROOM_ID +" = ?";
        String[] selectionArgs = {String.valueOf(room_id)};
        Cursor cursor = dbR.rawQuery(query,selectionArgs);
        if(cursor.moveToFirst()){
            @SuppressLint("Range") int PLAYER_NUM = cursor.getInt(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_PLAYER_NUM));
            @SuppressLint("Range") String creator_id = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_Creator_id));
            @SuppressLint("Range") String FIRST_SEAT_PLAYER_ID = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_FIRST_SEAT_PLAYER_ID));
            @SuppressLint("Range") String SECOND_SEAT_PLAYER_ID = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_SECOND_SEAT_PLAYER_ID));
            @SuppressLint("Range") String THIRD_SEAT_PLAYER_ID = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_THIRD_SEAT_PLAYER_ID));
            @SuppressLint("Range") String FORTH_SEAT_PLAYER_ID = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_FORTH_SEAT_PLAYER_ID));
            @SuppressLint("Range") String FIRST_SEAT_TYPE = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_FIRST_SEAT_TYPE));
            @SuppressLint("Range") String SECOND_SEAT_TYPE = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_SECOND_SEAT_TYPE));
            @SuppressLint("Range") String THIRD_SEAT_TYPE = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_THIRD_SEAT_TYPE));
            @SuppressLint("Range") String FORTH_SEAT_TYPE = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_FORTH_SEAT_TYPE));
            @SuppressLint("Range") String IS_FIRST_ROUND = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_IS_FIRST_ROUND));
            @SuppressLint("Range") int CURRENT_PLAYER_INDEX = cursor.getInt(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER_INDEX));
            @SuppressLint("Range") String PREVIOUS_PLAYER = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_PLAYER));
            @SuppressLint("Range") String PREVIOUS_SHOWN_CARD = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_SHOWN_CARD));
            @SuppressLint("Range") String PREVIOUS_PLAYER_OPERATION = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_PLAYER_OPERATION));
            @SuppressLint("Range") String CURRENT_SHOWN_CARD = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_SHOWN_CARD));
            @SuppressLint("Range") String CURRENT_PLAYER_OPERATION = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER_OPERATION));
            @SuppressLint("Range") String CURRENT_PLAYER = cursor.getString(cursor.getColumnIndex(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER));
            cursor.close();
//            return null;
            return new RoomModel(room_id,PLAYER_NUM,CURRENT_PLAYER_INDEX,IS_FIRST_ROUND,creator_id,FIRST_SEAT_PLAYER_ID,SECOND_SEAT_PLAYER_ID,THIRD_SEAT_PLAYER_ID,
                    FORTH_SEAT_PLAYER_ID,FIRST_SEAT_TYPE,SECOND_SEAT_TYPE,THIRD_SEAT_TYPE,FORTH_SEAT_TYPE,PREVIOUS_PLAYER,PREVIOUS_SHOWN_CARD,
                    PREVIOUS_PLAYER_OPERATION,CURRENT_PLAYER,CURRENT_SHOWN_CARD,CURRENT_PLAYER_OPERATION);
        }else{
            cursor.close();
            return null;
        }
    }

    @Override
    public int getRoomPlayerNum() {
        return 0;
    }

    @Override
    public int getRoomCount() {
        Cursor cursor= dbR.query(DatabaseContract.GameRoomEntry.TABLE_NAME,
                null,null,null,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int update(RoomModel roomModel){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.GameRoomEntry.COLUMN_ROOM_ID,roomModel.getROOM_ID());//房间ID
        values.put(DatabaseContract.GameRoomEntry.COLUMN_Creator_id,roomModel.getCreator_id());//创建者id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_PLAYER_NUM,roomModel.getPLAYER_NUM());//玩家数目
        values.put(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER_INDEX,roomModel.getCURRENT_PLAYER_INDEX());//当前轮到的玩家
        values.put(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_SHOWN_CARD,roomModel.getPREVIOUS_SHOWN_CARD());//上家出的牌

        values.put(DatabaseContract.GameRoomEntry.COLUMN_IS_FIRST_ROUND,roomModel.getIS_FIRST_ROUND());//第一回合标记

        values.put(DatabaseContract.GameRoomEntry.COLUMN_FIRST_SEAT_PLAYER_ID,roomModel.getFIRST_SEAT_PLAYER_ID());//第一个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_FIRST_SEAT_TYPE,roomModel.getFIRST_SEAT_TYPE());//第一个座位玩家的类型

        values.put(DatabaseContract.GameRoomEntry.COLUMN_SECOND_SEAT_PLAYER_ID,roomModel.getSECOND_SEAT_PLAYER_ID());//第2个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_SECOND_SEAT_TYPE,roomModel.getSECOND_SEAT_TYPE());//第2个座位玩家的类型

        values.put(DatabaseContract.GameRoomEntry.COLUMN_THIRD_SEAT_PLAYER_ID,roomModel.getTHIRD_SEAT_PLAYER_ID());//第3个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_THIRD_SEAT_TYPE,roomModel.getTHIRD_SEAT_TYPE());//第3个座位玩家的类型

        values.put(DatabaseContract.GameRoomEntry.COLUMN_FORTH_SEAT_PLAYER_ID,roomModel.getFORTH_SEAT_PLAYER_ID());//第4个坐位玩家的id
        values.put(DatabaseContract.GameRoomEntry.COLUMN_FORTH_SEAT_TYPE,roomModel.getFORTH_SEAT_TYPE());//第4个座位玩家的类型

        values.put(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_PLAYER,roomModel.getPREVIOUS_PLAYER());
        values.put(DatabaseContract.GameRoomEntry.COLUMN_PREVIOUS_PLAYER_OPERATION,roomModel.getPREVIOUS_PLAYER_OPERATION());
        values.put(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER,roomModel.getCURRENT_PLAYER());
        values.put(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_SHOWN_CARD,roomModel.getCURRENT_SHOWN_CARD());
        values.put(DatabaseContract.GameRoomEntry.COLUMN_CURRENT_PLAYER_OPERATION,roomModel.getCURRENT_PLAYER_OPERATION());


        String whereClause= DatabaseContract.GameRoomEntry.COLUMN_ROOM_ID+" = ?";
        String[] whereArgs = {String.valueOf(roomModel.getROOM_ID())};
        return dbW.update(DatabaseContract.GameRoomEntry.TABLE_NAME,
                values,
                whereClause,
                whereArgs
                );

    }

    @Override
    public Vector<Player> getAllPlayerInRoom(int room_id){
        RoomModel roomModel=getRoom(room_id);
        Vector<Player> players = new Vector<>();
        PlayerModel playerModel1 = new PlayerModel(roomModel.getFIRST_SEAT_PLAYER_ID(),room_id);
        PlayerModel playerModel2 = new PlayerModel(roomModel.getSECOND_SEAT_PLAYER_ID(),room_id);
        PlayerModel playerModel3 = new PlayerModel(roomModel.getTHIRD_SEAT_PLAYER_ID(),room_id);
        PlayerModel playerModel4 = new PlayerModel(roomModel.getFORTH_SEAT_PLAYER_ID(),room_id);
        players.add(playerModel1.toPlayer());
        players.add(playerModel2.toPlayer());
        players.add(playerModel3.toPlayer());
        players.add(playerModel4.toPlayer());
        return players;
    }

    public void updateCurrentPlayer(int room_id,int player_index){
        RoomModel roomModel = getRoom(room_id);
        roomModel.setCURRENT_PLAYER_INDEX(player_index);
        update(roomModel);
    }
    public void updateIsFirstRound(int room_id,boolean trueOrFalse){
        RoomModel roomModel = getRoom(room_id);
        if(trueOrFalse) {
            roomModel.setIS_FIRST_ROUND(RoomModel.IsFirstRound);
        }else{
            roomModel.setIS_FIRST_ROUND(RoomModel.NotFirstRound);
        }
        update(roomModel);
    }

    @Override
    public String getCurrentPlayer(int room_id) {
        RoomModel roomModel=getRoom(room_id);
        int index=roomModel.getCURRENT_PLAYER_INDEX();
        String currentPlayerId=null;
        switch (index){
            case 0:
                currentPlayerId=roomModel.getFIRST_SEAT_PLAYER_ID();
                break;
            case 1:
                currentPlayerId=roomModel.getSECOND_SEAT_PLAYER_ID();
                break;
            case 2:
                currentPlayerId=roomModel.getTHIRD_SEAT_PLAYER_ID();
                break;
            case 3:
                currentPlayerId=roomModel.getFORTH_SEAT_PLAYER_ID();
                break;
        }
        return currentPlayerId;
    }

    @Override
    public void test() {

    }

}
