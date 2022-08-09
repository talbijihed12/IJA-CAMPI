/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;


import com.pidev.entities.Utilisateur;
import com.pidev.service.User_service;
import com.pidev.utils.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sun.security.util.Password;

/**
 * FXML Controller class
 *
 * @author nmedia
 */
public class PasswordforgetController implements Initializable {

   
    @FXML
    private TextField tfcpass;
 User_service us=new User_service();

        ObservableList<Utilisateur>listusers=FXCollections.observableArrayList();
    @FXML
    private Button btretour;
   
    @FXML
    private TextField tfnew;
   
    @FXML
    private BorderPane tfAnchor;
    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfid;
    @FXML
    private Button tfok;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        // TODO
    }    

    
         
   
    @FXML
    private void retour(ActionEvent event) {
       
          
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("newLogin.fxml"));
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
    
        
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException {
        Connection cnx = DBConnection.getInstance().getCnx();
       
        String newpassword = tfnew.getText();
        String cpassword  = tfcpass.getText();
         int id_user = Integer.parseInt(tfid.getText());
        
     String login = tflogin.getText();
        
        
        String sql = "update utilisateurs set "
                + ",password = '"+newpassword+"'"
                
                
                + " where id_user = '" +id_user +"'";
        
        PreparedStatement pst = cnx.prepareStatement(sql);
        pst.executeUpdate();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(tfnew.equals("") || tfcpass.equals("")) {
        
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
            alert.showAndWait();
        }
        
       alert.setTitle("Succée");
        alert.setHeaderText("Modifiée");
        alert.setContentText("Moyen Transport bien Modifiée..");
        
        alert.showAndWait();
        
       
        
        tfnew.setText("");
                tfcpass.setText("");
                
       
                        

    }

}
    
    
    

    

