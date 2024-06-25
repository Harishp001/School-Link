package com.example.schoollink_2.route;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.Iterator;
import java.util.List;

public class DeleteManageRoute extends AppCompatActivity {

    private RecyclerView routeRecyclerView;
    private List<RouteHelperClass> routeList;
    private RouteAdapter2 routeAdapter2;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_manage_route);

        routeRecyclerView = findViewById(R.id.routeRecyclerView);
        routeList = new ArrayList<>();
        routeAdapter2 = new RouteAdapter2(this, routeList);
        routeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        routeRecyclerView.setAdapter(routeAdapter2);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedRoutes();
            }
        });

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
                routeAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

    }

    public void onDeleteButtonClick(View view) {
        deleteSelectedRoutes();
    }

    private void deleteSelectedRoutes() {
        Iterator<RouteHelperClass> iterator = routeList.iterator();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("route");

        while (iterator.hasNext()) {
            RouteHelperClass route = iterator.next();
            if (route.isSelected()) {
                // Remove the item from Firebase
                databaseReference.child(route.getRouteName()).removeValue();
                // Remove the item from the list
                iterator.remove();
            }
        }
        routeAdapter2.notifyDataSetChanged();
    }
}
