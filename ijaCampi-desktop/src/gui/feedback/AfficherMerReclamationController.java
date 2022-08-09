/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.feedback;

import com.pidev.service.ReclamationService;
import static gui.feedback.AdminReclamationController.idR;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
 */
public class AfficherMerReclamationController implements Initializable {

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
    private TextField recherche;
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
    private void modifier(ActionEvent event) {
        
        
                         Reclamation userSelec = (Reclamation) tabel.getSelectionModel().getSelectedItem();
                    idR = userSelec.getId_r();
                      if(rs.ReclamationById(idR).getEtat().equals("Traité")){
               
           Alert alert = new Alert(Alert.AlertType.ERROR, "cette reclamation ne peut pas étre modifier", ButtonType.OK);
           alert.showAndWait();
               }else{
                          Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/gui/feedback/ModifierReclamation.fxml"));
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

    @FXML
    private void supprimer(ActionEvent event) throws SQLDataException {
        
                
                         Reclamation userSelec = (Reclamation) tabel.getSelectionModel().getSelectedItem();
                    idR = userSelec.getId_r();
                      if(rs.ReclamationById(idR).getEtat().equals("Traité")){
               
           Alert alert = new Alert(Alert.AlertType.ERROR, "cette reclamation ne peut pas étre Supprimer", ButtonType.OK);
           alert.showAndWait();
               }else{
        
            rs.supprimerReclamation(idR);
            resetTableData();
                      }
        
    }
    
        public void resetTableData() throws SQLDataException
    {
        List<Reclamation> lisre = new ArrayList<>();
        lisre = rs.MesafficherReclamation(1);
        ObservableList<Reclamation> data = FXCollections.observableArrayList(lisre);
        tabel.setItems(data);
    }

    @FXML
    private void rechercher(ActionEvent event) {
         List<Reclamation> list = rs.MesafficherReclamation(1);
            
            //tableview.setItems(observablelist);
            
            FilteredList<Reclamation> filtredData= new FilteredList<>(reserData, b ->true);
            recherche.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Reclamation> Reservation;
                filtredData.setPredicate((Reclamation r) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                 
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                     String strDate = dateFormat.format(r.getDate());  

                    System.out.print("date"+strDate);
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(strDate.toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (r.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tabel.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabel.setItems(sortedData);
  
      
  
    }
    
    
}
