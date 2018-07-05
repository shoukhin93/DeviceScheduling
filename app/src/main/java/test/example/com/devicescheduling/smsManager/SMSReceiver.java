package test.example.com.devicescheduling.smsManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import test.example.com.devicescheduling.Constants;
import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistory;


public class SMSReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Log.d(Constants.LOGTAG, "on sms receive");
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
                        long isInserted = dbHelper.insertAlarmHistory(alarmHistory);
                        Log.d(Constants.LOGTAG, isInserted + "");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
