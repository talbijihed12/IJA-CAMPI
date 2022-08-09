/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class OffreLocationFormController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btrechercher;
    @FXML
    private Label lbdatedebut;
    @FXML
    private TextField tfdatedebut;
    @FXML
    private Label lbdatefin;
    @FXML
    private TextField tfdatefin;
    @FXML
    private Button btnreserver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void rechercher(KeyEvent event) {
    }

    @FXML
    private void reserver(ActionEvent event) {
    }
    
}
