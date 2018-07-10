package test.example.com.devicescheduling;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistoryDBModel;

public class ContentShow extends AppCompatActivity {
    ImageView imageView;
    TextView messageTextView;

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
        // TODO: change image resource id
        int imageResourceID = R.drawable.pic1;
        String message = detailInfo.getString
                (detailInfo.getColumnIndex(AlarmHistoryDBModel.COLUMN_MESSAGE));
        imageView.setImageResource(imageResourceID);
        messageTextView.setText(message);

        changePhoneStatus(phoneStatus);
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
}
