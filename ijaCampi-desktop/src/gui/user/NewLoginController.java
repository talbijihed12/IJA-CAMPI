/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;

;
import com.pidev.entities.Utilisateur;
import com.pidev.service.User_service;
import com.pidev.utils.DBConnection;
import gui.dashbords.AdminController;
import gui.dashbords.ClientController;
import gui.dashbords.agenttransportController;
import gui.dashbords.hebergeurController;

import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import static sun.security.jgss.GSSUtil.login;

/**
 * FXML Controller class
 *
 * @author nmedia
 */
public class NewLoginController implements Initializable {

    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpass;
    @FXML
    private Button btvalider;
    @FXML
    private Button tfsignup;
    @FXML
    private BorderPane tfAnchor;
    public int id;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
private Statement ste;
Connection mc=DBConnection.getInstance().getCnx();

    @FXML
    private void Connecter(ActionEvent event) throws SQLException  {
        
        String role="";
        Utilisateur u=new Utilisateur();
        User_service us=new User_service();
         role= us.getRole(tflogin.getText());
         System.out.println(role);
          Alert alert=new Alert(Alert.AlertType.WARNING);
         
 
           int res = us.VerifParticipation(us.getId_userBynom(tflogin.getText()));
        
        if(tflogin.getText().equals("")|| tfpass.getText().equals("") ){
           
         alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
            alert.setHeaderText(null);
            alert.setContentText("You need to fill all the fields first!");
            alert.showAndWait();
          }
        
        else if(res==0){alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Conditions de saisie");
          alert.setHeaderText(null);
           alert.setContentText("You should sign up!!");
           alert.showAndWait();
       
       }
       
        if(role.contains("[ROLE_ADMIN]") ){
           
         u.setRole("[ROLE_ADMIN]");
         Parent root = null;
        try {
            //root = FXMLLoader.load(getClass().getResource(ui+ ".fxml"));
            String login =tflogin.getText();
             String pwd = tfpass.getText();
            
            User_service hc = new User_service();
            Utilisateur h1 = new Utilisateur();
            try {
                h1 = hc.affiche(login,pwd);
            } catch (SQLException ex) {
            }
           
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/admin.fxml"));
            
            Parent root1 = loader.load();
            AdminController dhc = loader.getController();
            dhc.setID(""+h1.getId_user());
            
            tflogin.getScene().setRoot(root1);
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
    
          
         }
    
        if(role.contains("ROLE_USER")){

        u.setRole("ROLE_USER");
          Parent root = null;
        try {
            //root = FXMLLoader.load(getClass().getResource(ui+ ".fxml"));
            String login =tflogin.getText();
             String pwd = tfpass.getText();
            
            User_service hc = new User_service();
            Utilisateur h1 = new Utilisateur();
            try {
                h1 = hc.affiche(login,pwd);
            } catch (SQLException ex) {
            }
           
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/client.fxml"));
            
            Parent root1 = loader.load();
            ClientController dhc = loader.getController();
            dhc.setID(""+h1.getId_user());
            
            tflogin.getScene().setRoot(root1);
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
        }
          
        
         if(role.contains("Agent_transport")){

        u.setRole("Agent_transport");
            Parent root = null;
        try {
            //root = FXMLLoader.load(getClass().getResource(ui+ ".fxml"));
            String login =tflogin.getText();
             String pwd = tfpass.getText();
            
            User_service hc = new User_service();
            Utilisateur h1 = new Utilisateur();
            try {
                h1 = hc.affiche(login,pwd);
            } catch (SQLException ex) {
            }
           
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/agenttransport.fxml"));
            
            Parent root1 = loader.load();
            agenttransportController dhc = loader.getController();
            dhc.setID(""+h1.getId_user());
            
            tflogin.getScene().setRoot(root1);
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
        }
         if(role.contains("Hebergeur")){

        u.setRole("Hebergeur");
            Parent root = null;
        try {
            //root = FXMLLoader.load(getClass().getResource(ui+ ".fxml"));
            String login =tflogin.getText();
             String pwd = tfpass.getText();
            
            User_service hc = new User_service();
            Utilisateur h1 = new Utilisateur();
            try {
                h1 = hc.affiche(login,pwd);
            } catch (SQLException ex) {
            }
           
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/client.fxml"));
            
            Parent root1 = loader.load();
            ClientController dhc = loader.getController();
            dhc.setID(""+h1.getId_user());
            
            tflogin.getScene().setRoot(root1);
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
        }
    }
      
         private void loadUi(String ui) {
        
        Parent root = null;
        try {
            //root = FXMLLoader.load(getClass().getResource(ui+ ".fxml"));
            String login =tflogin.getText();
             String pwd = tfpass.getText();
            
            User_service hc = new User_service();
            Utilisateur h1 = new Utilisateur();
            try {
                h1 = hc.affiche(login,pwd);
            } catch (SQLException ex) {
            }
           
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ui+ ".fxml"));
            
            Parent root1 = loader.load();
           // DashbordAdminController dhc = loader.getController();
           // dhc.setID(""+h1.getId_user());
            
            tflogin.getScene().setRoot(root1);
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
    

    }

    @FXML
    private void signup(ActionEvent event) {
        
        
         
          
        
        Parent root = null;
        try {
            
            root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
    

    }

       

    @FXML
    private void oublierpass(MouseEvent event) {
          
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Passwordforget.fxml"));
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
    
        
    }
    }

    
             
          

        
          
            
        
    
      
    
         
    
    


   
    
    
    
