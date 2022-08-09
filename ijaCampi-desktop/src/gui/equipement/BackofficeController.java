/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.equipement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
/**
 * FXML Controller class
 *
 * @author brahim
 */
public class BackofficeController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btnprofile;
    @FXML
    private Button btnCommande;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnequipement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void profile(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void CommandeForm(MouseEvent event) {
        loadPage("CommandeForm");
    }

    @FXML
    private void EquipementForm(MouseEvent event) {
        loadPage("EquipementForm");
    }

  private void loadPage(String page) {
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        bp.setCenter(root);
    }

}
