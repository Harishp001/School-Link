package com.example.schoollink_2.driver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.schoollink_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TripStudent extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayout studentListContainer;
    private String routeName;
    private DrawerLayout drawer;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<String> selectedStudents = new ArrayList<>();
    private Button boardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_student);

        // Initialize views
        studentListContainer = findViewById(R.id.studentListContainer);

        // Setup Toolbar and DrawerLayout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Get route name from intent extra
        routeName = getIntent().getStringExtra("routeName");

        // Retrieve student details from Firebase Realtime Database based on route name
        DatabaseReference studentRef = FirebaseDatabase.getInstance().getReference().child("students");
        studentRef.orderByChild("address").equalTo(routeName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if data exists
                if (dataSnapshot.exists()) {
                    // Iterate through each student data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Get student details
                        String studentName = snapshot.child("studentName").getValue(String.class);
                        String studentAddress = snapshot.child("address").getValue(String.class);
                        String studentClass = snapshot.child("studentClass").getValue(String.class);
                        String studentDivision = snapshot.child("division").getValue(String.class);

                        // Create a RelativeLayout for each student entry
                        RelativeLayout studentLayout = new RelativeLayout(TripStudent.this);
                        studentLayout.setBackgroundResource(R.drawable.trip_details_background);
                        studentLayout.setPadding(16, 16, 16, 16);

                        // Create CheckBox
                        CheckBox checkBox = new CheckBox(TripStudent.this);
                        checkBox.setId(View.generateViewId());
                        checkBox.setTag(studentName); // Store student name in tag for later retrieval
                        RelativeLayout.LayoutParams checkBoxParams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        checkBoxParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                        checkBoxParams.addRule(RelativeLayout.CENTER_VERTICAL);
                        checkBox.setLayoutParams(checkBoxParams);
                        studentLayout.addView(checkBox);
                        checkBoxList.add(checkBox);

                        // Create LinearLayout for student details
                        LinearLayout studentDetailsLayout = new LinearLayout(TripStudent.this);
                        studentDetailsLayout.setOrientation(LinearLayout.VERTICAL);
                        RelativeLayout.LayoutParams detailsParams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        detailsParams.addRule(RelativeLayout.END_OF, checkBox.getId());
                        detailsParams.addRule(RelativeLayout.CENTER_VERTICAL);
                        detailsParams.setMargins(10, 0, 0, 0);
                        studentDetailsLayout.setLayoutParams(detailsParams);

                        // Add TextViews to student details layout
                        TextView studentNameTextView = new TextView(TripStudent.this);
                        studentNameTextView.setText("Student Name: " + studentName);
                        studentDetailsLayout.addView(studentNameTextView);

                        TextView studentAddressTextView = new TextView(TripStudent.this);
                        studentAddressTextView.setText("Address: " + studentAddress);
                        studentDetailsLayout.addView(studentAddressTextView);

                        TextView studentClassTextView = new TextView(TripStudent.this);
                        studentClassTextView.setText("Class: " + studentClass);
                        studentDetailsLayout.addView(studentClassTextView);

                        TextView studentDivisionTextView = new TextView(TripStudent.this);
                        studentDivisionTextView.setText("Division: " + studentDivision);
                        studentDetailsLayout.addView(studentDivisionTextView);

                        // Add student details layout to student layout
                        studentLayout.addView(studentDetailsLayout);

                        // Add student layout to studentListContainer
                        studentListContainer.addView(studentLayout);

                        // Add a divider view
                        View divider = new View(TripStudent.this);
                        LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                1);
                        dividerParams.setMargins(0, 16, 0, 16);
                        divider.setLayoutParams(dividerParams);
                        divider.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                        studentListContainer.addView(divider);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

        // Initialize the Board button
        boardButton = findViewById(R.id.boardButton);
        boardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedStudents.clear();
                for (CheckBox checkBox : checkBoxList) {
                    if (checkBox.isChecked()) {
                        selectedStudents.add((String) checkBox.getTag()); // Assuming the student name is stored in the tag
                    }
                }

                // Save selected students to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("selected_students", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("selected_students_set", new HashSet<>(selectedStudents));
                editor.apply();

                // Show a message indicating that the students have been boarded
                Toast.makeText(TripStudent.this, "Student Boarded", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_start_trip) {
            // Handle the start trip action
            // You can start a new activity or do any operation you want
            Intent intent = new Intent(TripStudent.this, MapActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_status) {
            // Handle the status action
            // You can start a new activity or do any operation you want
            Intent intent = new Intent(TripStudent.this, Status.class);
            startActivity(intent);
        } else if (id == R.id.nav_notification) {
            // Handle the notification action
            // You can start a new activity or do any operation you want
            Intent intent = new Intent(TripStudent.this, DriverNotification.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
