package com.example.schoollink_2.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoollink_2.R;

import java.util.List;

public class StudentAdapter2 extends RecyclerView.Adapter<StudentAdapter2.StudentViewHolder> {

    private Context context;
    private List<StudentHelperClass> studentList;

    public StudentAdapter2(Context context, List<StudentHelperClass> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentHelperClass student = studentList.get(position);
        holder.studentNameTextView.setText(student.getStudentName());
        holder.phoneNumberTextView.setText(student.getPhoneNumber());
        holder.addressTextView.setText(student.getAddress());
        holder.studentClassTextView.setText(student.getStudentClass());
        holder.divisionTextView.setText(student.getDivision());

        holder.deleteCheckBox.setOnCheckedChangeListener(null);
        holder.deleteCheckBox.setChecked(student.isSelected());

        holder.deleteCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            student.setSelected(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView studentNameTextView, phoneNumberTextView, addressTextView, studentClassTextView, divisionTextView;
        CheckBox deleteCheckBox;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            studentClassTextView = itemView.findViewById(R.id.studentClassTextView);
            divisionTextView = itemView.findViewById(R.id.divisionTextView);
            deleteCheckBox = itemView.findViewById(R.id.deleteCheckBox);
        }
    }
}
