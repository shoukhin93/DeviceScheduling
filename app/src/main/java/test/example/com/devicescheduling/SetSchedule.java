package test.example.com.devicescheduling;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import test.example.com.devicescheduling.smsManager.SMSManager;

public class SetSchedule extends AppCompatActivity {
    EditText phoneNumberEditText;
    Button sendButton;

    SMSManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule);

        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        sendButton = findViewById(R.id.send_button);
        smsManager = (SMSManager) getIntent().
                getSerializableExtra(Constants.SMS_MANAGER);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                smsManager.setRecieverPhoneNumber(phoneNumberEditText.getText().toString());
                smsManager.sendSMS();
            }
        });

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            int REQUEST_SMS_PERMISSION = 1;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_SMS_PERMISSION);
        }
    }
}
