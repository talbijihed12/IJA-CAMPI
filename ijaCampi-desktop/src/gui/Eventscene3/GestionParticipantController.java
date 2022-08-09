/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Eventscene3;

import com.pidev.entities.Utilisateur;
import com.pidev.entities.evenement;
import com.pidev.entities.participement_event;
import com.pidev.service.EvenementService;
import com.pidev.service.Serviceparticipement;
import com.pidev.utils.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class GestionParticipantController implements Initializable {

    @FXML
    private AnchorPane panelContainer;
    @FXML
    private Pane pane;
    @FXML
    private TextField filterField;
    @FXML
    private TextField ddd;
    @FXML
    private TextField user;
    @FXML
    private TableView<participement_event> RECl;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> dd;
    @FXML
    private TableColumn<?, ?> df;
    ObservableList<participement_event> list = FXCollections.observableArrayList();
    @FXML
    private TableColumn<participement_event, Integer> dess;
    @FXML
    private TableColumn<?,?> eveN;
    @FXML
    private TableColumn<?,?> evvven;
    ObservableList<?> h = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
                  

        try { 
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(GestionParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            afficherParticipant();
        } catch (SQLException ex) {
            Logger.getLogger(GestionParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void initialiserlist() throws SQLException{
        Connection cnx = DBConnection.getInstance().getCnx();
        Serviceparticipement e = new Serviceparticipement();
        String nom1="";
        String nom2="";
             try {
              
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM participement_event");
            while(rs.next()){
             
                participement_event pe=new participement_event(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
                nom1=e.affichernomutilisateur(pe.getId_user());
                nom2=e.affichernomEvent(pe.getId_Event());
                pe.setNom_user(nom1);
                pe.setNom_Event(nom2);
            list.add(pe);
                
                System.out.println(pe.getNom_Event());

                
        }
            } catch (SQLException ex) {
            Logger.getLogger(GestionParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
           
       }  
 
  private void afficherParticipant() throws SQLException{
      
                       Connection cnx = DBConnection.getInstance().getCnx();
                       Serviceparticipement e = new Serviceparticipement();
          dess.setCellValueFactory(new PropertyValueFactory<>("id_participement"));
          nom.setCellValueFactory(new PropertyValueFactory<>("id_Event"));
          dd.setCellValueFactory(new PropertyValueFactory<>("id_user"));
          df.setCellValueFactory(new PropertyValueFactory<>("nbr_prticipement"));
          eveN.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
          evvven.setCellValueFactory(new PropertyValueFactory<>("nom_Event"));

          
         
             

        
        
        RECl.setItems(list);
  }
       public participement_event gettempPArtenariat(TableColumn.CellEditEvent edittedCell) {
        participement_event test = RECl.getSelectionModel().getSelectedItem();
        return test;
    }


       @FXML
    private void getSelected(MouseEvent event) {
        
        int index = RECl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = DBConnection.getInstance().getCnx();
     
   ddd.setText(nom.getCellData(index).toString());  
    }
    
    
    
    
     
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
        TableColumn.CellEditEvent edittedcell = null;
        participement_event x = gettempPArtenariat(edittedcell);

        if (x != null) {

            int i = x.getId_participement();
            Serviceparticipement cat = new Serviceparticipement();

            int s = cat.deleteparticipement(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("participement supprimé ");
                alert.showAndWait();
            
                list.clear();
                initialiserlist(); 
                afficherParticipant();
                RECl.refresh();
          
    
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
    @FXML
     public void recherche(){
    Serviceparticipement re= new Serviceparticipement() ;
    List<participement_event>results = new ArrayList<>();
    results = re.afficher();
     FilteredList<participement_event> filteredData = new FilteredList<>(list , b -> true);
		participement_event r = new participement_event();
                evenement e= new evenement();
                Utilisateur u = new Utilisateur();
	
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(participement_event -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(r.getId_Event()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
                                }else if(String.valueOf(e.getNom_Event()).toLowerCase().indexOf(lowerCaseFilter) !=-1) {
					return true;
                                }else if(String.valueOf(u.getNom()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                                return true;
				} else if (String.valueOf(r.getId_participement()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}else if (String.valueOf(r.getId_user()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}else if (String.valueOf(r.getNbr_prticipement()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(r.getNom_Event()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		 
		SortedList<participement_event> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(RECl.comparatorProperty());
		
		RECl.setItems(sortedData);
               
        
    }
}

    
    
   


   