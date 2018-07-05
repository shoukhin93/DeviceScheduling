package drowsiness.example.com.devicescheduling.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import drowsiness.example.com.devicescheduling.database.model.AlarmHistory;
import drowsiness.example.com.devicescheduling.database.model.AllowedPhones;
import drowsiness.example.com.devicescheduling.database.model.MyAlarms;

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
        db.execSQL(AlarmHistory.CREATE_TABLE);
        db.execSQL(AllowedPhones.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyAlarms.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AlarmHistory.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AllowedPhones.TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

    public long insertMyAlarm(MyAlarms myAlarms) {
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

    public long insertAlarmHistory(AlarmHistory alarmHistory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AlarmHistory.COLUMN_PHONE, alarmHistory.getPhone());
        values.put(AlarmHistory.COLUMN_MESSAGE, alarmHistory.getMessage());
        values.put(AlarmHistory.COLUMN_IMAGE, alarmHistory.getImage());
        values.put(AlarmHistory.COLUMN_SOUND, alarmHistory.getSound());

        long id = db.insert(AlarmHistory.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long insertAllowedPhones(AllowedPhones allowedPhones) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AlarmHistory.COLUMN_PHONE, allowedPhones.getPhone());

        long id = db.insert(AllowedPhones.TABLE_NAME, null, values);
        db.close();
        return id;
    }
}
