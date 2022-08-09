
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.scene.control.ImageViewButton;

/**
 * FXML Controller class
 *
 * @author nmedia
 */
public class DashbordAdminController implements Initializable {

    @FXML
    private BorderPane DashbordPane;
    private Parent fxml;
    @FXML
    private Button btlogout;
    @FXML
    private Button btnprof;
    ImageViewButton image;
    private AnchorPane tfanc;
       private Statement ste;
        User_service us=new User_service();
        ObservableList<Utilisateur>listusers=FXCollections.observableArrayList();
Connection mc=DBConnection.getInstance().getCnx();
ResultSet rst;
    @FXML
    private TextField testid;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public void setID(String message){


}
   

    @FXML
    private void actionuser(ActionEvent event) {
         loadUi("Utilisateur");
    }
    
             private void loadUi(String ui) {
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+ ".fxml"));
        } catch (IOException ex) {
            
        }
        DashbordPane.setCenter(root);
    

    }

    @FXML
    private void deconnecter(ActionEvent event) {
try{
            btlogout.getScene().getWindow().hide();
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/newLogin.fxml"));
                Stage mainStage = new Stage();
                Scene scene= new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
        }catch(Exception e){
    }}
    

    
          

    

   
    @FXML
    private void profile(MouseEvent event) {
       Stage stage;
        try {
          
            stage = new Stage();
            fxml = FXMLLoader.load(getClass().getResource("frontend.fxml"));
            stage.setScene(new Scene(fxml));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnprof.getScene().getWindow());
            stage.showAndWait();
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }
    }
   

   
    
    
    
        
   

        
        
      
        
        
        
           
    
    
    
   
    
    
    

