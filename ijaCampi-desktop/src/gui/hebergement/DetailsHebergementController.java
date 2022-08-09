/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hebergement;


import com.pidev.utils.Mail;
import com.pidev.entities.Hebergement;
import com.pidev.service.HebergementCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailsHebergementController implements Initializable {
    public Hebergement h;
    private int id;
 
   
    @FXML
    private Button supprimer;
    @FXML
    private TextField nameid2;
    @FXML
    private TextField villeid2;
    @FXML
    private TextField categorieid2;
    @FXML
    private TextField capaciteid2;
    @FXML
    private TextField disponibiliteid2;
    @FXML
    private TextField prixid2;
    @FXML
    private Pane pane;
    @FXML
    private TextField nameid1;
    @FXML
    private TextField villeid1;
    @FXML
    private TextField categorieid1;
    @FXML
    private TextField capaciteid1;
    @FXML
    private TextField disponibiliteid1;
    @FXML
    private TextField prixid1;
    @FXML
    private Button modifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
   public void setNameid (String message){
        this.nameid2.setText(message);
        this.nameid1.setText(message);
    }
    public void setVilleid (String message){
        this.villeid2.setText(message);
        this.villeid1.setText(message);
    }
    public void setCategorieid (String message){
        this.categorieid2.setText(message);
        this.categorieid1.setText(message);
    }
    public void setCapaciteid (String message){
        this.capaciteid2.setText(message);
        this.capaciteid1.setText(message);
    }
    public void setDisponibiliteid (String message){
        this.disponibiliteid2.setText(message);
        this.disponibiliteid1.setText(message);
    }
    public void setPrixid (String message){
        this.prixid2.setText(message);
        this.prixid1.setText(message);
    }
    @FXML
    private void supprimerHebergement(ActionEvent event) {
        
            String name = nameid2.getText();
            String ville = villeid2.getText();
            String categorie = categorieid2.getText();
            int capacite = Integer.parseInt(capaciteid2.getText());
            boolean disponibilite =  Boolean.parseBoolean(disponibiliteid2.getText());
            int prix = Integer.parseInt(prixid2.getText());
            HebergementCRUD hc = new HebergementCRUD();
            hc.SupprimerHebergement(name,ville,categorie,capacite,prix);
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeHebergement.fxml "));
            try {
            //Parent root = loader.load();
            //DetailsWindowController dwc = loader.getController();
//            dwc.setNameid(h.getName());
//            dwc.setVilleid(h.getVille());
//            dwc.setCategorieid(h.getCategorie());
//            dwc.setCapaciteid(""+h.getCapacite());
//            dwc.setDisponibiliteid(""+h.getDisponibilite());
//            dwc.setPrixid(""+h.getPrix());
             Mail.send(
		"seif.labidi@esprit.tn",
		"211JMT2351",
		"seifeddine.labidi@isticbc.org",
		"Bienvenu sur IJA CAMPI",
		"Hebergement supprimé avec succes!");
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/client.fxml"));
              Parent root1 = loader.load();
              nameid1.getScene().setRoot(root1);
              //ListeHebergementController lhc = loader.getController();
              Alert fail= new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("Reservation effectuée");
                fail.setContentText("Votre réservation a été modifié avec succès!");
                fail.showAndWait();
	
            nameid2.getScene().setRoot(root1);
            
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
    }
    
    @FXML
    private void modifierHebergement(ActionEvent event) {
        
            String name = nameid2.getText();
            String ville = villeid2.getText();
            String categorie = categorieid2.getText();
            int capacite = Integer.parseInt(capaciteid2.getText());
            boolean disponibilite =  Boolean.parseBoolean(disponibiliteid2.getText());
            int prix = Integer.parseInt(prixid2.getText());
            String name1 = nameid1.getText();
            String ville1 = villeid1.getText();
            String categorie1 = categorieid1.getText();
            int capacite1 = Integer.parseInt(capaciteid1.getText());
            boolean disponibilite1 =  Boolean.parseBoolean(disponibiliteid1.getText());
            int prix2 = Integer.parseInt(prixid1.getText());
            HebergementCRUD hc = new HebergementCRUD();
            hc.ModifierHebergement(name, ville, categorie, capacite, disponibilite, prix, name1, ville1, categorie1, capacite1, disponibilite1, prix2);
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeHebergement.fxml "));
            try {
            //Parent root = loader.load();
            
             Mail.send(
		"seif.labidi@esprit.tn",
		"211JMT2351",
		"seifeddine.labidi@isticbc.org",
		"Bienvenu sur IJA CAMPI",
		"Hebergement modifié avec succes!");
                  
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/client.fxml"));
              Parent root1 = loader.load();
              nameid1.getScene().setRoot(root1);
              //ListeHebergementController lhc = loader.getController();
              Alert fail= new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("Reservation effectuée");
                fail.setContentText("Votre réservation a été modifié avec succès!");
                fail.showAndWait();
	
            nameid2.getScene().setRoot(root1);
	
            
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
    }
}
