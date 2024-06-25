package com.example.schoollink_2.parent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoollink_2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParentChildsList extends AppCompatActivity {

    ListView studentListView;
    EditText studentSearchBox;
    Button goToHomeButton;

    List<String> studentList = new ArrayList<>();
    Map<String, Map<String, String>> studentDataMap = new HashMap<>();
    ArrayAdapter<String> adapter;

    DatabaseReference databaseReference;

    String selectedStudentName;
    Map<String, String> selectedStudentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_childs_list);

        studentListView = findViewById(R.id.student_list_view);
        studentSearchBox = findViewById(R.id.student_search_box);
        goToHomeButton = findViewById(R.id.goto_home_button);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("students");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        studentListView.setAdapter(adapter);

        studentSearchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateStudentListFromDatabase();
            }
        });

        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedStudentInfo = studentList.get(position);
                String[] studentInfo = selectedStudentInfo.split("\n");
                selectedStudentName = studentInfo[0].substring(studentInfo[0].indexOf(":") + 2);
                selectedStudentData = studentDataMap.get(selectedStudentName);

                studentSearchBox.setText(selectedStudentName);

                SharedPreferences sharedPreferences = getSharedPreferences("ParentPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("selectedStudentName", selectedStudentName);
                if (selectedStudentData != null) {
                    editor.putString("studentClass", selectedStudentData.get("studentClass"));
                    editor.putString("address", selectedStudentData.get("address"));
                    editor.putString("division", selectedStudentData.get("division"));
                }
                editor.apply();

                navigateToParentHome();
            }
        });

        goToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToParentHome();
            }
        });
    }

    private void populateStudentListFromDatabase() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                studentDataMap.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String studentName = snapshot.child("studentName").getValue(String.class);
                    String studentClass = snapshot.child("studentClass").getValue(String.class);
                    String studentAddress = snapshot.child("address").getValue(String.class);
                    String studentDivision = snapshot.child("division").getValue(String.class);

                    String studentInfo = "Name: " + studentName + "\nClass: " + studentClass + "\nAddress: " + studentAddress;
                    studentList.add(studentInfo);

                    Map<String, String> studentData = new HashMap<>();
                    studentData.put("studentClass", studentClass);
                    studentData.put("address", studentAddress);
                    studentData.put("division", studentDivision);
                    studentDataMap.put(studentName, studentData);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ParentChildsList.this, "Failed to retrieve data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToParentHome() {
        Intent intent = new Intent(ParentChildsList.this, ParentHome.class);
        startActivity(intent);
        finish();
    }
}
