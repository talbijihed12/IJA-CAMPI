package com.ijacampii.gui.front.evenement;

import com.codename1.components.InteractionDialog;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.ijacampii.entities.Evenement;
import com.ijacampii.services.EvenementService;

import java.util.ArrayList;

public class ShowAll extends Form {

    public static Evenement currentEvenement = null;
    Form previous;
    Button addBtn;

    TextField searchTF;
    ArrayList<Component> componentModels;
    Label hebergementLabel, utilisateursLabel, equipementLabel, moyenTransportLabel, nomEventLabel, descriptionLabel, dateDebutLabel, dateFinLabel, nbrReservationLabel, prixLabel, activiteLabel;
    Button editBtn, deleteBtn;
    Container btnsContainer;

    public ShowAll(Form previous) {
        super("Evenements", new BoxLayout(BoxLayout.Y_AXIS));
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


        ArrayList<Evenement> listEvenements = EvenementService.getInstance().getAll();
        componentModels = new ArrayList<>();

        searchTF = new TextField("", "Chercher evenement par Nom");
        searchTF.addDataChangedListener((d, t) -> {
            if (componentModels.size() > 0) {
                for (Component componentModel : componentModels) {
                    this.removeComponent(componentModel);
                }
            }
            componentModels = new ArrayList<>();
            for (Evenement evenement : listEvenements) {
                if (evenement.getNomEvent().toLowerCase().startsWith(searchTF.getText().toLowerCase())) {
                    Component model = makeEvenementModel(evenement);
                    this.add(model);
                    componentModels.add(model);
                }
            }
            this.revalidate();
        });
        this.add(searchTF);


        if (listEvenements.size() > 0) {
            for (Evenement evenement : listEvenements) {
                Component model = makeEvenementModel(evenement);
                this.add(model);
                componentModels.add(model);
            }
        } else {
            this.add(new Label("Aucune donnée"));
        }
    }

    private void addActions() {
        addBtn.addActionListener(action -> {
            currentEvenement = null;
            new Manage(this).show();
        });

    }

    private Container makeModelWithoutButtons(Evenement evenement) {
        Container evenementModel = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        evenementModel.setUIID("containerRounded");


        hebergementLabel = new Label("Hebergement : " + evenement.getHebergement());
        hebergementLabel.setUIID("labelDefault");

        utilisateursLabel = new Label("Utilisateurs : " + evenement.getUtilisateurs());
        utilisateursLabel.setUIID("labelDefault");

        equipementLabel = new Label("Equipement : " + evenement.getEquipement());
        equipementLabel.setUIID("labelDefault");

        moyenTransportLabel = new Label("MoyenTransport : " + evenement.getMoyenTransport());
        moyenTransportLabel.setUIID("labelDefault");

        nomEventLabel = new Label("NomEvent : " + evenement.getNomEvent());
        nomEventLabel.setUIID("labelDefault");

        descriptionLabel = new Label("Description : " + evenement.getDescription());
        descriptionLabel.setUIID("labelDefault");

        dateDebutLabel = new Label("DateDebut : " + evenement.getDateDebut());
        dateDebutLabel.setUIID("labelDefault");

        dateFinLabel = new Label("DateFin : " + evenement.getDateFin());
        dateFinLabel.setUIID("labelDefault");

        nbrReservationLabel = new Label("NbrReservation : " + evenement.getNbrReservation());
        nbrReservationLabel.setUIID("labelDefault");

        prixLabel = new Label("Prix : " + evenement.getPrix());
        prixLabel.setUIID("labelDefault");

        activiteLabel = new Label("Activite : " + evenement.getActivite());
        activiteLabel.setUIID("labelDefault");

        hebergementLabel = new Label("Hebergement : " + evenement.getHebergement().getName());
        hebergementLabel.setUIID("labelDefault");

        utilisateursLabel = new Label("Utilisateurs : " + evenement.getUtilisateurs().getNom());
        utilisateursLabel.setUIID("labelDefault");

        equipementLabel = new Label("Equipement : " + evenement.getEquipement().getNom());
        equipementLabel.setUIID("labelDefault");

        moyenTransportLabel = new Label("MoyenTransport : " + evenement.getMoyenTransport().getType());
        moyenTransportLabel.setUIID("labelDefault");


        evenementModel.addAll(

                hebergementLabel, utilisateursLabel, equipementLabel, moyenTransportLabel, nomEventLabel, descriptionLabel, dateDebutLabel, dateFinLabel, nbrReservationLabel, prixLabel, activiteLabel
        );

        return evenementModel;
    }

    private Component makeEvenementModel(Evenement evenement) {

        Container evenementModel = makeModelWithoutButtons(evenement);

        btnsContainer = new Container(new BorderLayout());
        btnsContainer.setUIID("containerButtons");

        editBtn = new Button("Modifier");
        editBtn.setUIID("buttonWhiteCenter");
        editBtn.addActionListener(action -> {
            currentEvenement = evenement;
            new Manage(this).show();
        });

        deleteBtn = new Button("Supprimer");
        deleteBtn.setUIID("buttonWhiteCenter");
        deleteBtn.addActionListener(action -> {
            InteractionDialog dlg = new InteractionDialog("Confirmer la suppression");
            dlg.setLayout(new BorderLayout());
            dlg.add(BorderLayout.CENTER, new Label("Voulez vous vraiment supprimer ce evenement ?"));
            Button btnClose = new Button("Annuler");
            btnClose.addActionListener((ee) -> dlg.dispose());
            Button btnConfirm = new Button("Confirmer");
            btnConfirm.addActionListener(actionConf -> {
                int responseCode = EvenementService.getInstance().delete(evenement.getId());

                if (responseCode == 200) {
                    currentEvenement = null;
                    dlg.dispose();
                    evenementModel.remove();
                    this.refreshTheme();
                } else {
                    Dialog.show("Erreur", "Erreur de suppression du evenement. Code d'erreur : " + responseCode, new Command("Ok"));
                }
            });
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.addAll(btnClose, btnConfirm);
            dlg.addComponent(BorderLayout.SOUTH, btnContainer);
            dlg.show(800, 800, 0, 0);
        });

        btnsContainer.add(BorderLayout.WEST, editBtn);
        btnsContainer.add(BorderLayout.EAST, deleteBtn);


        evenementModel.add(btnsContainer);

        return evenementModel;
    }

}