package test.example.com.devicescheduling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import test.example.com.devicescheduling.adapters.HistoryAdapter;
import test.example.com.devicescheduling.models.HistoryModel;

public class AlarmHistory extends AppCompatActivity {
    private ArrayList<HistoryModel> historyData = new ArrayList<>();
    RecyclerView historyRecyclerView;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_history);

        historyAdapter = new HistoryAdapter(historyData, this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        historyRecyclerView.setLayoutManager(mLayoutManager);
        historyRecyclerView.setAdapter(historyAdapter);
        getHistoryData();
    }

    private void getHistoryData() {

    }
}
