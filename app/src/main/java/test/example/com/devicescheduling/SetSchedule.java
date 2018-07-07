package test.example.com.devicescheduling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import test.example.com.devicescheduling.smsManager.SMSManager;

public class SetSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule);

        SMSManager smsManager = (SMSManager) getIntent().
                getSerializableExtra(Constants.SMS_MANAGER);
        Log.d(Constants.LOGTAG, smsManager.getMessage());
    }
}
