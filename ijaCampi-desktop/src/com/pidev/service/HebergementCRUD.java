/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.pidev.entities.Hebergement;


/**
 *
 * @author HP
 */
public class HebergementCRUD {
    Connection cnx2;
    public HebergementCRUD(){
         cnx2 =     DBConnection.getInstance().getCnx();
         }
    public void ajouterHebergement() {
        try {
            String requete = "INSERT INTO hebergement (id_h,name,ville,categorie,capacite,disponibilite,prix)"+"VALUES (default,'dar selma', 'nabeul','foret',500,100)";
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
            System.out.println("Hebergement creer avec succes!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    public void ajouterHebergement2(Hebergement h) {
        try {
            String requete2 = "INSERT INTO hebergement (name,ville,categorie,capacite,disponibilite,prix,id_user)" + "VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, h.getName());
            pst.setString(2, h.getVille());
            pst.setString(3, h.getCategorie());
            pst.setInt(4, h.getCapacite());
            pst.setBoolean(5,h.getDisponibilite());
            pst.setInt(6, h.getPrix());
            pst.setInt(7, h.getId_user());
            pst.executeUpdate();
            System.out.println("l'Hebergement est ajoute!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            
            
            
    }
    public List<Hebergement> afficherHebergement() {
        List<Hebergement> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM hebergement";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Hebergement h = new Hebergement();
                h.setId(rs.getInt(1));
                h.setName(rs.getString("Name"));
                h.setVille(rs.getString("Ville"));
                h.setCategorie(rs.getString("Categorie"));
                h.setCapacite(rs.getInt("Capacite"));
                h.setDisponibilite(rs.getBoolean("Disponibilite"));
                h.setPrix(rs.getInt("Prix"));
                myList.add(h);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList;
    }
    public Hebergement afficherHebergementParId(int id) {
        Hebergement h = new Hebergement();
        try {
            String requete3 = "SELECT * FROM hebergement WHERE id_h = ?";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery(); 
            while (rs.next()) {
                
                h.setId(rs.getInt(1));
                h.setName(rs.getString("Name"));
                h.setVille(rs.getString("Ville"));
                h.setCategorie(rs.getString("Categorie"));
                h.setCapacite(rs.getInt("Capacite"));
                h.setDisponibilite(rs.getBoolean("Disponibilite"));
                h.setPrix(rs.getInt("Prix"));
                
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return h;
    }
    public Hebergement afficherHebergementSansId(String name, String ville , String Categorie , int capacite , boolean disponibilite, int prix ) {
        Hebergement h = new Hebergement();
        try {
            String req3 = "SELECT * FROM Hebergement WHERE Name=? AND Ville=? AND Categorie=? AND Capacite=? AND Disponibilite=? AND Prix=? " ;
            
           PreparedStatement pst2= cnx2.prepareStatement(req3) ;
            pst2.setString(1,name);
            pst2.setString(2,ville);
            pst2.setString(3,Categorie);
            pst2.setInt(4,capacite);
            pst2.setBoolean(5, disponibilite);
            pst2.setInt(6,prix);
            ResultSet rs = pst2.executeQuery(); 
            while (rs.next()) {
                
                h.setId(rs.getInt("Id_h"));
                h.setName(rs.getString("Name"));
                h.setVille(rs.getString("Ville"));
                h.setCategorie(rs.getString("Categorie"));
                h.setCapacite(rs.getInt("Capacite"));
                h.setDisponibilite(rs.getBoolean("Disponibilite"));
                h.setPrix(rs.getInt("Prix"));
                
            }    
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }                
        
       return h;
    }
    public void ModifierPrix (int id_h , String prix)
    {
        try {
            String req3 = "UPDATE hebergement SET prix= ? where id_h=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req3) ;
         
            pst.setString(1, prix);
            pst.setInt(2, id_h);
           
            pst.executeUpdate() ;
             System.out.println("Le prix de l'hebergement est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
}
     public void ModifierCaapacite (int id_h , String capacite)
    {
        try {
            String req3 = "UPDATE hebergement SET capacite= ? where id_h=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req3) ;
         
            pst.setString(1, capacite);
            pst.setInt(2, id_h);
           
            pst.executeUpdate() ;
             System.out.println("La capacité de l'hebergement est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
}
      public void ModifierName (int id_h , String Name)
    {
        try {
            String req3 = "UPDATE blog SET name= ? where id_h=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req3) ;
         
            pst.setString(1, Name);
            pst.setInt(2, id_h);
           
            pst.executeUpdate() ;
             System.out.println("Le nom de l'hebergement est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
}
       public void ModifierCategorie (int id_h , String categorie)
    {
        try {
            String req3 = "UPDATE hebergement SET categorie= ? where id_h=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req3) ;
         
            pst.setString(1, categorie);
            pst.setInt(2, id_h);
           
            pst.executeUpdate() ;
             System.out.println("La categorie de l'hebergement est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
}
        public void SupprimerHebergement (String name, String ville , String Categorie , int capacite , int prix ){
       
      try {
            String req3 = "DELETE FROM Hebergement WHERE Name=? AND Ville=? AND Categorie=? AND Capacite=? AND Prix=? " ;
            
           PreparedStatement pst2= cnx2.prepareStatement(req3) ;
            pst2.setString(1,name);
            pst2.setString(2,ville);
            pst2.setString(3,Categorie);
            pst2.setInt(4,capacite);
            pst2.setInt(5,prix);
           pst2.executeUpdate() ; 
            System.out.print("l'Hebergement est suprrimé ");
           
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
         
        }

    
    
    }
     public void ModifierHebergement (String name, String ville , String Categorie , int capacite , boolean disponibilite, int prix ,String name2, String ville2 , String Categorie2 , int capacite2 , boolean disponibilite2, int prix2){
       
      try {
            String req3 = "UPDATE Hebergement SET name = ? , ville = ?, categorie = ?,capacite = ?,disponibilite = ?,prix = ? WHERE Name=? AND Ville=? AND Categorie=? AND Capacite=? AND Disponibilite=? AND Prix=? " ;
            
           PreparedStatement pst2= cnx2.prepareStatement(req3) ;
            pst2.setString(1,name2);
            pst2.setString(2,ville2);
            pst2.setString(3,Categorie2);
            pst2.setInt(4,capacite2);
            pst2.setBoolean(5,disponibilite2);
            pst2.setInt(6, prix2);
            pst2.setString(7,name);
            pst2.setString(8,ville);
            pst2.setString(9,Categorie);
            pst2.setInt(10,capacite);
            pst2.setBoolean(11,disponibilite);
            pst2.setInt(12, prix);
           pst2.executeUpdate() ; 
            System.out.print("l'Hebergement est modifié ");
           
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
         
        }

    
    
    }

    public List<Hebergement> afficherHebergementParIdUser(int id) {
       List<Hebergement> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM hebergement where id_user='"+id+"' ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            //pst.setInt(1, id);
            ResultSet rs = pst.executeQuery(requete3);
            while (rs.next()) {
                Hebergement h = new Hebergement();
                h.setId(rs.getInt(1));
                h.setName(rs.getString("Name"));
                h.setVille(rs.getString("Ville"));
                h.setCategorie(rs.getString("Categorie"));
                h.setCapacite(rs.getInt("Capacite"));
                h.setDisponibilite(rs.getBoolean("Disponibilite"));
                h.setPrix(rs.getInt("Prix"));
                myList.add(h);
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return myList; //To change body of generated methods, choose Tools | Templates.
    }

}
