/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.entities.evenement;
import com.pidev.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pidev.entities.Hebergement;
import com.pidev.entities.Moyen_Transport;
import com.pidev.entities.participement_event;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author asus
 */
public class EvenementService {
    Connection cnx = DBConnection.getInstance().getCnx();
    
     public List<evenement> afficher() {
        List<evenement> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               list.add(new evenement(rs.getInt("id_Event"),rs.getInt("id_user"),rs.getInt("id_avis"),rs.getInt("id_equipement"),rs.getInt("id_transport"),rs.getInt("id_hebergement"),rs.getString("nom_Event"), rs.getString("description"), rs.getString("date_debut"),rs.getString("date_fin"), rs.getInt("nbr_reservation"), rs.getInt("prix"), rs.getString("activite")));
             
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
        
     }
     public List<evenement> afficherdes() {
        List<evenement> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM evenement order by id_Event  desc";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               list.add(new evenement(rs.getInt("id_Event"),rs.getInt("id_user"),rs.getInt("id_avis"),rs.getInt("id_equipement"),rs.getInt("id_transport"),rs.getInt("id_hebergement"),rs.getString("nom_Event"), rs.getString("description"), rs.getString("date_debut"),rs.getString("date_fin"), rs.getInt("nbr_reservation"), rs.getInt("prix"), rs.getString("activite")));
                
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
      } 


