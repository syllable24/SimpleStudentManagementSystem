/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.SchoolDao;
import enities.Organizations;
import enities.Schools;
import enums.Schooltypes;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author newe
 */
@ManagedBean
@SessionScoped
public class SchoolBean implements Serializable{

    //@Inject
    private final SchoolDao schoolDao = new SchoolDao();


    private List<Schools> schoolList;
    private List<Schools> filteredValue;//datatable filteredValue attribute
    private Integer id;
    private Schools school;
    private Organizations org;
    //@Inject
    
    public SchoolBean(){
        System.out.println("SchoolBean created");
    }

    
    
    public List<Schools> getSchoolList(){
        if(schoolList == null){
            schoolList = schoolDao.findAll();
        }
        return schoolList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Schools getSchool() {
        if(school == null){
            school = new Schools();
        }
        return school;
    }

    public void setSchool(Schools school) {
        if(school == null){
            school = new Schools();
        }
        System.out.println("School="+school.toString());
        this.school = school;
    }
    
    public void setOrg(Organizations org){
        this.school.setOrg(org);
    }
    
    public Organizations getOrg(){
        if(org == null){
            org = new Organizations();
        }
        return org;
    }
    
    public Schooltypes[] getSchooltypes() {
        return Schooltypes.values();
    }
    
    public void findSchoolById(Integer id){
         this.school = schoolDao.find(id);
         System.out.println("findById "+this.school.getOrg());
    }

    public List<Schools> getFilteredValue() {
        return filteredValue;
    }

    public void setFilteredValue(List<Schools> filteredValue) {
        this.filteredValue = filteredValue;
    }
    
    public void remove(){
        System.out.println("remove: "+school.getId());
        if(school != null && school.getId() != null){
            schoolDao.delete(school.getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("School "+school.getId() +" removed successfully"));
            clear();
        }
    }
    
    public void update(){
        String msg;
        if(school.getId() == null){
          try {   
            schoolDao.insert(school);
             msg = "School "+school.getOrg().getName() +" created successfully";
          } catch (SQLException | DatabaseException e ) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
          }
        }
        else{
           try {
             schoolDao.update(school);
             msg = "School "+school.getOrg().getName() +" updated successfully";
           } catch (SQLException | DatabaseException e) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
           }
        }
        clear();//reload School list
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("form:schoolList");
    }
    
    public void clear(){
        school = new Schools();
        schoolList = null;
        schoolList = getSchoolList();
        System.out.println(schoolList);
    }
    
    public void onRowSelect(SelectEvent event) {
        setId(((Schools) event.getObject()).getId());
        findSchoolById(getId());  
    }  
           
    public void onRowUnselect(UnselectEvent event) {  
        school = new Schools();
    }
    

}
