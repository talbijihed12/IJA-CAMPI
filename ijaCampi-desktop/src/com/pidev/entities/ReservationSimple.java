/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Seif Labidi
 */
public class ReservationSimple {
     private int id_rs;
    public Date dateDebut;
    public Date dateFin;
    private int user_id;
    public String nameHebergement; 
    
    public ReservationSimple(String nameHebergement, Date dateDebut, Date dateFin , int user_id) {
        this.nameHebergement = nameHebergement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.user_id = user_id;
    }

    public int getId_rs() {
        return id_rs;
    }

    public void setId_rs(int id_rs) {
        this.id_rs = id_rs;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getNameHebergement() {
        return nameHebergement;
    }
    private static final Logger LOG = Logger.getLogger(ReservationSimple.class.getName());

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNameHebergement(String nameHebergement) {
        this.nameHebergement = nameHebergement;
    }

    public ReservationSimple(int id_rs, Date dateDebut, Date dateFin, int user_id, String nameHebergement) {
        this.id_rs = id_rs;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.user_id = user_id;
        this.nameHebergement = nameHebergement;
    }

    

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public ReservationSimple() {
    }

    @Override
    public String toString() {
        return "ReservationSimple{" + "id_rs=" + id_rs + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", user_id=" + user_id + ", nameHebergement=" + nameHebergement + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id_rs;
        hash = 17 * hash + Objects.hashCode(this.dateDebut);
        hash = 17 * hash + Objects.hashCode(this.dateFin);
        hash = 17 * hash + this.user_id;
        hash = 17 * hash + Objects.hashCode(this.nameHebergement);
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
        final ReservationSimple other = (ReservationSimple) obj;
        if (this.id_rs != other.id_rs) {
            return false;
        }
        if (this.user_id != other.user_id) {
            return false;
        }
        if (!Objects.equals(this.nameHebergement, other.nameHebergement)) {
            return false;
        }
        if (!Objects.equals(this.dateDebut, other.dateDebut)) {
            return false;
        }
        if (!Objects.equals(this.dateFin, other.dateFin)) {
            return false;
        }
        return true;
    }

   

    

    
    
}
