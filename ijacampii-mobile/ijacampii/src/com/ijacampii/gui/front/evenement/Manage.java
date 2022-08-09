package com.ijacampii.gui.front.evenement;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.ijacampii.entities.*;
import com.ijacampii.services.*;
import com.ijacampii.utils.AlertUtils;

import java.util.ArrayList;

public class Manage extends Form {


    Evenement currentEvenement;

    TextField nomEventTF;
    TextField descriptionTF;
    TextField dateDebutTF;
    TextField dateFinTF;
    TextField nbrReservationTF;
    TextField prixTF;
    TextField activiteTF;
    Label nomEventLabel;
    Label descriptionLabel;
    Label dateDebutLabel;
    Label dateFinLabel;
    Label nbrReservationLabel;
    Label prixLabel;
    Label activiteLabel;


    ArrayList<Hebergement> listHebergements;
    PickerComponent hebergementPC;
    Hebergement selectedHebergement = null;
    ArrayList<Utilisateurs> listUtilisateurss;
    PickerComponent utilisateursPC;
    Utilisateurs selectedUtilisateurs = null;
    ArrayList<Equipement> listEquipements;
    PickerComponent equipementPC;
    Equipement selectedEquipement = null;
    ArrayList<MoyenTransport> listMoyenTransports;
    PickerComponent moyenTransportPC;
    MoyenTransport selectedMoyenTransport = null;


    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentEvenement == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentEvenement = ShowAll.currentEvenement;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

        String[] hebergementStrings;
        int hebergementIndex;
        hebergementPC = PickerComponent.createStrings("").label("Hebergement");
        listHebergements = HebergementService.getInstance().getAll();
        hebergementStrings = new String[listHebergements.size()];
        hebergementIndex = 0;
        for (Hebergement hebergement : listHebergements) {
            hebergementStrings[hebergementIndex] = hebergement.getName();
            hebergementIndex++;
        }
        if (listHebergements.size() > 0) {
            hebergementPC.getPicker().setStrings(hebergementStrings);
            hebergementPC.getPicker().addActionListener(l -> selectedHebergement = listHebergements.get(hebergementPC.getPicker().getSelectedStringIndex()));
        } else {
            hebergementPC.getPicker().setStrings("");
        }

        String[] utilisateursStrings;
        int utilisateursIndex;
        utilisateursPC = PickerComponent.createStrings("").label("Utilisateurs");
        listUtilisateurss = UtilisateursService.getInstance().getAll();
        utilisateursStrings = new String[listUtilisateurss.size()];
        utilisateursIndex = 0;
        for (Utilisateurs utilisateurs : listUtilisateurss) {
            utilisateursStrings[utilisateursIndex] = utilisateurs.getNom();
            utilisateursIndex++;
        }
        if (listUtilisateurss.size() > 0) {
            utilisateursPC.getPicker().setStrings(utilisateursStrings);
            utilisateursPC.getPicker().addActionListener(l -> selectedUtilisateurs = listUtilisateurss.get(utilisateursPC.getPicker().getSelectedStringIndex()));
        } else {
            utilisateursPC.getPicker().setStrings("");
        }

        String[] equipementStrings;
        int equipementIndex;
        equipementPC = PickerComponent.createStrings("").label("Equipement");
        listEquipements = EquipementService.getInstance().getAll();
        equipementStrings = new String[listEquipements.size()];
        equipementIndex = 0;
        for (Equipement equipement : listEquipements) {
            equipementStrings[equipementIndex] = equipement.getNom();
            equipementIndex++;
        }
        if (listEquipements.size() > 0) {
            equipementPC.getPicker().setStrings(equipementStrings);
            equipementPC.getPicker().addActionListener(l -> selectedEquipement = listEquipements.get(equipementPC.getPicker().getSelectedStringIndex()));
        } else {
            equipementPC.getPicker().setStrings("");
        }

        String[] moyenTransportStrings;
        int moyenTransportIndex;
        moyenTransportPC = PickerComponent.createStrings("").label("MoyenTransport");
        listMoyenTransports = MoyenTransportService.getInstance().getAll();
        moyenTransportStrings = new String[listMoyenTransports.size()];
        moyenTransportIndex = 0;
        for (MoyenTransport moyenTransport : listMoyenTransports) {
            moyenTransportStrings[moyenTransportIndex] = moyenTransport.getType();
            moyenTransportIndex++;
        }
        if (listMoyenTransports.size() > 0) {
            moyenTransportPC.getPicker().setStrings(moyenTransportStrings);
            moyenTransportPC.getPicker().addActionListener(l -> selectedMoyenTransport = listMoyenTransports.get(moyenTransportPC.getPicker().getSelectedStringIndex()));
        } else {
            moyenTransportPC.getPicker().setStrings("");
        }


        nomEventLabel = new Label("NomEvent : ");
        nomEventLabel.setUIID("labelDefault");
        nomEventTF = new TextField();
        nomEventTF.setHint("Tapez le nomEvent");


        descriptionLabel = new Label("Description : ");
        descriptionLabel.setUIID("labelDefault");
        descriptionTF = new TextField();
        descriptionTF.setHint("Tapez le description");


        dateDebutLabel = new Label("DateDebut : ");
        dateDebutLabel.setUIID("labelDefault");
        dateDebutTF = new TextField();
        dateDebutTF.setHint("Tapez le dateDebut");


        dateFinLabel = new Label("DateFin : ");
        dateFinLabel.setUIID("labelDefault");
        dateFinTF = new TextField();
        dateFinTF.setHint("Tapez le dateFin");


