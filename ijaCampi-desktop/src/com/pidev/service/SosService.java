package com.pidev.service;

import com.pidev.entities.Sos;
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

public class SosService {
   
    Connection mc;
    PreparedStatement ste;

    public SosService() {
         mc=DBConnection.getInstance().getCnx();
    }
    
    // Fonction AJOUTER
    public void ajouterSos(Sos s)
    { 
        try
        {
         String sql ="insert into sos( nom, prenom,telephone,id_g,desc_cas) Values(?,?,?,?,?)";
           ste=mc.prepareStatement(sql);
           ste.setString(1, s.getNom());
           ste.setString(2, s.getPrenom());
           ste.setInt(3,s.getTelephone());
           ste.setInt(3,s.getId_g());
           ste.setString(3,s.getDesc_cas());
           ste.executeUpdate();
           System.out.println("SOS Ajoutée");
        }
        catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    public List<Sos> afficherSos()
    {
      List<Sos> lst =  new ArrayList<>();
      
      try
      {
          String sql="select * from sos";

          ste=mc.prepareStatement(sql);
          
          ResultSet rs=ste.executeQuery();
                  while(rs.next()){
                   lst.add(new Sos( rs.getString("nom"),rs.getString("prenom"),rs.getInt("telephone"),rs.getInt("id_g"),rs.getString("desc_cas")));
                  }
      }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return lst;
    }
    
  
    
    public void updateSos(int id_c, String nom, String prenom, int telephone, int id_g, String desc_cas){
       String sql= "UPDATE sos SET nom='"+nom+"',prenom= '"+prenom+"',telephone= '"+telephone+"',id_g= '"+id_g+"',desc_cas= '"+desc_cas+"' where id_c='"+id_c+"'";
       
       try{
           Statement st= mc.createStatement();
           st.executeUpdate(sql);
           System.out.println(" SOS modifiée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
    public void supprimerSos(int id){
        String sql = "DELETE from sos where id_s= '"+id+"' "; 
        try{
           Statement st= mc.createStatement();
           st.executeUpdate(sql);
           System.out.println("SOS supprimée avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
    
}
