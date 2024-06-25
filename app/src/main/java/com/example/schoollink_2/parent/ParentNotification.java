package com.example.schoollink_2.parent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.Set;

public class ParentNotification extends AppCompatActivity {

    private static final String PREFS_NAME = "DriverMessages";
    private static final String KEY_NOTIFICATIONS = "notifications";

    private BottomNavigationView bottomNavigationView;
    private TextView notificationTextView;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_notification);

        // Retrieve notifications sent by the driver
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> notifications = prefs.getStringSet(KEY_NOTIFICATIONS, null);

        // Display the notifications in the TextView
        notificationTextView = findViewById(R.id.notificationTextView);
        StringBuilder notificationText = new StringBuilder();
        if (notifications != null) {
            for (String notification : notifications) {
                notificationText.append(notification).append("\n");
            }
        }
        notificationTextView.setText(notificationText.toString());

        // Handle clear button click
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v -> {
            clearNotifications();
        });

        // Handle bottom navigation item selection
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.post(() -> bottomNavigationView.setSelectedItemId(R.id.bottom_notification));

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), ParentHome.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
                return true;
            } else if (itemId == R.id.bottom_notification) {
                return true;
            } else if (itemId == R.id.bottom_contact) {
                startActivity(new Intent(getApplicationContext(), ParentContact.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
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
    }

    private void clearNotifications() {
        // Clear the notifications from SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(KEY_NOTIFICATIONS, new HashSet<>());
        editor.apply();

        // Update the TextView to reflect the cleared notifications
        notificationTextView.setText("");

        Toast.makeText(this, "Notifications cleared", Toast.LENGTH_SHORT).show();
    }
}
