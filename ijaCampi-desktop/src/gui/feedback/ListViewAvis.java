/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.feedback;


import com.pidev.entities.Avis;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewAvis extends ListCell<Avis> {
    
    
     @Override
     public void updateItem(Avis e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            AvistItemController data = new AvistItemController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
