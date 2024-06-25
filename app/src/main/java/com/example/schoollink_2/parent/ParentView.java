package com.example.schoollink_2.parent;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoollink_2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ParentView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ParentAdapter adapter;
    private List<ParentHelperClass> parentList;

    private DatabaseReference parentRef;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view);

        recyclerView = findViewById(R.id.parentRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        parentList = new ArrayList<>();
        adapter = new ParentAdapter(this, parentList);
        recyclerView.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        parentRef = FirebaseDatabase.getInstance().getReference().child("parents");

        parentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                parentList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ParentHelperClass parent = snapshot.getValue(ParentHelperClass.class);
                    parentList.add(parent);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ParentView.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
