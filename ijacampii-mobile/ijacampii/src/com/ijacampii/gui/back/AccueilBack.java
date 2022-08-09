package com.ijacampii.gui.back;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.ijacampii.gui.Login;

public class AccueilBack extends Form {

    Resources theme = UIManager.initFirstTheme("/theme");
    Label label;

    public AccueilBack() {
        super("Menu", new BoxLayout(BoxLayout.Y_AXIS));
        addGUIs();
    }

    private void addGUIs() {
        label = new Label("Admin"/*MainApp.getSession().getEmail()*/);
        label.setUIID("links");
        Button btnDeconnexion = new Button();
        btnDeconnexion.setMaterialIcon(FontImage.MATERIAL_ARROW_FORWARD);
        btnDeconnexion.addActionListener(action -> {
            Login.loginForm.showBack();
        });

        Container userContainer = new Container(new BorderLayout());
        userContainer.setUIID("userContainer");
        userContainer.add(BorderLayout.CENTER, label);
        userContainer.add(BorderLayout.EAST, btnDeconnexion);

        Container menuContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        menuContainer.addAll(
                userContainer,
                makeEvenementsButton(),
                makeParticipementEventsButton(),
                makeEquipementButton(),
                makeTransportButton(),
                  makeUserButton()
        );

        this.add(menuContainer);
    }

    private Button makeEvenementsButton() {
        Button button = new Button("Evenements");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.ijacampii.gui.back.evenement.ShowAll(this).show());
        return button;
    }

    private Button makeParticipementEventsButton() {
        Button button = new Button("ParticipementEvents");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.ijacampii.gui.back.participementEvent.ShowAll(this).show());
        return button;
    }
      private Button makeEquipementButton() {
        Button button = new Button("Equipement");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.ijacampii.gui.back.participementEvent.showAllEquipement(this).show());
        return button;
    }
 private Button makeTransportButton() {
        Button button = new Button("Transport");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.ijacampii.gui.back.participementEvent.showAllTransport(this).show());
        return button;
    }
 private Button makeUserButton() {
        Button button = new Button("Utilisateurs");
        button.setUIID("buttonMenu");
        //button.setMaterialIcon(FontImage.MATERIAL_BOOKMARK);
        button.addActionListener(action -> new com.ijacampii.gui.back.participementEvent.showAllUser(this).show());
        return button;
    }
}
