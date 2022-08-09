/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;



import com.pidev.entities.Vote;
import com.pidev.utils.DBConnection;
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



/**
 *
 * @author dell
 */
public class ServiceVote {
    
    Connection cn;
    PreparedStatement ste;

    
    public ServiceVote() {
         cn=DBConnection.getInstance().getCnx();
    }
    
     public void addVote (Vote v ) throws SQLException {
        
         

        
 String query ="INSERT INTO `vote`(`idu`, `type_vote`) VALUES (?,?)";
 
         PreparedStatement st  = cn.prepareStatement(query);
                st.setInt(1, v.getId_client());
                st.setInt(2,v.getType_vote());
               
                st.execute();
                
     }
     
     
        public void deleteReponse(int id_client) throws SQLException {

            String query = "DELETE FROM `vote` WHERE  `idu` = "+id_client;
		PreparedStatement st  = cn.prepareStatement(query); 
                st.execute();   
        }

    public int NumLike () throws SQLException{
      
        int c =0 ;
                    String query = "SELECT COUNT(type_vote) FROM vote WHERE type_vote=2";
                    Statement st  = cn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                     while(rs.next()){
                         
                         
                   c=c+ rs.getInt(1);
                     
                     }
                    // System.out.println("9adechh"+c);
                     return c ;


 }
    
    
    
     public int NumdeLike () throws SQLException{
        
        int c =0 ;
                    String query = "SELECT COUNT(type_vote) FROM vote WHERE type_vote=1";
                    Statement st  = cn.createStatement();
                    ResultSet rs = st.executeQuery(query);
                     while(rs.next()){
                         
                         
                   c=c+ rs.getInt(1);
                     
                     }
                   //  System.out.println("9adechh"+c);
                     return c ;


 }
     
     
     
         public List <Vote> getAllVote(int e , int u) throws SQLDataException {
         try {
             List<Vote> list=new ArrayList<Vote>();
             int c=0;
             String req="select * from vote where idu="+u+"and type_vote=1";
             Statement st=cn.createStatement();
             ResultSet rs=st.executeQuery(req);
             
             while(rs.next())
             {
                 Vote cm=new Vote();
                 cm.setId(rs.getInt(1));
                 cm.setId_client((rs.getInt(2)));
                 cm.setType_vote(rs.getInt(4));
                 
                 list.add(cm);
                 c++;
                 
             }
             if (c == 0)
             {
                 return null;
             }else {
                 return list;
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceVote.class.getName()).log(Level.SEVERE, null, ex);
             return null;
        
         }
    }

    
    
    
}
