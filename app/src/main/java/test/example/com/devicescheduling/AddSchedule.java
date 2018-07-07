package test.example.com.devicescheduling;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import test.example.com.devicescheduling.smsManager.SMSManager;

public class AddSchedule extends AppCompatActivity {
    Button dateChangeButton;
    Button timeChangeButton;
    Button saveButton;
    TextView timeTextView;
    TextView dateTextView;

    int mYear, mMonth, mDay;
    int mHour, mMinute, mAmPm;
    Calendar mCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        initializeVariables();
        setCurrentDateText();
        setCurrentTimeText();

        dateChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddSchedule.this,
                        new DatePickerDialog.OnDateSetListener() {

                            public void onDateSet(DatePicker datepicker, int
                                    selectedYear, int selectedMonth, int selectedDay) {
                                mCurrentDate.set(Calendar.YEAR, selectedYear);
                                mCurrentDate.set(Calendar.MONTH, selectedMonth);
                                mCurrentDate.set(Calendar.DAY_OF_MONTH, selectedDay);
                                setCurrentDateText();
                            }
                        }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        timeChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddSchedule.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                mCurrentDate.set(Calendar.HOUR, hourOfDay);
                                mCurrentDate.set(Calendar.MINUTE, minute);
                                setCurrentTimeText();
                            }
                        }, mHour, mMinute, false);

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SMSManager smsManager = new SMSManager();
                smsManager.setImage("1");
                smsManager.setSound("1");
                smsManager.setMessage("testing");
                smsManager.setTimestamp(String.valueOf(mCurrentDate.getTimeInMillis()));
                String message = smsManager.getFormattedSMS();
                Log.d(Constants.LOGTAG, message);
            }
        });
    }

    private void initializeVariables() {
        dateChangeButton = findViewById(R.id.date_change_button);
        timeChangeButton = findViewById(R.id.time_change_button);
        saveButton = findViewById(R.id.save_button);
        dateTextView = findViewById(R.id.date_text_view);
        timeTextView = findViewById(R.id.time_text_view);

        mCurrentDate = Calendar.getInstance();
    }

    private void setCurrentDateText() {
        mYear = mCurrentDate.get(Calendar.YEAR);
        mMonth = mCurrentDate.get(Calendar.MONTH);
        mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        // Since month is 0 index based, adding 1 with selected month
        String tempDate = "" + mDay + "/" + (mMonth + 1) + "/" + mYear;
        dateTextView.setText(tempDate);
    }

    private void setCurrentTimeText() {
        mHour = mCurrentDate.get(Calendar.HOUR);
        mMinute = mCurrentDate.get(Calendar.MINUTE);
        mAmPm = mCurrentDate.get(Calendar.AM_PM);

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a",
                Locale.ENGLISH);
        String tempTime = dateFormat.format(mCurrentDate.getTime());
        timeTextView.setText(tempTime);
    }
}
