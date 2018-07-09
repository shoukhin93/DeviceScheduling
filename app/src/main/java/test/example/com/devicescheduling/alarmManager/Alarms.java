package test.example.com.devicescheduling.alarmManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import test.example.com.devicescheduling.Constants;
import test.example.com.devicescheduling.ContentShow;

/**
 * Created by Shoukhin on 5/8/2018.
 */

public class Alarms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = null;
        if (pm != null) {
            wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
            wl.acquire(10 * 60 * 1000L /*10 minutes*/);
        }

        Bundle extras = intent.getExtras();
        int id = 0;
        if (extras != null) {
            id = extras.getInt(Constants.ID);
        }

        Intent i = new Intent(context, ContentShow.class);
        i.putExtra(Constants.ID, id);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);

        wl.release();
    }
}
