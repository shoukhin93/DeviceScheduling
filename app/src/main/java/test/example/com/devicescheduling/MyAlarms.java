package test.example.com.devicescheduling;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import test.example.com.devicescheduling.adapters.HistoryAdapter;
import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistoryDBModel;
import test.example.com.devicescheduling.database.model.MyAlarmsDBModel;
import test.example.com.devicescheduling.models.HistoryModel;
import test.example.com.devicescheduling.resourceManager.ResourceManager;
import test.example.com.devicescheduling.sharedPreferenceManager.SharedPrefManager;

public class MyAlarms extends AppCompatActivity {
    private ArrayList<HistoryModel> historyData = new ArrayList<>();
    RecyclerView historyRecyclerView;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_history);

        historyRecyclerView = findViewById(R.id.alarms_recycler_view);
        historyAdapter = new HistoryAdapter(historyData, this);
        historyAdapter.definedAsMySchedule();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        historyRecyclerView.setLayoutManager(mLayoutManager);
        historyRecyclerView.setAdapter(historyAdapter);
        getHistoryData();
    }

    private void getHistoryData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        Cursor scheduleHistory = databaseHelper.getMyHistory();
        while (scheduleHistory.moveToNext()) {
            HistoryModel historyModel = new HistoryModel();
            historyModel.setId(scheduleHistory.getInt
                    (scheduleHistory.getColumnIndex(MyAlarmsDBModel.COLUMN_ID)));

            historyModel.setTableName(MyAlarmsDBModel.TABLE_NAME);

            String tempImageResourceID = scheduleHistory.getString
                    (scheduleHistory.getColumnIndex(MyAlarmsDBModel.COLUMN_IMAGE));
            int mappedImageResourceID = ResourceManager.getMappedImageResourceID(
                    Integer.parseInt(tempImageResourceID));
            historyModel.setImageResourceID(mappedImageResourceID);

            historyModel.setMessage(scheduleHistory.getString
                    (scheduleHistory.getColumnIndex(MyAlarmsDBModel.COLUMN_MESSAGE)));

            String phoneNumber = scheduleHistory.getString
                    (scheduleHistory.getColumnIndex(AlarmHistoryDBModel.COLUMN_PHONE));
            historyModel.setSetterName(getPhoneNumberWithName(phoneNumber));

            String tempTimestamp = scheduleHistory.getString
                    (scheduleHistory.getColumnIndex(AlarmHistoryDBModel.COLUMN_TIMESTAMP));
            historyModel.setTimestamp(formatTimeInMillisToDate(tempTimestamp));

            historyData.add(historyModel);
        }
        historyAdapter.notifyDataSetChanged();
    }

    private String formatTimeInMillisToDate(String timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
                Locale.ENGLISH);
        return dateFormat.format(calendar.getTime());
    }

    private String getPhoneNumberWithName(String phoneNumber) {
        SharedPrefManager manager = SharedPrefManager.getInstance(this);
        String name = manager.getNameFromNumber(phoneNumber);
        if (!TextUtils.isEmpty(name))
            return phoneNumber + " (" + name + ")";

        return phoneNumber;
    }
}
