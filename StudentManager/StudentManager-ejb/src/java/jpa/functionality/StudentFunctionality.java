/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.functionality;

import ejb.entities.AbstractFacade;
import ejb.entities.Studenten;
import ejb.entities.Studiengaenge;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ralph
 */
@Stateless
@LocalBean
public class StudentFunctionality extends AbstractFacade<Studenten> {

    @PersistenceContext(unitName = "StudentManager-ejbPU")
    private EntityManager em;

    public StudentFunctionality() {
        super(Studenten.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public StudentFunctionality(Class<Studenten> entityClass) {
        super(entityClass);
    }
        
}
