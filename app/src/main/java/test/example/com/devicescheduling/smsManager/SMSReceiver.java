package test.example.com.devicescheduling.smsManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistoryDBModel;
import test.example.com.devicescheduling.sharedPreferenceManager.SharedPrefManager;

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

                    // Checking if sender is in allowed group
                    SharedPrefManager manager = SharedPrefManager.getInstance(context);
                    if (manager.isAllowed(SMSSenderNumber)) {
                        String message = currentMessage.getDisplayMessageBody();
                        SMSManager smsManager = new SMSManager(message);

                        if (smsManager.isSMSForThisApp()) {
                            smsManager.splitMessage(message);

                            AlarmHistoryDBModel alarmHistoryDBModel = new AlarmHistoryDBModel();
                            alarmHistoryDBModel.setPhone(SMSSenderNumber);
                            alarmHistoryDBModel.setImage(smsManager.getImage());
                            alarmHistoryDBModel.setSound(smsManager.getSound());
                            alarmHistoryDBModel.setMessage(smsManager.getMessage());
                            alarmHistoryDBModel.setTimestamp(smsManager.getTimestamp());

                            DatabaseHelper dbHelper = new DatabaseHelper(context);
                            long id = dbHelper.insertAlarmHistory(alarmHistoryDBModel);

                            //ManagerOfAlarms alarms = new ManagerOfAlarms(context);
                            //alarms.setAlarm("2018-07-06 13:49:18.429", (int) id);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}