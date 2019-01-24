/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enities.BaseEntity;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author newe
 * @param <T>
 * @param <ID>
 */

public class BaseDao<T extends BaseEntity<ID>, ID> implements Serializable {

    public BaseDao(){
        System.out.println("Create BaseDao");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eduobject");
        entityManager = emf.createEntityManager();

    }
    @PersistenceUnit(unitName="eduobject")
    private EntityManagerFactory entityManagerFactory;
    
    public EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }
    
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    
    private EntityManager entityManager;
    
    public EntityManager getEntityManager() {
        System.err.println("->> "+this.entityManagerFactory);
        return entityManagerFactory.createEntityManager();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    
    //utility database methods
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public T find(ID id, Class<T> type) {
        T result = (T) this.entityManager.find(type, id);
        if (result == null) {
            throw new EntityNotFoundException("Can't find Entity for ID "
                    + id);
        }

        return result;
    }

    public void delete(ID id, Class<T> type) {
        this.entityManager.getTransaction().begin();
        Object ref = this.entityManager.getReference(type, id);
        this.entityManager.remove(ref);
        System.out.println(ref.toString());
        this.entityManager.getTransaction().commit();
    }

    public T update(T t) throws SQLException,DatabaseException {
        this.entityManager.getTransaction().begin();
        Object ref=this.entityManager.merge(t);
        this.entityManager.getTransaction().commit();
        return (T) ref;
    }

    public void insert(T t) throws SQLException,DatabaseException {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(t);
        this.entityManager.getTransaction().commit();

    }

    /*
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAll(Class<T> type) {
        return entityManager.createQuery("Select entity.* FROM "+type.getSimpleName() +" entity").getResultList();
    }
    */
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAll(String tableName) {
        System.err.println("Table "+tableName+ " " + this.entityManager+ " -> "+this.entityManagerFactory);
        String str = "select u from table u";
        String jqlstr = str.replace("table", tableName);
        return entityManager.createQuery(jqlstr).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findWithNamedQuery(String namedQueryName) {
        return this.entityManager.createNamedQuery(namedQueryName).getResultList();
    }
    //put here other utility method you want to share with your daos
}
