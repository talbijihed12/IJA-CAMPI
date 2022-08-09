
package gui.transport;

import Services.Mail;
import com.pidev.entities.Moyen_Transport;
import com.pidev.entities.Offre_Location;
import com.pidev.service.Offre_LocationService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class ReservationFormController implements Initializable {

    @FXML
    private Label lbdatedebut;
    @FXML
    private DatePicker tfdatedebut;
    @FXML
    private Label lbdatefin;
    @FXML
    private DatePicker tfdatefin;
    @FXML
    private TextField tfId;
    @FXML
    private Button btnreserver;
    @FXML
    private TextField tfplace;
    @FXML
    private AnchorPane apres;
    
    private Parent fxml;
    @FXML
    private AnchorPane ap;
    
   private TableView<Moyen_Transport> tableReservations;
    @FXML
    private Button btnmesRes;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfId.setVisible(false);
    }    

    @FXML
    private void reserver(ActionEvent event) throws SQLException {
        

        LocalDate dd = tfdatedebut.getValue();
        Date date_debut = Date.valueOf(dd);
        LocalDate df = tfdatefin.getValue();
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


    private void OffreInterface(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/GUI/ReservationListClientForm.fxml"));
            apres.getChildren().removeAll();
            apres.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(ReservationFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ReservationListClientForm(MouseEvent event) {
        Stage stage;
        try {
            stage = new Stage();
            fxml = FXMLLoader.load(getClass().getResource("/GUI/reservationForm.fxml"));
            stage.setScene(new Scene(fxml));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnmesRes.getScene().getWindow());
            stage.showAndWait();
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }
    
    

    

    
}
