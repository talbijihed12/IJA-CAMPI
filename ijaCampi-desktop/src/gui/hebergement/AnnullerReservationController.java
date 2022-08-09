/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hebergement;

import com.pidev.service.ReservationSimpleCRUD;
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
public class AnnullerReservationController implements Initializable {

    @FXML
    private Pane pane;
    
    @FXML
    private Button supprimer;
    @FXML
    private TextField ddid;
    @FXML
    private TextField dfid;
    @FXML
    private TextField userid;
    @FXML
    private TextField rsid;
    @FXML
    private TextField nomhid;

    
    public void setPane(Pane pane) {
        this.pane = pane;
        // TODO
    }    

    public void setDdid(String ddid) {
        this.ddid.setText(ddid);
    }

    public void setDfid(String dfid) {
        this.dfid.setText(dfid);
    }

    public void setUserid(String userid) {
        this.userid.setText(userid);
    }

    public void setRsid(String rsid) {
        this.rsid.setText(rsid);
    }

    public void setNomhid(String nomhid) {
        this.nomhid.setText(nomhid);
    }

  

    public void setSupprimer(Button supprimer) {
        this.supprimer = supprimer;
    }

    /**
     * Initializes the controller class.
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerHebergement(ActionEvent event) {
         try {
            int id =Integer.parseInt( rsid.getText());
           
            ReservationSimpleCRUD rsc = new ReservationSimpleCRUD();
            
            rsc.supprimerReservationParId(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MesReservations.fxml "));
            
            Parent root1 = loader.load();
            MesReservationsController rs = loader.getController(); 
            
            
            nomhid.getScene().setRoot(root1);
        } catch (IOException ex) {
            Logger.getLogger(DetailsHebergementClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
