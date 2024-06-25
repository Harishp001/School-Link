package com.example.schoollink_2.bus;

public class BusHelperClass {

    private String busNumber;
    private String route;
    private String allocatedDriver;
    private String driverContactNumber;
    private String age;
    private boolean isSelected;


    public BusHelperClass(String busNumber, String route, String allocatedDriver, String driverContactNumber, String age) {
        this.busNumber = busNumber;
        this.route = route;
        this.allocatedDriver = allocatedDriver;
        this.driverContactNumber = driverContactNumber;
        this.age = age;
        this.isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getAllocatedDriver() {
        return allocatedDriver;
    }

    public void setAllocatedDriver(String allocatedDriver) {
        this.allocatedDriver = allocatedDriver;
    }

    public String getDriverContactNumber() {
        return driverContactNumber;
    }

    public void setDriverContactNumber(String driverContactNumber) {
        this.driverContactNumber = driverContactNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
