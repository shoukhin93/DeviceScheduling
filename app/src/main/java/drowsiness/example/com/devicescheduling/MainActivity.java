package drowsiness.example.com.devicescheduling;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import drowsiness.example.com.devicescheduling.database.DatabaseHelper;
import drowsiness.example.com.devicescheduling.database.model.AlarmHistory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        AlarmHistory myAlarms = new AlarmHistory();
        myAlarms.setPhone("000");
        myAlarms.setMessage("testing");
        myAlarms.setImage("asd");
        myAlarms.setSound("a");

        long inserted = databaseHelper.insertAlarmHistory(myAlarms);
        Log.d(Constants.LOGTAG, "" + inserted);


        /*AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, Alarms.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60, pi); // Millisec * Second * Minute*/
    }
}
