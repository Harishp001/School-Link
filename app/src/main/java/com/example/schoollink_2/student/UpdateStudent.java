package com.example.schoollink_2.student;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.schoollink_2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateStudent extends AppCompatActivity {

    private EditText updateStudentName, updatePhoneNumber, updateAddress, updateStudentClass, updateDivision;
    private Button updateButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        updateStudentName = findViewById(R.id.et_update_student_name);
        updatePhoneNumber = findViewById(R.id.et_update_phone_number);
        updateAddress = findViewById(R.id.et_update_address);
        updateStudentClass = findViewById(R.id.et_update_student_class);
        updateDivision = findViewById(R.id.et_update_division);
        updateButton = findViewById(R.id.updateStudentButton);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("students");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudentData();
            }
        });
    }

    private void updateStudentData() {
        String studentName = updateStudentName.getText().toString().trim();
        String phoneNumber = updatePhoneNumber.getText().toString().trim();
        String address = updateAddress.getText().toString().trim();
        String studentClass = updateStudentClass.getText().toString().trim();
        String division = updateDivision.getText().toString().trim();

        if (studentName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty() || studentClass.isEmpty() || division.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        StudentHelperClass studentHelperClass = new StudentHelperClass(studentName, phoneNumber, address, studentClass, division);

        // Update data in Firebase
        databaseReference.child(studentName).setValue(studentHelperClass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(UpdateStudent.this, "Student data updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateStudent.this, "Failed to update student data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
