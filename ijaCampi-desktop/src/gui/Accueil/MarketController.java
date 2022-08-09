/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Accueil;


import com.pidev.entities.evenement;
import com.pidev.entities.participement_event;
import com.pidev.service.EvenementService;
import com.pidev.service.Serviceparticipement;
import com.pidev.utils.MyListener222;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.Region;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;

/**
 * FXML Controller class
 *
 *
 */
public class MarketController implements Initializable {
    private Parent fxml;
    private MyListener222 myListener;
    @FXML
    private VBox chosenEquipementCard;
    @FXML
    private Label EqNameLable;
    @FXML
    private Label EqPriceLabel;
    @FXML
    private ImageView EquipementImg;
    @FXML
    private Label EqMarqLabel;
    @FXML
    private Label MarqLabelvalue;
    @FXML
    private TextField EqQuantiteSpinner;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Pane pane;
    @FXML
    private Button reserver;
    @FXML
    private Button ajouterevent;
     private List<evenement> evenement = new ArrayList<>();
    private TextField event;
    @FXML
    private TextField user;
    @FXML
    private TextField eventss;
    @FXML
    private TextField filterField;
   

    
      @FXML
    void ajouterevent(MouseEvent event){
        
        Stage stage;
        try {
            stage = new Stage();
            //fxml = FXMLLoader.load(getClass().getResource("/gui/Accueil/ajouterevent.fxml"));
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Accueil/ajouterevent.fxml"));
            
            Parent root1 = loader.load();
            AjoutereventController dhc = loader.getController();
           // dhc.setID(""+user.getText());
            dhc.setID(""+user.getText());
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(ajouterevent.getScene().getWindow());
            stage.showAndWait();
            
        } catch (IOException e){
           e.printStackTrace();
        }
        
    }
    
    /**
     * Initializes the controller class.
     */
    private void setChosenEquipement(evenement e) {
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
// format it as hexadecimal string and print
        String colorCode = String.format("#%06x", rand_num);
           EqNameLable.setText(e.getNom_Event());
           EqPriceLabel.setText(String.valueOf(e.getPrix())+" "+"TND");
           MarqLabelvalue.setText(e.getDescription());
           eventss.setText(String.valueOf(e.getId_Event()));
           user.setText(String.valueOf(e.getId_user()));
           chosenEquipementCard.setStyle("-fx-background-color:" +colorCode + ";\n" +
                "    -fx-background-radius: 30;");
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        EvenementService evenement=new EvenementService();
       if (evenement.getall().size() > 0) {
          setChosenEquipement(evenement.getall().get(0));
          myListener = new MyListener222() {
                @Override
                public void onClickListener(evenement e) {
                    setChosenEquipement(e);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenement.getall().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/Accueil/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                

                ItemController itemController = fxmlLoader.getController();
                
                    itemController.setData(evenement.getall().get(i),myListener);
               
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     
        
        
        
            
        
    }  
    
    @FXML
    private boolean controleDeSaisi() {  

        if (EqQuantiteSpinner.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier les champs SVP");
            alert.showAndWait();
            return false; 
       } else {

           

           if (!Pattern.matches("[0-9]*", EqQuantiteSpinner.getText())) {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("nombre réservation doit etre un entier");
            alert.showAndWait();
                EqQuantiteSpinner.requestFocus();
                EqQuantiteSpinner.selectEnd();
                return false;
                
            }
        }
           return true;
        }
        
    @FXML
     private void reserver(ActionEvent event) throws SQLException {
         if (controleDeSaisi()){
        Serviceparticipement r = new Serviceparticipement();
         int f = Integer.parseInt(user.getText());
        int g = Integer.parseInt(eventss.getText());
        int c =Integer.parseInt(EqQuantiteSpinner.getText());
                    
        r.ajouter(new participement_event(f,g,c));

           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("participation ajouté");
            alert.showAndWait();
                    }
              
     
    }

    public void setID(String string) {
          
        //this.ddd.setText(string);
        this.user.setText(string);//To change body of generated methods, choose Tools | Templates.
    } //To change body of generated methods, choose Tools | Templates.
    
     
    
}