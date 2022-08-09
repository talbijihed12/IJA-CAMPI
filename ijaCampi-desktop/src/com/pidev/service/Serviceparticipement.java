/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.entities.Utilisateur;
import com.pidev.entities.evenement;
import com.pidev.entities.participement_event;
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

/**
 *
 * @author asus
 */
public class Serviceparticipement {
    Connection cnx = DBConnection.getInstance().getCnx();
    
     public List<participement_event> afficher() {
        List<participement_event> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM participement_event";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                String nom = "jihed";
               list.add(new participement_event(rs.getInt("id_participement"),rs.getInt("id_Event"),rs.getInt("id_user"),rs.getInt("nbr_participement")));
                 
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
        
     } 
         public List<Utilisateur> afficherUtilisateur() {
        
        List<Utilisateur> myList = new ArrayList();
        try {
            String requete3 = "SELECT nom FROM Utilisateurs ";
            PreparedStatement pst = cnx.prepareStatement(requete3);
           // pst.setInt(1,id);
            ResultSet rs = pst.executeQuery(); 
            while (rs.next()) {
                Utilisateur h = new Utilisateur();
                h.setNom(rs.getString("Nom"));
                myList.add(h);
                
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList;
    }
     public List<Utilisateur> afficherparticipant(int id) {
        List<Utilisateur> list = new ArrayList<>();
          List<participement_event> list1 = new ArrayList<>();
        try {

            
            String requete1 = "SELECT id_user FROM participement_event  where id_Event="+id+"";
            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(requete1);
             while (rs1.next()) {
             int idu=rs1.getInt("id_user");
           /*
             return list1; 
             */
            String requete2 = "SELECT nom FROM utilisateurs  where id_user="+idu+"";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(requete2);
            while (rs2.next()) {
              list.add(new Utilisateur(rs2.getString("nom")));
            } }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return list;
        
     }
       public String affichernomEvent(int id_Event) {
       
          String s = null;
        try {

            
            String requete1 = "SELECT nom_Event FROM evenement  where id_Event=?";
            PreparedStatement pst = cnx.prepareStatement(requete1);
            pst.setInt(1,id_Event);
            ResultSet rs1 = pst.executeQuery();
            
             while (rs1.next()) {
             s=rs1.getString("nom_Event");
         
            } 
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }

        
        return s;
        
     }
     public String affichernomutilisateur(int id) {
       
          String s = null;
        try {

            
            String requete1 = "SELECT nom FROM utilisateurs  where id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete1);
            pst.setInt(1,id);
            ResultSet rs1 = pst.executeQuery();
            
             while (rs1.next()) {
             s=rs1.getString("nom");
                 System.out.println(s);
         
            } 
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }

        
        return s;
        
     }
     private Statement ste;
    private PreparedStatement pst;
    public void ajouter(participement_event r) {
          try {
                       
            String req = "INSERT INTO participement_event (id_user,id_Event,nbr_participement) VALUES (?,?,?)";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, r.getId_user());
            pre.setInt(2, r.getId_Event());
            pre.setInt(3, r.getNbr_prticipement());
         
            
            
  
            pre.executeUpdate();
            System.out.println("participement Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    
    }
    public void modifier(participement_event r) {
      try {
            String req = "UPDATE `participement_event` SET id_user=?,id_Event=?,nbr_participement=? WHERE `id_participement`=?";
            PreparedStatement pre = cnx.prepareStatement(req);
            pre.setInt(1, r.getId_Event());
            pre.setInt(2, r.getId_user());
            pre.setInt(3, r.getNbr_prticipement());
            pre.setInt(4, r.getId_participement());
            
            
             
            pre.executeUpdate();
            System.out.println("participement Modfié !");
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }
     public int deleteparticipement(int id_participement) throws SQLException {
        int i = 0;
        try {
            ste = cnx.createStatement();
            String sql = "DELETE FROM `participement_event` WHERE id_participement="+id_participement;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Serviceparticipement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
}
