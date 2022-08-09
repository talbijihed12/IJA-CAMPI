/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.equipement;


import com.pidev.entities.Commande;
import com.pidev.entities.LigneCommande;
import com.pidev.service.CommandeService;
import com.pidev.service.LigneCommandeService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author brahim
 */
public class ListCommandeController {
    private Commande commande;
    @FXML
    private TableView<LigneCommande> tvLc;
    private TableColumn<LigneCommande, String> tvfNom;
    @FXML
    private Label reflabel;
    @FXML
    private TextField labeladresse;
    @FXML
    private Label labelvtot;
    ObservableList<LigneCommande> listCommande = FXCollections.observableArrayList();
    @FXML
    private TableColumn<LigneCommande, Integer> tvid;
    @FXML
    private TableColumn<LigneCommande, Integer> tveq;
    @FXML
    private TableColumn<LigneCommande, Integer> tvfc;
    @FXML
    private TableColumn<LigneCommande, Integer> tvfq;
    private LigneCommandeService lcs;
    @FXML
    private Button show;
    @FXML
    private TextField idcommande;
    @FXML
    private Button btnajouter;
    private CommandeService cs;

    /**
     * Initializes the controller class.
     */
        public void SetId(String message)
        {
        this.idcommande.setText(message);
            
        
        }
        
        
        public void SetCommande(Commande commande){
            this.commande=commande;
          //  System.out.println(commande.getId());
            
        }
    public void initialize(URL url, ResourceBundle rb) {
     
    } 

    @FXML
    private void afficher(ActionEvent event) {
        LigneCommandeService lcs =new LigneCommandeService();
            //System.out.println("get id" +commande.getId());
           // System.out.println(lcs.getbyCommandeid(commande.getId()));
         tvid.setCellValueFactory(new PropertyValueFactory<LigneCommande,Integer>("id"));
        tveq.setCellValueFactory(new PropertyValueFactory<LigneCommande,Integer>("equipement_id"));
          tvfc.setCellValueFactory(new PropertyValueFactory<LigneCommande,Integer>("commande_id"));
        tvfq.setCellValueFactory(new PropertyValueFactory<LigneCommande,Integer>("quantite"));
        System.out.println( "xx"+  tvfc.getText());
        
            listCommande.addAll(lcs.getbyCommandeid(commande.getId()));
           tvLc.setItems(listCommande);
           
       ArrayList<LigneCommande> panier=new ArrayList();
             panier=lcs.getbyCommandeid(commande.getId());
                 
                this.commande.setPanier(panier);
                this.commande.setMontant(commande.total());
                this.commande.setReference("FAC"+this.commande.getId());
                labelvtot.setText(""+this.commande.getMontant());
                reflabel.setText(this.commande.getReference());
}

    @FXML
    private void ajouterCommande(ActionEvent event) throws SQLException {
       String adresse= labeladresse.getText();
       this.commande.setAdresse(adresse);
        CommandeService csl=new CommandeService();
       
      try{
          System.out.println(this.commande);
          csl.finilize(commande);
     
      }
     catch(Exception e)
     {
         System.out.println("err:"+e.getMessage());
     }
        
    }
    
    
    
}
