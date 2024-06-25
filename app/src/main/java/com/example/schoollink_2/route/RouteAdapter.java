package com.example.schoollink_2.route;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.schoollink_2.R;
import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {

    private Context context;
    private List<RouteHelperClass> routeList;

    public RouteAdapter(Context context, List<RouteHelperClass> routeList) {
        this.context = context;
        this.routeList = routeList;
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.route_item, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        RouteHelperClass route = routeList.get(position);
        holder.tvTripId.setText("Trip ID: " + route.getTripId());
        holder.tvVehicleNo.setText("Vehicle Number: " +route.getVehicleNo());
        holder.tvRouteName.setText("Route Name: " +route.getRouteName());
        holder.tvTime.setText("Time: " +route.getTime());

        if (position == routeList.size() - 1) {
            holder.lineView.setVisibility(View.GONE);
        } else {
            holder.lineView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    public static class RouteViewHolder extends RecyclerView.ViewHolder {

        TextView tvTripId, tvVehicleNo, tvRouteName, tvTime;
        View lineView;

        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTripId = itemView.findViewById(R.id.tripId);
            tvVehicleNo = itemView.findViewById(R.id.vehicleNo);
            tvRouteName = itemView.findViewById(R.id.routeName);
            tvTime = itemView.findViewById(R.id.time);
            lineView = itemView.findViewById(R.id.lineView);
        }
    }
}
