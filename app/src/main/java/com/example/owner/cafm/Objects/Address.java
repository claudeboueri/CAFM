package com.example.owner.cafm.Objects;


public class Address {

    private String Building;
    private String Project;
    private String Room;
    private String Floor;

    public Address(String building, String project, String room, String floor) {
        Building = building;
        Project = project;
        Room = room;
        Floor = floor;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

}
