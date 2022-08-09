/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hebergement;


import com.pidev.entities.Hebergement;
import com.pidev.service.HebergementCRUD;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailsHebergementClientController implements Initializable {

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
    private Button reserverid;
    @FXML
    private Pane pane;
    @FXML
    private TextField user;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setNameid (String message){
        this.nameid2.setText(message);
    }
    public void setVilleid (String message){
        this.villeid2.setText(message);
    }
    public void setCategorieid (String message){
        this.categorieid2.setText(message);
    }
    public void setCapaciteid (String message){
        this.capaciteid2.setText(message);
    }
    public void setDisponibiliteid (String message){
        this.disponibiliteid2.setText(message);
    }
    public void setPrixid (String message){
        this.prixid2.setText(message);
    }
    @FXML
    private void reserver(ActionEvent event){
        try {
            String name = nameid2.getText();
            String ville = villeid2.getText();
            String categorie = categorieid2.getText();
            int capacite = Integer.parseInt(capaciteid2.getText());
            boolean disponibilite =  Boolean.parseBoolean(disponibiliteid2.getText());
            int prix = Integer.parseInt(prixid2.getText());
            HebergementCRUD hc = new HebergementCRUD();
            Hebergement h = new Hebergement();
            h=hc.afficherHebergementSansId(name, ville, categorie, capacite, disponibilite, prix);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reserver.fxml "));
            
            Parent root1 = loader.load();
            ReserverController rc = loader.getController();
            rc.setId(""+h.getId());
            rc.setNomh(h.getName());
            rc.setUser(""+user.getText());
            
            nameid2.getScene().setRoot(root1);
        } catch (IOException ex) {
            Logger.getLogger(DetailsHebergementClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    void setUser(String string) {
        this.user.setText(string); //To change body of generated methods, choose Tools | Templates.
    }
}
