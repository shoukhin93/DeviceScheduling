package test.example.com.devicescheduling.broadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import test.example.com.devicescheduling.alarmManager.ManagerOfAlarms;

public class BootTimeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ManagerOfAlarms alarmManager = new ManagerOfAlarms(context);
        alarmManager.setBootTimeAlarms();
    }
}