     private Statement ste;
    private PreparedStatement pst;
    public void ajouter(evenement r) throws WriterException, UnsupportedEncodingException, IOException {
          try {
                       
            String req = "INSERT INTO evenement (id_user,id_equipement,id_transport,id_hebergement,nom_Event,description,date_debut,date_fin,nbr_reservation,prix,activite) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, r.getId_user());
            pre.setInt(2, r.getId_equipement());
            pre.setInt(3, r.getId_transport());
            pre.setInt(4, r.getId_hebergement());
            pre.setString(5, r.getNom_Event());
            pre.setString(6, r.getDescription());
            pre.setString(7, r.getDate_debut());
            pre.setString(8, r.getDate_fin());
            pre.setInt(9,r.getNbr_reservation());
            pre.setInt(10, r.getPrix());
            pre.setString(11, r.getActivite());
            
           
  
            pre.executeUpdate();
            System.out.println("Evenement Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }
    public void qr(evenement r) throws WriterException, UnsupportedEncodingException, IOException{
         String qrCodeData = r.getNom_Event();
            String filePath = "C:\\Users\\USER\\Downloads\\Nouveau dossier\\evenementCQR.png";
            String charset = "UTF-8"; 
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
            new String(qrCodeData.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
             .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
            
            
    }
    public List<evenement> recherche(String nom_Event ) {
        List<evenement> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM evenement WHERE nom_Event='"+nom_Event+"' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
               list.add(new evenement(rs.getInt("id_Event"),rs.getInt("id_user"),rs.getInt("id_avis"),rs.getInt("id_equipement"),rs.getInt("id_transport"),rs.getInt("id_hebergement"),rs.getString("nom_Event"), rs.getString("description"), rs.getString("date_debut"),rs.getString("date_fin"), rs.getInt("nbr_reservation"), rs.getInt("prix"), rs.getString("activite")));
                
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
   
    }
    public void modifier(evenement r) throws WriterException, UnsupportedEncodingException, IOException {
      try {
            String req = "UPDATE `evenement` SET id_user=?,id_avis=?,id_equipement=?,id_transport=?,id_hebergement=?,id_transport=?,nom_Event=?,description=?,date_debut=?,date_fin=?,nbr_reservation=?,prix=?,activite=? WHERE `Id_Event`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, r.getId_Event());
            pre.setInt(2, r.getId_user());
            pre.setInt(3, r.getId_avis());
            pre.setInt(4, r.getId_equipement());
            pre.setInt(5, r.getId_transport());
            pre.setInt(6, r.getId_hebergement());
            pre.setString(7, r.getNom_Event());
            pre.setString(8, r.getDescription());
            pre.setString(9, r.getDate_debut());
            pre.setString(10, r.getDate_fin());
            pre.setInt(11, r.getNbr_reservation());
            pre.setInt(12, r.getPrix());
            pre.setString(13, r.getActivite());
            pre.setInt(14, r.getId_Event());
            
            String qrCodeData = r.getNom_Event();
            String filePath = "C:\\Program Files\\QR\\evenementCQR.png";
            String charset = "UTF-8"; 
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
            new String(qrCodeData.getBytes(charset), charset),
            BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
             .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image modified successfully!");
            
             
            pre.executeUpdate();
            System.out.println("evenement Modifié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
     public int deleteEvenement(int id_Event) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `evenement` WHERE id_Event="+id_Event;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
      public List<Moyen_Transport> affichertransport(int id) {
        List<Moyen_Transport> list = new ArrayList<>();
          List<evenement> list1 = new ArrayList<>();
        try {

            
            String requete1 = "SELECT id_transport FROM evenement  where id_Event="+id+"";
            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(requete1);
             while (rs1.next()) {
             int idt=rs1.getInt("id_transport");
          
            String requete2 = "SELECT * FROM moyen_transport  where id_transport="+idt+"";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {
              list.add(new Moyen_Transport(rs2.getInt("id_user"),rs2.getString("type"),rs2.getString("matricule"),rs2.getString("marque"),rs2.getInt("nbr_place")));
            } }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
        
     }
     public void supprimer(String nom_Event) {
 try {
            String req = "DELETE FROM evenement WHERE nom_Event="+nom_Event;
            PreparedStatement pst = cnx.prepareStatement(req);
           
            pst.executeUpdate();
            System.out.println("evenement Supprimée !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
     }
     public int getNbrreservation(String nom_Event) {
        String sql="SELECT nbr_reservation FROM evenement WHERE nom_Event='"+nom_Event+"' ";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }
     public List<Hebergement> afficherhebergement(int id) {
        List<Hebergement> list = new ArrayList<>();
          List<evenement> list1 = new ArrayList<>();
        try {

            
            String requete1 = "SELECT id_h FROM evenement  where id_Event="+id+"";
            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(requete1);
             while (rs1.next()) {
             int idh=rs1.getInt("id_hebergement");
           /*
             return list1; 
             */
            String requete2 = "SELECT name,ville FROM hebergement  where id_h="+idh+"";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {
              list.add(new Hebergement(rs2.getString("nom"),rs2.getString("ville")));
            } }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
        
     }
     
      public ObservableList<evenement> getall() {
         
             ObservableList<evenement> res = FXCollections.observableArrayList();
         
         try {
            Statement stmt = cnx.createStatement();
            String sql = "SELECT * FROM evenement";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int id_Event = rs.getInt("id_Event");
               
                String nom_Event = rs.getString("nom_Event");
                String date_debut = rs.getString("date_debut");
                String date_fin= rs.getString("date_fin");
                int prix=rs.getInt("prix");
                String description=rs.getString("description");
                String activite=rs.getString("activite");
                int id_user= rs.getInt("id_user");
                evenement E = new evenement(prix,nom_Event,activite,description,date_debut,date_fin,id_Event,id_user);
               
                res.add(E);
            }
           
            rs.close();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;  
    }
      

public boolean AfficherReservationParDateDebut(Date dd,Date df){
        boolean resultat = true;
        try {
            String requete3 = "SELECT * FROM evenement WHERE date_debut between ? and ? ";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setDate(1,dd);
            pst.setDate(2,df);
            ResultSet rs = pst.executeQuery(); 
            if (rs.next() == false) { 
                resultat=false;
                //System.out.println("feragh");
            }
            else {
                //System.out.println("m3eby");
                resultat=true;
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultat;
    }
      public boolean AfficherReservationParDateFin(Date dd,Date df){
        boolean resultat = true;
        try {
            String requete3 = "SELECT * FROM evenement WHERE date_fin between ? and ? ";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setDate(1, dd);
            pst.setDate(2,df);
            ResultSet rs = pst.executeQuery(); 
            if (rs.next() == false) { 
                resultat=false;
                //System.out.println("feragh");
            }
            else {
                //System.out.println("m3eby");
                resultat=true;
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultat;
    }
}