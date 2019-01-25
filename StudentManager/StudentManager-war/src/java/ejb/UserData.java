/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.entities.Lehrer;
import ejb.entities.Studenten;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Status Bean, dass zur Identifikation des aktuell eingeloggtem Users dient.
 */
@WebListener
@Stateless
@LocalBean
public class UserData implements HttpSessionBindingListener{
       
    private Studenten currentStudent;
    private Lehrer currentLehrer;
    private long userID;
    private String userOverview;
    
    
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("EventName: " + event.getName() + " EventValue: " + event.getValue());
    }

    /**     
     * @return userID
     */
    public long getCurrentUserID(){
        return userID;
    }
    
    /**
     * @param id is assigned to userID. 
     */
    public void setCurrentUserID(long id){
        this.userID = id;           
    }
    
    /**
     * Setzt den aktuellen Studenten.
     * Weites wird der Overview String des Studenten initialisiert.
     * 
     * @param student is assigned to currentStudent.
     */
    public void setCurrentStudent(Studenten student){
        this.currentStudent = student;
        this.userID = student.getId();
        
        String studName = student.getVorname() + " " + student.getNachname();
        String stuStudigang = student.getStudiengaenge().getBezeichnung();
        Date studBirthdate = student.getGeburtsdatum();        
        
        this.userOverview = "<form method='post'>"
                        + "<p style = 'border:3px; border-style:solid; border-color:#00FF00; background-color:#00FF00; padding:1em;'> "
                        + studName
                        + "<br>"  + studBirthdate.toString() 
                        + "<br>"  + stuStudigang                         
                        + "<br> " + student.getGruppe().getBezeichnung() + " </p>"
                        + "<input type='submit' name='LogOut' value='LogOut'>"
                        + "</form>";
    }
    
    /**
     * Setzt den aktuellen Lehrer.
     * Weites wird der Overview String des Lehrers initialisiert.
     * 
     * @param lehrer is assigned to currentLehrer.
     */
    public void setCurrentLehrer(Lehrer lehrer){
        this.currentLehrer = lehrer;
        this.userID = lehrer.getId();
        
        String lehrName = lehrer.getNachname() + " " + lehrer.getVorname();
        Date lehrBirthdate = lehrer.getGeburtsdatum();
        
        this.userOverview = "<form method ='post'>"
                        + "<p style = 'border:3px; border-style:solid; border-color:#00FF00; background-color:#00FF00; padding:1em;'> "
                        + lehrName
                        + "<br>"  + lehrBirthdate.toString() + " </p>"
                        + "<input type='submit' name='LogOut' value='LogOut'>"
                        + "</form>";
    }        
    
    /**
     * @return User-Overview String mit LogOut-Button
     */
    public String getUserOverview(){
        return this.userOverview;
    }    

    /**     
     * @return currentStudent
     */
    public Studenten getCurrentStudent() {
        return currentStudent;
    }
    /**
     * @return currentLehrer
     */
    public Lehrer getCurrentLehrer() {
        return currentLehrer;
    } 
    
    /**
     * Löscht currentLehrer, currentStudent, userID und userOverview.
     */
    public void clear(){
        this.currentLehrer = null;
        this.currentStudent = null;
        this.userID = 0;
        this.userOverview = "";
    }
    
}
