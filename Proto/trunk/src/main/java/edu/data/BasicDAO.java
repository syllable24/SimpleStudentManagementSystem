/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.Table;

/**
 *
 * @author newe
 * @param <T>
 */
public class BasicDAO<T> implements EntityDAO<T>{
    
    private EntityManager entityManager;
    private Class<T> entityClass;
    /*
    @Inject
    public BasicDAO(@SSMSDatabase EntityManager entityManager, @SchoolsClass Class<T> entityClass){
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }
    */
    @Override
    public List<T> findAll() {
        System.err.println("Table");
        Table table = Entity.class.getAnnotation(Table.class);
        String tableName = table.name();
        String str = "select u.* from table u";
        String jqlstr = str.replace("table", tableName);
        return entityManager.createQuery(jqlstr).getResultList();
    }

    @Override
    public T find(int id) {
        return entityManager.find(entityClass, id);
    }
    
    @Override
    public T create(int id){
        T u = (T) new Object();
	save(u);
	return u;
    }

    @Override
    public void update(int id, Consumer<T>... updates) throws Exception {
        T entity = find(id);
        Arrays.stream(updates).forEach(up -> up.accept(entity));
        beginTransaction();
        commitTransaction();
    }

    @Override
    public void save(T entity) {
        beginTransaction();
        entityManager.persist(entity);
        commitTransaction();
    }

    @Override
    public void remove(int id) {
        T entity = find(id);
        beginTransaction();
        entityManager.remove(entity);
        commitTransaction();
    }

    private void beginTransaction() {
        try {
           entityManager.getTransaction().begin();
        } catch (IllegalStateException  e) {
            rollBackTransaction();
        }
    }

    private void commitTransaction() {
        try {
           entityManager.getTransaction().commit();
        } catch (IllegalStateException | RollbackException  e) {
            rollBackTransaction();
        }
    }

    private void rollBackTransaction() {
        try {
           entityManager.getTransaction().rollback();
        } catch (IllegalStateException | PersistenceException e) {
            e.printStackTrace();
        }
    }
    
}
