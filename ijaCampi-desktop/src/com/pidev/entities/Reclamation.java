/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author 21627
 */
public class Reclamation {
    private int id_r;
    private String description;
    private Date date;
    private String photo;
    private int idu ;
    private String etat;

    public Reclamation() {
    }

    public Reclamation(int id_r, String description, Date date, String photo, int idu ,String etat) {
        this.id_r = id_r;
        this.description = description;
        this.date = date;
        this.photo = photo;
        this.idu=idu;
        this.etat=etat;
    }

    public Reclamation(String description, Date date, String photo, int idu ,String etat) {
        this.description = description;
        this.date = date;
        this.photo = photo;
        this.idu=idu;
        this.etat=etat;
    }

    public int getId_r() {
        return id_r;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getIdu() {
        return idu;
    }

    public String getEtat() {
        return etat;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    @Override
    public String toString() {
        return "\n Reclamation" + id_r + ", description=" + description + ", date=" + date +", photo=" + photo +", idu=" + idu +", etat=" + etat +'}';
    }
}
