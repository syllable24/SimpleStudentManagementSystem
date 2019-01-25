/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entities;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    /**
     * Aktualisiert die Note eines Studenten in einem bestimmten Kurs. 
     * 
     * @param kn Verknüpfungs-Entity, welche die Zuordnung von Student, Kurs und 
     * Note beinhaltet.
     */
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
    
    /**
     * Entfernt einen Studenten aus einem Kurs. 
     *      
     * @param kn Verknüpfungs-Entity, welche die Zuordnung von Student und Kurs 
     * beinhaltet.
     */
    public void deleteStudentKursLink(Kursnoten kn){       
        Query q = em.createQuery("DELETE FROM Kursnoten k "
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
        if(kn.getNote().equals("Kein Eintrag")){
            em.persist(kn);
        }
    }
}
