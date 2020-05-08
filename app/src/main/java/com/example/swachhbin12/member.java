package com.example.swachhbin12;
public class member {
    String id;
    String name;
    String address;
    String phone;
    String waste;
    String zip;
    public member(){}

    public member(String id, String name, String address, String phone, String waste, String zip) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.waste = waste;
        this.zip = zip;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWaste() {
        return waste;
    }

    public String getZip() {
        return zip;
    }
}
