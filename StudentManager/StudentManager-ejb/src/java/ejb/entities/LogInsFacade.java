/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ralph
 */
@Stateless
public class LogInsFacade extends AbstractFacade<LogIns> {

    @PersistenceContext(unitName = "StudentManager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Prüft, ob die logIn Daten zu einem user gehören und retourniert das
     * entsprechende Entity-Objekt des Users. 
     * 
     * @param logInData
     * @return Entity der Userdaten, null wenn LogIn Daten nicht gefunden.
     */
    public Object determineUserType(LogIns logInData){
        LogIns queryResult;
        Object result = null;
        try{
            String pwMD5  = logInData.getPasswortMD5();
            String usrMD5 = logInData.getUsernameMD5();

            System.out.println("UserName: " + usrMD5);
            System.out.println("UserPWD: " + pwMD5);

            Query q = em.createQuery("SELECT l FROM LogIns l WHERE l.PasswortMD5 = '" + pwMD5 + "' AND l.UsernameMD5 = '" + usrMD5 + "'");        
            queryResult = (LogIns) q.getSingleResult();
            
            if (queryResult.getStudent() != null){
                result = queryResult.getStudent();                
            }
            else if (queryResult.getLehrer() != null){
                result = queryResult.getStudent();
            }        
        }
        catch(NoResultException e){
            System.out.println("No matching User!");            
        }
        return result;
    }
    
    public LogInsFacade() {
        super(LogIns.class);
    }
    
}
