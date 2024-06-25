package com.example.schoollink_2.driver;

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

public class DeleteManageDriver extends AppCompatActivity {

    private RecyclerView driverRecyclerView;
    private List<DriverHelperClass> driverList;
    private DriverAdapter2 driverAdapter2;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_manage_driver);

        driverRecyclerView = findViewById(R.id.driverRecyclerView);
        driverList = new ArrayList<>();
        driverAdapter2 = new DriverAdapter2(this, driverList);
        driverRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        driverRecyclerView.setAdapter(driverAdapter2);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedDrivers();
            }
        });

        // Retrieve driver data from Firebase
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("drivers");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                driverList.clear(); // Clear existing data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    DriverHelperClass driver = snapshot.getValue(DriverHelperClass.class);
                    driverList.add(driver);
                }
                // Notify adapter about data changes
                driverAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    public void onDeleteButtonClick(View view) {
        deleteSelectedDrivers();
    }

    private void deleteSelectedDrivers() {
        Iterator<DriverHelperClass> iterator = driverList.iterator();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("drivers");

        while (iterator.hasNext()) {
            DriverHelperClass driver = iterator.next();
            if (driver.isSelected()) {
                // Remove the item from Firebase
                databaseReference.child(driver.getDriverName()).removeValue();
                // Remove the item from the list
                iterator.remove();
            }
        }
        driverAdapter2.notifyDataSetChanged();
    }
}
