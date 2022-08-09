package com.ijacampii.entities;

public class MoyenTransport {

    private int id;
    String type, matricule, marque;
    int nbr_place ;
    double frais;
//            MoyenTransport moy = new MoyenTransport(tfType.getText(),tfMatricule.getText(),tfMarque.getText(),parseInt(tfNbrPlace.getText()),parseDouble(tfFrais.getText()));

    public MoyenTransport(String text, String text0, String text1, int parseInt, double parseDouble) {
        this.type=text;
        this.matricule=text0;
        this.marque=text1;
        this.nbr_place=parseInt;
        this.frais=parseDouble;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public double getFrais() {
        return frais;
    }

    public void setFrais(double frais) {
        this.frais = frais;
    }

    public MoyenTransport() {
    }

    public MoyenTransport(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public MoyenTransport(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}