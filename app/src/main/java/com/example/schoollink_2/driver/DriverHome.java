package com.example.schoollink_2.driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.schoollink_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverHome extends AppCompatActivity {

    private LinearLayout tripContainer;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        // Initialize views
        tripContainer = findViewById(R.id.tripContainer);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Use a runtime method to set the selected item
        bottomNavigationView.post(() -> bottomNavigationView.setSelectedItemId(R.id.bottom_home));

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home) {
                return true;
            } else if (itemId == R.id.bottom_contact) {
                startActivity(new Intent(getApplicationContext(), DriverContact.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
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

        // Retrieve trip details from Firebase Realtime Database
        DatabaseReference tripRef = FirebaseDatabase.getInstance().getReference().child("route");
        tripRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if data exists
                if (dataSnapshot.exists()) {
                    tripContainer.removeAllViews(); // Clear previous views

                    // Iterate through each trip data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Get trip details
                        String tripId = snapshot.child("tripId").getValue(String.class);
                        String vehicleNumber = snapshot.child("vehicleNo").getValue(String.class);
                        String routeName = snapshot.child("routeName").getValue(String.class);
                        String time = snapshot.child("time").getValue(String.class);

                        // Create a new LinearLayout for each trip
                        LinearLayout tripLayout = new LinearLayout(DriverHome.this);
                        tripLayout.setOrientation(LinearLayout.VERTICAL);
                        tripLayout.setPadding(16, 16, 16, 16);
                        tripLayout.setBackgroundResource(R.drawable.trip_details_background); // Optional: add background drawable for better UI
                        tripLayout.setOnClickListener(v -> {
                            Intent intent = new Intent(DriverHome.this, TripStudent.class);
                            intent.putExtra("routeName", routeName);
                            startActivity(intent);
                        });

                        // Create and add TextViews for trip details
                        TextView tripIdTextView = new TextView(DriverHome.this);
                        tripIdTextView.setText("Trip ID: " + tripId);
                        tripIdTextView.setTextSize(18);
                        tripIdTextView.setTextColor(getResources().getColor(R.color.black));
                        tripLayout.addView(tripIdTextView);

                        TextView vehicleNumberTextView = new TextView(DriverHome.this);
                        vehicleNumberTextView.setText("Vehicle Number: " + vehicleNumber);
                        vehicleNumberTextView.setTextSize(18);
                        vehicleNumberTextView.setTextColor(getResources().getColor(R.color.black));
                        tripLayout.addView(vehicleNumberTextView);

                        TextView routeNameTextView = new TextView(DriverHome.this);
                        routeNameTextView.setText("Route Name: " + routeName);
                        routeNameTextView.setTextSize(18);
                        routeNameTextView.setTextColor(getResources().getColor(R.color.black));
                        tripLayout.addView(routeNameTextView);

                        TextView timeTextView = new TextView(DriverHome.this);
                        timeTextView.setText("Time: " + time);
                        timeTextView.setTextSize(18);
                        timeTextView.setTextColor(getResources().getColor(R.color.black));
                        tripLayout.addView(timeTextView);

                        // Add the trip layout to the container
                        tripContainer.addView(tripLayout);

                        // Add a divider view
                        View divider = new View(DriverHome.this);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                1
                        );
                        params.setMargins(0, 16, 0, 16);
                        divider.setLayoutParams(params);
                        divider.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                        tripContainer.addView(divider);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
