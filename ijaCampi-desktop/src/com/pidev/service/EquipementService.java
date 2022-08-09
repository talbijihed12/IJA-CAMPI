/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;


import com.pidev.entities.Equipement;
import com.pidev.interfaces.Iservice;
import com.pidev.utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author brahim
 */
public class EquipementService implements Iservice <Equipement> {

private Statement ste;

    Connection con = DBConnection.getInstance().getCnx();
    @Override
    public ArrayList<Equipement> afficher() {
         ArrayList<Equipement> res = new ArrayList<Equipement>();
         
         try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Equipement";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String categorie = rs.getString("categorie");
                String marque= rs.getString("marque");
                float prix=rs.getFloat("prix");
                String description=rs.getString("description");
                String photo=rs.getString("photo");
                
                Equipement e=new Equipement();
                                Equipement E = new Equipement(prix,nom,marque,description,photo,categorie);
                                res.add(E);
                
             //   Equipement E = new Equipement(id,nom,categorie,prix,marque,description,photo,client_id,evenement_id);
              //  res.add(E);
            }
            rs.close();

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;  
    }

    
    
    public ArrayList<Equipement> getall() {
         ArrayList<Equipement> res = new ArrayList<Equipement>();
         
         try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Equipement";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String categorie = rs.getString("categorie");
                String marque= rs.getString("marque");
                float prix=rs.getFloat("prix");
                String description=rs.getString("description");
                String photo=rs.getString("photo");
               
                
                //public Equipement(float prix, String nom, String marque, String description, String photo, String categorie) 
                Equipement E = new Equipement(id,prix,nom,marque,description,photo,categorie);
                
                res.add(E);
            }
            rs.close();

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;  
    }

    
    
    
    @Override
    public void Ajouter(Equipement e) {
        String query="insert into equipement (nom,categorie,marque,prix,description,photo) values (?,?,?,?,?,?)";
       
    try {
            PreparedStatement preparedStmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setString(1, e.getNom());
            preparedStmt.setString(2, e.getCategorie());
            preparedStmt.setString(3, e.getMarque());
            preparedStmt.setFloat(4, e.getPrix());
            preparedStmt.setString(5, e.getDescription());
            preparedStmt.setString(6, e.getPhoto());
            preparedStmt.execute();
            ResultSet res=preparedStmt.getGeneratedKeys();
            while(res.next())
            {
               e.setId(res.getInt(1));
            }
            System.out.println("Insertion equipement Avec Succes");
            System.out.println(e.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(Equipement e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("update Equipement set  nom=?,categorie=?,marque=?,prix=?,description=?,photo=?  where id=?");
            preparedStmt.setString(1, e.getNom());
            preparedStmt.setString(2, e.getCategorie());
            preparedStmt.setString(3, e.getMarque());
            preparedStmt.setFloat(4, e.getPrix());
            preparedStmt.setString(5, e.getDescription());
            preparedStmt.setString(6, e.getPhoto());
            preparedStmt.setInt(7, e.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Equipement e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from Equipement where id= ?");
            preparedStmt.setInt(1, e.getId());
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }                
            }
    public void deleteOne(int id){
        try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from Equipement where id= ?");
            preparedStmt.setInt(1,id);
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }    
        
        
    }
    
    
    
      public Equipement  getEquipementById(int i) throws SQLException {
           ArrayList<Equipement> res = new ArrayList<Equipement>();

        ste = con.createStatement();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT *  from equipement where id=?");
            pre.setInt(1, i);
            
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

               int id = rs.getInt("id");
               String nom = rs.getString("nom");
               String description=rs.getString("description");
                String photo=rs.getString("photo");
                String marque= rs.getString("marque");
                    String categorie = rs.getString("categorie");
                float prix=rs.getFloat("prix");
             
                //public Equipement(float prix, String nom, String marque, String description, String photo, String categorie) 
                Equipement E = new Equipement(id,prix,nom,marque,description,photo,categorie);
               
                res.add(E);
               
            }
        } catch (SQLException ex) {
        }

        for(int e=0;e<res.size();e++)
        {
           if (res.get(e).getId()==i)
           {
               return res.get(e);
           }
        }
        return null;
    }
}
