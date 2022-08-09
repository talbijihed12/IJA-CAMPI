/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.transport;

import com.pidev.entities.Moyen_Transport;
import com.pidev.utils.MyListenerTran;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class OffreCardFormController {


    @FXML
    private Label lbfrais;
    
    private MyListenerTran myListener;

    private Moyen_Transport Moyen_Transport;
    @FXML
    private Label lbtype;
    @FXML
    private Label lbmatricule;
    @FXML
    private Label lbmarque;
    @FXML
    private Label lbnbrplace;
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(Moyen_Transport);
    }
    
    public void setData(Moyen_Transport Moyen_Transport, MyListenerTran myListener) {
        this.Moyen_Transport = Moyen_Transport;
        this.myListener = myListener;
        lbtype.setText(Moyen_Transport.getType());
        lbfrais.setText( String.valueOf(Moyen_Transport.getFrais())+ " "+"TND");
        lbmatricule.setText(Moyen_Transport.getMatricule());
        lbmarque.setText(Moyen_Transport.getMarque());
        lbnbrplace.setText(String.valueOf(Moyen_Transport.getNbr_place()));
        
        
    }

}
