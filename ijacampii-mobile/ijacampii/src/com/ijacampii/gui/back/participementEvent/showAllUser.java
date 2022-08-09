/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ijacampii.gui.back.participementEvent;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ijacampii.entities.Utilisateurs;
import com.ijacampii.services.ParticipementEventService;
import com.ijacampii.services.UtilisateursService;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class showAllUser extends Form{
       public static Utilisateurs currentParticipementEvent = null;
    Form previous;
    Button addBtn;
    Label utilisateursLabel, nbrParticipementLabel, evenementLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public showAllUser(Form previous) {
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


        ArrayList<Utilisateurs> listParticipementEvents = UtilisateursService.getInstance().getAll();


        if (listParticipementEvents.size() > 0) {
            for (Utilisateurs participementEvent : listParticipementEvents) {
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
            new MoyenTransportAjout(this).show();
        });

    }

    private Container makeModelWithoutButtons(Utilisateurs MoyenTransport) {
        Container participementEventModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        participementEventModel.setUIID("containerRounded");


       // utilisateursLabel = new Label("Utilisateurs : " + participementEvent.getUtilisateurs());
  //      utilisateursLabel.setUIID("labelDefault");

        nbrParticipementLabel = new Label("nom : " + MoyenTransport.getNom());
        nbrParticipementLabel.setUIID("labelDefault");

    //    evenementLabel = new Label("Evenement : " + participementEvent.getEvenement());
   //     evenementLabel.setUIID("labelDefault");

    //    utilisateursLabel = new Label("Utilisateurs : " + participementEvent.getUtilisateurs().getNom());
    //    utilisateursLabel.setUIID("labelDefault");

    //    evenementLabel = new Label("Evenement : " + participementEvent.getEvenement().getNomEvent());
    //    evenementLabel.setUIID("labelDefault");


        participementEventModel.addAll(

             nbrParticipementLabel
        );

        return participementEventModel;
    }

    private Component makeParticipementEventModel(Utilisateurs participementEvent) {

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
