/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.feedback;

import com.pidev.service.ReclamationService;
import entities.Reclamation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 *
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private ImageView imv;
    @FXML
    private TextArea Description;

    int c;
    int file;
    File pDir;
    File pfile;
    String lien;
    ReclamationService rs = new ReclamationService();
    @FXML
    private Pane pane;
    @FXML
    private Button Ajouter;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Gui/img/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";
    }    

    @FXML
    private void UploadImage(ActionEvent event) throws MalformedURLException {
        
                  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
        }
        
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        
                  if(file == 0|| Description.getText().equals("")){
               
           Alert alert = new Alert(Alert.AlertType.ERROR, "Complete vos cordnner", ButtonType.OK);
           alert.showAndWait();
               }
                  else {
        Reclamation r = new Reclamation();
        r.setDescription(Description.getText());
        r.setEtat("non traite");
               LocalDate dd = LocalDate.now();
               Date date = java.sql.Date.valueOf(dd);
               r.setDate((java.sql.Date) date);
               r.setIdu(1);
                copier(pfile,pDir);
               r.setPhoto(lien);
               
  
               rs.ajouterReclamation(r);
               
                    Parent fxml;
                         Stage stage;
        try {
            stage = new Stage();
            fxml = FXMLLoader.load(getClass().getResource("/gui/feedback/AfficherMerReclamation.fxml"));
            stage.setScene(new Scene(fxml));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Ajouter.getScene().getWindow());
            stage.showAndWait();
            
        } catch (IOException e){
           e.printStackTrace();
        }
                        
                  }
               

    }
    
    
     public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }
    
}
