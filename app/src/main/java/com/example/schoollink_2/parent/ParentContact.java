package com.example.schoollink_2.parent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ParentContact extends AppCompatActivity {
    private static final String PREFS_NAME = "DriverMessages";
    private static final String KEY_SELECTED_MESSAGE = "selectedMessage";
    private static final String STUDENT_PREFS = "SelectedStudent";
    private static final String KEY_STUDENT_NAME = "studentName";

    private BottomNavigationView bottomNavigationView;
    private String studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_contact);

        // Retrieve the student's name from SharedPreferences
        SharedPreferences studentPrefs = getSharedPreferences(STUDENT_PREFS, Context.MODE_PRIVATE);
        studentName = studentPrefs.getString(KEY_STUDENT_NAME, "Unknown Student");

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.post(() -> bottomNavigationView.setSelectedItemId(R.id.bottom_contact));

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), ParentHome.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
                return true;
            } else if (itemId == R.id.bottom_notification) {
                startActivity(new Intent(getApplicationContext(), ParentNotification.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
                return true;
            } else if (itemId == R.id.bottom_contact) {
                return true;
            } else if (itemId == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), ParentProfile.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else {
                return false;
            }
        });

        // Handle clicking on messages
        TextView markAbsence = findViewById(R.id.markAbsence);
        markAbsence.setOnClickListener(v -> handleMessageClick("Mark Absence"));

        TextView late = findViewById(R.id.late);
        late.setOnClickListener(v -> handleMessageClick("10-15 Min Late"));
    }

    private void handleMessageClick(String message) {
        // Append the student's name to the message
        String messageWithStudent = message + " - " + "\nStudent Name: " + studentName;

        // Save the message to shared preferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_SELECTED_MESSAGE, messageWithStudent);
        editor.apply();

        Toast.makeText(this, "Message Sent: " + messageWithStudent, Toast.LENGTH_SHORT).show();
    }
}
