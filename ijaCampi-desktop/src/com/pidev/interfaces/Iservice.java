/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author brahim
 */
public interface Iservice <T> {
    public ArrayList<T> afficher() throws SQLException;
    public void Ajouter(T t) throws SQLException;
    public void modifier(T t) throws SQLException;
    public void supprimer(T t) throws SQLException;
    

}
