/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.user;


import com.pidev.service.User_service;

import com.jfoenix.controls.JFXDatePicker;
import com.pidev.entities.Utilisateur;
import com.pidev.utils.DBConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author nmedia
 */
public class UtilisateurController implements Initializable {

   
    @FXML
    private TableColumn<Utilisateur, String> tvnom;
    @FXML
    private TableColumn<Utilisateur,String> tvprenom;
    @FXML
    private TableColumn<Utilisateur,Integer> tvnum_tel;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnom;
    
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tflogin;
    @FXML
    private PasswordField tfpass;
    @FXML
    private Button btnajouteruser;
    @FXML
    private Button btnmodifieruser;
    @FXML
    private Button btnsupprimeruser;
    @FXML
    private Button btnliste;
    @FXML
    private TextField tfrechercher;
    @FXML
    private TableColumn<Utilisateur,String> tvadresse;
    @FXML
    private TableColumn<Utilisateur,DatePicker> tvdate;
    @FXML
    private TableColumn<Utilisateur,String> tvrol;
    @FXML
    private TableColumn<Utilisateur,String> tvlogin;
    @FXML
    private TableColumn<Utilisateur,String> tvpass;
    @FXML
    private TableView<Utilisateur> tableviewuser;
    @FXML
    private RadioButton btnrole;
    
     User_service us=new User_service();

