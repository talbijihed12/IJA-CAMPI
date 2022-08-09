package com.ijacampii.gui.back.participementEvent;


import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.ijacampii.entities.Evenement;
import com.ijacampii.entities.ParticipementEvent;
import com.ijacampii.entities.Utilisateurs;
import com.ijacampii.services.EvenementService;
import com.ijacampii.services.ParticipementEventService;
import com.ijacampii.services.UtilisateursService;
import com.ijacampii.utils.AlertUtils;

import java.util.ArrayList;

public class Manage extends Form {


    ParticipementEvent currentParticipementEvent;

    TextField nbrParticipementTF;
    Label nbrParticipementLabel;


    ArrayList<Utilisateurs> listUtilisateurss;
    PickerComponent utilisateursPC;
    Utilisateurs selectedUtilisateurs = null;
    ArrayList<Evenement> listEvenements;
    PickerComponent evenementPC;
    Evenement selectedEvenement = null;


    Button manageButton;

    Form previous;

    public Manage(Form previous) {
        super(ShowAll.currentParticipementEvent == null ? "Ajouter" : "Modifier", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        currentParticipementEvent = ShowAll.currentParticipementEvent;

        addGUIs();
        addActions();

        getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    private void addGUIs() {

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

        String[] evenementStrings;
        int evenementIndex;
        evenementPC = PickerComponent.createStrings("").label("Evenement");
        listEvenements = EvenementService.getInstance().getAll();
        evenementStrings = new String[listEvenements.size()];
        evenementIndex = 0;
        for (Evenement evenement : listEvenements) {
            evenementStrings[evenementIndex] = evenement.getNomEvent();
            evenementIndex++;
        }
        if (listEvenements.size() > 0) {
            evenementPC.getPicker().setStrings(evenementStrings);
            evenementPC.getPicker().addActionListener(l -> selectedEvenement = listEvenements.get(evenementPC.getPicker().getSelectedStringIndex()));
        } else {
            evenementPC.getPicker().setStrings("");
        }


        nbrParticipementLabel = new Label("NbrParticipement : ");
        nbrParticipementLabel.setUIID("labelDefault");
        nbrParticipementTF = new TextField();
        nbrParticipementTF.setHint("Tapez le nbrParticipement");


        if (currentParticipementEvent == null) {


            manageButton = new Button("Ajouter");
        } else {

            nbrParticipementTF.setText(String.valueOf(currentParticipementEvent.getNbrParticipement()));


            utilisateursPC.getPicker().setSelectedString(currentParticipementEvent.getUtilisateurs().getNom());
            selectedUtilisateurs = currentParticipementEvent.getUtilisateurs();
            evenementPC.getPicker().setSelectedString(currentParticipementEvent.getEvenement().getNomEvent());
            selectedEvenement = currentParticipementEvent.getEvenement();


            manageButton = new Button("Modifier");
        }
        manageButton.setUIID("buttonWhiteCenter");

        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        container.setUIID("containerRounded");

        container.addAll(


                nbrParticipementLabel, nbrParticipementTF,

                utilisateursPC, evenementPC,
                manageButton
        );

        this.addAll(container);
    }

    private void addActions() {

        if (currentParticipementEvent == null) {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ParticipementEventService.getInstance().add(
                            new ParticipementEvent(


                                    selectedUtilisateurs,
                                    (int) Float.parseFloat(nbrParticipementTF.getText()),
                                    selectedEvenement
                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("ParticipementEvent ajouté avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur d'ajout de participementEvent. Code d'erreur : " + responseCode, new Command("Ok"));
                    }
                }
            });
        } else {
            manageButton.addActionListener(action -> {
                if (controleDeSaisie()) {
                    int responseCode = ParticipementEventService.getInstance().edit(
                            new ParticipementEvent(
                                    currentParticipementEvent.getId(),


                                    selectedUtilisateurs,
                                    (int) Float.parseFloat(nbrParticipementTF.getText()),
                                    selectedEvenement

                            )
                    );
                    if (responseCode == 200) {
                        AlertUtils.makeNotification("ParticipementEvent modifié avec succes");
                        showBackAndRefresh();
                    } else {
                        Dialog.show("Erreur", "Erreur de modification de participementEvent. Code d'erreur : " + responseCode, new Command("Ok"));
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


        if (nbrParticipementTF.getText().equals("")) {
            Dialog.show("Avertissement", "NbrParticipement vide", new Command("Ok"));
            return false;
        }
        try {
            Float.parseFloat(nbrParticipementTF.getText());
        } catch (NumberFormatException e) {
            Dialog.show("Avertissement", nbrParticipementTF.getText() + " n'est pas un nombre valide (nbrParticipement)", new Command("Ok"));
            return false;
        }


        if (selectedUtilisateurs == null) {
            Dialog.show("Avertissement", "Veuillez choisir un utilisateurs", new Command("Ok"));
            return false;
        }

        if (selectedEvenement == null) {
            Dialog.show("Avertissement", "Veuillez choisir un evenement", new Command("Ok"));
            return false;
        }


        return true;
    }
}