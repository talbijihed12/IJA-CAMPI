package com.ijacampii.entities;

public class ParticipementEvent {

    private int id;
    private Utilisateurs utilisateurs;
    private int nbrParticipement;
    private Evenement evenement;

    public ParticipementEvent() {
    }

    public ParticipementEvent(int id, Utilisateurs utilisateurs, int nbrParticipement, Evenement evenement) {
        this.id = id;
        this.utilisateurs = utilisateurs;
        this.nbrParticipement = nbrParticipement;
        this.evenement = evenement;
    }

    public ParticipementEvent(Utilisateurs utilisateurs, int nbrParticipement, Evenement evenement) {
        this.utilisateurs = utilisateurs;
        this.nbrParticipement = nbrParticipement;
        this.evenement = evenement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateurs getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateurs utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public int getNbrParticipement() {
        return nbrParticipement;
    }

    public void setNbrParticipement(int nbrParticipement) {
        this.nbrParticipement = nbrParticipement;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }


}