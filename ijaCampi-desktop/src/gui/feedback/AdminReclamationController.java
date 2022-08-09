/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.feedback;

import static gui.feedback.AfficherMerReclamationController.idR;
import com.pidev.service.ReclamationService;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
 */
public class AdminReclamationController implements Initializable {

    @FXML
    private TableColumn<Reclamation, String> description;
    @FXML
    private TableColumn<Reclamation, String> date;
    @FXML
    private TableColumn<Reclamation, String> etat;
    
    private ObservableList<Reclamation> reserData = FXCollections.observableArrayList();
    
    ReclamationService rs = new ReclamationService();
    @FXML
    private TableView<Reclamation> tabel;

    public static int idR;
    @FXML
    private Pane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                           List<Reclamation> listReser= new ArrayList<Reclamation>();
                   listReser = rs.MesafficherReclamation(1);
                   reserData.clear();
                   reserData.addAll(listReser);
                   tabel.setItems(reserData);
                   description.setCellValueFactory
                        (
                                new PropertyValueFactory<>("description")
                        );
                   date.setCellValueFactory
                        (
                                new PropertyValueFactory<>("date")
                        );
                   etat.setCellValueFactory
                        (
                                new PropertyValueFactory<>("etat")
                        );
    }    

    @FXML
    private void Traite(ActionEvent event) {
        

            
            Reclamation userSelec = (Reclamation) tabel.getSelectionModel().getSelectedItem();
            idR = userSelec.getId_r();
            
            System.err.println(rs.ReclamationById(idR).getEtat());
                    
            if(rs.ReclamationById(idR).getEtat().equals("Traité")){
               
           Alert alert = new Alert(Alert.AlertType.ERROR, "cette reclamation est déja traitée", ButtonType.OK);
           alert.showAndWait();
               }
            else{
                    
                    rs.updateReclamtion("Traité", idR);
            
                          Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/feedback/AdminReclamation.fxml"));
                            Stage myWindow = (Stage) tabel.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AfficherMerReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    }
    
}
