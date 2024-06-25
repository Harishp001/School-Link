package com.example.schoollink_2.driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollink_2.R;
import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    private Context context;
    private ArrayList<DriverHelperClass> driverList;

    public DriverAdapter(Context context, ArrayList<DriverHelperClass> driverList) {
        this.context = context;
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_item, parent, false);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        DriverHelperClass driver = driverList.get(position);
        holder.textViewName.setText("Name: " + driver.getDriverName());
        holder.textViewContact.setText("Contact: " + driver.getContactInfo());
        holder.textViewLicense.setText("License: " + driver.getLicenseNumber());
        holder.textViewAddress.setText("Address: " + driver.getDriverAddress());
        holder.textViewAge.setText("Age: " + driver.getDriverAge());
        holder.textViewUsername.setText("Username: " + driver.getDriverUsername());
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class DriverViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewContact, textViewLicense, textViewAddress, textViewAge, textViewUsername;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewContact = itemView.findViewById(R.id.textViewContact);
            textViewLicense = itemView.findViewById(R.id.textViewLicense);
            textViewAddress = itemView.findViewById(R.id.textViewAddress);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
        }
    }
}
