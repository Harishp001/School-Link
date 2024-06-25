package com.example.schoollink_2.bus;

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

public class UpdateBus extends AppCompatActivity {

    private EditText updateBusNumber, updateRoute, updateAllocatedDriver, updateDriverContactNumber, updateAge;
    private Button updateButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bus);

        updateBusNumber = findViewById(R.id.update_bus_number);
        updateRoute = findViewById(R.id.update_route);
        updateAllocatedDriver = findViewById(R.id.update_allocated_driver);
        updateDriverContactNumber = findViewById(R.id.update_driver_contact_number);
        updateAge = findViewById(R.id.update_age);
        updateButton = findViewById(R.id.updateButton);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("bus");

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBusData();
            }
        });
    }

    private void updateBusData() {
        String busNumber = updateBusNumber.getText().toString().trim();
        String route = updateRoute.getText().toString().trim();
        String allocatedDriver = updateAllocatedDriver.getText().toString().trim();
        String driverContactNumber = updateDriverContactNumber.getText().toString().trim();
        String age = updateAge.getText().toString().trim();

        if (busNumber.isEmpty() || route.isEmpty() || allocatedDriver.isEmpty() || driverContactNumber.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        BusHelperClass busHelperClass = new BusHelperClass(busNumber, route, allocatedDriver, driverContactNumber, age);

        // Update data in Firebase
        databaseReference.child(busNumber).setValue(busHelperClass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(UpdateBus.this, "Bus data updated successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateBus.this, "Failed to update bus data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
