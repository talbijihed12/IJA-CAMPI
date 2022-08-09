/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.transport;


import com.pidev.entities.Offre_Location;
import com.pidev.service.Offre_LocationService;
import com.pidev.utils.DBConnection;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.lang.String;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class ReservationListClientFormController implements Initializable {

    @FXML
    private TableColumn<Offre_Location, Date> tvdatedebut;
    @FXML
    private TableColumn<Offre_Location, Date> tvdatefin;
    @FXML
    private TableColumn<Offre_Location, String> tvplace;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnannuler;
    @FXML
    private TextField tfplace;
    @FXML
    private DatePicker pickerdd;
    @FXML
    private DatePicker pickerdf;
    @FXML
    private TextField tfIdres;
    @FXML
    private TableView<Offre_Location> tableReservation;
    
    ObservableList<Offre_Location> listReservations = FXCollections.observableArrayList();
    @FXML
    private AnchorPane tvId;
    @FXML
    private TableColumn<Offre_Location, Integer> tvIduser;

    /**
     * Initializes the controller class.
     */
    
    Offre_LocationService resSer = new Offre_LocationService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }    

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Connection cnx = DBConnection.getInstance().getCnx();
        
        String id = tfIdres.getText();
        int id_reservaion = parseInt(id);
        
        LocalDate date_debut1 = pickerdd.getValue();
        Date date_debut = Date.valueOf(date_debut1);
        
        LocalDate date_fin1 = pickerdf.getValue();
        Date date_fin = Date.valueOf(date_fin1);
        
        String place = tfplace.getText();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        
        if(place.isEmpty()){
        
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
            alert.showAndWait();
        }else{
            String sql = "update reservation_moyen_transport set "
                + "date_debut = "+date_debut+""
                + ",date_fin = "+date_fin+""
                + ",place = '"+place+"'"
                + " where id_transport = '" +id_reservaion+"'";
        
        PreparedStatement pst = cnx.prepareStatement(sql);
        pst.execute();
        
        
        
        alert.setTitle("Succée");
        alert.setHeaderText("Modifiée");
        alert.setContentText("Reservation bien Modifiée..");
        
        alert.showAndWait();
        
        
        
            
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        
        //String id = tfIdres.getText();
        //int id_reservaion = parseInt(id);
        
       // LocalDate date_debut1 = pickerdd.getValue();
        //Date date_debut = Date.valueOf(date_debut1);
        
       // LocalDate date_fin1 = pickerdf.getValue();
       // Date date_fin = Date.valueOf(date_fin1);
        
        String place = tfplace.getText();
        
        Offre_Location res = new Offre_Location(place);
        Offre_LocationService resSer = new Offre_LocationService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(place.isEmpty()){
        
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
            alert.showAndWait();
        }else{
             try{
            
        resSer.supprimer(res);
        alert.setTitle("Succée");
        alert.setHeaderText("Supprimée");
        alert.setContentText("Moyen Transport bien Supprimée..");
        
        
        }catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erreur..!");
            alert.setHeaderText("n'est pas Supprimée");
            alert.setContentText(ex.getMessage());
        }finally{
            alert.showAndWait();
        }
        
        listReservations.clear();
        afficher();
            
        }
        
        
    }
    
    private void afficher() {
        
        tvdatedebut.setSortType(TableColumn.SortType.ASCENDING);
        tvdatefin.setSortType(TableColumn.SortType.ASCENDING);
        tvplace.setSortType(TableColumn.SortType.ASCENDING);

        
        tvdatedebut.setCellValueFactory(new PropertyValueFactory<Offre_Location, Date>("date_debut"));
        //System.out.println(tvdatedebut.getCellData(0));
        
        tvdatefin.setCellValueFactory(new PropertyValueFactory<Offre_Location, Date>("date_fin"));
        System.out.println(tvdatefin.getCellData(1));
        
        tvplace.setCellValueFactory(new PropertyValueFactory<Offre_Location, String>("place"));
        //System.out.println(tvplace.getCellData(2));
        
        tvIduser.setCellValueFactory(new PropertyValueFactory<Offre_Location, Integer>("id_reservation"));
        tfIdres.setVisible(false);
        
        listReservations.addAll(resSer.afficher());
        
        tableReservation.setItems(listReservations);
        
        
    }
    
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
       
        
        int index = tableReservation.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = DBConnection.getInstance().getCnx();
            
                pickerdd.setValue(tvdatedebut.getCellData(index).toLocalDate());
                pickerdf.setValue(tvdatefin.getCellData(index).toLocalDate());

               tfplace.setText(tvplace.getCellData(index).toString());
               tfIdres.setText(tvIduser.getCellData(index).toString());
             
    
    }
    
}
