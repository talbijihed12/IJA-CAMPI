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

/**
 * FXML Controller class
 *
 * @author Omar Amri
 */
public class MoyenTransportCheckController implements Initializable {
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Moyen_Transport Moyen_Transport, MyListenerTran myListener) {
        this.Moyen_Transport = Moyen_Transport;
        this.myListener = myListener;
        lbtype.setText(Moyen_Transport.getType());
        lbmatricule.setText(Moyen_Transport.getMatricule());
        lbmarque.setText(Moyen_Transport.getMarque());
        lbnbrplace.setText( String.valueOf(Moyen_Transport.getNbr_place()));
        
        
    }
}
