package com.example.schoollink_2.route;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollink_2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class RouteView extends AppCompatActivity {

    private RecyclerView routeRecyclerView;
    private List<RouteHelperClass> routeList;
    private RouteAdapter routeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_view);

        routeRecyclerView = findViewById(R.id.routeRecyclerView);
        routeList = new ArrayList<>();
        routeAdapter = new RouteAdapter(this, routeList);
        routeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        routeRecyclerView.setAdapter(routeAdapter);

        // Retrieve route data from Firebase
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("route");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String tripId = snapshot.child("tripId").getValue(String.class);
                    String vehicleNo = snapshot.child("vehicleNo").getValue(String.class);
                    String routeName = snapshot.child("routeName").getValue(String.class);
                    String time = snapshot.child("time").getValue(String.class);

                    // Add route data to the list
                    routeList.add(new RouteHelperClass(tripId, vehicleNo, routeName, time));
                }
                // Notify adapter about data changes
                routeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

    }
}
