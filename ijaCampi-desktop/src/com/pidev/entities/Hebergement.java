/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Hebergement {
    private int id; 
    public String name;
    public String ville;
    public String categorie;
    public int capacite;
    public Boolean disponibilite; 
    public int prix;
    private int id_user; 
    
            public Hebergement(String name, String ville, String categorie, int capacite, boolean disponibilite, int prix) {
        
        this.name = name;
        this.ville = ville;
        this.categorie = categorie;
        this.capacite = capacite;
        this.disponibilite = disponibilite;
        this.prix = prix;
    }

    public Hebergement(int id, String name, String ville, String categorie, int capacite, Boolean disponibilite, int prix, int id_h) {
        this.id = id;
        this.name = name;
        this.ville = ville;
        this.categorie = categorie;
        this.capacite = capacite;
        this.disponibilite = disponibilite;
        this.prix = prix;
        this.id_user = id_h;
    }

    public int getId_user() {
        return id_user;
    }

    public Hebergement(String name, String ville, String categorie, int capacite, Boolean disponibilite, int prix, int id_h) {
        this.name = name;
        this.ville = ville;
        this.categorie = categorie;
        this.capacite = capacite;
        this.disponibilite = disponibilite;
        this.prix = prix;
        this.id_user = id_h;
    }

    public Hebergement() {
        
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

    public String getVille() {
        return ville;
    }

    public Hebergement(String name, String ville) {
        this.name = name;
        this.ville = ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Hebergement{" + "id=" + id + ", name=" + name + ", ville=" + ville + ", categorie=" + categorie + ", capacite=" + capacite + ", disponibilite=" + disponibilite + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.ville);
        hash = 97 * hash + Objects.hashCode(this.categorie);
        hash = 97 * hash + this.capacite;
        hash = 97 * hash + (this.disponibilite ? 1 : 0);
        hash = 97 * hash + this.prix;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hebergement other = (Hebergement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.capacite != other.capacite) {
            return false;
        }
        if (this.disponibilite != other.disponibilite) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }
    
}   
