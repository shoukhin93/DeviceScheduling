package test.example.com.devicescheduling.alarmManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shoukhin on 7/5/2018.
 */

public class ManagerOfAlarms {
    private Context context;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
            Locale.ENGLISH);

    public ManagerOfAlarms(Context context) {
        this.context = context;
    }

    public void setAlarm(String time, int id) {
        Date alarmTime = stringToDateTime(time);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarms.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, i, 0);
        if (am != null) {
            am.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getTime(),
                    1000 * 60, pi);
        }
    }

    private Date stringToDateTime(String time) {
        Date convertedTime = null;
        try {
            convertedTime = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedTime;
    }
}
