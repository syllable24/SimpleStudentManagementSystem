/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.message;

import ejb.entities.Studiengaenge;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ralph
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TestDestination")
    ,@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class TestMessageBean implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;    
    @PersistenceContext(unitName = "StudentManager-ejbPU")
    private EntityManager em;    
    
    public TestMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objm;
            objm = (ObjectMessage) message;
            Studiengaenge st = (Studiengaenge) objm.getObject();
            System.out.println("---------------->>> " + st.getBezeichnung());                                                
            persist(st);
            
        } catch (JMSException ex) {
            mdc.setRollbackOnly();
            Logger.getLogger(TestMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
