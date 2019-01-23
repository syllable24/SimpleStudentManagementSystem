/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.entities.Lehrer;
import ejb.entities.LehrerFacade;
import ejb.entities.Studenten;
import ejb.entities.StudentenFacade;
import java.util.Date;
import javax.ejb.EJB;
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
    
    @EJB 
    private LehrerFacade lehrerF;
    
    @EJB 
    private StudentenFacade studF;
    
    private Studenten currentStudent;
    private Lehrer currentLehrer;
    private long userID;
    private String userOverview;
    
    
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
    
    public void setCurrentStudent(Studenten student){
        this.currentStudent = student;
        this.userID = student.getId();
        
        String studName = student.getVorname() + " " + student.getNachname();
        String stuStudigang = student.getStudiengaenge().getBezeichnung();
        Date studBirthdate = student.getGeburtsdatum();        
        
        this.userOverview = "<p style = 'border:3px; border-style:solid; border-color:#00FF00; background-color:#00FF00; padding:1em;'> "
                        + studName
                        + "<br>"  + studBirthdate.toString() 
                        + "<br>"  + stuStudigang                         
                        + "<br> " + student.getGruppe().getBezeichnung() + " </p>";
    }
    
    public void setCurrentLehrer(Lehrer lehrer){
        this.currentLehrer = lehrer;
        this.userID = lehrer.getId();
        
        String lehrName = lehrer.getNachname() + " " + lehrer.getVorname();
        Date lehrBirthdate = lehrer.getGeburtsdatum();
        
        this.userOverview = "<p style = 'border:3px; border-style:solid; border-color:#00FF00; background-color:#00FF00; padding:1em;'> "
                        + lehrName
                        + "<br>"  + lehrBirthdate.toString() + " </p>";
    }
    
    public String getUserOverview(){
        return this.userOverview;
    }    

    public Studenten getCurrentStudent() {
        return currentStudent;
    }

    public Lehrer getCurrentLehrer() {
        return currentLehrer;
    }    
}
