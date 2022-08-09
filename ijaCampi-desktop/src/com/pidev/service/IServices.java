
package com.pidev.service;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Omar Amri
 * @param <T>
 */
public interface IServices<T> {
    
    
    public void ajouter(T entity) throws SQLException;
    
    public void supprimer(T entity) throws SQLException;
    
    public void modifier(T entity) throws SQLException;
    
    public ArrayList<T> afficher() throws SQLException;
    
}
