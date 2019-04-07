package com.example.harshal.afinal;

public class ticket_list {
    private String date;
    private int id;
    private String fromto;
    private int cost;

    public ticket_list(int id, String date, String fromto, int cost) {
        this.date = date;
        this.id = id;
        this.fromto = fromto;
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

    public String getFromto() {
        return fromto;
    }

    public void setFromto(String fromto) {
        this.fromto = fromto;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
