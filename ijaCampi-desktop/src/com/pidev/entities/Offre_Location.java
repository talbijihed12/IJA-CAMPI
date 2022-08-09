
package com.pidev.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Omar Amri
 */
public class Offre_Location {
    
    
    private int id_reservation;
    Moyen_Transport moyen ;
    Utilisateur user;
    Date date_debut, date_fin;
    String place;
    
    
    public Offre_Location() {
    }

    public Offre_Location(String place) {
        this.place = place;
    }
    
    

    public Offre_Location(int id_reservation) {
        this.id_reservation = id_reservation;
    }
    
    

    public Offre_Location(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Offre_Location(int id_reservation, Moyen_Transport moyen, Utilisateur user, Date date_debut, Date date_fin, String place) {
        this.id_reservation = id_reservation;
        this.moyen = moyen;
        this.user = user;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.place = place;
    }

    public Offre_Location(Date date_debut, Date date_fin, String place) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.place = place;
    }
    
    public Offre_Location(Date date_debut, Date date_fin) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    

    public int id_reservation() {
        return id_reservation;
    }

    public Moyen_Transport getMoyen() {
        return moyen;
    }
    

    public Utilisateur getUser() {
        return user;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void id_reservation(int id_offrelocation) {
        this.id_reservation = id_offrelocation;
    }

    public void setMoyen(Moyen_Transport moyen) {
        this.moyen = moyen;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    @Override
    public String toString() {
        return "Offre_Location{" + "id_reservation=" + id_reservation + ", moyen=" + moyen.toString() + ", user=" + user.toString() + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", place=" + place + '}';
    }

    
    
    
}
