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
public class LigneCommande {
   private int id,quantite;
  private Equipement equipement;
   private Commande commande;

    public LigneCommande() {
    }

    public LigneCommande(int id, Equipement equipement, Commande commande,int quantite) {
        this.id = id;
        this.quantite = quantite;
        this.equipement = equipement;
        this.commande = commande;
    }
    public LigneCommande( int quantite, Equipement equipement, Commande commande) {
       
        this.quantite = quantite;
        this.equipement = equipement;
        this.commande = commande;
    }
    public LigneCommande( int quantite, Equipement equipement) {
       
        this.quantite = quantite;
        this.equipement = equipement;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", quantite=" + quantite + ", equipement=" + equipement.affich() + ", commande"+ commande.toString()  +'}';
    }
    public  String  affiche(){
    return quantite +equipement.affich();
}

}
