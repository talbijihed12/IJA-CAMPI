/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;


import com.pidev.service.User_service;
import com.jfoenix.controls.JFXDatePicker;
import com.pidev.entities.Utilisateur;
import gui.hebergement.DetailsHebergementClientController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author nmedia
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfNum_tel;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpass;
    @FXML
    private Button btretour;
    @FXML
    private Button btsave;
    @FXML
    private RadioButton btnrole;
    @FXML
    private DatePicker tfdate;
     
    @FXML
    private BorderPane tfAnchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void tfsign(ActionEvent event) {
         Alert alert=new Alert(Alert.AlertType.WARNING);
        if(tflogin.getText().equals("")|| tfpass.getText().equals("")||tfnom.getText().equals(" ")||tfprenom.getText().equals(" ")||tfNum_tel.getText().equals(" ")||tfadresse.getText().equals(" ")){
           
         alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
          }
        else{
        alert.setAlertType(Alert.AlertType.INFORMATION);
        
        alert.setContentText("sign up has succeded");
         alert.showAndWait();

        }
        
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        
   String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
        int Num_tel = Integer.parseInt(tfNum_tel.getText());
         

         String login = tflogin.getText();
           String password = tfpass.getText();
        String role = btnrole.getText();


        Utilisateur u = new Utilisateur(Num_tel,nom,prenom,adresse,login,password,role,tfdate.getValue().format(DateTimeFormatter.ISO_DATE));
                        User_service us = new User_service();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);  
          if(tfnom.equals(" ")||tfprenom.equals(" ")||tfadresse.equals(" ")||tfNum_tel.equals(" ")||tflogin.equals(" ")||tfpass.equals(" ")||btnrole.equals(" ")){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
        }
        else{
        try{
            
        us.addOne(u);
        alert.setTitle("Succée");
        alert.setHeaderText("Ajoutée");
        alert.setContentText("Utilisateur bien inscrit..");
        
        }catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(" user n'a pas inscrit");
            alert.setContentText(ex.getMessage());
        }finally{
            alert.showAndWait();
        }
        }
}

    @FXML
    

    private void retour(ActionEvent event) {
      Parent root = null;
        try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("newLogin.fxml "));
            
            Parent root1 = loader.load();
            NewLoginController nlc = loader.getController();
            tfnom.getScene().setRoot(root1);
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
    
    }
}
