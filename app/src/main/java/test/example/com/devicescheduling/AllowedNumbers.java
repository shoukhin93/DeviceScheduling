package test.example.com.devicescheduling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import test.example.com.devicescheduling.sharedPreferenceManager.SharedPrefManager;

public class AllowedNumbers extends AppCompatActivity {
    Button saveButton;
    EditText phoneNumberEditText;
    ListView allowedNumbersListView;
    Map<String, ?> allowedNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allowed_numbers);
        initializeVariables();
        showAllAllowedNumbers();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNumberEditText.getText().toString();
                String name = "tes";
                SharedPrefManager manager = SharedPrefManager.getInstance(AllowedNumbers.this);
                //manager.saveAllowedNumber(number, name);
                Toast.makeText(getApplicationContext(), "Number Saved!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeVariables() {
        saveButton = findViewById(R.id.save_button);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        allowedNumbersListView = findViewById(R.id.allowed_numbers_list_view);
        SharedPrefManager manager = SharedPrefManager.getInstance(this);
        allowedNumbers = manager.getAllAllowedNumbers();
    }

    private void showAllAllowedNumbers() {
        ArrayList<String> tempAllowedNumbers = new ArrayList<>();
        for (Map.Entry<String, ?> allowedNumber : allowedNumbers.entrySet()) {
            String tempAllowedNumber = allowedNumber.getKey() + " ("
                    + allowedNumber.getValue() + ")";
            tempAllowedNumbers.add(tempAllowedNumber);
        }

        ArrayAdapter<String> allowedNumbersAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                tempAllowedNumbers);
        allowedNumbersListView.setAdapter(allowedNumbersAdapter);
    }
}
