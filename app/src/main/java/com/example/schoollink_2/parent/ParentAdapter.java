package com.example.schoollink_2.parent;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schoollink_2.R;

import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ParentViewHolder> {

    private Context context;
    private List<ParentHelperClass> parentList;

    public ParentAdapter(Context context, List<ParentHelperClass> parentList) {
        this.context = context;
        this.parentList = parentList;
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_item, parent, false);
        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        ParentHelperClass parent = parentList.get(position);
        holder.bind(parent);
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView, phoneTextView, addressTextView, emailTextView, usernameTextView, passwordTextView;

        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            phoneTextView = itemView.findViewById(R.id.phoneTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
        }

        public void bind(ParentHelperClass parent) {
            nameTextView.setText("Name: " + parent.getName());
            phoneTextView.setText("Phone: " + parent.getPhone());
            addressTextView.setText("Address: " + parent.getAddress());
            emailTextView.setText("Email: " + parent.getEmail());
            usernameTextView.setText("Username: " + parent.getUsername());
        }
    }
}
