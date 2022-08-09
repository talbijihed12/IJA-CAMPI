
package gui.transport;


import com.pidev.entities.Offre_Location;
import com.pidev.service.Offre_LocationService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Date;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class ReservationListController implements Initializable {
    

    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btrechercher;
    
    
    @FXML
    private TableColumn<Offre_Location, Integer> clId;
    @FXML
    private TextField tfId;
    private ComboBox<String> combobox;
    @FXML
    private TableView<Offre_Location> tableReservation;
    @FXML
    private TableColumn<Offre_Location, Date> tvdatedebut;
    @FXML
    private TableColumn<Offre_Location, Date> tvdatefin;
    @FXML
    private TableColumn<Offre_Location, String> tvplace;
    
    Offre_LocationService resSer = new Offre_LocationService();
    
    ObservableList<Offre_Location> listReservations = FXCollections.observableArrayList();
    @FXML
    private Button btnListReservations;
    @FXML
    private Button btnsupprimerrevision;
    @FXML
    private Pane pane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clId.setVisible(false);
        tfId.setVisible(false);
        
        afficher();
    }    


    private void modifier(ActionEvent event) throws SQLException {
        

      
    }


    @FXML
    private void rechercher() throws SQLException {
        
    //Offre_LocationService offLocService= new Offre_LocationService() ;
    //List<Offre_Location> results = new ArrayList<>();
    //results = offLocService.afficher();
     //FilteredList<Offre_Location> filteredData = new FilteredList<>(listReservations , b -> true);
		//Offre_Location o = new Offre_Location();

		//tfrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			//filteredData.setPredicate(Offre_Location -> {
								
				//if (newValue == null || newValue.isEmpty()) {
				//	return true;
				//}
				
                               // Date lowerCaseDate = newValue.toLowerCase();
				//String lowerCaseFilter = newValue.toLowerCase();
				
				//if (Offre_Location.getDate_debut().indexOf(lowerCaseFilter) != -1) {
				//	return true; 
				//}else if (Offre_Location.getDate_fin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
				//	return true; 
				//}else if (String.valueOf(o.getDate_fin()).indexOf(lowerCaseFilter) != -1) 
				//	return true;
				 //    else  
				  //  	 return false;
			//});
		//});
                
                
               // SortedList<Offre_Location> sortedData = new SortedList<>(filteredData);
		
		//sortedData.comparatorProperty().bind(tableReservation.comparatorProperty());
		
              //  tableReservation.setItems(sortedData);
               
        
    }

    @FXML
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
        
        clId.setCellValueFactory(new PropertyValueFactory<Offre_Location, Integer>("id_reservation"));
        clId.setVisible(false);
        
        listReservations.addAll(resSer.afficher());
        
        tableReservation.setItems(listReservations);
        
        
    }

    private void reserver(ActionEvent event) throws SQLException {
       
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
        int id_reservation = clId.getCellData(0);
       // LocalDate dd = tvdatefin.getValue();
       // Date date_debut = Date.valueOf(dd);
       // LocalDate df = tvdatefin.getValue();
        //Date date_fin = Date.valueOf(df);
        
        String place = tvplace.getText();
        
        Offre_Location res = new Offre_Location(id_reservation);
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
        alert.setContentText("Reservation bien Supprimée..");
        
        }catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erreur..!");
            alert.setHeaderText("n'est pas Supprimée");
            alert.setContentText(ex.getMessage());
        }finally{
            alert.showAndWait();
        }
        
    }
    }
    
}
