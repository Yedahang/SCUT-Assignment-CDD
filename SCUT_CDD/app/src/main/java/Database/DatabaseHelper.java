package Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "CDD.db";
    private static  final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.GameRoomEntry.SQL_CREATE_ENTRIES);//房间表
        db.execSQL(DatabaseContract.UserEntry.SQL_CREATE_ENTRIES);//用户表
        db.execSQL(DatabaseContract.PlayerEntry.SQL_CREATE_ENTRIES);//玩家表

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.UserEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
