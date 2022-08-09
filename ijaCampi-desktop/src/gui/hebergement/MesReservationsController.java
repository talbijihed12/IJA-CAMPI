package gui.hebergement;

import com.pidev.entities.ReservationSimple;
import com.pidev.service.ReservationSimpleCRUD;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MesReservationsController implements Initializable {
    
    @FXML
    private TableView<ReservationSimple> tableid;
    ObservableList<ReservationSimple> h = FXCollections.observableArrayList();
    ObservableList<ReservationSimple> data = FXCollections.observableArrayList();
    ObservableList<ReservationSimple> filteredData = FXCollections.observableArrayList();
    @FXML
    private TextField filterField;
    
    int id_user;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
    @FXML
    private Pane pane;
    @FXML
    private TableColumn<ReservationSimple, String> nomhid;
    @FXML
    private TableColumn<ReservationSimple, Date> ddid;
    @FXML
    private TableColumn<ReservationSimple, Date> dfid;
    @FXML
    private TextField user;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        
        
    }    
     
    private void afficherListeReservation(ActionEvent event) {
              
//    
//        
//        
//        
  table();
    }
     public void table()
      {
         
          
                ReservationSimpleCRUD rsc = new ReservationSimpleCRUD(); 
                //h.addAll(rsc.AfficherReservationParIdUser(id_user)) ; 
                h.addAll(rsc.afficherReservation());
                filteredData.addAll(h);
                h.addListener(new ListChangeListener<ReservationSimple>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends ReservationSimple> change) {
				updateFilteredData();
			}
		});
                tableid.setItems(filteredData);
                filterField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    updateFilteredData();
          });
      
                nomhid.setSortType(TableColumn.SortType.ASCENDING);
                ddid.setSortType(TableColumn.SortType.ASCENDING);
                dfid.setSortType(TableColumn.SortType.ASCENDING);
                
                
                
                nomhid.setCellValueFactory(new PropertyValueFactory<ReservationSimple,String>("Name"));
                ddid.setCellValueFactory(new PropertyValueFactory<ReservationSimple,Date>("Ville"));
                dfid.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
                
                
                
                
                
                
                
                
                tableid.setRowFactory(tv -> {TableRow<ReservationSimple> myRow = new TableRow<>();
                myRow.setOnMouseClicked (event ->
                {
                 if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
            int myIndex =  tableid.getSelectionModel().getSelectedIndex();
           
           nomhid.setText(tableid.getItems().get(myIndex).getNameHebergement());
           ddid.setText(dateFormat.format(tableid.getItems().get(myIndex).getDateDebut()));
           dfid.setText(dateFormat.format(tableid.getItems().get(myIndex).getDateFin()));
           
                          
        }
     });
        return myRow;
                   });
}
private void updateFilteredData() {
		filteredData.clear();
			
		for (ReservationSimple h1 : h) {
			if (matchesFilter(h1)) {
				filteredData.add(h1);
			}
		}
		
		// Must re-sort table after items changed
		reapplyTableSortOrder();
	}
private boolean matchesFilter(ReservationSimple reservationSimple) {
		String filterString = filterField.getText();
		if (filterString == null || filterString.isEmpty()) {
			// No filter --> Add all.
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if (reservationSimple.getNameHebergement().toLowerCase().contains(lowerCaseFilterString)) {
			return true;
		} else if (reservationSimple.getDateDebut().equals(lowerCaseFilterString)) {
			return true;
		} else if (reservationSimple.getDateFin().equals(lowerCaseFilterString)) {
			return true;
		} 
		
		return false; // Does not match
	}
        private void reapplyTableSortOrder(){
            ArrayList<TableColumn<ReservationSimple,?>> sortOrder = new ArrayList<>(tableid.getSortOrder());
            tableid.getSortOrder().clear();
            tableid.getSortOrder().addAll(sortOrder);
        }
    @FXML
public void clickItem(MouseEvent event)
{
    if (event.getClickCount() == 2) //Checking double click
    {
        int s = tableid.getSelectionModel().getSelectedItem().getId_rs();
       
         try {
             
            
            ReservationSimpleCRUD src = new ReservationSimpleCRUD();
            ReservationSimple h1 = new ReservationSimple();
            h1 = src.AfficherReservationParId(s);
           
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AnnullerReservation.fxml "));
            
            Parent root1 = loader.load();
            AnnullerReservationController mrc = loader.getController();
            mrc.setRsid(""+h1.getId_rs());
            mrc.setDdid(dateFormat.format(h1.getDateDebut()));
            mrc.setDfid(dateFormat.format(h1.getDateFin()));
            mrc.setUserid(""+h1.getUser_id());
            mrc.setNomhid(h1.getNameHebergement());
            tableid.getScene().setRoot(root1);
            } catch (IOException e){
                System.out.println(e);
                
            }
         
    }
        
}

   public void setID(String string) {
        
        this.user.setText(string); //To change body of generated methods, choose Tools | Templates.
    }
   

    

   
}