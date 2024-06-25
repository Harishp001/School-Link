package com.example.schoollink_2.bus;

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

public class AddManageBus extends AppCompatActivity {

    TextView tvAddNewBus;
    EditText etBusNumber, etRoute, etAllocatedDriver, etDriverContactNumber, etAge;
    Button btnCreate;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manage_bus);

        tvAddNewBus = findViewById(R.id.tv_add_new_bus);
        etBusNumber = findViewById(R.id.et_bus_number);
        etRoute = findViewById(R.id.et_route);
        etAllocatedDriver = findViewById(R.id.et_allocated_driver);
        etDriverContactNumber = findViewById(R.id.et_driver_contact_number);
        etAge = findViewById(R.id.et_age);
        btnCreate = findViewById(R.id.createButton);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("bus");

                String busNumber = etBusNumber.getText().toString();
                String route = etRoute.getText().toString();
                String allocateDriver = etAllocatedDriver.getText().toString();
                String driverNumber = etDriverContactNumber.getText().toString();
                String age = etAge.getText().toString();

                BusHelperClass busHelperClass = new BusHelperClass(busNumber, route, allocateDriver, driverNumber, age);
                reference.child(busNumber).setValue(busHelperClass);

                Toast.makeText(AddManageBus.this, "Bus Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}