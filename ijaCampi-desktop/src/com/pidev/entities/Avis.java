/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

/**
 *
 * @author 21627
 */
public class Avis {
    private int id_a;
    private String commentaire;
    private int idu;

    public Avis() {
    }

    public Avis(int id_a, String commentaire, int idu) {
        this.id_a = id_a;
        this.commentaire = commentaire;
        this.idu = idu;
    }

    public Avis(String commentaire, int idu) {
        this.commentaire = commentaire;
        this.idu = idu;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getId_a() {
        return id_a;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getIdu() {
        return idu;
    }
    
    @Override
    public String toString() {
        return "\n Avis" + id_a + ", commentaire=" + commentaire + ", idu=" + idu +'}';
    }
    
}
