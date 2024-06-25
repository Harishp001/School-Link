package com.example.schoollink_2.route;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.schoollink_2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateRoute extends AppCompatActivity {

    private EditText updateRouteName, updateTripId, updateVehicleNo, updateTime;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_route);

        updateRouteName = findViewById(R.id.update_route_name);
        updateTripId = findViewById(R.id.update_trip_id);
        updateVehicleNo = findViewById(R.id.update_vehicle_no);
        updateTime = findViewById(R.id.update_time);
        updateButton = findViewById(R.id.updateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRouteData();
            }
        });
    }

    private void updateRouteData() {
        String routeName = updateRouteName.getText().toString().trim();
        String tripId = updateTripId.getText().toString().trim();
        String vehicleNo = updateVehicleNo.getText().toString().trim();
        String time = updateTime.getText().toString().trim();

        // Update data in Firebase database
        DatabaseReference routeRef = FirebaseDatabase.getInstance().getReference().child("route");
        String routeId = routeRef.push().getKey();
        RouteHelperClass route = new RouteHelperClass( tripId, vehicleNo, routeName, time);
        routeRef.child(tripId).setValue(route);

        // Clear EditText fields after updating
        updateRouteName.setText("");
        updateTripId.setText("");
        updateVehicleNo.setText("");
        updateTime.setText("");
    }
}
