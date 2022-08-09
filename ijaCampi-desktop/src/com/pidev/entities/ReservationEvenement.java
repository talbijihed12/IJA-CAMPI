/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Seif Labidi
 */
public class ReservationEvenement {
    private int id_re;
    public Date dateDebut;
    public Date dateFin;
    private int id_evenement;
    private int id_hebergement;

    public ReservationEvenement(int duree, Date dateDebut, Date dateFin, int id_evenement, int id_hebergement) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_evenement=id_evenement;
        this.id_hebergement=id_hebergement;
        
    }

    public ReservationEvenement() {
    }

    public int getId_re() {
        return id_re;
    }

    public void setId_re(int id_re) {
        this.id_re = id_re;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public int getId_hebergement() {
        return id_hebergement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public void setId_hebergement(int id_hebergement) {
        this.id_hebergement = id_hebergement;
    }

    public ReservationEvenement(Date dateDebut, Date dateFin, int id_evenement, int id_hebergement) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_evenement = id_evenement;
        this.id_hebergement = id_hebergement;
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

    @Override
    public String toString() {
        return "ReservationEvenement{" + "id_re=" + id_re  + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_re;
        
        hash = 97 * hash + Objects.hashCode(this.dateDebut);
        hash = 97 * hash + Objects.hashCode(this.dateFin);
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
        final ReservationEvenement other = (ReservationEvenement) obj;
        if (this.id_re != other.id_re) {
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
