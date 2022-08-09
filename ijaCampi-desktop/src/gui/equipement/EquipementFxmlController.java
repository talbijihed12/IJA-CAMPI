/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.equipement;


import com.pidev.entities.Equipement;
import com.pidev.utils.MyListener;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author brahim
 */
public class EquipementFxmlController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    private MyListener myListener;
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(equipement);
        
    }
    /**
     * Initializes the controller class.
     */
    private Equipement equipement;
    public void setData(Equipement equipement, MyListener myListener) {
        this.equipement = equipement;
        this.myListener = myListener;
        nameLabel.setText(equipement.getNom());
        priceLable.setText( String.valueOf(equipement.getPrix())+ " "+"TND");
        try{
            Image image = new Image(getClass().getResourceAsStream(equipement.getPhoto()));
            
            img.setImage(image);
        }
        catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
        
    }
      
    
}
