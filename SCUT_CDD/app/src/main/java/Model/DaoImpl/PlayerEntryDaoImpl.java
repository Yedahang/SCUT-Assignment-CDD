package Model.DaoImpl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Database.DatabaseContract;
import Database.DatabaseHelper;
import Model.Dao.PlayerEntryDao;
import Model.Entity.PlayerModel;

public class PlayerEntryDaoImpl implements PlayerEntryDao {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase dbR;
    private SQLiteDatabase dbW;

    public PlayerEntryDaoImpl(Context context) {
        dbHelper = new DatabaseHelper(context);
        dbR=dbHelper.getReadableDatabase();
        dbW=dbHelper.getWritableDatabase();
    }


    /**
     * 描述: 增
     * @author 叶达杭
     * @param _playerModel 添加说明
     * @return void 添加说明
    */
    public long insert(PlayerModel _playerModel){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.PlayerEntry.COLUMN_USER_ID, _playerModel.getUSER_ID());//用户ID
        values.put(DatabaseContract.PlayerEntry.COLUMN_ROOM_ID, _playerModel.getROOM_ID());//房间ID
        values.put(DatabaseContract.PlayerEntry.COLUMN_HAND_CARD_NUM,_playerModel.getCARD_NUM());
        values.put(DatabaseContract.PlayerEntry.COLUMN_HAND_CARDS,_playerModel.getCARDS());
        values.put(DatabaseContract.PlayerEntry.COLUMN_ITS_FIRST_BOTTOM_PLAYER,_playerModel.getITS_FIRST_BOTTOM_PLAYER());
        values.put(DatabaseContract.PlayerEntry.COLUMN_ITS_SECOND_BOTTOM_PLAYER,_playerModel.getITS_SECOND_BOTTOM_PLAYER());
        values.put(DatabaseContract.PlayerEntry.COLUMN_ITS_THIRD_BOTTOM_PLAYER,_playerModel.getITS_THIRD_BOTTOM_PLAYER());
        return dbW.insert(DatabaseContract.PlayerEntry.TABLE_NAME,null,values);
    }

    @Override
    public int getRoomId(String user_id) {
        return 0;
    }

    /**
     * 描述:改
     * @author 叶达杭
     * @param _playerModel
     * @return int
    */
    public int update(PlayerModel _playerModel){
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.PlayerEntry.COLUMN_USER_ID,_playerModel.getUSER_ID());
        values.put(DatabaseContract.PlayerEntry.COLUMN_ROOM_ID,_playerModel.getROOM_ID());
        values.put(DatabaseContract.PlayerEntry.COLUMN_HAND_CARD_NUM,_playerModel.getCARD_NUM());
        values.put(DatabaseContract.PlayerEntry.COLUMN_HAND_CARDS,_playerModel.getCARDS());
        values.put(DatabaseContract.PlayerEntry.COLUMN_ITS_FIRST_BOTTOM_PLAYER,_playerModel.getITS_FIRST_BOTTOM_PLAYER());
        values.put(DatabaseContract.PlayerEntry.COLUMN_ITS_SECOND_BOTTOM_PLAYER,_playerModel.getITS_SECOND_BOTTOM_PLAYER());
        values.put(DatabaseContract.PlayerEntry.COLUMN_ITS_THIRD_BOTTOM_PLAYER,_playerModel.getITS_THIRD_BOTTOM_PLAYER());
        //用用户ID作为index
        String whereClause = DatabaseContract.PlayerEntry.COLUMN_USER_ID +" = ?";

        String[] whereArgs = {_playerModel.getUSER_ID()};

        return dbW.update(DatabaseContract.PlayerEntry.TABLE_NAME,values,whereClause,whereArgs);
    }

    /**
     * 描述: 删
     * @author 叶达杭
     * @param user_id 添加说明
     * @return int 添加说明
    */
    public int delete(String user_id){
        String whereClause = DatabaseContract.PlayerEntry.COLUMN_USER_ID+" = ?";
        String[] whereArgs = {user_id};
        return dbR.delete(DatabaseContract.PlayerEntry.TABLE_NAME,whereClause,whereArgs);
    }

    /**
     * 描述: 查
     * @author 叶达杭
     * @param user_id 添加说明
     * @return Model.Entity.Entry_Player 添加说明
    */
    public PlayerModel query(String user_id){
        String query ="SELECT * FROM "+ DatabaseContract.PlayerEntry.TABLE_NAME+" WHERE "+
                DatabaseContract.PlayerEntry.COLUMN_USER_ID +" = ?";
        String[] selectionArgs = {user_id};
        Cursor cursor = dbR.rawQuery(query,selectionArgs);
        if(cursor.moveToFirst()){
            @SuppressLint("Range") int room_id = cursor.getInt(cursor.getColumnIndex(DatabaseContract.PlayerEntry.COLUMN_ROOM_ID));
            @SuppressLint("Range") int card_num = cursor.getInt(cursor.getColumnIndex(DatabaseContract.PlayerEntry.COLUMN_HAND_CARD_NUM));
            @SuppressLint("Range") String cards = cursor.getString(cursor.getColumnIndex(DatabaseContract.PlayerEntry.COLUMN_HAND_CARDS));
            @SuppressLint("Range") String ITS_FIRST_BOTTOM_PLAYER = cursor.getString(cursor.getColumnIndex(DatabaseContract.PlayerEntry.COLUMN_ITS_FIRST_BOTTOM_PLAYER));
            @SuppressLint("Range") String ITS_SECOND_BOTTOM_PLAYER = cursor.getString(cursor.getColumnIndex(DatabaseContract.PlayerEntry.COLUMN_ITS_SECOND_BOTTOM_PLAYER));
            @SuppressLint("Range") String ITS_THIRD_BOTTOM_PLAYER = cursor.getString(cursor.getColumnIndex(DatabaseContract.PlayerEntry.COLUMN_ITS_THIRD_BOTTOM_PLAYER));
            cursor.close();
            return  new PlayerModel(user_id,room_id,card_num,cards,ITS_FIRST_BOTTOM_PLAYER,
                    ITS_SECOND_BOTTOM_PLAYER,ITS_THIRD_BOTTOM_PLAYER);
        }else{
            cursor.close();
            return null;
        }
    }

}
