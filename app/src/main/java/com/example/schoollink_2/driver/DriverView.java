package com.example.schoollink_2.driver;

import android.os.Bundle;
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

public class DriverView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<DriverHelperClass> driverList;
    private DriverAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view);

        recyclerView = findViewById(R.id.driverRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        driverList = new ArrayList<>();
        adapter = new DriverAdapter(this, driverList);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("drivers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                driverList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String driverName = snapshot.child("driverName").getValue(String.class);
                    String contactInfo = snapshot.child("contactInfo").getValue(String.class);
                    String licenseNumber = snapshot.child("licenseNumber").getValue(String.class);
                    String driverAddress = snapshot.child("driverAddress").getValue(String.class);
                    String driverAge = snapshot.child("driverAge").getValue(String.class);
                    String driverUsername = snapshot.child("driverUsername").getValue(String.class);
                    String driverPassword = snapshot.child("driverPassword").getValue(String.class);


                    DriverHelperClass driver = new DriverHelperClass(driverName, contactInfo, licenseNumber, driverAddress, driverAge,driverUsername, driverPassword );
                    driverList.add(driver);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}
