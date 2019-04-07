package com.example.harshal.afinal;

public class RevList {
    private String date;
    private int id;
    private String name;
    private int cost;

    public RevList() {
    }

    public RevList(int id, String date, String name, int cost) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.cost = cost;

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

    public void setName(String Name) {
        this.name = Name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

}
