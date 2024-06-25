package com.example.schoollink_2.parent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentHome extends AppCompatActivity {

    TextView studentNameTextView, routeNameTextView, timeTextView, vehicleNoTextView, tripIdTextView;
    DatabaseReference databaseReference;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);

        studentNameTextView = findViewById(R.id.studentNameTextView);
        routeNameTextView = findViewById(R.id.routeNameTextView);
        timeTextView = findViewById(R.id.timeTextView);
        vehicleNoTextView = findViewById(R.id.vehicleNoTextView);
        tripIdTextView = findViewById(R.id.tripIdTextView);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.post(() -> bottomNavigationView.setSelectedItemId(R.id.bottom_home));

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home) {
                return true;
            } else if (itemId == R.id.bottom_contact) {
                startActivity(new Intent(getApplicationContext(), ParentContact.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (itemId == R.id.bottom_notification) {
                startActivity(new Intent(getApplicationContext(), ParentNotification.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

        SharedPreferences sharedPreferences = getSharedPreferences("SelectedStudent", MODE_PRIVATE);
        String selectedStudentName = sharedPreferences.getString("studentName", null);
        String studentAddress = sharedPreferences.getString("address", null);

        if (selectedStudentName != null) {
            studentNameTextView.setText(selectedStudentName);
            fetchRouteData(studentAddress);
        }
    }

    public void onManageParentHomeClick(View view) {
        // Handle the click event here
        startActivity(new Intent(getApplicationContext(), ParentMapActivity.class));
    }

    private void fetchRouteData(String studentAddress) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("route");
        databaseReference.orderByChild("routeName").equalTo(studentAddress).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String routeName = snapshot.child("routeName").getValue(String.class);
                        String time = snapshot.child("time").getValue(String.class);
                        String vehicleNo = snapshot.child("vehicleNo").getValue(String.class);
                        String tripId = snapshot.child("tripId").getValue(String.class);

                        routeNameTextView.setText("Pick at: " + routeName);
                        timeTextView.setText("Pick up: " + time);
                        vehicleNoTextView.setText(vehicleNo);
                        tripIdTextView.setText("ID: " + tripId);
                    }
                } else {
                    Toast.makeText(ParentHome.this, "No route found for this address", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ParentHome.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
