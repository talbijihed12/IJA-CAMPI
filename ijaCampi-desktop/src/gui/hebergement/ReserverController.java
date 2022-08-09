/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hebergement;

import com.pidev.service.ReservationSimpleCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReserverController implements Initializable {
    String name;
    String ville; 
    String categorie;
    int capacite;
    boolean disponibilite;
    int prix;

    @FXML
    private DatePicker ddid;
    @FXML
    private DatePicker dfid;
    @FXML
    private Button comfirmerid;
    @FXML
    private TextField idid;
    @FXML
    private Pane pane;
    @FXML
    private TextField user;
    @FXML
    private TextField nomh;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setId (String message){
        this.idid.setText(message);
    }
     @FXML
  private void reserver(ActionEvent event){
      ReservationSimpleCRUD rsc = new ReservationSimpleCRUD();
      int id = Integer.parseInt(idid.getText());
      LocalDate ldd = ddid.getValue();
      Date dd = Date.valueOf(ldd);
      LocalDate ldf = dfid.getValue();
      Date df = Date.valueOf(ldf);
      if (ldd.isAfter(ldf)){
          Alert fail= new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("Reservation non effectuée");
                fail.setContentText("La date que fin ne peut pas etre avant la date de début!");
                fail.showAndWait();
      }
     else if(rsc.AfficherReservationParDateDebut(dd,df)==true || rsc.AfficherReservationParDateFin(dd, df)==true) { 
          Alert fail= new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("Reservation non effectuée!");
                fail.setContentText("La date que vous avez choisi est déja reservée!");
                fail.showAndWait();
      }
      else {
      
          try {
              rsc.ajouterReservationSimple(dd,df,id,nomh.getText(),Integer.parseInt(user.getText()));
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/client.fxml"));
              Parent root1 = loader.load();
              idid.getScene().setRoot(root1);
              //ListeHebergementController lhc = loader.getController();
              Alert fail= new Alert(Alert.AlertType.INFORMATION);
                fail.setHeaderText("Reservation effectuée");
                fail.setContentText("Votre réservation a été effectuée avec succès!");
                fail.showAndWait();
              
          } catch (IOException ex) {
              Logger.getLogger(ReserverController.class.getName()).log(Level.SEVERE, null, ex);
          }
  }
  }

    void setUser(String string) {
        this.user.setText(string); //To change body of generated methods, choose Tools | Templates.
    }

    void setNomh(String name) {
        this.nomh.setText(name); //To change body of generated methods, choose Tools | Templates.
    }
}
