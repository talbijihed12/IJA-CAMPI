/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import com.pidev.utils.DBConnection;
import com.pidev.entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.pidev.service.iSERVICE;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nmedia
 */
public class User_service implements iSERVICE<Utilisateur> {
    
Connection mc=DBConnection.getInstance().getCnx();
private Statement ste;

    /**
     *
     * @param p
     * @throws java.sql.SQLException
     */
    @Override
    public void addOne(Utilisateur p) throws SQLException  {
       
           String sql ="insert into utilisateurs(nom,prenom,date_naissance,num_tel,adresse,login,mdp,role) Values(?,?,?,?,?,?,?,?)";
             PreparedStatement ste=mc.prepareStatement(sql);
            ste=mc.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
              ste.setString(3, p.getDate_naissance());
               ste.setInt(4, p.getNum_tel());
               ste.setString(5,p.getAdresse());

              ste.setString(6, p.getLogin());
               ste.setString(7, p.getMdp());
               ste.setString(8,p.getRole());
              
            ste.executeUpdate();
            System.out.println("Utilisateur Ajoutée");
       
        
    }
@Override
    public void updateOne(Utilisateur p)throws SQLException{
 
           
              String sql="UPDATE utilisateurs SET nom=?,prenom=?,date_naissance=?,num_tel=?,adresse=?,login=?,mdp=?,role=? where id_user=?";
               PreparedStatement ste=mc.prepareStatement(sql);

               ste=mc.prepareStatement(sql);
               ste.setString(1, p.getNom());
               ste.setString(2, p.getPrenom());
               ste.setString(3, p.getDate_naissance());
               ste.setInt(4, p.getNum_tel());
               ste.setString(5,p.getAdresse());
               ste.setString(6, p.getLogin());
               ste.setString(7, p.getMdp());
               ste.setString(8,p.getRole());
                ste.setInt(9,p.getId_user());
              
         
            ste.executeUpdate();
             System.out.println("Utilisateur modifié");

       
    }
@Override
         public List<Utilisateur> getAll() throws SQLException{
        List<Utilisateur> users = new ArrayList<>();
        
             String sql="select * from utilisateurs";
              PreparedStatement ste=mc.prepareStatement(sql);
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
         users.add(new Utilisateur(rs.getInt("id_user"),rs.getInt("Num_tel"),rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse"),rs.getString("login"),rs.getString("mdp"),rs.getString("role"),rs.getString("date_naissance")));

                       
                
            }
       
        System.out.println(users);
        return users;
        
        
    }

         public void deleteOne1(int id)throws SQLException {
             
           String sql ="DELETE from utilisateurs where id_user=?";
           PreparedStatement ste=mc.prepareStatement(sql);
            ste=mc.prepareStatement(sql);
            ste.setInt(1, id);
            ste.executeUpdate();
              System.out.println("Utilisateur supprimé");
        }         
         


public ArrayList<Utilisateur> trierNom() throws SQLException {
        
        ArrayList<Utilisateur> lesusersTriers = new ArrayList<>();
        
        try {

     String req = "SELECT * FROM Utilisateurs order by Nom desc";
            Statement st = mc.createStatement();
            ResultSet rst = st.executeQuery(req);

            while(rst.next()) {
                Utilisateur us1 = new Utilisateur();
                
                us1.setNom(rst.getString("Nom"));
                us1.setPrenom(rst.getString("Prenom"));
               us1.setDate_naissance(rst.getString("date_naissance"));
               us1.setNum_tel(rst.getInt("Num_tel"));
               us1.setAdresse(rst.getString("adresse"));
               us1.setLogin(rst.getString("login"));
               us1.setMdp(rst.getString("mdp"));
               us1.setRole(rst.getString("role"));
              
                
                lesusersTriers.add(us1);
}
}       catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        
        return lesusersTriers;
    }
 public ArrayList<Utilisateur> rechercheParNom(Utilisateur e) throws SQLException {
        
        
        
        ArrayList<Utilisateur> lesusersRech = new ArrayList<>();
        
        try {

     String req = "SELECT * FROM Utilisateurs WHERE Nom=?";
     
            PreparedStatement preparedStmt = mc.prepareStatement(req);
            preparedStmt.setString(1, e.getNom());
            ResultSet rst = preparedStmt.executeQuery();

            while(rst.next()) {
                Utilisateur us1 = new Utilisateur();
                
                us1.setNom(rst.getString("Nom"));
                 us1.setPrenom(rst.getString("Prenom"));
               us1.setDate_naissance(rst.getString("date_naissance"));
               us1.setNum_tel(rst.getInt("Num_tel"));
               us1.setAdresse(rst.getString("adresse"));
               us1.setLogin(rst.getString("login"));
               us1.setMdp(rst.getString("mdp"));
               us1.setRole(rst.getString("role"));
              
                
               lesusersRech.add(us1);
}
}       catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return lesusersRech;
        
    }
 public String getRole(String username) {
        String role = "";
        try {
            PreparedStatement pre = mc.prepareStatement("select role from Utilisateurs where login=?");
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                role = rs.getString(1);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return role;
    }
   public int VerifParticipation(int Id_user) {
          int res=0;
        try {
            PreparedStatement pre = mc.prepareStatement("select count(*) from utilisateurs where Id_user=?");
            pre.setInt(1, Id_user);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
               res = rs.getInt(1);
               
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return res;
    }
   
        public int getId_userBynom(String i) throws SQLException {
        ste = mc.createStatement();
        int id_user = 0;

        try {

            PreparedStatement pre = mc.prepareStatement("SELECT Id_user  from utilisateurs where nom=?");
            pre.setString(1, i);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {


                
                 id_user = rs.getInt("Id_user");

            }
        } catch (SQLException ex) {
        }

        return id_user;

    }

    @Override
    public void deleteOne(Utilisateur u) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public   Utilisateur affiche(String Login,String password ) throws SQLException{
            Utilisateur u = new Utilisateur(); 
             String sql="select * from utilisateurs where login=? and mdp=?";
              PreparedStatement ste=mc.prepareStatement(sql);
              ste.setString(1, Login);
              ste.setString(2,password);
              ResultSet rs=ste.executeQuery();
              while (rs.next()){
                  u.setId_user(rs.getInt(1));
                  
              }
    return u;
            
        
    
    
    
    }
     public   Utilisateur afficherParId(int id ) throws SQLException{
            Utilisateur u = new Utilisateur(); 
             String sql="select * from utilisateurs where login=? and mdp=?";
              PreparedStatement ste=mc.prepareStatement(sql);
              ste.setInt(1, id);
              ResultSet rs=ste.executeQuery();
              while (rs.next()){
                  u.setId_user(rs.getInt(1));
                  u.setAdresse(rs.getString("adresse"));
                                    u.setNom(rs.getString("nom"));
                  u.setPrenom(rs.getString("prenom"));
                                    u.setDate_naissance(rs.getString("date_naissance"));

u.setNum_tel(rs.getInt("Num_tel"));
  u.setLogin(rs.getString("login"));
                  
              }
    return u;
            
        
    
    
    
    }
     
     
     public Utilisateur getUserById(int i) throws SQLException {
           ArrayList<Utilisateur> res = new ArrayList<Utilisateur>();

        ste = mc.createStatement();

        try {

            PreparedStatement pre = mc.prepareStatement("SELECT *  from utilisateurs where id=?");
            pre.setInt(1, i);
   
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id_user");
                String nom=rs.getString("nom");
                String role=rs.getString("role");
                /*   public Commande(Utilisateur camper, Date date_commande, String reference, float montant, String adresse) {
        this.camper = camper;
        this.date_commande = date_commande;
        this.reference = reference;
        this.montant = montant;
        this.adresse = adresse;
    }*/   
               Utilisateur E=new Utilisateur(id,nom,role);
                res.add(E);
            }
        } catch (SQLException ex) {
        }

         for(int e=0;e<res.size();e++)
        {
           if (res.get(e).getId_user()==i)
           {
               return res.get(e);
           }
        }
        return null;

    }

    public String getroleparid(int parseInt) {
        Utilisateur u = new Utilisateur();
    try {
        //To change body of generated methods, choose Tools | Templates.
        
        String sql="select role from utilisateurs where id_user=?";
        PreparedStatement ste=mc.prepareStatement(sql);
        ste.setInt(1, parseInt);
        ResultSet rs=ste.executeQuery();
        rs.next();
            
           u.setRole(rs.getString("role"));
            
        
        return rs.getString("role");
    } catch (SQLException ex) {
        Logger.getLogger(User_service.class.getName()).log(Level.SEVERE, null, ex);
    }
        return u.getRole();  
                }
}
    
    
    


