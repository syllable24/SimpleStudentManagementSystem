/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Table;

/**
 *
 * @author newe
 * @param <T>
 * @param <ID>
 */
@Named("crudDao")
public class CrudDao<T extends BaseEntity<ID>, ID> implements Serializable{
    
    
    protected BaseDao<T,ID> dao;
    
    protected Class<T> entityClass;
    
    protected ID id;
    
    public CrudDao(){
        this.dao = new BaseDao(getEntityClass(), id);
    }
    
   public Class<T> getEntityClass() {
        if (entityClass == null) {
            //only works if one extends BaseDao, we will take care of it with CDI
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
   
    public EntityManager getEntityManager(){
        return dao.getEntityManager();
    }
    
    public T find(ID id){
        
        return dao.find(id, getEntityClass());
    }
    
    public void delete(ID id){
         dao.delete(id, getEntityClass());
    }
    
    public T update(T t){
        return dao.update(t);
    }
    
    public void insert(T t){
        dao.insert(t);
    }
    
    public List<T> findAll(){
        String tableName = getEntityClass().getAnnotation(Table.class).name();
        return dao.findAll(tableName);
    }
    
    public List<T> findWithNamedQuery(String namedQueryName){
        return dao.findWithNamedQuery(namedQueryName);
    }
}
