package com.example.schoollink_2.student;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoollink_2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddManageStudent extends AppCompatActivity {

    TextView tvAddNewStudent;
    EditText etStudentName, etPhoneNumber, etAddress, etClass, etDivision;
    Button btnCreateStudent;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manage_student);

        tvAddNewStudent = findViewById(R.id.tv_add_new_student);
        etStudentName = findViewById(R.id.add_student_name);
        etPhoneNumber = findViewById(R.id.add_phone_number);
        etAddress = findViewById(R.id.add_address);
        etClass = findViewById(R.id.add_class);
        etDivision = findViewById(R.id.add_division);
        btnCreateStudent = findViewById(R.id.add_student_button);

        btnCreateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("students");

                String studentName = etStudentName.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                String address = etAddress.getText().toString();
                String studentClass = etClass.getText().toString();
                String division = etDivision.getText().toString();

                StudentHelperClass studentHelperClass = new StudentHelperClass(studentName, phoneNumber, address, studentClass, division);
                reference.child(studentName).setValue(studentHelperClass);

                Toast.makeText(AddManageStudent.this, "Student Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}