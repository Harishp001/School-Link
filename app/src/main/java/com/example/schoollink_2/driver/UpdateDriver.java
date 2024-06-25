package com.example.schoollink_2.driver;

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

public class UpdateDriver extends AppCompatActivity {

    private EditText updateDriverName, updateContactInfo, updateLicenseNumber, updateAddress, updateAge, updateUsername, updatePassword;
    private Button updateButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_driver);

        updateDriverName = findViewById(R.id.et_update_driver_name);
        updateContactInfo = findViewById(R.id.et_update_contactInfo);
        updateLicenseNumber = findViewById(R.id.et_update_license_number);
        updateAddress = findViewById(R.id.et_update_driver_address);
        updateAge = findViewById(R.id.et_update_driver_age);
        updateUsername = findViewById(R.id.et_update_driver_username);
        updatePassword = findViewById(R.id.et_update_driver_password);
        updateButton = findViewById(R.id.updateDriverButton);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("drivers");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDriverData();
            }
        });
    }

    private void updateDriverData() {
        String driverName = updateDriverName.getText().toString().trim();
        String contactInfo = updateContactInfo.getText().toString().trim();
        String licenseNumber = updateLicenseNumber.getText().toString().trim();
        String address = updateAddress.getText().toString().trim();
        String age = updateAge.getText().toString().trim();
        String username = updateUsername.getText().toString().trim();
        String password = updatePassword.getText().toString().trim();

        if (driverName.isEmpty() || contactInfo.isEmpty() || licenseNumber.isEmpty() || address.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DriverHelperClass driverHelperClass = new DriverHelperClass(driverName, contactInfo, licenseNumber, address, age ,username, password);

        // Update data in Firebase
        databaseReference.child(driverName).setValue(driverHelperClass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(UpdateDriver.this, "Driver data updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateDriver.this, "Failed to update Driver data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
