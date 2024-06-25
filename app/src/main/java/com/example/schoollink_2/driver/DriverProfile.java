package com.example.schoollink_2.driver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverProfile extends AppCompatActivity {

    TextView usernameTextView, addressTextView, ageTextView, nameTextView, licenseNumberTextView, contactInfoTextView;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_profile);

        usernameTextView = findViewById(R.id.username_text_view);
        addressTextView = findViewById(R.id.address_text_view);
        ageTextView = findViewById(R.id.age_text_view);
        nameTextView = findViewById(R.id.name_text_view);
        licenseNumberTextView = findViewById(R.id.license_number_text_view);
        contactInfoTextView = findViewById(R.id.contact_info_text_view);
        logoutButton = findViewById(R.id.logout_button);

        // Retrieve user details from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("driverUsername", "");

        // Retrieve driver details from Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("drivers");
        databaseReference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String address = dataSnapshot.child("driverAddress").getValue(String.class);
                    String age = dataSnapshot.child("driverAge").getValue(String.class);
                    String name = dataSnapshot.child("driverName").getValue(String.class);
                    String licenseNumber = dataSnapshot.child("licenseNumber").getValue(String.class);
                    String contactInfo = dataSnapshot.child("contactInfo").getValue(String.class);

                    // Set retrieved details to TextViews
                    usernameTextView.setText(username);
                    addressTextView.setText(address);
                    ageTextView.setText(age);
                    nameTextView.setText(name);
                    licenseNumberTextView.setText(licenseNumber);
                    contactInfoTextView.setText(contactInfo);

                    // Save driver's name to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("driverName", name);
                    editor.apply();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });

        // Logout button click listener
        logoutButton.setOnClickListener(v -> {
            // Clear SharedPreferences on logout
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            // Redirect to login activity
            Intent intent = new Intent(DriverProfile.this, DriverLoginActivity.class);
            startActivity(intent);
            finish();
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Use a runtime method to set the selected item
        bottomNavigationView.post(() -> bottomNavigationView.setSelectedItemId(R.id.bottom_profile));

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home) {
                startActivity(new Intent(getApplicationContext(), DriverHome.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
                return true;
            } else if (itemId == R.id.bottom_contact) {
                startActivity(new Intent(getApplicationContext(), DriverContact.class));
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                finish();
                return true;
            } else if (itemId == R.id.bottom_profile) {
                return true;
            } else {
                return false;
            }
        });
    }
}
