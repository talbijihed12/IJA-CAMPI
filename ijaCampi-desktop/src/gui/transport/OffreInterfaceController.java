/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.transport;

import com.pidev.entities.Moyen_Transport;
import com.pidev.service.MoyenTransportService;
import com.pidev.utils.MyListenerTran;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.layout.Region;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class OffreInterfaceController implements Initializable {


    @FXML
    private Label lbfrais;
    private Label lbdatedebut;
    private Label lbdatefin;
    private Label lbnumtel;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
    private MyListenerTran myListener;
    @FXML
    private VBox chosenOffreCard;
    
    static int current_offer = 0;
    @FXML
    private BorderPane bp;
    @FXML
    private Button btnCheck;
    @FXML
    private Label lbtype;
    @FXML
    private Label lbmatricule;
    @FXML
    private Label lbmarque;
    @FXML
    private Label lbnbrplace;
    
    private Parent fxml;
    @FXML
    private AnchorPane app;
    @FXML
    private Pane pane;

    /**
     * Initializes the controller class.
     */
    
    private void setChosenOffres(Moyen_Transport e) {
        Random obj = new Random();
        int rand_num = obj.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", rand_num);
        lbtype.setText(e.getType());
        lbfrais.setText(String.valueOf(e.getFrais()) + " " + "TND");
        lbmatricule.setText(e.getMatricule());
        lbmarque.setText(e.getMarque());
        lbnbrplace.setText(String.valueOf(e.getNbr_place()));
        chosenOffreCard.setStyle("-fx-background-color:" + colorCode + ";\n"
                + "    -fx-background-radius: 30;");
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MoyenTransportService MoyenTransportService = new MoyenTransportService();
        try {
            if (MoyenTransportService.afficher().size() > 0) {
                //  setChosenFruit(fruits.get(0));
                setChosenOffres(MoyenTransportService.afficher().get(0));
                myListener = new MyListenerTran() {
                    
                    @Override
                    public void onClickListener(Moyen_Transport Moyen_Transport) {
                        
                        setChosenOffres(Moyen_Transport);
                        current_offer = Moyen_Transport.getId_transport();
                        
                    }
                };
            }
        } catch (SQLException ex) {
            Logger.getLogger(OffreInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < MoyenTransportService.afficher().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OffreCardForm.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                OffreCardFormController OffreCardController = fxmlLoader.getController();
                // itemController.setData(fruits.get(i),myListener);
                OffreCardController.setData(MoyenTransportService.afficher().get(i), myListener);
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
        } catch (SQLException ex) {
            Logger.getLogger(OffreInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    
    private void loadPage(String page) {
        Parent root = null;
        
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        bp.setCenter(root);
    }

    private void moyenTransportCheck(MouseEvent event) {
        loadPage("moyenTransportCheck");
    }

    @FXML
    private void reservationForm(MouseEvent event) {
        Stage stage;
        try {
            stage = new Stage();
            fxml = FXMLLoader.load(getClass().getResource("/gui/transport/Reservation.fxml"));
            stage.setScene(new Scene(fxml));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(btnCheck.getScene().getWindow());
            stage.showAndWait();
            
        } catch (IOException e){
           e.printStackTrace();
        }
    }
    
    
        
    }
    

