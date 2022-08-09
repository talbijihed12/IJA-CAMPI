package com.ijacampii.gui.back.participementEvent;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ijacampii.entities.ParticipementEvent;
import com.ijacampii.services.ParticipementEventService;

import java.util.ArrayList;

public class ShowAll extends Form {

    public static ParticipementEvent currentParticipementEvent = null;
    Form previous;
    Button addBtn;
    Label utilisateursLabel, nbrParticipementLabel, evenementLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("ParticipementEvents", new BoxLayout(BoxLayout.Y_AXIS));
        this.previous = previous;

        addGUIs();
        addActions();

        super.getToolbar().addMaterialCommandToLeftBar("  ", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void refresh() {
        this.removeAll();
        addGUIs();
        addActions();
        this.refreshTheme();
    }

    private void addGUIs() {
        addBtn = new Button("Ajouter");
        addBtn.setUIID("buttonWhiteCenter");
        this.add(addBtn);


        ArrayList<ParticipementEvent> listParticipementEvents = ParticipementEventService.getInstance().getAll();


        if (listParticipementEvents.size() > 0) {
            for (ParticipementEvent participementEvent : listParticipementEvents) {
                Component model = makeParticipementEventModel(participementEvent);
                this.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentParticipementEvent = null;
            new Manage(this).show();
        });

    }

    private Container makeModelWithoutButtons(ParticipementEvent participementEvent) {
        Container participementEventModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        participementEventModel.setUIID("containerRounded");


        utilisateursLabel = new Label("Utilisateurs : " + participementEvent.getUtilisateurs());
        utilisateursLabel.setUIID("labelDefault");

        nbrParticipementLabel = new Label("NbrParticipement : " + participementEvent.getNbrParticipement());
        nbrParticipementLabel.setUIID("labelDefault");

        evenementLabel = new Label("Evenement : " + participementEvent.getEvenement());
        evenementLabel.setUIID("labelDefault");

        utilisateursLabel = new Label("Utilisateurs : " + participementEvent.getUtilisateurs().getNom());
        utilisateursLabel.setUIID("labelDefault");

        evenementLabel = new Label("Evenement : " + participementEvent.getEvenement().getNomEvent());
        evenementLabel.setUIID("labelDefault");


        participementEventModel.addAll(

                utilisateursLabel, nbrParticipementLabel, evenementLabel
        );

        return participementEventModel;
    }

    private Component makeParticipementEventModel(ParticipementEvent participementEvent) {

        Container participementEventModel = makeModelWithoutButtons(participementEvent);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentParticipementEvent = participementEvent;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce participementEvent ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = ParticipementEventService.getInstance().delete(participementEvent.getId());

                if (responseCode == 200) {
                    currentParticipementEvent = null;
                    dlg.dispose();
                    participementEventModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du participementEvent. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        participementEventModel.add(btnsContainer);

        return participementEventModel;
    }

}