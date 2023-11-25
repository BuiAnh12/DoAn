package com.model;
public class Staff {
    private int staffId;
    private String name;
    private int age;
    private String email;
    private String address;
    
    public Staff(int staffId, String name, int age, String email, String address) {
        this.staffId = staffId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}