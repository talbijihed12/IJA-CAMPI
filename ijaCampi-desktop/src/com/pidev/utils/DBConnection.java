/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asus
 */
public class DBConnection {
     public String url="jdbc:mysql://localhost:3306/ijacampii";
    public String user="root";
    public String pwd="";
    public static DBConnection cn;
    private Connection cnx;
    public DBConnection(){
        try {
            System.out.println("Connexion établie");
            cnx=DriverManager.getConnection(url, user, pwd);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static DBConnection getInstance(){
        if(cn==null)
            cn= new DBConnection();
            return cn;
      
    }

    public Connection getCnx() {
         
        return cnx;
    }
    
    
}
