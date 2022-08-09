/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

/**
 *
 * @author asus
 */
public class utilisateurs {
    private int id_user ; 
    private String nom ;

    public utilisateurs(String nom) {
        this.nom = nom;
    }
    public utilisateurs() {
        this.nom = nom;
    }

    public int getId_user() {
        return id_user;
    }

    public utilisateurs(int id_user) {
        this.id_user = id_user;
    }
    

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom(){
        return nom;
    }

    @Override
    public String toString() {
        return "utilisateurs{" + "nom=" + nom + '}';
    }
    
    
}
