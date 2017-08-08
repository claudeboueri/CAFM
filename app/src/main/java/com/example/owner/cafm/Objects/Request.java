package com.example.owner.cafm.Objects;



public class Request {

    private int Emergency;
    private int Type;
    private String Description;
    private String Photo;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getEmergency() {
        return Emergency;
    }

    public void setEmergency(int emergency) {
        Emergency = emergency;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
