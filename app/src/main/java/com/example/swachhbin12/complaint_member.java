package com.example.swachhbin12;

public class complaint_member {
    String id1,name,phone,complaint;
    public complaint_member(){}

    public complaint_member(String id1, String name, String phone, String complaint) {
        this.id1 = id1;
        this.name = name;
        this.phone = phone;
        this.complaint = complaint;
    }

    public String getId1() {
        return id1;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getComplaint() {
        return complaint;
    }
}
