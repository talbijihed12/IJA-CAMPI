/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.entities.ReservationEvenement;
import com.pidev.utils.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Seif Labidi
 */
public class ReservationEvenementCRUD {
    Connection cnx2;
    public ReservationEvenementCRUD(){
         cnx2 = DBConnection.getInstance().getCnx();
         }
    public void ajouterReservationEvenement(ReservationEvenement re) {
        try {
            String requete2 = "INSERT INTO ReservationEvenement (dateDebut,dateFin)" + "VALUES (?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            //pst.setInt(1, re.getDuree());
            pst.setDate(1, (Date) re.getDateDebut());
            pst.setDate(2, (Date) re.getDateFin());
            pst.executeUpdate();
            System.out.println("Reservation effectue!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void SupprimerReservationEvenement (int id_re ){
       
      try {
            String req3 = "DELETE FROM ReservationSimple WHERE id_re=? " ;
            
           PreparedStatement pst2= cnx2.prepareStatement(req3) ;
            pst2.setInt(1,id_re);
           pst2.executeUpdate() ; 
            System.out.print("Reservation suprrimée! ");
           
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
         
        }

    
    
    }
}
