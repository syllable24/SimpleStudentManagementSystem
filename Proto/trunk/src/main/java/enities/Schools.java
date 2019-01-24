/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enities;

import enums.Schooltypes;
import java.util.Collection;
import javax.enterprise.inject.Model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author newe
 */
@Model
@Entity
@Table(name = "Schools")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Schools.findAll", query = "SELECT s FROM Schools s")
        ,@NamedQuery(name = "Schools.find", query = "SELECT s FROM Schools s WHERE s.id = :id")
  , @NamedQuery(name = "Schools.findById", query = "SELECT s FROM Schools s WHERE s.id = :id")
  , @NamedQuery(name = "Schools.findByOrg", query = "SELECT s FROM Schools s WHERE s.org = :org")})
public class Schools extends BaseEntity<Integer> {

  private static final long serialVersionUID = 1L;
  
  private Schooltypes model;
  
   
  @JoinColumn(name="org")
  @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
  private Organizations org;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
  private Collection<Departments> departmentsCollection;

  public Schools() {
      this.setOrg(new Organizations());
  }

  public Schools(Organizations org) {
    this.org = org;
  }

  public Schools(Organizations org, int id) {
    this.org = org;
    this.setId(id);
  }

  public Organizations getOrg() {
      if (org == null) org=new Organizations();
      return org;
  }

  public void setOrg(Organizations org) {
    this.org = org;
  }
  
  public Schooltypes getModel() {
        return model;
    }

    public void setModel(Schooltypes model) {
        this.model = model;
    }
    



  @XmlTransient
  public Collection<Departments> getDepartmentsCollection() {
    return departmentsCollection;
  }

  public void setDepartmentsCollection(Collection<Departments> departmentsCollection) {
    this.departmentsCollection = departmentsCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (org != null ? org.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Schools)) {
      return false;
    }
    Schools other = (Schools) object;
    if ((this.org == null && other.org != null) || (this.org != null && !this.org.equals(other.org))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Schools[ id="+ getId() +", model="+ model + " , org=" + org + " ]";
  }
  
}
