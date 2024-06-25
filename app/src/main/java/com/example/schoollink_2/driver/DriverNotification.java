package com.example.schoollink_2.driver;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;

public class DriverNotification extends AppCompatActivity {
    private static final String PREFS_NAME = "DriverMessages";
    private static final String KEY_SELECTED_MESSAGE = "selectedMessage";

    private TextView notificationTextView;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_notification);

        // Retrieve the selected message from shared preferences
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String selectedMessage = prefs.getString(KEY_SELECTED_MESSAGE, "No message selected");

        // Display the selected message in a TextView
        notificationTextView = findViewById(R.id.notificationTextView);
        notificationTextView.setText(selectedMessage);

        // Handle clear button click
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> clearNotification());
    }

    private void clearNotification() {
        // Clear the selected message from SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_SELECTED_MESSAGE, "");
        editor.apply();

        // Update the TextView to reflect the cleared message
        notificationTextView.setText("No message selected");

        Toast.makeText(this, "Notification cleared", Toast.LENGTH_SHORT).show();
    }
}
