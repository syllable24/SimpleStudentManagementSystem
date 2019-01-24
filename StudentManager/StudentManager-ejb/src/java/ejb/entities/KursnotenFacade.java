/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entities;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ralph
 */
@Stateless
public class KursnotenFacade extends AbstractFacade<Kursnoten> {

    @EJB
    StudentenFacade studentenFacade;
    
    @PersistenceContext(unitName = "StudentManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;      
    }

    public KursnotenFacade() {
        super(Kursnoten.class);
    }       
    
    public void updateKursnote(Kursnoten kn){       
        Query q = em.createQuery("UPDATE Kursnoten k "
                        + "SET k.student = :student, k.kurse = :kurs, k.Note = :note "
                        + "WHERE k.id = :kursnoteID");

        q.setParameter("student", kn.getStudent());
        q.setParameter("kurs", kn.getKurse());   
        q.setParameter("note",kn.getNote());
        q.setParameter("kursnoteID",  + kn.getId());
        q.executeUpdate();
        
    }
    public void deletetudentKursLink(Kursnoten kn){       
        Query q = em.createQuery("DELETE Kursnoten k "
                                 + "WHERE k.id = :kursnoteID");       
        q.setParameter("kursnoteID",  + kn.getId());
        q.executeUpdate();        
    }    
    
    
    /**
     * Schreibt einen Studenten in einen Kurs ein. 
     * Dabei darf keine Note gesetzt sein.
     * 
     * @param kn Verknüpfung von Student und Kurs
     */
    public void insertStudentKursLink(Kursnoten kn){
        if(kn.getNote() == null){
            em.persist(kn);
        }
    }
}
