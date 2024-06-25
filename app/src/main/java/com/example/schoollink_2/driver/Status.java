package com.example.schoollink_2.driver;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.schoollink_2.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Status extends AppCompatActivity {

    private LinearLayout studentListContainer;
    private List<CheckBox> checkBoxList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        // Initialize views
        studentListContainer = findViewById(R.id.studentListContainer);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Add checkboxes for each selected student
        addCheckBoxesForStudents();

        // Handle "Reached" button click
        findViewById(R.id.boardButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove selected students
                removeSelectedStudents();
            }
        });
    }

    // Method to add checkboxes for each selected student
    private void addCheckBoxesForStudents() {
        // Retrieve selected students from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("selected_students", MODE_PRIVATE);
        Set<String> selectedStudentsSet = sharedPreferences.getStringSet("selected_students_set", new HashSet<>());

        // Add checkboxes for each selected student
        for (String student : selectedStudentsSet) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(student);
            checkBoxList.add(checkBox);
            studentListContainer.addView(checkBox);
        }
    }

    // Method to remove selected students
    private void removeSelectedStudents() {
        SharedPreferences sharedPreferences = getSharedPreferences("selected_students", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> selectedStudentsSet = new HashSet<>(sharedPreferences.getStringSet("selected_students_set", new HashSet<>()));

        // Iterate through checkboxes and remove selected students
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                studentListContainer.removeView(checkBox);
                selectedStudentsSet.remove(checkBox.getText().toString());
            }
        }

        // Save updated student set to SharedPreferences
        editor.putStringSet("selected_students_set", selectedStudentsSet);
        editor.apply();

        // Clear the checkbox list
        checkBoxList.clear();

        // Show toast message indicating successful removal
        Toast.makeText(this, "Student Reached", Toast.LENGTH_SHORT).show();

        // Refresh the list of checkboxes
        addCheckBoxesForStudents();
    }
}
