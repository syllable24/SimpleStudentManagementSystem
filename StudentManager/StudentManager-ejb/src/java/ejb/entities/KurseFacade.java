/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ralph
 */
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
    
}
