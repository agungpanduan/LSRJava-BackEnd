/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lsr.util;

import java.io.Serializable;

/**
 *
 * @author hendra
 */
public interface GenericDAO <T, K extends Serializable>{
    
	T findById(K id);
        
	T getById(K id);
    
    void delete(K id);
    
    void delete(T deletedEntity);
    
    void save(T newEntity);
    
    T update(T editedEntity);
    
    void flush();
    
    void clear();
}
