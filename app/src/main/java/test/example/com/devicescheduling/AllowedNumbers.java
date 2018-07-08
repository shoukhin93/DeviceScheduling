package test.example.com.devicescheduling;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    EditText nameEditText;
    ListView allowedNumbersListView;

    Map<String, ?> allowedNumbers;
    ArrayList<String> sharedPrefValueIndexes;

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
                String name = nameEditText.getText().toString();
                SharedPrefManager manager = SharedPrefManager.getInstance(AllowedNumbers.this);
                manager.saveAllowedNumber(number, name);
                Toast.makeText(getApplicationContext(), "Number Saved!",
                        Toast.LENGTH_SHORT).show();

                showAllAllowedNumbers();
            }
        });

        allowedNumbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = sharedPrefValueIndexes.get(position);
                String name = (String) allowedNumbers.get(key);
                phoneNumberEditText.setText(key);
                nameEditText.setText(name);
            }
        });

        allowedNumbersListView.setOnItemLongClickListener(new AdapterView
                .OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {

                AlertDialog.Builder alertDialogBuilder = new
                        AlertDialog.Builder(AllowedNumbers.this);
                alertDialogBuilder.setMessage("Are you sure want to delete?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                removePhone(position);
                                showAllAllowedNumbers();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    private void initializeVariables() {
        saveButton = findViewById(R.id.save_button);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);
        nameEditText = findViewById(R.id.name_edit_text);
        allowedNumbersListView = findViewById(R.id.allowed_numbers_list_view);
    }

    private void showAllAllowedNumbers() {
        SharedPrefManager manager = SharedPrefManager.getInstance(this);
        allowedNumbers = manager.getAllAllowedNumbers();
        ArrayList<String> tempAllowedNumbers = new ArrayList<>();

        for (Map.Entry<String, ?> allowedNumber : allowedNumbers.entrySet()) {
            String tempAllowedNumber = allowedNumber.getKey() + " ("
                    + allowedNumber.getValue() + ")";
            tempAllowedNumbers.add(tempAllowedNumber);
        }

        ArrayAdapter<String> allowedNumbersAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                tempAllowedNumbers);
        allowedNumbersListView.setAdapter(allowedNumbersAdapter);

        // Saving the index number of map to update / delete data
        sharedPrefValueIndexes = new ArrayList<>(allowedNumbers.keySet());
    }

    private void removePhone(int position) {
        String key = sharedPrefValueIndexes.get(position);
        SharedPrefManager manager = SharedPrefManager
                .getInstance(AllowedNumbers.this);
        manager.removeValue(key);
    }
}
