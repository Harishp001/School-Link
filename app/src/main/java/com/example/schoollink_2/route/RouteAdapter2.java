package com.example.schoollink_2.route;

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

public class RouteAdapter2 extends RecyclerView.Adapter<RouteAdapter2.RouteViewHolder> {

    private Context context;
    private List<RouteHelperClass> routeList;

    public RouteAdapter2(Context context, List<RouteHelperClass> routeList) {
        this.context = context;
        this.routeList = routeList;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_item2, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        RouteHelperClass routeHelperClass = routeList.get(position);
        holder.tripIdTextView.setText("Trip ID: " + routeHelperClass.getTripId());
        holder.routeNameTextView.setText("Route Name: " + routeHelperClass.getRouteName());
        holder.vehicleNoTextView.setText("Vehicle No: " + routeHelperClass.getVehicleNo());
        holder.timeTextView.setText("Time: " + routeHelperClass.getTime());

        holder.updateCheckBox.setChecked(routeHelperClass.isSelected());
        holder.updateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                routeHelperClass.setSelected(isChecked);
            }
        });

        if (position == routeList.size() - 1) {
            holder.lineView.setVisibility(View.GONE);
        } else {
            holder.lineView.setVisibility(View.VISIBLE);
        }
    }

    public void deleteSelectedItems() {
        List<RouteHelperClass> selectedRouteList = new ArrayList<>();
        for (RouteHelperClass route : routeList) {
            if (route.isSelected()) {
                selectedRouteList.add(route);
            }
        }
        routeList.removeAll(selectedRouteList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    public static class RouteViewHolder extends RecyclerView.ViewHolder {

        TextView routeNameTextView, tripIdTextView, vehicleNoTextView, timeTextView;
        View lineView;
        CheckBox updateCheckBox;

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            routeNameTextView = itemView.findViewById(R.id.routeNameTextView);
            tripIdTextView = itemView.findViewById(R.id.tripIdTextView);
            vehicleNoTextView = itemView.findViewById(R.id.vehicleNoTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            updateCheckBox = itemView.findViewById(R.id.updateCheckBox);
            lineView = itemView.findViewById(R.id.lineView);
        }
    }
}
