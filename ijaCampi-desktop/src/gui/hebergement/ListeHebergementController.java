/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.hebergement;


import com.pidev.entities.Hebergement;
import com.pidev.service.HebergementCRUD;
import com.pidev.service.User_service;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
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
public class ListeHebergementController implements Initializable {
    public int currentId;
    @FXML
    private TableColumn<Hebergement, String> nameid;
    @FXML
    private TableColumn<Hebergement, String> villeid;
    @FXML
    private TableColumn<Hebergement, String> categorieid;
    @FXML
    private TableColumn<Hebergement, Integer> capaciteid;
    @FXML
    private TableColumn<Hebergement, Boolean> disponibiliteid;
    @FXML
    private TableColumn<Hebergement, Integer> prixid;
    @FXML
    private TableView<Hebergement> tableid;
    ObservableList<Hebergement> h = FXCollections.observableArrayList();
    ObservableList<Hebergement> data = FXCollections.observableArrayList();
    ObservableList<Hebergement> filteredData = FXCollections.observableArrayList();
    @FXML
    private TextField filterField;
    @FXML
    private Pane pane;
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
     public int getCurrentId() {
        return this.currentId;
    }
    private void afficherListeHebergement(ActionEvent event) {
              
//    
//        
//        
//        
  table();
    }
     public void table()
      {
         
          
                HebergementCRUD hc = new HebergementCRUD(); 
                h.addAll(hc.afficherHebergement()) ; 
                filteredData.addAll(h);
                h.addListener(new ListChangeListener<Hebergement>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Hebergement> change) {
				updateFilteredData();
			}
		});
                tableid.setItems(filteredData);
                filterField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    updateFilteredData();
          });
      
                nameid.setSortType(TableColumn.SortType.ASCENDING);
                villeid.setSortType(TableColumn.SortType.ASCENDING);
                categorieid.setSortType(TableColumn.SortType.ASCENDING);
                prixid.setSortType(TableColumn.SortType.ASCENDING);
                categorieid.setSortType(TableColumn.SortType.ASCENDING);
                disponibiliteid.setSortType(TableColumn.SortType.ASCENDING);
                nameid.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("Name"));
                villeid.setCellValueFactory(new PropertyValueFactory<Hebergement,String>("Ville"));
                categorieid.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
                capaciteid.setCellValueFactory(new PropertyValueFactory<>("Capacite"));
                disponibiliteid.setCellValueFactory(new PropertyValueFactory<>("Disponibilite"));
                prixid.setCellValueFactory(new PropertyValueFactory<>("Prix"));
                
                
                
                
                
                tableid.setRowFactory( tv -> {TableRow<Hebergement> myRow = new TableRow<>();
                myRow.setOnMouseClicked (event ->
                {
                 if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
            int myIndex =  tableid.getSelectionModel().getSelectedIndex();
           
           nameid.setText(tableid.getItems().get(myIndex).getName());
           villeid.setText(tableid.getItems().get(myIndex).getVille());
           categorieid.setText(tableid.getItems().get(myIndex).getCategorie());
           capaciteid.setText(String.valueOf(tableid.getItems().get(myIndex).getCapacite()));
           disponibiliteid.setText(String.valueOf(tableid.getItems().get(myIndex).getDisponibilite()));
           prixid.setText(String.valueOf(tableid.getItems().get(myIndex).getPrix()));
           
                          
        }
     });
        return myRow;
                   });
}
private void updateFilteredData() {
		filteredData.clear();
			
		for (Hebergement h1 : h) {
			if (matchesFilter(h1)) {
				filteredData.add(h1);
			}
		}
		
		// Must re-sort table after items changed
		reapplyTableSortOrder();
	}
private boolean matchesFilter(Hebergement hebergement) {
		String filterString = filterField.getText();
		if (filterString == null || filterString.isEmpty()) {
			// No filter --> Add all.
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if (hebergement.getName().toLowerCase().contains(lowerCaseFilterString)) {
			return true;
		} else if (hebergement.getCategorie().toLowerCase().contains(lowerCaseFilterString)) {
			return true;
		} else if (hebergement.getVille().toLowerCase().contains(lowerCaseFilterString)) {
			return true;
		} 
		
		return false; // Does not match
	}
        private void reapplyTableSortOrder(){
            ArrayList<TableColumn<Hebergement,?>> sortOrder = new ArrayList<>(tableid.getSortOrder());
            tableid.getSortOrder().clear();
            tableid.getSortOrder().addAll(sortOrder);
        }
    @FXML
public void clickItem(MouseEvent event)
{
    if (event.getClickCount() == 2) //Checking double click
    {
        int s = tableid.getSelectionModel().getSelectedItem().getId();
       
         try {
             
            String a = null;
            HebergementCRUD hc = new HebergementCRUD();
            Hebergement h1 = new Hebergement();
            h1 = hc.afficherHebergementParId(s);
            User_service us = new User_service();
            FXMLLoader loader = new FXMLLoader();
            try {   
                     a= us.getroleparid(parseInt(user.getText()));
                } catch (NumberFormatException nfe) {
  
                }
           if (a.contains("Camper")) {
            
            loader = new FXMLLoader(getClass().getResource("/gui/hebergement/DetailsHebergementClient.fxml"));
            Parent root1 = loader.load();
            DetailsHebergementClientController dhc = loader.getController();
            dhc.setNameid(h1.getName());
            dhc.setVilleid(h1.getVille());
            dhc.setCategorieid(h1.getCategorie());
            dhc.setCapaciteid(""+h1.getCapacite());
            dhc.setDisponibiliteid(""+h1.getDisponibilite());
            dhc.setPrixid(""+h1.getPrix());
            dhc.setUser(""+user.getText());
            tableid.getScene().setRoot(root1);
           }
           //else if (us.getroleparid(parseInt(user.getText())).contains("Admin")|| us.getroleparid(parseInt(user.getText())).contains("Hebergeur")) {
           else if (a.contains("Admin")) {
               loader = new FXMLLoader(getClass().getResource("/gui/hebergement/DetailsHebergement.fxml "));
               Parent root1 = loader.load();
            DetailsHebergementController dhc = loader.getController();
            dhc.setNameid(h1.getName());
            dhc.setVilleid(h1.getVille());
            dhc.setCategorieid(h1.getCategorie());
            dhc.setCapaciteid(""+h1.getCapacite());
            dhc.setDisponibiliteid(""+h1.getDisponibilite());
            dhc.setPrixid(""+h1.getPrix());
            
            tableid.getScene().setRoot(root1);
           }
            
            
            } catch (IOException e){
                System.out.println(e);
                
            }
         
    }
        
}

    public void setID(String string) {
        
        this.user.setText(string); //To change body of generated methods, choose Tools | Templates.
    }

   

    

   
}
