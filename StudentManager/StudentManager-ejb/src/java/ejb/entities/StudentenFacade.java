/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entities;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ralph
 */
@Stateless
public class StudentenFacade extends AbstractFacade<Studenten> {

    @PersistenceContext(unitName = "StudentManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StudentenFacade() {
        super(Studenten.class);
    }
    
    public List<Kurse> getKurse(long studentenID){
        Studenten stud = this.find(studentenID);         
        List<Kurse> kurse = stud.getKurses();
        return kurse;
    }    
}
