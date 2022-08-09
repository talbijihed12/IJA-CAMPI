/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.equipement;


import com.pidev.entities.Commande;
import com.pidev.service.CommandeService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
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

/**
 * FXML Controller class
 *
 * @author brahim
 */
public class CommandeFormController {

    @FXML
    private Label lbtype;
    @FXML
    private TextField tfmontant;
    @FXML
    private Label lbmatricule;
    @FXML
    private TextField tfdate;
    @FXML
    private Label lbmarque;
    @FXML
    private TextField tfadresse;
    @FXML
    private Label lbnbrplaces;
    @FXML
    private TextField tfreference;
    @FXML
    private TableView<Commande> tableviewmoyen;
    @FXML
    private TableColumn<Commande, String> tvreference;
    @FXML
    private TableColumn<Commande, Date> tvdate;
    @FXML
    private TableColumn<Commande, String> tvadresse;
    @FXML
    private TableColumn<Commande, Float> tvmontant;
    private TableColumn<Commande, Integer> tvid;
    @FXML
    private Button btnmodifierCommande;
    @FXML
    private Button btnliste;
    @FXML
    private Button btnsupprimerEquipement;
    @FXML
    private Label lbnbrplaces1;
    @FXML
    private TextField tfclient;
    @FXML
    private TextField tfrechercher;
    @FXML
    private Button btrechercher;
    ObservableList<Commande> listCommande = FXCollections.observableArrayList();
    CommandeService cs=new CommandeService();
    @FXML
    private TableColumn<Commande, Integer> tvidc;
    @FXML
    private TableColumn<Commande, Integer> tvuserid;

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void rechercher(KeyEvent event) {
    }

    private void ajouter(ActionEvent event) throws SQLException {
        String ref = tfreference.getText();
        Date date = Date.valueOf(tfdate.getText());
        String adresse = tfadresse.getText();
        float montant = 0f;

        montant = Float.valueOf(tfmontant.getText());

        Commande c = new Commande(date, ref, montant, adresse);
        CommandeService cs = new CommandeService();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (ref.equals(" ") || adresse.equals(" ") || date.equals(" ")) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle("Erreur..!");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez remplir les champs..!");
        } else {
            try {
                cs.Ajouter(c);
                alert.setTitle("Succée");
                alert.setHeaderText("Ajoutée");
                alert.setContentText("Moyen Transport bien Ajoutée..");

            } catch (Exception ex) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Erreur..!");
                alert.setHeaderText("n'est pas Ajoutée");
                alert.setContentText(ex.getMessage());
            } finally {
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

     @FXML
    private void afficher(ActionEvent event) {
        CommandeService cs=new CommandeService();
        tvidc.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("id"));
        tvdate.setCellValueFactory(new PropertyValueFactory <Commande,Date>("date"));
        tvreference.setCellValueFactory(new PropertyValueFactory<Commande,String>("reference"));
        tvadresse.setCellValueFactory(new PropertyValueFactory<Commande,String>("adresse"));
        tvmontant.setCellValueFactory(new PropertyValueFactory<Commande,Float>("montant"));               
        tvuserid.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("utilisateur_id"));
        listCommande.addAll(cs.afficher());
       
    tableviewmoyen.setItems(listCommande);
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

}
