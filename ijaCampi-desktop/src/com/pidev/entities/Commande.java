/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 *
 * @author brahim
 */
public class Commande {
    private int id;
    private Utilisateur camper;  
    Date date_commande;
    String reference;
    float montant;
    String adresse;
    ArrayList <LigneCommande> panier ;
    
 public Commande() {
    }

    public Commande(Utilisateur camper, ArrayList<LigneCommande> panier) {
        this.camper=camper;
        this.panier=panier;
    }
 public float total (){
        float total=0;
        for(int i=0;i<panier.size();i++)
        {
            
           total=total+ (panier.get(i).getEquipement().getPrix() * panier.get(i).getQuantite());
        }
        return total;
    }

    public Commande(Utilisateur camper, Date date_commande, String reference, float montant, String adresse, ArrayList<LigneCommande> panier) {
        this.camper = camper;
        this.date_commande = date_commande;
        this.reference = reference;
        this.montant = montant;
        this.adresse = adresse;
        this.panier = panier;
    }
 
    public Commande(Utilisateur camper, Date date_commande, String reference,String adresse, ArrayList<LigneCommande> panier) {
        this.camper = camper;
        this.date_commande = date_commande;
        this.reference = reference;
        this.adresse = adresse;
        this.panier = panier;
    }

    public Commande(int id, Date date_commande, String reference, float montant, String adresse) {
        this.id = id;
        this.date_commande = date_commande;
        this.reference = reference;
        this.montant = montant;
        this.adresse = adresse;
    }
    
     public Commande( Date date_commande, String reference, float montant, String adresse) {
        this.date_commande = date_commande;
        this.reference = reference;
        this.montant = montant;
        this.adresse = adresse;
    }

    public Commande(Utilisateur camper, Date date_commande, String reference, float montant, String adresse) {
        this.camper = camper;
        this.date_commande = date_commande;
        this.reference = reference;
        this.montant = montant;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", camper=" + camper.toString() + ", date_commande=" + date_commande + ", reference=" + reference + ", montant=" + montant + ", adresse=" + adresse + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getCamper() {
        return camper;
    }

    public void setCamper(Utilisateur camper) {
        this.camper = camper;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ArrayList<LigneCommande> getPanier() {
        return panier;
    }

    public void setPanier(ArrayList<LigneCommande> panier) {
        this.panier = panier;
    }
   

}
