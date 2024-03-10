package Model.DaoImpl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Database.DatabaseContract;
import Database.DatabaseHelper;
import Model.Dao.UserEntryDao;
import Model.Entity.User;

public class UserEntryDaoImpl implements UserEntryDao {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase dbR;
    private SQLiteDatabase dbW;
    public UserEntryDaoImpl(Context context) {
        dbHelper = new DatabaseHelper(context);
        dbR=dbHelper.getReadableDatabase();
        dbW=dbHelper.getWritableDatabase();
    }

    public void insertDate(String user_id,String user_nickname){
        if(isItemExists(user_id)){
            //存在该项 则不能插入
        }else {
            ContentValues values = new ContentValues();
            values.put(DatabaseContract.UserEntry.COLUMN_USER_ID, user_id);
            values.put(DatabaseContract.UserEntry.COLUMN_USER_NICKNAME, user_nickname);
            dbW.insert(DatabaseContract.UserEntry.TABLE_NAME,null,values);
        }
    }

    public User getUser(String user_id){
         String query ="SELECT * FROM "+ DatabaseContract.UserEntry.TABLE_NAME+" WHERE "+
                 DatabaseContract.UserEntry.COLUMN_USER_ID +" = ?";
         String[] selectionArgs = {user_id};
         Cursor cursor = dbR.rawQuery(query,selectionArgs);
         if(cursor.moveToFirst()){
             @SuppressLint("Range") String nickName = cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_USER_NICKNAME));
             cursor.close();
             return  new User(user_id,nickName);
         }else{
             cursor.close();
             return null;
         }
    }

    /**
     * 描述: 删
     * @author 叶达杭
     * @param user_id
     * @return int
    */
    public int delete(String user_id){
        String selection = DatabaseContract.UserEntry.COLUMN_USER_ID+" = ?";
        String[] selectionArgs={user_id};
        return dbR.delete(DatabaseContract.UserEntry.TABLE_NAME,selection,selectionArgs);
    }

    private boolean isItemExists(String user_id){
        String query = "SELECT * FROM "+ DatabaseContract.UserEntry.TABLE_NAME+" WHERE "+
                DatabaseContract.UserEntry.COLUMN_USER_ID+" = ?";
        String[] selectionArgs = {user_id};

        Cursor cursor = dbR.rawQuery(query,selectionArgs);
        boolean exist = cursor.moveToFirst();
        cursor.close();
        return exist;
    }
}
