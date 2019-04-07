package com.example.harshal.afinal;

public class Complaint_list {
    private String date;
    private int id;
    private String name;
    private String complaint;

    public Complaint_list(int id,String date, String name, String complaint) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.complaint = complaint;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }
}
