package test.example.com.devicescheduling;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistoryDBModel;
import test.example.com.devicescheduling.resourceManager.ResourceManager;

public class ContentShow extends AppCompatActivity {
    ImageView imageView;
    TextView messageTextView;
    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_show);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        imageView = findViewById(R.id.content_image_view);
        messageTextView = findViewById(R.id.message_text_view);
        Bundle bundle = getIntent().getExtras();

        if (bundle == null)
            return;

        int id = bundle.getInt(Constants.ID);
        int phoneStatus = bundle.getInt(Constants.PHONE_STATUS);
        String tableName = bundle.getString(Constants.TABLE_NAME);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor detailInfo = databaseHelper.getDetailInfo(id, tableName);
        detailInfo.moveToFirst();

        String tempImageResourceID = detailInfo.getString
                (detailInfo.getColumnIndex(AlarmHistoryDBModel.COLUMN_IMAGE));
        int imageResourceID = getImageResourceId(tempImageResourceID);

        String tempSoundResourceID = detailInfo.getString
                (detailInfo.getColumnIndex(AlarmHistoryDBModel.COLUMN_SOUND));
        int soundResourceID = getSoundResourceId(tempSoundResourceID);

        String message = detailInfo.getString
                (detailInfo.getColumnIndex(AlarmHistoryDBModel.COLUMN_MESSAGE));

        String senderPhoneNumber = detailInfo.getString
                (detailInfo.getColumnIndex(AlarmHistoryDBModel.COLUMN_PHONE));
        String senderName = getSMSSenderName(senderPhoneNumber);

        imageView.setImageResource(imageResourceID);
        messageTextView.setText(message);
        changePhoneStatus(phoneStatus);
        playSound(soundResourceID);
        setSenderPhoneAndNameText(senderPhoneNumber, senderName);
    }

    private void setSenderPhoneAndNameText(String number, String name) {
        String nameAndPhone;
        TextView nameAndPhoneTextView = findViewById(R.id.sender_name_text_view);

        if (TextUtils.isEmpty(name))
            nameAndPhone = number;
        else
            nameAndPhone = number + " (" + name + ")";

        nameAndPhoneTextView.setText(nameAndPhone);
    }

    private void playSound(int soundResourceID) {
        mediaPlayer = MediaPlayer.create(this, soundResourceID);
        mediaPlayer.start();
    }

    private void changePhoneStatus(int status) {

        // -1 for no change mode
        if (status == -1)
            return;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        } else {
            AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(status);
        }
    }

    private int getImageResourceId(String imageID) {
        int mappedImageResourceID = Integer.parseInt(imageID);
        int imageResourceID = ResourceManager.getMappedImageResourceID(mappedImageResourceID);
        return imageResourceID;
    }

    private int getSoundResourceId(String soundID) {
        int mappedSoundResourceID = Integer.parseInt(soundID);
        int soundResourceID = ResourceManager.getSound(mappedSoundResourceID);
        return soundResourceID;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

    private String getSMSSenderName(String SMSSenderNumber) {
        Uri lookupUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.
                CONTENT_FILTER_URI, Uri.encode(SMSSenderNumber));
        Cursor c = getContentResolver().query(lookupUri,
                new String[]{ContactsContract.Data.DISPLAY_NAME},
                null, null, null);
        try {
            c.moveToFirst();
            String displayName = c.getString(0);
            return displayName;

        } catch (Exception e) {
        } finally {
            c.close();
        }
        return "";
    }
}
