/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashbords;

import gui.Accueil.MarketController;
import gui.Eventscene2.GestionEvenementController;
import gui.Eventscene4.gestionevenementclientController;
import gui.hebergement.ListeHebergementController;
import gui.hebergement.MesReservationsController;
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
public class ClientController {

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Eventscene4/gestionevenementclient.fxml"));
            
            Parent root1 = loader.load();
            gestionevenementclientController dhc = loader.getController();
            dhc.setID(""+iduser.getText());
            dhc.setID2(""+iduser.getText());
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
    private void loadreservation(MouseEvent event) {
        try {
            //fxml = FXMLLoader.load(getClass().getResource("/gui/hebergement/MesReservations.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/hebergement/MesReservations.fxml"));
            
            Parent root1 = loader.load();
            MesReservationsController dhc = loader.getController();
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
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/OffreInterface.fxml"));
            
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void loadstore(MouseEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/equipement/StoreInterface.fxml"));
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
            fxml = FXMLLoader.load(getClass().getResource("/gui/feedback/AjouterReclamation.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    public void setID(String string) {
        this.iduser.setText(string); //To change body of generated methods, choose Tools | Templates.
    }
    
}
