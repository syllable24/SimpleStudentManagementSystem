 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import enities.BaseEntity;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

/**
 *
 * @author newe
 * @param <T>
 * @param <ID>
 */
public class CrudDao<T extends BaseEntity<ID>, ID> extends BaseDao implements Serializable{
    
    
    @PersistenceContext(unitName="eduobject")
    private EntityManager entityManager;
    
       
    protected Class<T> entityClass;
    
       
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
   
    
    public T find(ID id){
        return (T) super.find(id, getEntityClass());
    }

    public void delete(ID id){
        System.out.println("getEntityClass: "+getEntityClass().toString());
        super.delete(id, getEntityClass());
    }
    
  
    
      
    public List<T> findAll(){
        String tableName = getEntityClass().getAnnotation(Table.class).name();
        return this.findAll(tableName);
    }
    
}
