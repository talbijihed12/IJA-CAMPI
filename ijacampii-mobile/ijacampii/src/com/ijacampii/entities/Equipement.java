/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijacampii.entities;

/**
 *
 * @author brahim
 */
public class Equipement {

    private int id;
    private float prix;
    private String nom, marque, description, photo, categorie;

    public Equipement(int id, float prix, String nom, String marque, String description, String photo, String categorie) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
    }
    public Equipement(String nom,String marque,float prix,String description,String categorie,String photo)
    {
             this.prix = prix;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;   
    }
    public Equipement(float prix, String nom, String marque, String description, String photo, String categorie) {
        this.prix = prix;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
    }

    public Equipement() {
         //To change body of generated methods, choose Tools | Templates.
    }

    public Equipement(int i, String string) {
        this.id=i;
        this.nom=string;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id + ", prix=" + prix + ", nom=" + nom + ", marque=" + marque + ", description=" + description + ", photo=" + photo + ", categorie=" + categorie + '}';
    }
    
}
