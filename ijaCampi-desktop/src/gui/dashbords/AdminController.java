/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashbords;

import gui.Accueil.MarketController;
import gui.Eventscene2.GestionEvenementController;
import gui.hebergement.ListeHebergementController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author asus
 */
public class AdminController {

    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button21;
    @FXML
    private Button button211;
    @FXML
    private Button button2111;
    @FXML
    private Button button21111;
    @FXML
    private Button button211111;
    @FXML
    private Button button2111111;
    @FXML
    private Button button21111111;
    @FXML
    private Pane pane;
    private Parent fxml;
    @FXML
    private TextField iduser;

    @FXML
    private void Acceuil(MouseEvent event) {
        try {
            //fxml = FXMLLoader.load(getClass().getResource("/gui/Accueil/market.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Accueil/market.fxml"));
            
            Parent root1 = loader.load();
            MarketController dhc = loader.getController();
            dhc.setID(""+iduser.getText());
            pane.getChildren().removeAll();
            pane.getChildren().setAll(root1);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void loadGestionE(MouseEvent event) {
        try {
            //fxml = FXMLLoader.load(getClass().getResource("/gui/Eventscene2/GestionEvenement.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Eventscene2/GestionEvenement.fxml"));
            
            Parent root1 = loader.load();
            GestionEvenementController dhc = loader.getController();
            dhc.setID(""+iduser.getText());
            pane.getChildren().removeAll();
            pane.getChildren().setAll(root1);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void listehebergement(MouseEvent event) {
            try {
            //fxml = FXMLLoader.load(getClass().getResource("/gui/hebergement/ListeHebergement.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/hebergement/ListeHebergement.fxml"));
            
            Parent root1 = loader.load();
            ListeHebergementController dhc = loader.getController();
            dhc.setID(""+iduser.getText());
            pane.getChildren().removeAll();
            pane.getChildren().setAll(root1);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }


    @FXML
    private void loadtransport(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/MoyenTransportForm.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }


    @FXML
    private void avis(MouseEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/feedback/ShowAvis.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void reclamation(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/feedback/AdminReclamation.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }

    @FXML
    private void loadparticipant(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/Eventscene3/GestionPaticipant.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void reservation(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/RservationList.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void gestionequipement(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/equipement/EquipementForm.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void commande(MouseEvent event) {
          try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/equipement/CommandeForm.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    public void setID(String string) {
        this.iduser.setText(string);
    }
    
}
