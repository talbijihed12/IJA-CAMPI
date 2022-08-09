/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

import java.util.Objects;

/**
 *
 * @author asus
 */
public class evenement {
    public evenement(int id_Event,String nom_Event, String description, String date_debut, String date_fin, int nbr_reservation, int prix, String activite,int id_user) {
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_reservation = nbr_reservation;
        this.prix = prix;
        this.activite = activite;
        this.id_Event = id_Event;
        this.id_user = id_user;


        
        
    }

   

    public evenement(String nom_Event) {
        this.nom_Event = nom_Event;
    }

    public evenement(String nom_Event, String description, String date_debut, String date_fin, int nbr_reservation, int prix, String activite) {
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_reservation = nbr_reservation;
        this.prix = prix;
        this.activite = activite;
    }

    public evenement(int nbr_reservation) {
        this.nbr_reservation = nbr_reservation;
    }
    
    private int id_Event;
    private int id_user;
    private int id_avis;
    private int id_equipement;
    private int id_transport;
    private int id_hebergement;
    private String nom_Event;
    private String description;
    private String date_debut;
    private String date_fin;
    private int nbr_reservation;
    private int prix;
    private String activite;

    public evenement(int id_Event, int id_user, int id_avis, int id_equipement, int id_transport, int id_hebergement, String nom_Event, String description, String date_debut, String date_fin, int nbr_reservation, int prix, String activite) {
        this.id_Event = id_Event;
        this.id_user = id_user;
        this.id_avis = id_avis;
        this.id_equipement = id_equipement;
        this.id_transport = id_transport;
       this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_reservation = nbr_reservation;
        this.prix = prix;
        this.id_hebergement = id_hebergement;
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_reservation = nbr_reservation;
        this.prix = prix;
        this.activite = activite;
    }

    

    public evenement(int prix, String nom_Event, String activite, String description, String date_debut, String date_fin) {
      
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.activite = activite;
    }

    public evenement(int id_Event, int id_user, String nom_Event, String description, String date_debut, String date_fin, int prix, String activite) {
        this.id_Event = id_Event;
        this.id_user = id_user;
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.activite = activite;
    }

    public evenement(String nom_Event, String description, int prix) {
        this.nom_Event = nom_Event;
        this.description = description;
        this.prix = prix;
    }

    public evenement(int id1, int id2, int id3, String text, String text0, String dated, String datef, int g, int f, String text1) {
       
    }

    public evenement(int prix, String nom_Event, String activite, String description, String date_debut, String date_fin, int id_Event, int id_user) {
       this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_reservation = nbr_reservation;
        this.prix = prix;
        this.activite = activite;
        this.id_Event = id_Event;
        this.id_user = id_user;
    }



    public evenement(String nom_Event, String description, String date_debut, String date_fin) {
          this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public evenement() {
          this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

   

   

    @Override
    public String toString() {
        return "evenement{" + "id_Event=" + id_Event + ", id_user=" + id_user + ", id_avis=" + id_avis + ", id_equipement=" + id_equipement + ", id_transport=" + id_transport + ", id_hebergement=" + id_hebergement + ", nom_Event=" + nom_Event + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", nbr_reservation=" + nbr_reservation + ", prix=" + prix + ", activite=" + activite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id_Event;
        hash = 83 * hash + this.id_user;
        hash = 83 * hash + this.id_avis;
        hash = 83 * hash + this.id_equipement;
        hash = 83 * hash + this.id_transport;
        hash = 83 * hash + this.id_hebergement;
        hash = 83 * hash + Objects.hashCode(this.nom_Event);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.date_debut);
        hash = 83 * hash + Objects.hashCode(this.date_fin);
        hash = 83 * hash + this.nbr_reservation;
        hash = 83 * hash + this.prix;
        hash = 83 * hash + Objects.hashCode(this.activite);
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
        final evenement other = (evenement) obj;
        if (this.id_Event != other.id_Event) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_avis != other.id_avis) {
            return false;
        }
        if (this.id_equipement != other.id_equipement) {
            return false;
        }
        if (this.id_transport != other.id_transport) {
            return false;
        }
        if (this.id_hebergement != other.id_hebergement) {
            return false;
        }
        if (this.nbr_reservation != other.nbr_reservation) {
            return false;
        }
        if (this.prix != other.prix) {
            return false;
        }
        if (!Objects.equals(this.nom_Event, other.nom_Event)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        if (!Objects.equals(this.activite, other.activite)) {
            return false;
        }
        return true;
    }

    public evenement(int id_user, int id_equipement, int id_transport, int id_hebergement, String nom_Event, String description, String date_debut, String date_fin, int nbr_reservation, int prix, String activite) {
        this.id_user = id_user;
        this.id_equipement = id_equipement;
        this.id_transport = id_transport;
        this.id_hebergement = id_hebergement;
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_reservation = nbr_reservation;
        this.prix = prix;
        this.activite = activite;
    }
    public evenement(int id_Event , int id_user, int id_equipement, int id_transport, int id_hebergement, String nom_Event, String description, String date_debut, String date_fin, int nbr_reservation, int prix, String activite) {
        this.id_Event = id_Event;
        this.id_user = id_user;
        this.id_equipement = id_equipement;
        this.id_transport = id_transport;
        this.id_hebergement = id_hebergement;
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_reservation = nbr_reservation;
        this.prix = prix;
        this.activite = activite;
    }

  

    public int getId_Event() {
        return id_Event;
    }

    public void setId_Event(int id_Event) {
        this.id_Event = id_Event;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public int getId_equipement() {
        return id_equipement;
    }

    public void setId_equipement(int id_equipement) {
        this.id_equipement = id_equipement;
    }

    public int getId_transport() {
        return id_transport;
    }

    public void setId_transport(int id_transport) {
        this.id_transport = id_transport;
    }

    public int getId_hebergement() {
        return id_hebergement;
    }

    public void setId_hebergement(int id_hebergement) {
        this.id_hebergement = id_hebergement;
    }

    public String getNom_Event() {
        return nom_Event;
    }
    

    public void setNom_Event(String nom_Event) {
        this.nom_Event = nom_Event;
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getNbr_reservation() {
        return nbr_reservation;
    }

    public void setNbr_reservation(int nbr_reservation) {
        this.nbr_reservation = nbr_reservation;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public evenement(String nom_Event, String description, String date_debut, String date_fin,int id_Event,int id_user) {
        this.nom_Event = nom_Event;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_Event=id_Event;
        this.id_user=id_user;
    }

    public String getNom_Event(String nom_Event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getString(String nom_Event) {
        return nom_Event;
    }

   
   

    
}
