/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dashbords;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author asus
 */
public class agenttransportController {

    @FXML
    private Button button;
    @FXML
    private Button button211;
    @FXML
    private Button button2111;
    @FXML
    private Pane pane;
    private Parent fxml;
    @FXML
    private TextField iduser;

    @FXML
    private void Acceuil(MouseEvent event) {
       try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/OffreInterface.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void loadtransport(MouseEvent event) {
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/MoyenTransportForm.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void reservation(MouseEvent event) {
         try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/RservationList.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }
     public void setID(String string) {
        this.iduser.setText(string); //To change body of generated methods, choose Tools | Templates.
    }
}
