package test.example.com.devicescheduling.alarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import test.example.com.devicescheduling.Constants;
import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistoryDBModel;

/**
 * Created by Shoukhin on 7/5/2018.
 */

public class ManagerOfAlarms {
    private Context context;

    public ManagerOfAlarms(Context context) {
        this.context = context;
    }

    public void setAlarm(String time, int id) {
        Calendar alarmTime = Calendar.getInstance();
        alarmTime.setTimeInMillis(Long.parseLong(time));
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarms.class);
        i.putExtra(Constants.ID, id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, i,
                PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.set(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), pendingIntent);
        }
    }

    public void setBootTimeAlarms() {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor schedules = databaseHelper.getAllScheduleHistory();

        for (schedules.moveToFirst(); !schedules.isAfterLast(); schedules.moveToNext()) {
            int id = schedules.getInt
                    (schedules.getColumnIndex(AlarmHistoryDBModel.COLUMN_ID));
            String timeStamp = schedules.getString
                    (schedules.getColumnIndex(AlarmHistoryDBModel.COLUMN_TIMESTAMP));
            Calendar currentTime = Calendar.getInstance();

            if (currentTime.getTimeInMillis() >= Long.parseLong(timeStamp)) {
                setAlarm(timeStamp, id);
            }
        }
    }
}
