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
public class Sos {
      private int  id_c;
    private String nom;
    private String prenom;
    private int telephone;
    private int id_g;
    private String desc_cas;

    public Sos() {
    }

    public Sos(int id_c, String nom, String prenom, int telephone, int id_g, String desc_cas) {
        this.id_c = id_c;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.id_g = id_g;
        this.desc_cas = desc_cas;
    }

    public Sos(String nom, String prenom, int telephone, int id_g, String desc_cas) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.id_g = id_g;
        this.desc_cas = desc_cas;
    }

    public int getId_c() {
        return id_c;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getTelephone() {
        return telephone;
    }

    public int getId_g() {
        return id_g;
    }

    public String getDesc_cas() {
        return desc_cas;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setId_g(int id_g) {
        this.id_g = id_g;
    }

    public void setDesc_cas(String desc_cas) {
        this.desc_cas = desc_cas;
    }
    
@Override
    public String toString() {
        return "\n Sos" + id_c + ", nom=" + nom + ", prenom=" + prenom +", telephone=" + telephone +", id_g=" + id_g +", desc_cas=" + desc_cas +'}';
    }
   					
}
