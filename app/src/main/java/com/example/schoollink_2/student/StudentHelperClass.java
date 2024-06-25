package com.example.schoollink_2.student;

public class StudentHelperClass {

    private String studentName;
    private String phoneNumber;
    private String address;
    private String studentClass;
    private String division;
    private boolean isSelected;

    // Default constructor required for calls to DataSnapshot.getValue(StudentHelperClass.class)
    public StudentHelperClass() {
    }

    public StudentHelperClass(String studentName, String phoneNumber, String address, String studentClass, String division) {
        this.studentName = studentName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.studentClass = studentClass;
        this.division = division;
        this.isSelected = false;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
