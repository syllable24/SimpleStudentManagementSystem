/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author ralph
 */
@Singleton
@LocalBean
@WebListener
public class SessionManager implements HttpSessionListener {

    Object currentUser;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }
        
    public void setCurrentUser(Object user){
        if(currentUser == null){
            currentUser = user;
        }        
    }
    
    public Object getCurrentUser(){
        return currentUser;
    }    
}
