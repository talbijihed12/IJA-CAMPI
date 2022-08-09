/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.equipement;

import com.pidev.entities.Equipement;
import com.pidev.service.EquipementService;
import com.pidev.utils.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author brahim
 */
public class EquipementFormController implements Initializable {

    @FXML
    private Label lbtype;
    @FXML
    private TextField tfnom;
    @FXML
    private Label lbmatricule;
    @FXML
    private TextField tfdescription;
    @FXML
    private Label lbmarque;
    @FXML
    private TextField tfmarque;
    @FXML
    private Label lbnbrplaces;
    @FXML
    private TextField tfcategie;
    @FXML
    private TableView<Equipement> tableviewmoyen;
    @FXML
    private TableColumn<Equipement,String> tvnom;
    @FXML
    private TableColumn<Equipement, String> tvdescription;
    @FXML
    private TableColumn<Equipement, String> tvmarque;
    @FXML
    private TableColumn<Equipement, Float> tvprix;
    @FXML
    private TableColumn<Equipement, String> tvphoto;
    @FXML
    private TableColumn<Equipement, String> tvcategorie;
    @FXML
    private Button btnajouterCommande;
    @FXML
    private Button btnmodifierCommande;
    @FXML
    private Button btnliste;
    @FXML
    private Button btnsupprimerEquipement;
    @FXML
    private Label lbnbrplaces1;
    @FXML
    private TextField tfprix;
    @FXML
    private Label lbmarque1;
    @FXML
    private TextField tfphoto;
    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btrechercher;
ObservableList<Equipement> listEquipement = FXCollections.observableArrayList();
      EquipementService es=new EquipementService();
    @FXML
    private TableColumn<Equipement, Integer> tvid;
    @FXML
    private TextField tfid;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

       @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = tableviewmoyen.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
            Connection cnx = DBConnection.getInstance().getCnx();
            tfnom.setText(tvnom.getCellData(index).toString());
            tfdescription.setText(tvdescription.getCellData(index).toString());
            tfmarque.setText(tvmarque.getCellData(index).toString());
            tfphoto.setText(tvphoto.getCellData(index).toString());
            tfcategie.setText(tvcategorie.getCellData(index).toString());
            tfprix.setText(String.valueOf(tvprix.getCellData(index)));
            tfid.setText(tvid.getCellData(index).toString());
            System.out.println();
            
    
    }

    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
        
    List<Equipement>results = new ArrayList<>();
    results = es.getall();
     FilteredList <Equipement> filteredData = new FilteredList<>( listEquipement, b -> true);
		Equipement r = new Equipement();
		// 2. Set the filter Predicate whenever the filter changes.
		tfrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Equipement -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Equipement.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Equipement.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (Equipement.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                     }else if (Equipement.getPhoto().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;   
                                        
                                        
				}else if (String.valueOf(r.getPhoto()).indexOf(lowerCaseFilter) != -1) 
					return true;
				     else  
				    	 return false;
			});
		});
                
                
                SortedList<Equipement> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tableviewmoyen.comparatorProperty());
		
		tableviewmoyen.setItems(sortedData);
               
        
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
      String nom=tfnom.getText();
      String desc=tfdescription.getText();
      String marque=tfmarque.getText();
      float prix= Float.valueOf( tfprix.getText());
      String photo=tfphoto.getText();
      String categorie=tfcategie.getText();
      // public Equipement(float prix, String nom, String marque, String description, String photo, String categorie) {
 
      Equipement e=new Equipement(prix,nom,marque,desc,photo,categorie);
      
      
      
      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(nom.equals(" ")||desc.equals(" ")||marque.equals(" ")){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText("error");
            alert.setContentText("Vous devez remplir les champs..!");
        }
        else{
        
        es.Ajouter(e);
        alert.setTitle("Succée");
        alert.setHeaderText("Ajoutée");
        alert.setContentText("Moyen Transport bien Ajoutée..");
        listEquipement.clear();
        afficher();
        alert.showAndWait();
      
        }
    }
    @FXML
    private void modifier(ActionEvent event) {
         String nom=tfnom.getText();
      String desc=tfdescription.getText();
      String marque=tfmarque.getText();
      float prix= Float.valueOf( tfprix.getText());
      String photo=tfphoto.getText();
      String categorie=tfcategie.getText();
     
      int id=Integer.parseInt(tfid.getText());
             Equipement e=new Equipement(id,   prix,nom,marque,desc,photo,categorie);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        es.modifier(e);
        alert.setTitle("Succée");
        alert.setHeaderText("Modifiée");
        alert.setContentText("Moyen Transport bien Modifiée..");
        alert.showAndWait();
        
        
    }

    @FXML
    private void afficher() throws SQLException {
   
        tvnom.setCellValueFactory(new PropertyValueFactory<Equipement,String>("nom"));
        
        tvmarque.setCellValueFactory(new PropertyValueFactory<Equipement,String>("marque"));
        tvdescription.setCellValueFactory(new PropertyValueFactory<Equipement,String>("description"));
        tvprix.setCellValueFactory(new PropertyValueFactory<Equipement,Float>("prix"));
         tvphoto.setCellValueFactory(new PropertyValueFactory<Equipement,String>("photo"));
         tvcategorie.setCellValueFactory(new PropertyValueFactory<Equipement,String>("categorie")); 
           tvid.setCellValueFactory(new PropertyValueFactory<Equipement,Integer>("id")); 
         listEquipement.addAll(es.getall());
            
         
       
            
        
        tableviewmoyen.setItems(listEquipement);    
        

        /*
     tvtype.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, String>("type"));
        System.out.println(tvtype.getCellData(0));
        
        tvmatricule.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, String>("matricule"));
        System.out.println(tvmatricule.getCellData(1));
        
        tvmarque.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, String>("marque"));
        System.out.println(tvmarque.getCellData(2));
        
        tvnbrplaces.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, Integer>("nbr_place"));
        System.out.println(tvnbrplaces.getCellData(3));
        
        try {
            listMoyenT.addAll(moyTSer.afficher());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        tableviewmoyen.setItems(listMoyenT);    
    */
    }

    @FXML
    private void supprimer(ActionEvent event) {
         String nom=tfnom.getText();
         Equipement e=new Equipement(nom);
         
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(tableviewmoyen.getItems().get(0).getId());
        es.deleteOne(tableviewmoyen.getSelectionModel().getSelectedItem().getId());
        System.out.println(tableviewmoyen.getSelectionModel().getSelectedItem().getId());
        tableviewmoyen.getItems().removeAll(tableviewmoyen.getSelectionModel().getSelectedItem());
        alert.setTitle("Succée");
        alert.setHeaderText("Supprimée");
        alert.setContentText("Moyen Transport bien Supprimée..");
        alert.showAndWait();
    }

 


 

  
    }
