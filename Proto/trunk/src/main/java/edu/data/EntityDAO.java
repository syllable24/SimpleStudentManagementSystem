/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author newe
 * @param <T>
 */
public interface EntityDAO<T> {
    
    List<T> findAll();
        
    T find(int id);
    
    void update(int id, Consumer<T>... updates) throws Exception;
    
    void save(T entity);
    
    void remove(int id);
    
    T create(int id);
    
         
   
}
