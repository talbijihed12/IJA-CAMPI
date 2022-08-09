/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Accueil;

import com.google.zxing.WriterException;
import com.pidev.entities.evenement;
import com.pidev.service.EvenementService;
import com.pidev.utils.DBConnection;
import gui.EvenementaddController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutereventController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private TextField ddd;
    @FXML
    private DatePicker DD;
    @FXML
    private DatePicker DF;
    @FXML
    private ComboBox<String> EQ;
    @FXML
    private TextArea DESC;
    @FXML
    private TextField user;
    @FXML
    private TextField NE;
    @FXML
    private TextField NB;
    @FXML
    private TextField activite;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> tr;
    @FXML
    private ComboBox<String> hb;
     private ResultSet rs = null ;
    @FXML
    private Button ajouter;
    @FXML
    private AnchorPane Stage;
    

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementaddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
      public void initialiserlist() throws SQLException{
             try {
                 Connection cnx = DBConnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM evenement");
            while(rs.next()){
        }
            }   catch (SQLException ex) {
            Logger.getLogger(EvenementaddController.class.getName()).log(Level.SEVERE, null, ex);
        }
              Connection cnx = DBConnection.getInstance().getCnx();
         rs = cnx.createStatement().executeQuery("SELECT matricule FROM moyen_transport");
           while (rs.next()){
               tr.getItems().addAll(rs.getString("matricule"));
           
           }
            ResultSet rs1 = null ; 
        ResultSet rs2 = null ; 
        
           rs1 = cnx.createStatement().executeQuery("SELECT ville FROM hebergement");
           while (rs1.next()){
               hb.getItems().addAll(rs1.getString("ville"));
           
           }
           rs2 = cnx.createStatement().executeQuery("SELECT nom FROM equipement");
           while (rs2.next()){
               EQ.getItems().addAll(rs2.getString("nom"));
           
           }
           
        }  
      
    
      private boolean controleDeSaisi() {  

        if (NE.getText().isEmpty() /*|| user.getText().isEmpty()*/ ||  EQ.getValue().isEmpty()|| tr.getValue().isEmpty()|| hb.getValue().isEmpty()
                || DESC.getText().isEmpty()|| NB.getText().isEmpty()|| prix.getText().isEmpty()|| activite.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier les champs SVP");
            alert.showAndWait();
            return false; 
       } else {

           

           if (!Pattern.matches("[A-z]*", NE.getText())) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ nom d'événement");
            alert.showAndWait();
                NE.requestFocus();
                NE.selectEnd();
                return false;
                
            }if (!Pattern.matches("[A-z]*", DESC.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ description");
            alert.showAndWait();
                DESC.requestFocus();
                DESC.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", NB.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ nombre réservation");
            alert.showAndWait();
                NB.requestFocus();
                NB.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", prix.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ prix d'événement");
            alert.showAndWait();
                prix.requestFocus();
                prix.selectEnd();
                return false;
            }
             if (!Pattern.matches("[A-z]*", activite.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ d'activité");
            alert.showAndWait();
                activite.requestFocus();
                activite.selectEnd();
                return false;
            }
           
        }
        return true;
    }
    
    @FXML
   private void add(ActionEvent event) throws SQLException, WriterException, IOException {
       
               EvenementService es = new EvenementService();
               evenement evenement =  new evenement();
        
            if (controleDeSaisi()){
                 ResultSet rs6 = null ; 
        ResultSet rs7 = null ; 
        ResultSet rs8 = null ; 
       
        String n= tr.getValue();
        String k= hb.getValue();
        String j= EQ.getValue();
       String dated=DD.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       String datef=DF.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
                 Connection cnx = DBConnection.getInstance().getCnx();
         rs6=cnx.createStatement().executeQuery("SELECT id_transport FROM moyen_transport where matricule='"+n+"'");
         rs6.next();
         int id1=rs6.getInt("id_transport");
         rs7=cnx.createStatement().executeQuery("SELECT id_h FROM hebergement where ville='"+k+"'");
         rs7.next();
         int id2=rs7.getInt("id_h");
         rs8=cnx.createStatement().executeQuery("SELECT id FROM equipement where nom='"+j+"'");
         rs8.next();
         int id3=rs8.getInt("id");
         int y = Integer.parseInt(user.getText());
         
         int f = Integer.parseInt(NB.getText());
         
         int g = Integer.parseInt(prix.getText());
        
              //evenement evenement =  new evenement(y,id1,id2,id3,NE.getText(),DESC.getText(),dated,datef,g,f,activite.getText());
              evenement.setActivite(activite.getText());
              evenement.setDate_debut(dated);
              evenement.setDate_fin(datef);
              evenement.setDescription(DESC.getText());
              evenement.setId_equipement(id3);
              evenement.setId_hebergement(id2);
              evenement.setId_transport(id1);
              evenement.setId_user(y);
              evenement.setNbr_reservation(f);
              evenement.setNom_Event(NE.getText());
              evenement.setPrix(g);
              
              
            es.ajouter(evenement);
            
            }
            initialiserlist(); 
                
                es.qr(evenement);
        
    }

    void setID(String string) {
        //this.ddd.setText(string);
        this.user.setText(string); //To change body of generated methods, choose Tools | Templates.
    }
    
}
