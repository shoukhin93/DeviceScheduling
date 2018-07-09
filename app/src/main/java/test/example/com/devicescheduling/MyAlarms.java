package test.example.com.devicescheduling;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import test.example.com.devicescheduling.adapters.HistoryAdapter;
import test.example.com.devicescheduling.database.DatabaseHelper;
import test.example.com.devicescheduling.database.model.AlarmHistoryDBModel;
import test.example.com.devicescheduling.database.model.MyAlarmsDBModel;
import test.example.com.devicescheduling.models.HistoryModel;

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
            // TODO: change image resource id static to dynamic
            historyModel.setId(scheduleHistory.getInt
                    (scheduleHistory.getColumnIndex(MyAlarmsDBModel.COLUMN_ID)));

            historyModel.setTableName(MyAlarmsDBModel.TABLE_NAME);
            historyModel.setImageResourceID(R.drawable.pic1);

            historyModel.setMessage(scheduleHistory.getString
                    (scheduleHistory.getColumnIndex(MyAlarmsDBModel.COLUMN_MESSAGE)));

            historyModel.setSetterName(scheduleHistory.getString
                    (scheduleHistory.getColumnIndex(MyAlarmsDBModel.COLUMN_PHONE)));

            historyModel.setTimestamp(scheduleHistory.getString
                    (scheduleHistory.getColumnIndex(MyAlarmsDBModel.COLUMN_TIMESTAMP)));

            historyData.add(historyModel);
        }
        historyAdapter.notifyDataSetChanged();
    }
}
