/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.io.Serializable;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

/**
 *
 * @author newe
 * @param <T>
 * @param <ID>
 */

@Dependent
@Named("baseDao")
public class BaseDao<T extends BaseEntity<ID>, ID> implements Serializable {

    
    private EntityManager entityManager;
    private Class<T> entityClass;
    private ID id;
    
    public BaseDao(Class<T> entityClass, ID id){
        this.entityManager = Persistence.createEntityManagerFactory("eduobject").createEntityManager();
        this.entityClass = entityClass;
        this.id = id;
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
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
        Object ref = this.entityManager.getReference(type, id);
        this.entityManager.remove(ref);
    }

    public T update(T t) {
        return (T) this.entityManager.merge(t);
    }

    public void insert(T t) {
        this.entityManager.persist(t);
    }

    /*
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAll(Class<T> type) {
        return entityManager.createQuery("Select entity.* FROM "+type.getSimpleName() +" entity").getResultList();
    }
    */
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<T> findAll(String tableName) {
        System.err.println("Table "+tableName+ " " + entityManager);
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
