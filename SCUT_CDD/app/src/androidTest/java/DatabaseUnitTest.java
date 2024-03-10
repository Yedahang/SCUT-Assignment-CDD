import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import Database.DatabaseContract;
import Database.DatabaseHelper;

@RunWith(AndroidJUnit4.class)
public class DatabaseUnitTest {

    @Test
    public void testSQL(){

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DatabaseHelper dbHelper= new DatabaseHelper(appContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //插入数据
        ContentValues values =new ContentValues();
//        values.put(DatabaseContract.UserEntry.USER_ID,"112233");
        values.put(DatabaseContract.UserEntry.COLUMN_USER_NICKNAME,"Admin");
        values.put(DatabaseContract.UserEntry.COLUMN_USER_SCORE,"1000");

        long newRowId = db.insert(DatabaseContract.UserEntry.TABLE_NAME,null,values);
        Log.d("TestOutput","开始测试");

        Cursor cursor = db.query(DatabaseContract.UserEntry.TABLE_NAME,null,"id=?",
                new String[]{"112233"},null,null,null);
       if(cursor.moveToFirst()){
           String nickName = cursor.getString(cursor.getColumnIndex(DatabaseContract.UserEntry.COLUMN_USER_NICKNAME));
           System.out.println("测试"+nickName);
       }
    }
}


