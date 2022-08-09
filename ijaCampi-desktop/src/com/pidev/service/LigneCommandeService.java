/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;
import com.pidev.entities.Commande;
import com.pidev.entities.Equipement;
import com.pidev.interfaces.Iservice;

import com.pidev.entities.LigneCommande;
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
public class LigneCommandeService implements Iservice <LigneCommande> {
    Connection con = DBConnection.getInstance().getCnx();
    private CommandeService cs=new CommandeService();
    private EquipementService es=new EquipementService();
    private Statement ste;

    @Override
    public ArrayList<LigneCommande> afficher() {
  ArrayList<LigneCommande> res = new ArrayList<LigneCommande>();
         
         try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM ligne_commande";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int equipement_id = rs.getInt("id_equipement_");
                int commande_id = rs.getInt("id_commande");
                int  quantite= rs.getInt("quantite");
                Equipement e=es.getall().get(equipement_id);
                System.out.println("hi");
                Commande c=cs.afficher().get(commande_id);
                 LigneCommande E=new LigneCommande(id,e,c,quantite);
                 res.add(E);
                
             //   Equipement E = new Equipement(id,nom,categorie,prix,marque,description,photo,client_id,evenement_id);
              //  res.add(E);
            }
            rs.close();

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;      }

    @Override
    public void Ajouter(LigneCommande e) {
String query="insert into ligne_commande (quantite,id_equipement,id_commande) values (?,?,?)";
       
    try {
            PreparedStatement preparedStmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setInt(1,e.getQuantite());
            preparedStmt.setInt(2, e.getEquipement().getId());
            preparedStmt.setInt(3, e.getCommande().getId());
            
            preparedStmt.execute();
            ResultSet res=preparedStmt.getGeneratedKeys();
            while(res.next())
            {
               e.setId(res.getInt(1));
            }
            System.out.println("Insertion LigneCommande Avec Succes");
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
    }

    @Override
    public void modifier(LigneCommande e) {
  try {
            PreparedStatement preparedStmt = con.prepareStatement("update ligne_commande set  quanite=?,equipement_id=?,commande_id=?  where id=?");
            preparedStmt.setInt(1, e.getQuantite());
            preparedStmt.setInt(2, e.getCommande().getId());
            preparedStmt.setInt(3, e.getEquipement().getId());
            preparedStmt.setInt(4, e.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }       }

    @Override
    public void supprimer(LigneCommande e) {
 try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from ligne_commande where id= ?");
            preparedStmt.setInt(1, e.getId());
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }         
    }
     
     public ArrayList<LigneCommande> getLigneCommandeById(int i) throws SQLException {
           ArrayList<LigneCommande> res = new ArrayList<LigneCommande>();

        ste = con.createStatement();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT *  from ligne_commande where id=?");
            pre.setInt(1, i);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int equipement_id = rs.getInt("id_equipement");
                int commande_id = rs.getInt("id_commande");
                int  quantite= rs.getInt("quantite");
                Equipement e=es.getEquipementById(equipement_id);
                System.out.println("hi");
                Commande c=cs.getCommandeById(commande_id);
                 LigneCommande E=new LigneCommande(id,e,c,quantite);
                 res.add(E);
            }
        } catch (SQLException ex) {
        }

        return res;

    }
    
    public ArrayList<LigneCommande> getbyCommandeid(int id_commande) {
           ArrayList<LigneCommande> res = new ArrayList<LigneCommande>();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT *  from ligne_commande where id_commande=?");
            pre.setInt(1, id_commande);
   
            ResultSet rs = pre.executeQuery();
             while (rs.next()) {
                int id = rs.getInt("id");
                int equipement_id = rs.getInt("id_equipement");
                int commande_id = rs.getInt("id_commande");
                int  quantite= rs.getInt("quantite");
                Equipement e=es.getEquipementById(equipement_id);
                System.out.println("hi");
                Commande c=cs.getCommandeById(commande_id);
                 LigneCommande E=new LigneCommande(id,e,c,quantite);
                 res.add(E);
            }
        } catch (SQLException ex) {
        }

        return res;
            
      
     }
    
    public ArrayList<LigneCommande> getbyCid(int idC)
    {
          ArrayList<LigneCommande> res = new ArrayList<LigneCommande>();
            EquipementService es=new EquipementService();
           try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM ligne_commande where id_commande=" + idC ;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int equipement_id = rs.getInt("id_equipement");
                int commande_id = rs.getInt("id_commande");
              
                int  quantite= rs.getInt("quantite");
                
                ArrayList<Equipement> e1 = es.getall();
                Equipement e=e1.get(equipement_id);
               
                Commande c=cs.afficher().get(commande_id);
                LigneCommande E=new LigneCommande(id,e,c,quantite);
             
                res.add(E);
             //   Equipement E = new Equipement(id,nom,categorie,prix,marque,description,photo,client_id,evenement_id);
              //  res.add(E);
            }
            rs.close();

        }
        catch (Exception e) {
            System.err.println("error:"+e.getMessage());
        }
        return res;
    }
}
