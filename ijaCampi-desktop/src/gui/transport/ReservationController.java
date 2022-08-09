/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.transport;

import Services.Mail;
import com.pidev.entities.Offre_Location;
import com.pidev.service.Offre_LocationService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class ReservationController implements Initializable {

    @FXML
    private Button btnreserver;
    @FXML
    private TextField tfplace;
    @FXML
    private DatePicker tvdatedebut;
    @FXML
    private DatePicker tvdatefin;
    @FXML
    private TextField tfId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfId.setVisible(false);
    }    

    @FXML
    private void reserver(ActionEvent event) throws SQLException {
        
         LocalDate dd = tvdatefin.getValue();
        Date date_debut = Date.valueOf(dd);
        LocalDate df = tvdatefin.getValue();
        Date date_fin = Date.valueOf(df);
        
        String place = tfplace.getText();

        
        Offre_Location offLoc = new Offre_Location(date_debut,date_fin,place);
        
        Offre_LocationService offLocService = new Offre_LocationService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
        offLocService.ajouter(offLoc);
        
        alert.setTitle("Succée");
        alert.setHeaderText("");
        alert.setContentText("Moyen Transport Resrvée..");
        alert.showAndWait();

        Mail.send(
                "amri.omar@esprit.tn",
		"213JMT1357",
		"amri.omar@isticbc.org",
		"IJACAMPI Services Clients",
		"Check : Reservation bien effectuée.."
                
	);
        
        
    }

    
}
