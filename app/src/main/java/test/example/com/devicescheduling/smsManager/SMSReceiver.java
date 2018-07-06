package test.example.com.devicescheduling.smsManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import test.example.com.devicescheduling.Constants;
import test.example.com.devicescheduling.alarmManager.ManagerOfAlarms;
import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistory;

public class SMSReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage
                            .createFromPdu((byte[]) pdusObj[i]);
                    String SMSSenderNumber = currentMessage
                            .getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();
                    SMSManager smsManager = new SMSManager(message);

                    if (smsManager.isSMSForThisApp()) {
                        AlarmHistory alarmHistory = new AlarmHistory();
                        alarmHistory.setPhone(SMSSenderNumber);
                        alarmHistory.setImage(smsManager.getImage());
                        alarmHistory.setSound(smsManager.getSound());
                        alarmHistory.setMessage(smsManager.getMessage());

                        DatabaseHelper dbHelper = new DatabaseHelper(context);
                        long id = dbHelper.insertAlarmHistory(alarmHistory);

                        //ManagerOfAlarms alarms = new ManagerOfAlarms(context);
                        //alarms.setAlarm("2018-07-05 22:50:18.429", (int) id);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}