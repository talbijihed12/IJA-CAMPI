package com.ijacampii.entities;

public class Hebergement {

    private int id;
    private String name;

    public Hebergement() {
    }

    public Hebergement(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Hebergement(String name) {
        this.name = name;
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


}