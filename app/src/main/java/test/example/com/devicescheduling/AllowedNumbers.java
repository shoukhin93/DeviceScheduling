package test.example.com.devicescheduling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import test.example.com.devicescheduling.sharedPreferenceManager.SharedPrefManager;

public class AllowedNumbers extends AppCompatActivity {
    Button saveButton;
    EditText phoneNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allowed_numbers);
        saveButton = findViewById(R.id.save_button);
        phoneNumberEditText = findViewById(R.id.phone_number_edit_text);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phoneNumberEditText.getText().toString();
                String name = "tes";
                SharedPrefManager manager = SharedPrefManager.getInstance(AllowedNumbers.this);
                manager.saveAllowedNumber(number, name);
                Toast.makeText(getApplicationContext(), "Number Saved!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
