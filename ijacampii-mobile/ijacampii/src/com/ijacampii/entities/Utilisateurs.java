package com.ijacampii.entities;

public class Utilisateurs {

    private int id;
    private String nom;

    public Utilisateurs() {
    }

    public Utilisateurs(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Utilisateurs(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}