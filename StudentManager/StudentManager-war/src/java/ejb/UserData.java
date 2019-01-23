/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 *
 * @author ralph
 */
@WebListener
@Stateless
@LocalBean
public class UserData implements HttpSessionBindingListener{
    
    private long userID;
    
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("EventName: " + event.getName() + " EventValue: " + event.getValue());
    }

    public long getCurrentUserID(){
        return userID;
    }
    
    public void setCurrentUserID(long id){
        this.userID = id;
    }
    
}
