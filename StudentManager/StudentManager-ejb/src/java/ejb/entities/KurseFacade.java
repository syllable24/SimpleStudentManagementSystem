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
public class KurseFacade extends AbstractFacade<Kurse> {

    @PersistenceContext(unitName = "StudentManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KurseFacade() {
        super(Kurse.class);
    }
    
    /**
     * Retourniert eine Kurs-Liste basierend auf dem Namen des Kurses. 
     * 
     * @param name Name des Kurses.
     * @return Liste an Kursen, die den übergebenen Namen haben.
     */
    public List<Kurse> getKursByName(String name){
        List<Kurse> result = null;
        
        Query q = em.createQuery("SELECT k FROM Kurse k WHERE k.Bezeichnung ='" + name + "'");
        result = (List<Kurse>) q.getResultList();
        return result;
    }    
    
}
