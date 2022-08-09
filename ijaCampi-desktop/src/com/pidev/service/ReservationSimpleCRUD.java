/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;


import com.pidev.entities.Hebergement;
import com.pidev.entities.ReservationSimple;
import com.pidev.utils.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Seif Labidi
 */
public class ReservationSimpleCRUD {
    Connection cnx2;
    public ReservationSimpleCRUD(){
         cnx2 = DBConnection.getInstance().getCnx();
         }
    public void ajouterReservationSimple(Date dd , Date df , int id , String nameh , int user ) {
        try {
            String requete2 = "INSERT INTO ReservationSimple (dateDebut,dateFin,NameHebergement,id_h,User_id)" + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setDate(1,(Date) dd);
            pst.setDate(2, (Date) df);
            pst.setInt(4, id);
            pst.setString(3, nameh);
            pst.setInt(5,user);
            pst.executeUpdate();
            System.out.println("Reservation effectue!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
      public void SupprimerReservationSimple (int id_rs ){
       
      try {
            String req3 = "DELETE FROM ReservationSimple WHERE id_rs=? " ;
            
           PreparedStatement pst2= cnx2.prepareStatement(req3) ;
            pst2.setInt(1,id_rs);
           pst2.executeUpdate() ; 
            System.out.print("Reservation suprrimée! ");
           
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
         
        }

    
    }
      public boolean AfficherReservationParDateDebut(Date dd,Date df){
        boolean resultat = true;
        try {
            String requete3 = "SELECT * FROM reservationsimple WHERE dateDebut between ? and ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setDate(1,dd);
            pst.setDate(2, df);
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
            String requete3 = "SELECT * FROM reservationsimple WHERE dateFin between ? and ? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setDate(1,dd);
            pst.setDate(2, df);
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

//    public ReservationSimple AfficherReservationParIdUser(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public ReservationSimple AfficherReservationParId(int id) {
         ReservationSimple h = new ReservationSimple();
        try {
            String requete3 = "SELECT * FROM ReservationSimple WHERE id_rs = ?";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery(); 
            while (rs.next()) {
                
                h.setId_rs(rs.getInt("Rs_id"));
                h.setNameHebergement(rs.getString("NameHebergement"));
                h.setUser_id(rs.getInt("User_id"));
                h.setDateDebut(rs.getDate("DateDebut"));
                h.setDateFin(rs.getDate("DateFin"));
               
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return h;
    }

    public void supprimerReservationParId(int id) {
        try {
            String req3 = "DELETE FROM ReservationSimple WHERE id_rs=? " ;
            
           PreparedStatement pst2= cnx2.prepareStatement(req3) ;
            pst2.setInt(1,id);
            
           pst2.executeUpdate() ; 
            System.out.print("la reservation est annulée ");
           
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
         
        }
    }
    public List<ReservationSimple> afficherReservation() {
        List<ReservationSimple> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM ReservationSimple";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                ReservationSimple h = new ReservationSimple();
                h.setId_rs(rs.getInt("id_rs"));
                h.setNameHebergement(rs.getString("NameHebergement"));
                h.setUser_id(rs.getInt("User_id"));
                h.setDateDebut(rs.getDate("DateDebut"));
                h.setDateFin(rs.getDate("DateFin"));
                myList.add(h);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList;
    }
}
