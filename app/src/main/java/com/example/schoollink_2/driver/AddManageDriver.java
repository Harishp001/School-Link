package com.example.schoollink_2.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoollink_2.R;
import com.example.schoollink_2.parent.ParentHelperClass;
import com.example.schoollink_2.parent.ParentLoginActivity;
import com.example.schoollink_2.parent.ParentSignUpActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddManageDriver extends AppCompatActivity {

    TextView tvAddNewDriver;
    EditText etDriverName, etContactInfo, etLicenseNumber, etDriverAge, etDriverAddress, etDriverUsername, etDriverPassword;
    Button btnCreateDriver;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manage_driver);

        tvAddNewDriver = findViewById(R.id.tv_add_new_driver);
        etDriverName = findViewById(R.id.et_driver_name);
        etContactInfo = findViewById(R.id.et_contactInfo);
        etLicenseNumber = findViewById(R.id.et_license_number);
        etDriverAddress = findViewById(R.id.et_driver_address);
        etDriverAge = findViewById(R.id.et_driver_age);
        etDriverUsername = findViewById(R.id.et_driver_username);
        etDriverPassword = findViewById(R.id.et_driver_password);
        btnCreateDriver = findViewById(R.id.createButton);

        btnCreateDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("drivers");

                String driverName = etDriverName.getText().toString();
                String contactInfo = etContactInfo.getText().toString();
                String licenseNumber = etLicenseNumber.getText().toString();
                String driverAddress = etDriverAddress.getText().toString();
                String driverAge = etDriverAge.getText().toString();
                String driverUsername = etDriverUsername.getText().toString();
                String driverPassword = etDriverPassword.getText().toString();

                DriverHelperClass driverHelperClass = new DriverHelperClass(driverName, contactInfo, licenseNumber, driverAddress, driverAge, driverUsername, driverPassword);
                reference.child(driverUsername).setValue(driverHelperClass);

                Toast.makeText(AddManageDriver.this, "Driver Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}