/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hebergement;


import Services.Mail;
import com.pidev.entities.Hebergement;
import com.pidev.service.HebergementCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.lang.Boolean;
import static java.lang.Boolean.parseBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HebergementController implements Initializable {

    @FXML
    private TextField nameid;
    @FXML
    private TextField categorieid;
    @FXML
    private Button ajouterid;
    @FXML
    private TextField capaciteid;
    @FXML
    private TextField prixid;
    @FXML
    private TextField villeid;
    private boolean disponibilite;
    @FXML
    private ToggleButton nondispoid;
    @FXML
    private ToggleButton dispoid;
    @FXML
    private Pane pane;
    @FXML
    private TextField iduser;

    @FXML protected void handleToggleYesAction(ActionEvent t) {
        disponibilite = true;
    }

    @FXML protected void handleToggleNoAction(ActionEvent t) {
        disponibilite = false;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        disponibilite=true;
        dispoid.setSelected(true);
        capaciteid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                     capaciteid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        prixid.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                     prixid.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
      
    }    

    @FXML
    private void ajouterHebergement(ActionEvent event) {
            if (nameid.getText() == null || nameid.getText().trim().isEmpty()) {
                Alert fail= new Alert(AlertType.INFORMATION);
                fail.setHeaderText("Ajout impossible");
                fail.setContentText("Vous n'avez pas saisis un nom");
                fail.showAndWait();
                }
            else if (villeid.getText() == null || villeid.getText().trim().isEmpty())  {
                Alert fail= new Alert(AlertType.INFORMATION);
                fail.setHeaderText("Ajout impossible");
                fail.setContentText("Vous n'avez pas saisis une ville");
                fail.showAndWait();
            }
            else if (categorieid.getText() == null || categorieid.getText().trim().isEmpty())  {
                Alert fail= new Alert(AlertType.INFORMATION);
                fail.setHeaderText("Ajout impossible");
                fail.setContentText("Vous n'avez pas saisis une categorie");
                fail.showAndWait();
            }
            else if (capaciteid.getText() == null || capaciteid.getText().trim().isEmpty())  {
                Alert fail= new Alert(AlertType.INFORMATION);
                fail.setHeaderText("Ajout impossible");
                fail.setContentText("Vous n'avez pas saisis une capacite");
                fail.showAndWait();
            }
            else if (prixid.getText() == null || prixid.getText().trim().isEmpty())  {
                Alert fail= new Alert(AlertType.INFORMATION);
                fail.setHeaderText("Ajout impossible");
                fail.setContentText("Vous n'avez pas saisis un prix");
                fail.showAndWait();
            }
            else {
            String name = nameid.getText();
            String ville = villeid.getText();
            String categorie = categorieid.getText();
            int capacite = Integer.parseInt(capaciteid.getText());
            boolean dispo = disponibilite; 
            int prix = Integer.parseInt(prixid.getText());
            
            
            Hebergement h = new Hebergement(name,ville,categorie,capacite,dispo,prix,Integer.parseInt(iduser.getText()));
            HebergementCRUD hc = new HebergementCRUD();
            hc.ajouterHebergement2(h);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml "));
            try {
            Parent root = loader.load();
            DetailsWindowController dwc = loader.getController();
            dwc.setNameid(h.getName());
            dwc.setVilleid(h.getVille());
            dwc.setCategorieid(h.getCategorie());
            dwc.setCapaciteid(""+h.getCapacite());
            dwc.setDisponibiliteid(""+h.getDisponibilite());
            dwc.setPrixid(""+h.getPrix());
             Mail.send(
		"seif.labidi@esprit.tn",
		"211JMT2351",
		"seifeddine.labidi@isticbc.org",
		"Bienvenu sur IJA CAMPI",
		"Hebergement crée avec succes!"
	);
            nameid.getScene().setRoot(root);
            
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
            }
    }

    public void setID(String string) {
        this.iduser.setText(string); //To change body of generated methods, choose Tools | Templates.
    }
}
