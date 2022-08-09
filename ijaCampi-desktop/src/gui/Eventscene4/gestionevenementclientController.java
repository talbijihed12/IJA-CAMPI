/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Eventscene4;

import com.google.zxing.WriterException;
import com.pidev.entities.evenement;
import com.pidev.service.EvenementService;

import  gui.EvenementaddController.*;
import com.pidev.utils.DBConnection;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class gestionevenementclientController implements Initializable {
    private Parent fxml;

    @FXML
    private TextField user;
    @FXML
    private TextField NE;
    @FXML
    private TextField NB;
    @FXML
    private TextField activite;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> tr;
    @FXML
    private ComboBox<String> hb;
    @FXML
    private DatePicker DD;
    @FXML
    private DatePicker DF;
    @FXML
    private ComboBox<String> EQ;
    @FXML
    private TextArea DESC;
    private Connection cnx = null; 
    private PreparedStatement pst = null ;
    private ResultSet rs = null ;
     private ResultSet rsd = null ;
    ObservableList<evenement> list = FXCollections.observableArrayList();
    @FXML
    private TableView<evenement> RECl;
    @FXML 
    private Pane pane;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> dd;
    @FXML
    private TableColumn<?, ?> df;
    @FXML
    private TableColumn<?, ?> tra;
    @FXML
    private TableColumn<?, ?> heb;
    @FXML
    private TableColumn<?, ?> desc;
    @FXML
    private TableColumn<?, ?> nbrR;
    @FXML
    private TableColumn<?, ?> equip;
    @FXML
    private TableColumn<?, ?> idU;
    @FXML
    private TableColumn<?, ?> cprix;
    @FXML
    private TableColumn<?, ?> cactivite;
    @FXML
    private TableColumn<?, ?> df1;
    @FXML
    private TableColumn<?, ?> idddd;
    @FXML
    private TextField ddd;
    @FXML
    private TextField filterField;
    @FXML
    private TextField u;
    void Acceuil(MouseEvent event){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/Accueil/market.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }
    void transport(MouseEvent event){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/OffreInterface.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }void gestionmoyentransport(MouseEvent event){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/MoyenTransportForm.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }
    void gestionreservation(MouseEvent event){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/RservationList.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }
     void storeee(MouseEvent event){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/equipement/StoreInterface.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }
    void loadGestionE(MouseEvent event){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/Eventscene2/GestionEvenement.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }
    void GestionParticipation(MouseEvent event){
        try {
            fxml = FXMLLoader.load(getClass().getResource("/gui/Eventscene3/GestionPaticipant.fxml"));
            pane.getChildren().removeAll();
            pane.getChildren().setAll(fxml);
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initialiserlist();
        } catch (SQLException ex) {
            Logger.getLogger(gestionevenementclientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficherEvenement();
    }    
      public void initialiserlist() throws SQLException{
          
          
          System.out.println(u.getText());
          int a = 3;
          try{
          a = Integer.parseInt(u.getText());
            } catch(NumberFormatException ex){ // handle your exception
    
}
          

             try {
                 Connection cnx = DBConnection.getInstance().getCnx();
                 
                 System.out.println(a);
                 String requete3 = "SELECT * FROM evenement where id_user'"+a+"'";
            PreparedStatement pst = cnx.prepareStatement(requete3);
           // pst.setInt(1,a);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new evenement(rs.getInt("id_Event"),rs.getInt("id_user"),rs.getInt("id_equipement"),rs.getInt("id_transport"),rs.getInt("id_hebergement"),rs.getString("nom_Event"),rs.getString("description"),rs.getString("date_debut"),rs.getString("date_fin"),rs.getInt("nbr_reservation"),rs.getInt("prix"),rs.getString("activite")));
        }
            }   catch (SQLException ex) {
            Logger.getLogger(gestionevenementclientController.class.getName()).log(Level.SEVERE, null, ex);
        }
              Connection cnx = DBConnection.getInstance().getCnx();
         rs = cnx.createStatement().executeQuery("SELECT matricule FROM moyen_transport");
           while (rs.next()){
            tr.getItems().addAll(rs.getString("matricule"));
           
           }
            ResultSet rs1 = null ; 
        ResultSet rs2 = null ; 
        
           rs1 = cnx.createStatement().executeQuery("SELECT ville FROM hebergement");
           while (rs1.next()){
            hb.getItems().addAll(rs1.getString("ville"));
           
           }
           rs2 = cnx.createStatement().executeQuery("SELECT nom FROM equipement");
           while (rs2.next()){
            EQ.getItems().addAll(rs2.getString("nom"));
           
           }
           
        }

    private boolean controleDeSaisi() {  

        if (NE.getText().isEmpty() /*|| user.getText().isEmpty()*/ || df.getText().isEmpty()|| dd.getText().isEmpty()
                || EQ.getValue().isEmpty()|| tr.getValue().isEmpty()|| hb.getValue().isEmpty()
                || DESC.getText().isEmpty()|| NB.getText().isEmpty()|| prix.getText().isEmpty()|| activite.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier les champs SVP");
            alert.showAndWait();
            return false; 
       } else {

           

           if (!Pattern.matches("[A-z]*", NE.getText())) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ nom d'événement");
            alert.showAndWait();
                NE.requestFocus();
                NE.selectEnd();
                return false;
                
            }if (!Pattern.matches("[A-z]*", DESC.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ description");
            alert.showAndWait();
                DESC.requestFocus();
                DESC.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", NB.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ nombre réservation");
            alert.showAndWait();
                NB.requestFocus();
                NB.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", prix.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ prix d'événement");
            alert.showAndWait();
                prix.requestFocus();
                prix.selectEnd();
                return false;
            }
             if (!Pattern.matches("[A-z]*", activite.getText())) {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier champ d'activité");
            alert.showAndWait();
                activite.requestFocus();
                activite.selectEnd();
                return false;
            }
           
        }
        return true;
    }
      @FXML
      
    private void add(ActionEvent event) throws SQLException, WriterException, IOException {
       
               EvenementService es = new EvenementService();
               evenement evenement =  new evenement();
        
            if (controleDeSaisi()){
                 ResultSet rs6 = null ; 
        ResultSet rs7 = null ; 
        ResultSet rs8 = null ; 
       
        String n= tr.getValue();
        String k= hb.getValue();
        String j= EQ.getValue();
       String dated=DD.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       String datef=DF.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
                 Connection cnx = DBConnection.getInstance().getCnx();
         rs6=cnx.createStatement().executeQuery("SELECT id_transport FROM moyen_transport where matricule='"+n+"'");
         rs6.next();
         int id1=rs6.getInt("id_transport");
         rs7=cnx.createStatement().executeQuery("SELECT id_h FROM hebergement where ville='"+k+"'");
         rs7.next();
         int id2=rs7.getInt("id_h");
         rs8=cnx.createStatement().executeQuery("SELECT id FROM equipement where nom='"+j+"'");
         rs8.next();
         int id3=rs8.getInt("id");
         int y = Integer.parseInt(user.getText());
         
         int f = Integer.parseInt(NB.getText());
         
         int g = Integer.parseInt(prix.getText());
        
              //evenement evenement =  new evenement(y,id1,id2,id3,NE.getText(),DESC.getText(),dated,datef,g,f,activite.getText());
              evenement.setActivite(activite.getText());
              evenement.setDate_debut(dated);
              evenement.setDate_fin(datef);
              evenement.setDescription(DESC.getText());
              evenement.setId_equipement(id3);
              evenement.setId_hebergement(id2);
              evenement.setId_transport(id1);
              evenement.setId_user(y);
              evenement.setNbr_reservation(f);
              evenement.setNom_Event(NE.getText());
              evenement.setPrix(g);
              
              
            es.ajouter(evenement);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Evenement ajouté");
                alert.showAndWait();
                
            initialiserlist(); 
                afficherEvenement();
                RECl.refresh();
                es.qr(evenement);
                
            
            }
            initialiserlist(); 
                afficherEvenement();
                RECl.refresh();
                es.qr(evenement);
        
    }
     private void afficherEvenement(){
       
          idddd.setCellValueFactory(new PropertyValueFactory<>("id_Event"));
          nom.setCellValueFactory(new PropertyValueFactory<>("nom_Event"));
          nbrR.setCellValueFactory(new PropertyValueFactory<>("nbr_reservation"));
          desc.setCellValueFactory(new PropertyValueFactory<>("description"));
          dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
          df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
          heb.setCellValueFactory(new PropertyValueFactory<>("id_hebergement"));
          tra.setCellValueFactory(new PropertyValueFactory<>("id_transport"));
          equip.setCellValueFactory(new PropertyValueFactory<>("id_equipement"));
          cprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          cactivite.setCellValueFactory(new PropertyValueFactory<>("activite"));
          idU.setCellValueFactory(new PropertyValueFactory<>("id_user"));
          
  
        
        
        RECl.setItems(list);
    }
            @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = RECl.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    ResultSet rs6 = null ; 
        ResultSet rs7 = null ; 
        ResultSet rs8 = null ; 
              Connection cnx = DBConnection.getInstance().getCnx();
                 hb.setValue(heb.getCellData(index).toString());   
    tr.setValue(tra.getCellData(index).toString());   
    EQ.setValue(equip.getCellData(index).toString()); 
            
         rs6=cnx.createStatement().executeQuery("SELECT matricule FROM moyen_transport where id_transport='"+tr.getValue()+"'");
         rs6.next();
         String id1=rs6.getString("matricule");
         rs7=cnx.createStatement().executeQuery("SELECT ville FROM hebergement where id_h='"+hb.getValue()+"'");
         rs7.next();
         String id2=rs7.getString("ville");
         rs8=cnx.createStatement().executeQuery("SELECT nom FROM equipement where id='"+EQ.getValue()+"'");
         rs8.next();
         String id3=rs8.getString("nom");
     
             hb.setValue(id2);   
    tr.setValue(id1);   
    EQ.setValue(id3); 
   NE.setText(nom.getCellData(index).toString());
   String v = String.valueOf(df.getCellData(index).toString());
    NB.setText(nbrR.getCellData(index).toString());
    DESC.setText(desc.getCellData(index).toString());
   DD.setValue(LocalDate.now());
   DF.setValue(LocalDate.now());
  
    prix.setText(cprix.getCellData(index).toString());   
    activite.setText(cactivite.getCellData(index).toString());   
    user.setText(idU.getCellData(index).toString());  
     try {
         ddd.setText(idddd.getCellData(index).toString());
     }  
        catch(NumberFormatException ex){ // handle your exception
    
    }
    }
 
     public evenement getEvent(TableColumn.CellEditEvent edittedCell) {
        evenement test = RECl.getSelectionModel().getSelectedItem();
        return test;
    }

      @FXML
    private void supprimerEvent(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        evenement x = getEvent(edittedcell);

        if (x != null) {

            int i = Integer.parseInt(ddd.getText()) ;
            EvenementService cat= new EvenementService();

            int s = cat.deleteEvenement(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Evenement supprimé");
                alert.showAndWait();
            
                list.clear();
                initialiserlist(); 
                afficherEvenement();
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
    public void Edit () throws SQLException{   
        if (controleDeSaisi()){
        
              
            Connection cnx = DBConnection.getInstance().getCnx();
            String value0 = ddd.getText();
            LocalDate value1 = DD.getValue();
            String value2 = user.getText();
            String value3 = activite.getText();
            String value4 = prix.getText();
            LocalDate value5 = DF.getValue();
            String value6 = DESC.getText();
            String value7 = NB.getText();
            String value8 = NE.getText();
            String value9 = EQ.getValue();
            String value10 = tr.getValue();
            String value11 = hb.getValue();
                    ResultSet rs6 = null ; 
        ResultSet rs7 = null ; 
        ResultSet rs8 = null ; 
        EvenementService es = new EvenementService();
        String n= tr.getValue();
        String k= hb.getValue();
        String j= EQ.getValue();
       String dated=DD.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
       String datef=DF.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
         rs6=cnx.createStatement().executeQuery("SELECT id_transport FROM moyen_transport where matricule='"+n+"'");
         rs6.next();
         int id1=rs6.getInt("id_transport");
         rs7=cnx.createStatement().executeQuery("SELECT id_h FROM hebergement where ville='"+k+"'");
         rs7.next();
         int id2=rs7.getInt("id_h");
         rs8=cnx.createStatement().executeQuery("SELECT id FROM equipement where nom='"+j+"'");
         rs8.next();
         int id3=rs8.getInt("id");
         int y = Integer.parseInt(user.getText());
         
         int f = Integer.parseInt(NB.getText());
         
         int g = Integer.parseInt(prix.getText());
            
            
           
            String sql = "update evenement set date_debut= '"+value1+"',id_user= '"+value2+"',activite= '"+
                    value3+"',prix= '"+value4+"',date_fin= '"+value5+"',description= '"+value6+"',nbr_reservation= '"+value7+"',nom_Event= '"+value8+"',id_equipement= '"+id3+"',id_transport= '"+id2+"',id_hebergement= '"+id1+"' where id_Event='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
                   list.clear();
                initialiserlist(); 
                afficherEvenement();
                RECl.refresh();
          
            JOptionPane.showMessageDialog(null, "Update");
            ddd.setText("");
             list.clear();
                initialiserlist(); 
                afficherEvenement();
                RECl.refresh();
    ddd.setText("");
    nom.setText("");
    user.setText("");
    activite.setText("");
    prix.setText("");
    DESC.setText("");
    NE.setText("");
    EQ.setValue("");
    tr.setValue("");
    hb.setValue("");
    NB.setText("");
       list.clear();
                initialiserlist(); 
                afficherEvenement();
                RECl.refresh();
              
          
            
       
        }
        
    }
    @FXML
     public void recherche(){
    EvenementService re= new EvenementService() ;
    List<evenement>results = new ArrayList<>();
    results = re.afficher();
     FilteredList<evenement> filteredData = new FilteredList<>(list , b -> true);
		evenement r = new evenement();
	
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(evenement -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (evenement.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (evenement.getNom_Event().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}else if (evenement.getDate_fin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
                                }else if (String.valueOf(evenement.getPrix()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
				}else if (String.valueOf(evenement.getActivite()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
				return true; 
				}else if (evenement.getDate_debut().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
                                }else if (String.valueOf(evenement.getNbr_reservation()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}else if (evenement.getDate_debut().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (String.valueOf(r.getDescription()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		 
		SortedList<evenement> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(RECl.comparatorProperty());
		
		RECl.setItems(sortedData);
               
        
    }
    /*private void loadGestionE(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Acceuil/AcceuilScene.fxml"));
        Scene scene = button.getScene();
        root.translateXProperty().set(scene.getHeight());
        pane.getChildren().add(root);
        Timeline timeline= new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);
        KeyFrame kf =new KeyFrame(Duration.seconds(1),kv);
        timeline.getKeyFrames().add(kf);
         timeline.setOnFinished(event1->{
        pane.getChildren().remove(pane);
        });
        timeline.play();
        
        
        
    }*/

    public void setID(String string) {
        this.user.setText(string); //To change body of generated methods, choose Tools | Templates.
   }
    public void setID2(String string) {
        this.u.setText(string); //To change body of generated methods, choose Tools | Templates.
   }

    
}

    
