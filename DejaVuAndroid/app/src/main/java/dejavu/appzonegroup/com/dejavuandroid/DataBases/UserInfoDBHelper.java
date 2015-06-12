package dejavu.appzonegroup.com.dejavuandroid.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CrowdStar on 2/16/2015.
 */
public class UserInfoDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DBNAME";
    private static final String TABLE_NAME = "TableName";

    public UserInfoDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , Subject VARCHAR(15), Message VARCHAR(45));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNotification(String subject, String body) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Subject", subject);
        contentValues.put("Message", body);
        getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public Cursor ReadNotification() {
        String column[] = {"Subject", "Message"};
        return getWritableDatabase().query(TABLE_NAME, column,null, null, null, null, null);
    }
}
