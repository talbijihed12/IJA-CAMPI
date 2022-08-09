/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;


import com.pidev.entities.Avis;
import com.pidev.utils.DBConnection;
import entities.Reclamation;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AvisService {
   
    Connection mc;
    PreparedStatement ste;

    public AvisService() {
         mc=DBConnection.getInstance().getCnx();
    }
    
    // Fonction AJOUTER
    public void ajouterAvis(Avis av)
    { 
        try
        {
         String sql ="insert into avis(commentaire,idu) Values(?,?)";
           ste=mc.prepareStatement(sql);
           ste.setString(1, av.getCommentaire());
           ste.setInt(2,av.getIdu());
           ste.executeUpdate();
           System.out.println("Avis Ajoutée");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    public List<Avis> afficherAvis()
    {
      List<Avis> lst =  new ArrayList<>();
      
      try
      {
          String sql="select * from avis";

          ste=mc.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                   lst.add(new Avis(rs.getInt("id_a"), rs.getString("commentaire"),rs.getInt("idu")));
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return lst;
    }
    
  
    
    public void updateAvis(int id_a, String commentaire){
       String sql= "UPDATE avis SET commentaire='"+commentaire+"' where id_a='"+id_a+"'";
       
       try{
           Statement st= mc.createStatement();
           st.executeUpdate(sql);
           System.out.println(" Avis modifiée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    
    public void supprimerAvis(int id){
        String sql = "DELETE from avis where id_a= '"+id+"' "; 
        try{
           Statement st= mc.createStatement();
           st.executeUpdate(sql);
           System.out.println("Avis supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    
        public ObservableList<Avis> getAllAvis() throws SQLDataException {

        
        List<Avis> list =new ArrayList<Avis>();
        int count =0;
        
        String requete="select * from avis";
         try{
             Statement st = mc.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                           Avis e= new Avis();
                           e.setId_a(rs.getInt(1));
                           e.setIdu(rs.getInt(3));
                           e.setCommentaire(rs.getString(2));

                
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        }
         
 public Avis AvisById(int id)
    {
      Avis r =  new Avis();
      
      try
      {
          String sql="select * from avis where id_a="+id;

          ste=mc.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
             r= new Avis(rs.getInt("id_a"), rs.getString("commentaire"),rs.getInt("idu"))         ;   
                      }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return r;
    }
               
 
 public User UserById(int id)
    {
      User r =  new User();
      
      try
      {
          String sql="select * from user where iduser="+id;

          ste=mc.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                      
                      r.setId(rs.getInt(1));
                      r.setNom(rs.getString(2));
                      
                      
                      }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return r;
    }
               
               
               
   
           
}

