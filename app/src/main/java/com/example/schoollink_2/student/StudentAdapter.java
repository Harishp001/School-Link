package com.example.schoollink_2.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollink_2.R;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private ArrayList<StudentHelperClass> studentList;

    public StudentAdapter(Context context, ArrayList<StudentHelperClass> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentHelperClass student = studentList.get(position);
        holder.studentName.setText("Student Name: " + student.getStudentName());
        holder.phoneNumber.setText("Phone Number: " + student.getPhoneNumber());
        holder.address.setText("Address: " + student.getAddress());
        holder.studentClass.setText("Class: " + student.getStudentClass());
        holder.division.setText("Division: " + student.getDivision());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView studentName, phoneNumber, address, studentClass, division;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            address = itemView.findViewById(R.id.address);
            studentClass = itemView.findViewById(R.id.studentClass);
            division = itemView.findViewById(R.id.division);
        }
    }
}
