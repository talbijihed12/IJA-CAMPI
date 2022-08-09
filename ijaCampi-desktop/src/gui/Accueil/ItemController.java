/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Accueil;

import com.pidev.PIDEV;
import com.pidev.entities.evenement;
import com.pidev.utils.DBConnection;
import com.pidev.utils.MyListener222;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



public class ItemController {
    @FXML
    private Label nameLabel;
     @FXML
    private Label date_debut;
      @FXML
    private Label date_fin;
    @FXML
    private Label priceLable;
    @FXML
    private Label img;
    private MyListener222 myListener;
    @FXML
    private TextField user;
    @FXML
    private TextField event;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(evenement);
    }
    private evenement evenement;
    public void setData(evenement evenement, MyListener222 myListener) {
        this.evenement = evenement;
        this.myListener = myListener;
              nameLabel.setText(evenement.getNom_Event());
        priceLable.setText( String.valueOf(evenement.getPrix())+ " "+"TND");
        img.setText(evenement.getDescription());
        date_debut.setText(evenement.getDate_debut());
        date_fin.setText(evenement.getDate_fin());
        user.setText(( String.valueOf(evenement.getId_user())));
        event.setText(( String.valueOf(evenement.getId_Event())));

        
        
        
    }
      
    
}

