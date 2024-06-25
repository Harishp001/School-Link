package com.example.schoollink_2.driver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.Set;

public class DriverContact extends AppCompatActivity {

    private static final String PREFS_NAME = "DriverMessages";
    private static final String KEY_NOTIFICATIONS = "notifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_contact);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Use a runtime method to set the selected item
        bottomNavigationView.post(() -> bottomNavigationView.setSelectedItemId(R.id.bottom_contact));

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), DriverHome.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
                return true;
            } else if (itemId == R.id.bottom_contact) {
                return true;
            } else if (itemId == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), DriverProfile.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else {
                return false;
            }
        });

        // Handle clicking on messages
        TextView vehicleBreakdown = findViewById(R.id.vehicleBreakdown);
        vehicleBreakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("Vehicle breakdown");
            }
        });

        TextView flatTyre = findViewById(R.id.flatTyre);
        flatTyre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("Flat tyre");
            }
        });

        TextView vehicleStopped = findViewById(R.id.vehicleStopped);
        vehicleStopped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("Vehicle stopped by police");
            }
        });

        TextView fuelShortage = findViewById(R.id.fuelShortage);
        fuelShortage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage("Fuel Shortage");
            }
        });
    }

    private void sendMessage(String message) {
        // Save the message to shared preferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> notifications = prefs.getStringSet(KEY_NOTIFICATIONS, new HashSet<>());
        notifications.add(message);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet(KEY_NOTIFICATIONS, notifications);
        editor.apply();

        // Display a toast message indicating that the message is sent
        Toast.makeText(this, "Message Sent: " + message, Toast.LENGTH_SHORT).show();
    }
}
