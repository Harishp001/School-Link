package com.example.schoollink_2.route;

public class RouteHelperClass {
    private String routeName;
    private String tripId;
    private String vehicleNo;
    private String route;
    private String time;
    private boolean isSelected;

    public RouteHelperClass() {
        // Empty constructor needed for Firebase
    }

    public RouteHelperClass(String tripId, String vehicleNo,String routeName, String time) {
        this.tripId = tripId;
        this.vehicleNo = vehicleNo;
        this.routeName = routeName;
        this.time = time;
        this.isSelected = false;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
