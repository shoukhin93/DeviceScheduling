package test.example.com.devicescheduling;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import test.example.com.devicescheduling.smsManager.SMSManager;

public class AddSchedule extends AppCompatActivity {
    Button dateChangeButton;
    Button timeChangeButton;
    Button changeImageButton;
    Button playSoundButton;
    Button saveButton;
    ImageView selectedImageView;
    TextView timeTextView;
    TextView dateTextView;
    Spinner soundListSpinner;

    int mYear, mMonth, mDay;
    int mHour, mMinute, mAmPm;
    Calendar mCurrentDate;
    int imageResourceCode;
    MediaPlayer mediaPlayer;

    private final int SELECT_IMAGE_REQUEST_CODE = 1;

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

        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSchedule.this, SelectImage.class);
                startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE);
            }
        });

        playSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soundName = soundListSpinner.getSelectedItem().toString();
                int soundResourceID = getSoundResourceIDFromName(soundName);
                mediaPlayer = MediaPlayer.create(AddSchedule.this,
                        soundResourceID);
                mediaPlayer.start();
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

    private int getSoundResourceIDFromName(String soundName) {
        if (soundName.equals("1")) {
            return R.raw.crazy_smile;
        }
        return 0;
    }

    private void initializeVariables() {
        dateChangeButton = findViewById(R.id.date_change_button);
        timeChangeButton = findViewById(R.id.time_change_button);
        changeImageButton = findViewById(R.id.image_change_button);
        playSoundButton = findViewById(R.id.sound_play_button);
        saveButton = findViewById(R.id.save_button);
        dateTextView = findViewById(R.id.date_text_view);
        timeTextView = findViewById(R.id.time_text_view);
        selectedImageView = findViewById(R.id.selected_image_view);
        soundListSpinner = findViewById(R.id.sound_list_spinner);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SELECT_IMAGE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                imageResourceCode = data.getIntExtra(Constants.IMAGE_RESOURSE_CODE, 0);
                setImageFromResource(imageResourceCode);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                imageResourceCode = 1;
            }
        }
    }

    private void setImageFromResource(int imageResourceCode) {
        selectedImageView.setImageResource(imageResourceCode);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }
}
