/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hebergement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private TextField nameid;
    @FXML
    private TextField villeid;
    @FXML
    private TextField categorieid;
    @FXML
    private TextField capaciteid;
    @FXML
    private TextField disponibiliteid;
    @FXML
    private TextField prixid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setNameid (String message){
        this.nameid.setText(message);
    }
    public void setVilleid (String message){
        this.villeid.setText(message);
    }
    public void setCategorieid (String message){
        this.categorieid.setText(message);
    }
    public void setCapaciteid (String message){
        this.capaciteid.setText(message);
    }
    public void setDisponibiliteid (String message){
        this.disponibiliteid.setText(message);
    }
    public void setPrixid (String message){
        this.prixid.setText(message);
    }
    public void aa (ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/hebergeur.fxml"));
            Parent root1 = loader.load();
            nameid.getScene().setRoot(root1);
            //ListeHebergementController lhc = loader.getController();
            Alert fail= new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("Reservation effectuée");
            fail.setContentText("Votre ajout a été effectuée avec succès!");
            fail.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(DetailsWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
