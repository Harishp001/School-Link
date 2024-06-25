package com.example.schoollink_2.bus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class DeleteManageBus extends AppCompatActivity {

    private RecyclerView busRecyclerView;
    private List<BusHelperClass> busList;
    private BusAdapter2 busAdapter2;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_manage_bus);

        busRecyclerView = findViewById(R.id.busRecyclerView);
        busList = new ArrayList<>();
        busAdapter2 = new BusAdapter2(this, busList);
        busRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        busRecyclerView.setAdapter(busAdapter2);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedBuses();
            }
        });

        // Retrieve bus data from Firebase
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("bus");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String busNumber = snapshot.child("busNumber").getValue(String.class);
                    String route = snapshot.child("route").getValue(String.class);
                    String allocatedDriver = snapshot.child("allocatedDriver").getValue(String.class);
                    String driverContactNumber = snapshot.child("driverContactNumber").getValue(String.class);
                    String age = snapshot.child("age").getValue(String.class);

                    // Add bus data to the list
                    busList.add(new BusHelperClass(busNumber, route, allocatedDriver, driverContactNumber, age));
                }
                // Notify adapter about data changes
                busAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });

    }

        public void onDeleteButtonClick(View view) {
            deleteSelectedBuses();
        }

        private void deleteSelectedBuses() {
            Iterator<BusHelperClass> iterator = busList.iterator();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("bus");

            while (iterator.hasNext()) {
                BusHelperClass bus = iterator.next();
                if (bus.isSelected()) {
                    // Remove the item from Firebase
                    databaseReference.child(bus.getBusNumber()).removeValue();
                    // Remove the item from the list
                    iterator.remove();
                }
            }
            busAdapter2.notifyDataSetChanged();

    }
}
