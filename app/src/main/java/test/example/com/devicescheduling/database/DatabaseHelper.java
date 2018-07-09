package test.example.com.devicescheduling.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import test.example.com.devicescheduling.database.model.AlarmHistoryDBModel;
import test.example.com.devicescheduling.database.model.AllowedPhonesDBModel;
import test.example.com.devicescheduling.database.model.MyAlarmsDBModel;

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
        db.execSQL(MyAlarmsDBModel.CREATE_TABLE);
        db.execSQL(AlarmHistoryDBModel.CREATE_TABLE);
        db.execSQL(AllowedPhonesDBModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyAlarmsDBModel.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AlarmHistoryDBModel.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AllowedPhonesDBModel.TABLE_NAME);

        // Create tables again
        onCreate(db);

    }

    public long insertMyAlarm(MyAlarmsDBModel myAlarmsDBModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyAlarmsDBModel.COLUMN_PHONE, myAlarmsDBModel.getPhone());
        values.put(MyAlarmsDBModel.COLUMN_MESSAGE, myAlarmsDBModel.getMessage());
        values.put(MyAlarmsDBModel.COLUMN_IMAGE, myAlarmsDBModel.getImage());
        values.put(MyAlarmsDBModel.COLUMN_SOUND, myAlarmsDBModel.getSound());

        long id = db.insert(MyAlarmsDBModel.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long insertAlarmHistory(AlarmHistoryDBModel alarmHistoryDBModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AlarmHistoryDBModel.COLUMN_PHONE, alarmHistoryDBModel.getPhone());
        values.put(AlarmHistoryDBModel.COLUMN_MESSAGE, alarmHistoryDBModel.getMessage());
        values.put(AlarmHistoryDBModel.COLUMN_IMAGE, alarmHistoryDBModel.getImage());
        values.put(AlarmHistoryDBModel.COLUMN_SOUND, alarmHistoryDBModel.getSound());

        long id = db.insert(AlarmHistoryDBModel.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public long insertAllowedPhones(AllowedPhonesDBModel allowedPhonesDBModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AlarmHistoryDBModel.COLUMN_PHONE, allowedPhonesDBModel.getPhone());

        long id = db.insert(AllowedPhonesDBModel.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Cursor getAllScheduleHistory() {
        String RAW_QUERY = "select * from " + AlarmHistoryDBModel.TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        return database.rawQuery(RAW_QUERY, null);
    }
}
