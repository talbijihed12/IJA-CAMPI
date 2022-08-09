/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.service;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nmedia
 * @param <T>
 */
public interface iSERVICE <T>{
    void addOne(T u) throws SQLException;
    void updateOne(T u) throws SQLException;
    void deleteOne(T u)throws SQLException;
    List<T> getAll()throws SQLException;
    
}
