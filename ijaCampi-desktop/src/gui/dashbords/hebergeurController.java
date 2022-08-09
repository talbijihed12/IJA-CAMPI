/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashbords;

import gui.Accueil.MarketController;
import gui.hebergement.HebergementController;
import gui.hebergement.MesHebergementController;
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
public class hebergeurController {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Pane pane;
    private Parent fxml;
    @FXML
    private TextField iduser;

    @FXML
    private void loadGestionE(MouseEvent event) {
        try {
           // fxml = FXMLLoader.load(getClass().getResource("/gui/hebergement/Hebergement.fxml"));
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/hebergement/Hebergement.fxml"));
            
            Parent root1 = loader.load();
            HebergementController dhc = loader.getController();
            dhc.setID(""+iduser.getText());
            pane.getChildren().removeAll();
            pane.getChildren().setAll(root1);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void loadparticipant(MouseEvent event) {
         try {
            //fxml = FXMLLoader.load(getClass().getResource("/gui/hebergement/MesHebergement.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/hebergement/MesHebergement.fxml"));
            
            Parent root1 = loader.load();
            MesHebergementController dhc = loader.getController();
            dhc.setID(""+iduser.getText());
            pane.getChildren().removeAll();
            pane.getChildren().setAll(root1);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }
     public void setID(String string) {
        this.iduser.setText(string); //To change body of generated methods, choose Tools | Templates.
    }
}
