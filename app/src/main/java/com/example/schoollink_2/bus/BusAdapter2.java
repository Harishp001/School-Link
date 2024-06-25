package com.example.schoollink_2.bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollink_2.R;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter2 extends RecyclerView.Adapter<BusAdapter2.BusViewHolder> {

    private Context context;
    private List<BusHelperClass> busList;


    public BusAdapter2(Context context, List<BusHelperClass> busList) {
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bus_card2, parent, false);
        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {
        BusHelperClass busHelperClass = busList.get(position);
        holder.busNumberTextView.setText("Bus Number: " + busHelperClass.getBusNumber());
        holder.routeTextView.setText("Route: " + busHelperClass.getRoute());
        holder.allocatedDriverTextView.setText("Allocated Driver: " + busHelperClass.getAllocatedDriver());
        holder.driverContactNumberTextView.setText("Driver Contact Number: " + busHelperClass.getDriverContactNumber());
        holder.ageTextView.setText("Age: " + busHelperClass.getAge());

        holder.updateCheckBox.setChecked(busHelperClass.isSelected());
        holder.updateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                busHelperClass.setSelected(isChecked);
            }
        });

        if (position == busList.size() - 1) {
            holder.lineView.setVisibility(View.GONE);
        } else {
            holder.lineView.setVisibility(View.VISIBLE);
        }
    }

    public void deleteSelectedItems() {
        List<BusHelperClass> selectedBusList = new ArrayList<>();
        for (BusHelperClass bus : busList) {
            if (bus.isSelected()) {
                selectedBusList.add(bus);
            }
        }
        busList.removeAll(selectedBusList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public static class BusViewHolder extends RecyclerView.ViewHolder {

        TextView busNumberTextView, routeTextView, allocatedDriverTextView, driverContactNumberTextView, ageTextView;
        View lineView;
        CheckBox updateCheckBox;

        public BusViewHolder(@NonNull View itemView) {
            super(itemView);
            busNumberTextView = itemView.findViewById(R.id.busNumberTextView);
            routeTextView = itemView.findViewById(R.id.routeTextView);
            allocatedDriverTextView = itemView.findViewById(R.id.allocatedDriverTextView);
            driverContactNumberTextView = itemView.findViewById(R.id.driverContactNumberTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            updateCheckBox = itemView.findViewById(R.id.updateCheckBox);
            lineView = itemView.findViewById(R.id.lineView);
        }
    }
}
