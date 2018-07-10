package test.example.com.devicescheduling;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import test.example.com.devicescheduling.smsManager.SMSManager;

public class SetSchedule extends AppCompatActivity {
    EditText phoneNumberEditText;
    Button sendButton;
    FloatingActionButton pickContact;

    SMSManager smsManager;
    private final int PICK_CONTACT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_schedule);

        if (ContextCompat.checkSelfPermission(SetSchedule.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // If you do not have permission, request it
            ActivityCompat.requestPermissions(SetSchedule.this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    10);
        }

        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        pickContact = findViewById(R.id.pick_contact);
        sendButton = findViewById(R.id.send_button);
        smsManager = (SMSManager) getIntent().
                getSerializableExtra(Constants.SMS_MANAGER);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog
                        .Builder(SetSchedule.this);
                alertDialogBuilder.setMessage("Are you sure want" +
                        "to set schedule? Standard SMS charge may be applicable");
                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Log.d(Constants.LOGTAG, smsManager.getFormattedSMS());
                                        smsManager.setReceiverPhoneNumber
                                                (phoneNumberEditText.getText().toString());
                                        smsManager.sendSMS();
                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new
                        DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        pickContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, PICK_CONTACT);
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

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null,
                            null, null, null);
                    if (c.moveToFirst()) {
                        String contactNumber = c.getString(
                                c.getColumnIndexOrThrow(ContactsContract.
                                        CommonDataKinds.Phone.NUMBER));
                        phoneNumberEditText.setText(contactNumber);
                    }
                }
                break;
        }
    }
}
