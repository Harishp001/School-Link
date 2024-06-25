package com.example.schoollink_2.driver;

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

public class DriverAdapter2 extends RecyclerView.Adapter<DriverAdapter2.ViewHolder> {

    private Context context;
    private List<DriverHelperClass> driverList;

    public DriverAdapter2(Context context, List<DriverHelperClass> driverList) {
        this.context = context;
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_driver, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DriverHelperClass driver = driverList.get(position);

        holder.driverNameTextView.setText(driver.getDriverName());
        holder.driveContactTextView.setText(driver.getContactInfo());
        holder.driverLicenseTextView.setText(driver.getLicenseNumber());
        holder.driverAddressTextView.setText(driver.getDriverAddress());
        holder.driverAgeTextView.setText(driver.getDriverAge());

        holder.updateCheckBox.setChecked(driver.isSelected());

        holder.updateCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driver.setSelected(((CheckBox) v).isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView driverNameTextView, driveContactTextView, driverLicenseTextView, driverAddressTextView, driverAgeTextView;
        public CheckBox updateCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            driverNameTextView = itemView.findViewById(R.id.driverNameTextView);
            driveContactTextView = itemView.findViewById(R.id.driveContactTextView);
            driverLicenseTextView = itemView.findViewById(R.id.driverLicenseTextView);
            driverAddressTextView = itemView.findViewById(R.id.driverAddressTextView);
            driverAgeTextView = itemView.findViewById(R.id.driverAgeTextView);
            updateCheckBox = itemView.findViewById(R.id.updateCheckBox);
        }
    }
}
