package com.example.schoollink_2.driver;

public class DriverHelperClass {

    private String driverName;
    private String contactInfo;
    private String licenseNumber;
    private String driverAddress;
    private String driverAge;
    private String driverUsername;
    private String driverPassword;
    private boolean isSelected;

    public DriverHelperClass() {
    }

    public DriverHelperClass(String driverName, String contactInfo, String licenseNumber, String driverAddress, String driverAge, String driverUsername, String driverPassword) {
        this.driverName = driverName;
        this.contactInfo = contactInfo;
        this.licenseNumber = licenseNumber;
        this.driverAddress = driverAddress;
        this.driverAge = driverAge;
        this.driverUsername = driverUsername;
        this.driverPassword = driverPassword;
        this.isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(String driverAge) {
        this.driverAge = driverAge;
    }

    public String getDriverUsername() {
        return driverUsername;
    }

    public void setDriverUsername(String driverUsername) {
        this.driverUsername = driverUsername;
    }

    public String getDriverPassword() {
        return driverPassword;
    }

    public void setDriverPassword(String driverPassword) {
        this.driverPassword = driverPassword;
    }
}
