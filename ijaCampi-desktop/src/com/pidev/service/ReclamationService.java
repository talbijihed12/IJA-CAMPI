package com.pidev.service;

import com.pidev.utils.DBConnection;
import entities.Reclamation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReclamationService {
   
    Connection mc;
    PreparedStatement ste;

    public ReclamationService() {
         mc=DBConnection.getInstance().getCnx();
    }
    
    // Fonction AJOUTER
    public void ajouterReclamation(Reclamation rec)
    { 
        try
        {
         String sql ="insert into reclamation(description,date,photo,idu,etat) Values(?,?,?,?,?)";
           ste=mc.prepareStatement(sql);
           ste.setString(1, rec.getDescription());
           ste.setDate(2, rec.getDate());
           ste.setString(3,rec.getPhoto());
            ste.setInt(4,rec.getIdu());
             ste.setString(5,rec.getEtat());
           ste.executeUpdate();
           System.out.println("Reclamation Ajoutée");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    public List<Reclamation> MesafficherReclamation(int u)
    {
      List<Reclamation> lst =  new ArrayList<>();
      
      try
      {
          String sql="select * from reclamation where idu="+u;

          ste=mc.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                   lst.add(new Reclamation(rs.getInt("id_r"), rs.getString("description"),rs.getDate("date"),rs.getString("photo"),rs.getInt("idu"),rs.getString("etat")));
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return lst;
    }
    
  
    
    public void updateReclamation(int id_r, String description, String photo){
       String sql= "UPDATE reclamation SET description='"+description+"',photo= '"+photo+"' where id_r='"+id_r+"'";
       
       try{
           Statement st= mc.createStatement();
           st.executeUpdate(sql);
           System.out.println(" Reclamation modifiée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    public void supprimerReclamation(int id){
        String sql = "DELETE from reclamation where id_r= '"+id+"' "; 
        try{
           Statement st= mc.createStatement();
           st.executeUpdate(sql);
           System.out.println("Reclamation supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    
        
        public void updateReclamtion( String commentaire, int id_a){
       String sql= "UPDATE reclamation SET etat='"+commentaire+"' where id_r='"+id_a+"'";
       
       try{
           Statement st= mc.createStatement();
           st.executeUpdate(sql);
           System.out.println(" Avis modifiée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    
      public Reclamation ReclamationById(int id)
    {
      Reclamation r =  new Reclamation();
      
      try
      {
          String sql="select * from reclamation where id_r="+id;

          ste=mc.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                   r =new Reclamation(rs.getInt("id_r"), rs.getString("description"),rs.getDate("date"),rs.getString("photo"),rs.getInt("idu"),rs.getString("etat"));
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return r;
    }
    
}
