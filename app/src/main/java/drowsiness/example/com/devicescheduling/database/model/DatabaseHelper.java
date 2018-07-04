package drowsiness.example.com.devicescheduling.database.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import drowsiness.example.com.devicescheduling.Constants;

/**
 * Created by Shoukhin on 7/4/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "device_scheduling_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyAlarms.CREATE_TABLE);
        Log.d(Constants.LOGTAG, "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyAlarms.TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

    public long insertNote(MyAlarms myAlarms) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyAlarms.COLUMN_PHONE, myAlarms.getPhone());
        values.put(MyAlarms.COLUMN_MESSAGE, myAlarms.getMessage());
        values.put(MyAlarms.COLUMN_IMAGE, myAlarms.getImage());
        values.put(MyAlarms.COLUMN_SOUND, myAlarms.getSound());

        long id = db.insert(MyAlarms.TABLE_NAME, null, values);
        db.close();

        return id;
    }
}
