package com.ijacampii.entities;

public class Evenement {

    private int id;
    private Hebergement hebergement;
    private Utilisateurs utilisateurs;
    private Equipement equipement;
    private MoyenTransport moyenTransport;
    private String nomEvent;
    private String description;
    private String dateDebut;
    private String dateFin;
    private int nbrReservation;
    private int prix;
    private String activite;

    public Evenement() {
    }

    public Evenement(int id, Hebergement hebergement, Utilisateurs utilisateurs, Equipement equipement, MoyenTransport moyenTransport, String nomEvent, String description, String dateDebut, String dateFin, int nbrReservation, int prix, String activite) {
        this.id = id;
        this.hebergement = hebergement;
        this.utilisateurs = utilisateurs;
        this.equipement = equipement;
        this.moyenTransport = moyenTransport;
        this.nomEvent = nomEvent;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrReservation = nbrReservation;
        this.prix = prix;
        this.activite = activite;
    }

    public Evenement(Hebergement hebergement, Utilisateurs utilisateurs, Equipement equipement, MoyenTransport moyenTransport, String nomEvent, String description, String dateDebut, String dateFin, int nbrReservation, int prix, String activite) {
        this.hebergement = hebergement;
        this.utilisateurs = utilisateurs;
        this.equipement = equipement;
        this.moyenTransport = moyenTransport;
        this.nomEvent = nomEvent;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrReservation = nbrReservation;
        this.prix = prix;
        this.activite = activite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }

    public Utilisateurs getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateurs utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public MoyenTransport getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(MoyenTransport moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbrReservation() {
        return nbrReservation;
    }

    public void setNbrReservation(int nbrReservation) {
        this.nbrReservation = nbrReservation;
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


}