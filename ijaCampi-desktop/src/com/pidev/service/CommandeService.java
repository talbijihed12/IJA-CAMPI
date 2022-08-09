/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;
import com.pidev.entities.Commande;
import com.pidev.entities.Utilisateur;
import com.pidev.interfaces.Iservice;
import com.pidev.utils.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author brahim
 */
public class CommandeService implements Iservice <Commande> {
Connection con = DBConnection.getInstance().getCnx();
   private Statement ste;


@Override
    public ArrayList<Commande> afficher() {
      ArrayList<Commande> res = new ArrayList<Commande>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Commande";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                float montant=rs.getFloat("montant");
                Date date=rs.getDate("date");
                String adresse = rs.getString("adresse");
                String reference= rs.getString("reference");
                int utilisateur_id=rs.getInt("id_utilisateur");
               // Produit E = new Produit(id,qteStock,nom,marque,description,photo,categorie,prix_ut);
               User_service us=new User_service();
              // Utilisateur u =us.getId_userBynom(utilisateur_id);
             //  Utilisateur u=new Utilisateur();
               Utilisateur u=us.getUserById(utilisateur_id);
              
              Commande c=new Commande(u,date,reference,montant,adresse);
                res.add(c);
            }
            rs.close();

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;  
    }

    @Override
    public void Ajouter(Commande e) {

String query="insert into commande (montant,date,adresse,reference,id_utilisateur) values (?,?,?,?,?)";
       
    try {
            PreparedStatement preparedStmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setFloat(1,e.getMontant() );
            preparedStmt.setDate(2, e.getDate_commande());
            preparedStmt.setString(3, e.getAdresse());
            preparedStmt.setString(4, e.getReference()); 
            preparedStmt.setInt(5, e.getCamper().getId_user());
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
            System.out.println("x:"+e.getCamper().getId_user());
        }

    }
     public void finilize(Commande e) {

        try {
            PreparedStatement preparedStmt = con.prepareStatement("update commande set adresse=?,reference=? where id=?");
            System.out.println("cc");            
            preparedStmt.setString(1, e.getAdresse());
            System.out.println(e.getAdresse());
            preparedStmt.setString(2, e.getReference());
            preparedStmt.setInt(3, e.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            System.out.println("ho");
            System.err.println(ex.getMessage());
        }    }


    @Override
    public void modifier(Commande e) {

        try {
            PreparedStatement preparedStmt = con.prepareStatement("update commande set  montant=?,date=?,adresse=?,reference=?,id_utilisateur=?  where id=?");
System.out.println("cc");            
            preparedStmt.setFloat(1, e.getMontant());
            preparedStmt.setDate(2, e.getDate_commande());
            preparedStmt.setString(3, e.getAdresse());
            preparedStmt.setString(4, e.getReference());
            preparedStmt.setInt(5, e.getCamper().getId_user());
            preparedStmt.setInt(6, e.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            System.out.println("ho");
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(Commande e) {
         try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from commande where id= ?");
            preparedStmt.setInt(1, e.getId());
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }                
    }
       public int add(Commande e) {

String query="insert into commande (montant,date,adresse,reference,id_utilisateur) values (?,?,?,?,?)";
       
    try {
            PreparedStatement preparedStmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setFloat(1,e.getMontant() );
            preparedStmt.setDate(2, e.getDate_commande());
            preparedStmt.setString(3, e.getAdresse());
            preparedStmt.setString(4, e.getReference()); 
            preparedStmt.setInt(5, e.getCamper().getId_user());
            preparedStmt.execute();
            ResultSet res=preparedStmt.getGeneratedKeys();
            while(res.next())
            {
               e.setId(res.getInt(1));
            }
            System.out.println("Insertion equipement Avec Succes");
          
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("x:"+e.getCamper().getId_user());
        }
         return e.getId();
    }

       public Commande getCommandeById(int i) throws SQLException {
           ArrayList<Commande> res = new ArrayList<Commande>();

        ste = con.createStatement();

        try {

            PreparedStatement pre = con.prepareStatement("SELECT *  from commande where id=?");
            pre.setInt(1, i);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                float montant=rs.getFloat("montant");
                Date date=rs.getDate("date");
                String adresse = rs.getString("adresse");
                String reference= rs.getString("reference");
                int utilisateur_id=rs.getInt("id_utilisateur");
                User_service us=new User_service();
                Utilisateur u=new Utilisateur(1,"omar","client");
                /*   public Commande(Utilisateur camper, Date date_commande, String reference, float montant, String adresse) {
        this.camper = camper;
        this.date_commande = date_commande;
        this.reference = reference;
        this.montant = montant;
        this.adresse = adresse;
    }*/   
                Commande E=new Commande(u,date,reference,montant,adresse);
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