        ObservableList<Utilisateur>listusers=FXCollections.observableArrayList();
    @FXML
    private DatePicker tfdate;
    
   
    @FXML
    private TextField tfnum_tel;
    @FXML
    private BorderPane tfAnchor;
    @FXML
    private TextField tfid;
    @FXML
    private AnchorPane tfa;
    @FXML
    private RadioButton btnrole1;
    @FXML
    private RadioButton btnrole2;
    @FXML
    private RadioButton btnrole3;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         }    

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent  event) {
         int index = tableviewuser.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = DBConnection.getInstance().getCnx();
            
            tfnom.setText(tvnom.getCellData(index).toString());
   
             tfprenom.setText(tvprenom.getCellData(index).toString());
             tfadresse.setText(tvadresse.getCellData(index).toString());
        tflogin.setText(tvlogin.getCellData(index).toString());
       tfpass.setText(tvpass.getCellData(index).toString());
       btnrole.setText(tvrol.getCellData(index).toString());
       tfnum_tel.setText(tvnum_tel.getCellData(index).toString());
    
    
    
    
    }
    
    

    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
        User_service us= new User_service() ;
    List<Utilisateur>results = new ArrayList<>();
    results = us.getAll();
     FilteredList<Utilisateur> filteredData = new FilteredList<>(listusers , b -> true);
		Utilisateur r = new Utilisateur();
		// 2. Set the filter Predicate whenever the filter changes.
		tfrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Utilisateur -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Utilisateur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Utilisateur.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (Utilisateur.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                     }else if (Utilisateur.getLogin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;   
                                        }else if (Utilisateur.getMdp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
                                        }else if (Utilisateur.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
                                        
				}else if (String.valueOf(r.getNom()).indexOf(lowerCaseFilter) != -1) 
					return true;
				     else  
				    	 return false;
			});
		});
                
                
                SortedList<Utilisateur> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tableviewuser.comparatorProperty());
		
		tableviewuser.setItems(sortedData);
               
        
    }


    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        Connection cnx = DBConnection.getInstance().getCnx();
       
       
      
         String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
                String Date_naissance = tfadresse.getText();

        int Num_tel = Integer.parseInt(tfnum_tel.getText());

         String login = tflogin.getText();
           String password = tfpass.getText();
        String role = btnrole.getText();
        int id_user=Integer.parseInt(tfid.getText());
         String sql = "update utilisateurs set "
                + "nom = '"+nom+"'"
                + ",prenom = '"+prenom+"'"
                + ",adresse = '"+adresse+"'"
                + ",num_tel = "+Num_tel+""
                + ",date_naissance = "+Date_naissance+""
                  + ",login = "+login+""
                  + ",password = "+password+""
                 +",rile = "+role+""

                + " where ud_user = '" +id_user +"'";
        
        PreparedStatement pst = cnx.prepareStatement(sql);
        pst.executeUpdate(sql);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(tfnom.equals(" ")||tfprenom.equals(" ")||tfadresse.equals(" ")||tfnum_tel.equals(" ")||tflogin.equals(" ")||tfpass.equals(" ")||btnrole.equals(" ")){
        
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
            alert.showAndWait();
        }
        
        alert.setTitle("Succée");
        alert.setHeaderText("Modifiée");
        alert.setContentText("Moyen Transport bien Modifiée..");
        
        alert.showAndWait();
        
        listusers.clear();
        afficher();
        
        tfnom.setText("");
                tfprenom.setText("");
        tfadresse.setText("");
                tflogin.setText("");
                        tfpass.setText("");
                        tfnum_tel.setText("");
                        


       
    }


    @FXML
    private void supprimer(ActionEvent event) {
    String nom = tfnom.getText();
        
        Utilisateur u = new Utilisateur(nom);
        User_service us = new User_service();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        try{
            
        us.deleteOne1(tableviewuser.getSelectionModel().getSelectedItem().getId_user());
        tableviewuser.getItems().removeAll(tableviewuser.getSelectionModel().getSelectedItem());
        
        alert.setTitle("Succée");
        alert.setHeaderText("Supprimée");
        alert.setContentText("Utilisateur bien Supprimée..");
        
        }catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erreur..!");
            alert.setHeaderText("n'est pas Supprimée");
            alert.setContentText(ex.getMessage());
        }finally{
            alert.showAndWait();
        }
    }
   
    

    @FXML
    private void afficher() {
         tvnom.setCellValueFactory(new PropertyValueFactory<Utilisateur,String>("nom"));
        System.out.println(tvnom.getCellData(0));
        
        tvprenom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("prenom"));
        System.out.println(tvprenom.getCellData(1));
        
        tvnum_tel.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("Num_tel"));
        System.out.println(tvnum_tel.getCellData(2));
        
        tvadresse.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("adresse"));
        System.out.println(tvadresse.getCellData(3));
          tvlogin.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("login"));
        System.out.println(tvlogin.getCellData(4));
          tvdate.setCellValueFactory(new PropertyValueFactory<Utilisateur, DatePicker>("date_naissance"));
        System.out.println(tvdate.getCellData(5));
          tvrol.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("role"));
        System.out.println(tvrol.getCellData(6));
          tvpass.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("password"));
        System.out.println(tvpass.getCellData(7));
        
        
        
        try {
            listusers.addAll(us.getAll());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        tableviewuser.setItems(listusers);
        
    
    }

    @FXML
    private void ajouteruser(ActionEvent event) {
         String nom = tfnom.getText();
        String prenom = tfprenom.getText();
        String adresse = tfadresse.getText();
        int Num_tel = Integer.parseInt(tfnum_tel.getText());
         

         String login = tflogin.getText();
           String password = tfpass.getText();
        String role = btnrole.getText();


        Utilisateur u = new Utilisateur(Num_tel,nom,prenom,adresse,login,password,role,tfdate.getValue().format(DateTimeFormatter.ISO_DATE));
                        User_service us = new User_service();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(tfnom.equals(" ")||tfprenom.equals(" ")||tfadresse.equals(" ")||tfnum_tel.equals(" ")||tflogin.equals(" ")||tfpass.equals(" ")||btnrole.equals(" ")){
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
        }
        else{
        try{
            
        us.addOne(u);
        alert.setTitle("Succée");
        alert.setHeaderText("Ajoutée");
        alert.setContentText("Utilisateur bien Ajoutée..");
        
        }catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(" user n'est pas Ajoutée");
            alert.setContentText(ex.getMessage());
        }finally{
            alert.showAndWait();
            
            listusers.clear();
            afficher();
        }
        }
        
    }

    private void deconnecter(ActionEvent event) {
          loadUi("NewLogin");
    }
     private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            
        }
        tfAnchor.setCenter(root);
    
    }
    }
       




