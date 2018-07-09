package test.example.com.devicescheduling;

import android.database.Cursor;
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
        //Bundle bundle = getIntent().getExtras();

        //if (bundle == null)
        //  return;

        //int id = bundle.getInt(Constants.ID);
        //String tableName = bundle.getString(Constants.TABLE_NAME);
        int id = 2;
        String tableName = AlarmHistoryDBModel.TABLE_NAME;
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor detailInfo = databaseHelper.getDetailInfo(id, tableName);
        detailInfo.moveToFirst();
        // TODO: change image resource id
        int imageResourceID = R.drawable.pic1;
        String message = detailInfo.getString
                (detailInfo.getColumnIndex(AlarmHistoryDBModel.COLUMN_MESSAGE));
        imageView.setImageResource(imageResourceID);
        messageTextView.setText(message);

    }
}
