package com.example.schoollink_2.bus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollink_2.R;
import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {

    private Context context;
    private List<BusHelperClass> busList;


    public BusAdapter(Context context, List<BusHelperClass> busList) {
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bus_card, parent, false);
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

        if (position == busList.size() - 1) {
            holder.lineView.setVisibility(View.GONE);
        } else {
            holder.lineView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public static class BusViewHolder extends RecyclerView.ViewHolder {

        TextView busNumberTextView, routeTextView, allocatedDriverTextView, driverContactNumberTextView, ageTextView;
        View lineView;

        public BusViewHolder(@NonNull View itemView) {
            super(itemView);
            busNumberTextView = itemView.findViewById(R.id.busNumberTextView);
            routeTextView = itemView.findViewById(R.id.routeTextView);
            allocatedDriverTextView = itemView.findViewById(R.id.allocatedDriverTextView);
            driverContactNumberTextView = itemView.findViewById(R.id.driverContactNumberTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            lineView = itemView.findViewById(R.id.lineView);
        }
    }
}