        nbrReservationLabel = new Label("NbrReservation : ");
        nbrReservationLabel.setUIID("labelDefault");
        nbrReservationTF = new TextField();
        nbrReservationTF.setHint("Tapez le nbrReservation");


        prixLabel = new Label("Prix : ");
        prixLabel.setUIID("labelDefault");
        prixTF = new TextField();
        prixTF.setHint("Tapez le prix");


        activiteLabel = new Label("Activite : ");
        activiteLabel.setUIID("labelDefault");
        activiteTF = new TextField();
        activiteTF.setHint("Tapez le activite");


        if (currentEvenement == null) {


            manageButton = new Button("Ajouter");
        } else {


            nomEventTF.setText(currentEvenement.getNomEvent());
            descriptionTF.setText(currentEvenement.getDescription());
            dateDebutTF.setText(currentEvenement.getDateDebut());
            dateFinTF.setText(currentEvenement.getDateFin());
            nbrReservationTF.setText(String.valueOf(currentEvenement.getNbrReservation()));
            prixTF.setText(String.valueOf(currentEvenement.getPrix()));
            activiteTF.setText(currentEvenement.getActivite());

            hebergementPC.getPicker().setSelectedString(currentEvenement.getHebergement().getName());
            selectedHebergement = currentEvenement.getHebergement();
            utilisateursPC.getPicker().setSelectedString(currentEvenement.getUtilisateurs().getNom());
            selectedUtilisateurs = currentEvenement.getUtilisateurs();
            equipementPC.getPicker().setSelectedString(currentEvenement.getEquipement().getNom());
            selectedEquipement = currentEvenement.getEquipement();
            moyenTransportPC.getPicker().setSelectedString(currentEvenement.getMoyenTransport().getType());
            selectedMoyenTransport = currentEvenement.getMoyenTransport();


            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nomEventLabel, nomEventTF,
                descriptionLabel, descriptionTF,
                dateDebutLabel, dateDebutTF,
                dateFinLabel, dateFinTF,
                nbrReservationLabel, nbrReservationTF,
                prixLabel, prixTF,
                activiteLabel, activiteTF,
                hebergementPC, utilisateursPC, equipementPC, moyenTransportPC,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        if (currentEvenement == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EvenementService.getInstance().add(
                            new Evenement(


                                    selectedHebergement,
                                    selectedUtilisateurs,
                                    selectedEquipement,
                                    selectedMoyenTransport,
                                    nomEventTF.getText(),
                                    descriptionTF.getText(),
                                    dateDebutTF.getText(),
                                    dateFinTF.getText(),
                                    (int) Float.parseFloat(nbrReservationTF.getText()),
                                    (int) Float.parseFloat(prixTF.getText()),
                                    activiteTF.getText()
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Evenement ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de evenement. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = EvenementService.getInstance().edit(
                            new Evenement(
                                    currentEvenement.getId(),


                                    selectedHebergement,
                                    selectedUtilisateurs,
                                    selectedEquipement,
                                    selectedMoyenTransport,
                                    nomEventTF.getText(),
                                    descriptionTF.getText(),
                                    dateDebutTF.getText(),
                                    dateFinTF.getText(),
                                    (int) Float.parseFloat(nbrReservationTF.getText()),
                                    (int) Float.parseFloat(prixTF.getText()),
                                    activiteTF.getText()

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("Evenement modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de evenement. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        }
    }

    private void showBackAndRefresh() {
        ((ShowAll) previous).refresh();
        previous.showBack();
    }

    private boolean controleDeSaisie() {


        if (nomEventTF.getText().equals("")) {
            Dialog.show("Avertissement", "NomEvent vide", new Command("Ok"));
            return false;
        }


        if (descriptionTF.getText().equals("")) {
            Dialog.show("Avertissement", "Description vide", new Command("Ok"));
            return false;
        }


        if (dateDebutTF.getText().equals("")) {
            Dialog.show("Avertissement", "DateDebut vide", new Command("Ok"));
            return false;
        }


        if (dateFinTF.getText().equals("")) {
            Dialog.show("Avertissement", "DateFin vide", new Command("Ok"));
            return false;
        }


        if (nbrReservationTF.getText().equals("")) {
            Dialog.show("Avertissement", "NbrReservation vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(nbrReservationTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", nbrReservationTF.getText() + " n'est pas un nombre valide (nbrReservation)", new Command("Ok"));
            return false;
        }


        if (prixTF.getText().equals("")) {
            Dialog.show("Avertissement", "Prix vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(prixTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", prixTF.getText() + " n'est pas un nombre valide (prix)", new Command("Ok"));
            return false;
        }


        if (activiteTF.getText().equals("")) {
            Dialog.show("Avertissement", "Activite vide", new Command("Ok"));
            return false;
        }


        if (selectedHebergement == null) {
            Dialog.show("Avertissement", "Veuillez choisir un hebergement", new Command("Ok"));
            return false;
        }

        if (selectedUtilisateurs == null) {
            Dialog.show("Avertissement", "Veuillez choisir un utilisateurs", new Command("Ok"));
            return false;
        }

        if (selectedEquipement == null) {
            Dialog.show("Avertissement", "Veuillez choisir un equipement", new Command("Ok"));
            return false;
        }

        if (selectedMoyenTransport == null) {
            Dialog.show("Avertissement", "Veuillez choisir un moyenTransport", new Command("Ok"));
            return false;
        }


        return true;
    }
}