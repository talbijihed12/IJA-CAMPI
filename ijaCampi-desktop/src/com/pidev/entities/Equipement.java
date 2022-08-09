/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

/**
 *
 * @author brahim
 */
public class Equipement {
     
   private evenement evenement;
   private Utilisateur camper; 
   private int id;
   private float prix;
   private String nom,marque,description,photo,categorie;
   public Equipement(String nom)
   {
       this.nom=nom;
   }
   public String affich(){
       return "{id:"+ this.id+" nom"+this.nom+" cat"+this.categorie+" mar"+this.marque+" prx"+this.prix+" desc"+this.description+" ph"+this.photo;
   }
           
    public Equipement(evenement evenement, Utilisateur camper, int id, float prix, String nom, String marque, String description, String photo, String categorie) {
        this.evenement = evenement;
        this.camper = camper;
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
    }
    public Equipement(evenement evenement, Utilisateur camper, float prix, String nom, String marque, String description, String photo, String categorie) {
        this.evenement = evenement;
        this.camper = camper;
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
    }

    public Equipement(evenement evenement, Utilisateur camper, String nom, String marque, String description, String photo, String categorie) {
        this.evenement = evenement;
        this.camper = camper;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
    }
    public Equipement(Utilisateur camper,float prix, String nom, String marque, String description, String photo, String categorie) {
        this.camper = camper;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.prix=prix;
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
    public Equipement(int id,float prix, String nom, String marque, String description, String photo, String categorie) {
        this.id=id;
        this.prix = prix;
        this.nom = nom;
        this.marque = marque;
        this.description = description;
        this.photo = photo;
        this.categorie = categorie;
    }

    public Equipement() {
    }
    public evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(evenement evenement) {
        this.evenement = evenement;
    }

    public Utilisateur getCamper() {
        return camper;
    }

    public void setCamper(Utilisateur camper) {
        this.camper = camper;
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

   
    public String toStringEV() {
        return "Equipement{" + "evenement=" + evenement.toString() + ", camper=" + camper.toString() + ", id=" + id+ ", nom=" + nom + ", marque=" + marque + ", description=" + description + ", photo=" + photo + ", categorie=" + categorie + '}';
    }

  
public String toStringC() {
        return "Equipement{" +  ", camper=" + camper.toString() + ", id=" + id + ", prix=" + prix + ", nom=" + nom + ", marque=" + marque + ", description=" + description + ", photo=" + photo + ", categorie=" + categorie + '}';
    }
    
   
   }
