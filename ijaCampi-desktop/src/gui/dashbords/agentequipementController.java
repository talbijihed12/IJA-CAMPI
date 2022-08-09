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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author asus
 */
public class agentequipementController {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button21;
    @FXML
    private Pane pane;
    private Parent fxml;

    @FXML
    private void loadGestionE(MouseEvent event) {
           try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/equipement/StoreInterface.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void loadparticipant(MouseEvent event) {
                  try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/equipement/CommandeForm.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }

    @FXML
    private void equipement(MouseEvent event) {
                try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/equipement/EquipementForm.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }
    
}
