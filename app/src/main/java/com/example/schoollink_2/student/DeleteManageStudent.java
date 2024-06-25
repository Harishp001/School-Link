package com.example.schoollink_2.student;

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

public class DeleteManageStudent extends AppCompatActivity {

    private RecyclerView studentRecyclerView;
    private List<StudentHelperClass> studentList;
    private StudentAdapter2 studentAdapter2;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_manage_student);

        studentRecyclerView = findViewById(R.id.studentRecyclerView);
        studentList = new ArrayList<>();
        studentAdapter2 = new StudentAdapter2(this, studentList);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentRecyclerView.setAdapter(studentAdapter2);

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedStudents();
            }
        });

        // Retrieve student data from Firebase
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("students");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear(); // Clear existing data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    StudentHelperClass student = snapshot.getValue(StudentHelperClass.class);
                    studentList.add(student);
                }
                // Notify adapter about data changes
                studentAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }

    public void onDeleteButtonClick(View view) {
        deleteSelectedStudents();
    }

    private void deleteSelectedStudents() {
        Iterator<StudentHelperClass> iterator = studentList.iterator();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("students");

        while (iterator.hasNext()) {
            StudentHelperClass student = iterator.next();
            if (student.isSelected()) {
                // Remove the item from Firebase
                databaseReference.child(student.getStudentName()).removeValue();
                // Remove the item from the list
                iterator.remove();
            }
        }
        studentAdapter2.notifyDataSetChanged();
    }
}
