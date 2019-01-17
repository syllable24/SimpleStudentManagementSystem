/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.message;

import ejb.entities.Studenten;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author ralph
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TestDestination")
    ,@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class TestMessageBean implements MessageListener {
    
    public TestMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objm;
            objm = (ObjectMessage) message;
            Studenten st = (Studenten) objm.getObject();
            System.out.println("---------------->>> " + st.getNachname() + "; " + st.getVorname());
        } catch (JMSException ex) {
            Logger.getLogger(TestMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
