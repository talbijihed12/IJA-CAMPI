/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static javafx.application.Application.launch;
/**
 *
 * @author Youssef
 */
public class PIDEV extends Application {
     public static final String CURRENCY = "$";
    
    @Override
    public void start(Stage primaryStage) {
       try {
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/dashbords/client.fxml"));
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/user/newLogin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("AddMembre");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PIDEV.class.getName()).log(Level.SEVERE, null, ex);
        }
       
      
    }

    /**
     * @param args the command line arguments
     */
           

    public static void main(String[] args) {
        launch(args);
    }
    
}