
package gui.transport;

import com.pidev.entities.Moyen_Transport;
import com.pidev.entities.Utilisateur;
import com.pidev.service.MoyenTransportService;
import com.pidev.utils.DBConnection;
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
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class MoyenTransportFormController implements Initializable {
    


    private TextField tftype;
    @FXML
    private TextField tfmatricule;
    @FXML
    private TextField tfmarque;
    @FXML
    private TextField tfnbrplaces;
    @FXML
    private Button btnajoutermoyentransport;
    @FXML
    private Button btnmodifiermoyentransport;
    @FXML
    private Button btnsupprimermoyentransport;
    @FXML
    private Button btnliste;
    @FXML
    private Label lbtype;
    @FXML
    private Label lbmatricule;
    @FXML
    private Label lbmarque;
    @FXML
    private Label lbnbrplaces;
    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btrechercher;
    @FXML
    private TableView<Moyen_Transport> tableviewmoyen;
    @FXML
    private TableColumn<Moyen_Transport, String> tvtype;
    @FXML
    private TableColumn<Moyen_Transport, String> tvmatricule;
    @FXML
    private TableColumn<Moyen_Transport, String> tvmarque;
    @FXML
    private TableColumn<Moyen_Transport, Integer> tvnbrplaces;
    
    MoyenTransportService moyTSer = new MoyenTransportService();
    ObservableList<Moyen_Transport> listMoyenT = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Moyen_Transport, Integer> clId;
    @FXML
    private TextField tfId;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TableColumn<Moyen_Transport, Double> tvfrais;
    @FXML
    private Label lbnbrplaces1;
    @FXML
    private TextField tffrais;
    @FXML
    private TableColumn<Utilisateur, String> clNomUser;
    @FXML
    private TextField tfIduser;
    @FXML
    private Pane pane;


    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void afficher() {
        
        listMoyenT.clear();
        
        tvtype.setSortType(TableColumn.SortType.ASCENDING);
        tvmatricule.setSortType(TableColumn.SortType.ASCENDING);
        tvmarque.setSortType(TableColumn.SortType.ASCENDING);
        tvnbrplaces.setSortType(TableColumn.SortType.ASCENDING);
        tvfrais.setSortType(TableColumn.SortType.ASCENDING);

        
        tvtype.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, String>("type"));
        System.out.println(tvtype.getCellData(0));
        
        tvmatricule.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, String>("matricule"));
        System.out.println(tvmatricule.getCellData(1));
        
        tvmarque.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, String>("marque"));
        System.out.println(tvmarque.getCellData(2));
        
        tvnbrplaces.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, Integer>("nbr_place"));
        System.out.println(tvnbrplaces.getCellData(3));
        
        tvfrais.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, Double>("frais"));
        System.out.println(tvfrais.getCellData(4));
        
        clNomUser.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
        System.out.println(clNomUser.getCellData(4));
        
        clId.setCellValueFactory(new PropertyValueFactory<Moyen_Transport, Integer>("id_transport"));
        clId.setVisible(false);
        try {
            listMoyenT.addAll(moyTSer.afficher());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        tableviewmoyen.setItems(listMoyenT);
        

        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfId.setVisible(false); 
        tfIduser.setVisible(false);
        
        
        combobox.setItems(FXCollections.observableArrayList("Autobus", "Midibus", "Minibus", "Voiture"));
        
        Connection cnx = DBConnection.getInstance().getCnx();
        
        
        String nom1="";
        
        MoyenTransportService mSer = new MoyenTransportService();
        
        Moyen_Transport pe = new Moyen_Transport();
        
        
        try {
            nom1= mSer.afficherNomUser(pe.getId_user());
            pe.setNom_user(nom1);
        } catch (SQLException ex) {
        }
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
        String type = combobox.getValue();
        String matricule = tfmatricule.getText();
        String marque = tfmarque.getText();
        int nbr_place = parseInt(tfnbrplaces.getText());
        Double frais = parseDouble(tffrais.getText());
        
        Moyen_Transport moyt = new Moyen_Transport(nbr_place, type, matricule, marque,frais);
        MoyenTransportService moyTSer = new MoyenTransportService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(type.isEmpty() || matricule.isEmpty() || marque.isEmpty() || frais.isNaN()){
        
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
            alert.showAndWait();
        }else {
                   try{
            
        moyTSer.ajouter(moyt);
        alert.setTitle("Succée");
        alert.setHeaderText("Ajoutée");
        alert.setContentText("Moyen Transport bien Ajoutée..");
        
        listMoyenT.clear();
        afficher();
        
        tftype.setText("");
        tfmatricule.setText("");
        tfmarque.setText("");
        tfnbrplaces.setText("");
        tffrais.setText("");
        
        
        }catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erreur..!");
            alert.setHeaderText("n'est pas Ajoutée");
            alert.setContentText(ex.getMessage());
        }finally{
            alert.showAndWait();
        }
            
        }
        
 
       
        
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        
        Connection cnx = DBConnection.getInstance().getCnx();
        
        String type = combobox.getValue();
        String matricule = tfmatricule.getText();
        String marque = tfmarque.getText();
        int nbr_place = parseInt(tfnbrplaces.getText());
        Double frais = parseDouble(tffrais.getText());
        int id_transport = Integer.parseInt(tfId.getText());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        
        if(type.isEmpty() || matricule.isEmpty() || marque.isEmpty()){
        
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
            alert.showAndWait();
        }else{
            String sql = "update moyen_transport set "
                + "type = '"+type+"'"
                + ",matricule = '"+matricule+"'"
                + ",marque = '"+marque+"'"
                + ",nbr_place = "+nbr_place+""
                + " where id_transport = '" +id_transport +"'";
        
        PreparedStatement pst = cnx.prepareStatement(sql);
        pst.execute();
        
        
        
        alert.setTitle("Succée");
        alert.setHeaderText("Modifiée");
        alert.setContentText("Moyen Transport bien Modifiée..");
        
        alert.showAndWait();
        
//        listMoyenT.clear();
        afficher();
        
        combobox.setValue("");
        tfmatricule.setText("");
        tfmarque.setText("");
        tfnbrplaces.setText("");
        tffrais.setText("");
        
            
        }
        
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        


        String type = combobox.getValue();
        String matricule = tfmatricule.getText();
        String marque = tfmarque.getText();
        int nbr_place = parseInt(tfnbrplaces.getText());
        Double frais = parseDouble(tffrais.getText());
        int id_transport = Integer.parseInt(tfId.getText());
        
        Moyen_Transport moyt = new Moyen_Transport(matricule);
        MoyenTransportService moyTrSer = new MoyenTransportService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        if(type.isEmpty() || matricule.isEmpty() || marque.isEmpty()){
        
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
            alert.showAndWait();
        }else{
             try{
            
        moyTrSer.supprimer(moyt);
        alert.setTitle("Succée");
        alert.setHeaderText("Supprimée");
        alert.setContentText("Moyen Transport bien Supprimée..");
        
        combobox.setValue("");
        tfmatricule.setText("");
        tfmarque.setText("");
        tfnbrplaces.setText("");
        tffrais.setText("");
        
        }catch (SQLException ex) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Erreur..!");
            alert.setHeaderText("n'est pas Supprimée");
            alert.setContentText(ex.getMessage());
        }finally{
            alert.showAndWait();
        }
        
        listMoyenT.clear();
        afficher();
            
        }
        
        
        
       
    }

    @FXML
    private void rechercher() throws SQLException {
        
        
    MoyenTransportService moyTSer= new MoyenTransportService() ;
    List<Moyen_Transport>results = new ArrayList<>();
    results = moyTSer.afficher();
     FilteredList<Moyen_Transport> filteredData = new FilteredList<>(listMoyenT , b -> true);
		Moyen_Transport r = new Moyen_Transport();
		// 2. Set the filter Predicate whenever the filter changes.
		tfrechercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Moyen_Transport -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Moyen_Transport.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Moyen_Transport.getMatricule().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (Moyen_Transport.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (String.valueOf(r.getMarque()).indexOf(lowerCaseFilter) != -1) 
					return true;
				     else  
				    	 return false;
			});
		});
                
                
                SortedList<Moyen_Transport> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tableviewmoyen.comparatorProperty());
		
		tableviewmoyen.setItems(sortedData);
               
        
    }

    
    
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = tableviewmoyen.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = DBConnection.getInstance().getCnx();
            
            combobox.setValue(tvtype.getCellData(index).toString());
             tfmatricule.setText(tvmatricule.getCellData(index).toString());
             tfmarque.setText(tvmarque.getCellData(index).toString());
               tfnbrplaces.setText(tvnbrplaces.getCellData(index).toString());
               tffrais.setText(tvfrais.getCellData(index).toString());
               tfId.setText(clId.getCellData(index).toString());
             
    
    }
    
}
