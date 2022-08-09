package gui.transport;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Omar Amri
 */
public class HomeFormController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button btnprofile;
    @FXML
    private Button btntransport;
    @FXML
    private Button btnoffres;
    @FXML
    private AnchorPane ap;

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

    private void offres(MouseEvent event) {
        loadPage("offres");
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

    @FXML
    private void MoyenTransportForm(MouseEvent event) {
        loadPage("MoyenTransportForm");
    }

    private void OffreLocationForm(MouseEvent event) {
        
    }

    @FXML
    private void RservationList(MouseEvent event) {
        loadPage("RservationList");
    }
    
}
