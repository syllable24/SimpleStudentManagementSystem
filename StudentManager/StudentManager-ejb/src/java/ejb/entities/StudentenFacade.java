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
import javax.persistence.Query;

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
    
    /**
     * Retourniert eine Studenten-Liste basierend auf dem Nachnamen der Studenten. 
     * 
     * @param name Nachname des Studenten
     * @return Liste an Studenten, die den übergebenen Nachnamen haben.
     */
    public List<Studenten> getStudentByName(String name){
        List<Studenten> result = null;
        
        Query q = em.createQuery("SELECT s FROM Studenten s WHERE s.Nachname = '" + name + "'");
        // q.setParameter für LIKE Query verwenden
        
        result = (List<Studenten>) q.getResultList();        
        return result;
    }                
    
}
