/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev;

import com.google.zxing.WriterException;
import com.pidev.entities.evenement;
import com.pidev.entities.participement_event;
import com.pidev.service.EvenementService;
import com.pidev.service.Serviceparticipement;
import com.pidev.utils.DBConnection;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class IjaCampi2 extends Application{
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
    }
      // DBConnection cnx=new DBConnection();
        //cnx.getCnx();
        
        //EvenementService v = new EvenementService ();
        evenement evenement = new evenement(1,1,1,1,"rrrr","ggg","2002-02-02","2022-02-02",17,10,"aucune");
        //v.afficherhebergement(1);
        EvenementService v = new EvenementService ();
        //v.afficherdes().forEach(System.out::println);
       // System.out.print(v.afficherhebergement(1));
      
        
        //System.out.println(v.afficher());
                
        //v.supprimer("rrrr");
        
        //System.out.print(v.recherche("rrrr"));
        
//        v.ajouter(evenement);
        
      /*evenement.setId_Event(1);
      evenement.setId_user(1);
      evenement.setId_avis(1);
      evenement.setId_equipement(1);
      evenement.setId_transport(1);
      evenement.setId_hebergement(1);
      evenement.setNom_Event("jihed");
      evenement.setDate_debut("06-06-2022");
      evenement.setDate_fin("07-07-2052");
      evenement.setNbr_reservation(88);
      evenement.setPrix(88);
      evenement.setActivite("jjjjj");
      v.modifier(evenement);*/
      
      //v.deleteEvenement(1);
      //System.out.println(v.getNbrreservation("rrrr"));
     
      
      
      
      
      
      
      
      /*
      
        participement_event pe = new participement_event(1,2,10);
        Serviceparticipement sp = new Serviceparticipement();
       */
        //sp.ajouter(pe);
        
        //pe.setId_Event(1);
        //pe.setId_user(1);
        //pe.setNbr_prticipement(30);
        //pe.setId_participement(2);
        //sp.modifier(pe);
        
        
        //System.out.println(sp.afficherparticipant(1));
        
        //sp.deleteparticipement(1);

    @Override
    public void start(Stage primaryStage) throws Exception {
      try{  
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/EVENEMENT"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    primaryStage.setTitle("event");
    primaryStage.setScene(scene);
    primaryStage.show();}
      catch (IOException ex){
          Logger.getLogger(IjaCampi2.class.getName()).log(Level.SEVERE,null , ex);
      }
    }
        
        
      
       
    }
    

