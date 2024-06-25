package com.example.schoollink_2.route;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.schoollink_2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddManageRoute extends AppCompatActivity {

    TextView tvAddNewRoute;
    EditText etTripId, etVehicleNo, etRouteName, etTime;
    Button btnCreate;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_manage_route);

        tvAddNewRoute = findViewById(R.id.tv_add_new_route);
        etTripId = findViewById(R.id.et_trip_id);
        etVehicleNo = findViewById(R.id.et_vehicle_no);
        etRouteName = findViewById(R.id.et_route_name);
        etTime = findViewById(R.id.et_time);
        btnCreate = findViewById(R.id.createButton);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("route");

                String tripId = etTripId.getText().toString();
                String vehicleNo = etVehicleNo.getText().toString();
                String routeName = etRouteName.getText().toString();
                String time = etTime.getText().toString();

                RouteHelperClass routeHelperClass = new RouteHelperClass(tripId, vehicleNo, routeName, time);
                reference.child(tripId).setValue(routeHelperClass);

                Toast.makeText(AddManageRoute.this, "Route Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
