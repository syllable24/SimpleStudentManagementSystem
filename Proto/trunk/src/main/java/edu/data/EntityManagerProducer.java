/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author newe
 */
public class EntityManagerProducer {
    
    @Produces
    @SSMSDatabase
    public EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory("eduobject").createEntityManager();
    }
    
    public void close( @Disposes @SSMSDatabase EntityManager entityManager) {
        entityManager.close();
    }
}