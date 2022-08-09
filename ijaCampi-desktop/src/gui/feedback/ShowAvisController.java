/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.feedback;



import com.pidev.entities.Avis;
import com.pidev.entities.BadWords;
import com.pidev.entities.Vote;
import com.pidev.service.AvisService;
import com.pidev.service.ServiceVote;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * 
 */
public class ShowAvisController implements Initializable {

    @FXML
    private ListView<Avis> listView;
   
    ObservableList<Avis> data;
    
    public static int idE ;
    
    AvisService ds = new AvisService();

   
    @FXML
    private TextField cmt;

    AvisService as = new AvisService();
    ServiceVote sv = new ServiceVote();

    
    @FXML
    private Label deslike;
    @FXML
    private Label like;
    @FXML
    private Pane pane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            like.setText(String.valueOf(sv.NumLike()));
            deslike.setText(String.valueOf(sv.NumdeLike()));
        } catch (SQLException ex) {
            Logger.getLogger(ShowAvisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                if (idE != 0){
                  Avis avis = as.AvisById(idE);
                  cmt.setText(avis.getCommentaire());
                  
                          
        AvisService cs = new AvisService();
        try {
            data = (ObservableList<Avis>) cs.getAllAvis();
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowAvisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listView.setItems(data);
        listView.setCellFactory((ListView<Avis> param) -> new ListViewAvis());
                }
        
       else{
        
        
        
        AvisService cs = new AvisService();
        try {
            data = (ObservableList<Avis>) cs.getAllAvis();
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowAvisController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listView.setItems(data);
        listView.setCellFactory((ListView<Avis> param) -> new ListViewAvis());
        
        
        // TODO
    }
                
    }








    @FXML
    private void deslike(ActionEvent event) throws SQLException {
        
        Vote v = new Vote();
        v.setType_vote(1);
        v.setId_client(1);
        sv.addVote(v);
//          
                            try {
       Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowAvis.fxml"));
            Stage myWindow =  (Stage) deslike.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           Logger.getLogger(ShowAvisController.class.getName()).log(Level.SEVERE,null,ex);
        }
          
    }

    @FXML
    private void Commenter(ActionEvent event) {
        
        
        if(idE == 0){

        
        
        Avis a = new Avis();
        
        BadWords.loadConfigs();

        
           if(cmt.getText().equals("")){
               
           Alert alert = new Alert(Alert.AlertType.ERROR, "Complete vos cordnner", ButtonType.OK);
           alert.showAndWait();
           }else{
               
          if (BadWords.filterText(cmt.getText())) {

           Alert alert = new Alert(Alert.AlertType.ERROR, "Cette termes est inaccebtable !", ButtonType.OK);
              alert.showAndWait();
              
          }
          else{


        a.setCommentaire(cmt.getText());
        a.setIdu(2);
        as.ajouterAvis(a);
        
                                    try {
       Parent root = FXMLLoader.load(getClass().getResource("/gui/feedback/ShowAvis.fxml"));
            Stage myWindow =  (Stage) cmt.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
          }
        
           }
        }
        else{
        

           as.updateAvis(idE, cmt.getText());
           idE =0;
            
            
            
         try {
          Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowAvis.fxml"));
            Stage myWindow =  (Stage) cmt.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
        
        
        }
          
                   
                  }

    @FXML
    private void like(ActionEvent event) throws SQLException {
        
        Vote v = new Vote();
        v.setType_vote(2);
        v.setId_client(1);
       sv.addVote(v);
        
       try {
       Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowAvis.fxml"));
            Stage myWindow =  (Stage) cmt.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           Logger.getLogger(ShowAvisController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        
             ObservableList<Avis> e;
            e = listView.getSelectionModel().getSelectedItems();
            for (Avis m : e) 
           idE=m.getId_a();
            
            as.supprimerAvis(idE);
            
             try {
            Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowAvis.fxml"));
            Stage myWindow =  (Stage) cmt.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
    }

    @FXML
    private void modefier(ActionEvent event) {
        
             ObservableList<Avis> e;
            e = listView.getSelectionModel().getSelectedItems();
        
            for (Avis m : e) 
           idE=m.getId_a();
            
            
             try {
            Parent root = FXMLLoader.load(getClass().getResource("/Gui/ShowAvis.fxml"));
            Stage myWindow =  (Stage) cmt.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
           // Logger.getLogger(ReponseItemController.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
        
        
    }
        
    }

    

    

