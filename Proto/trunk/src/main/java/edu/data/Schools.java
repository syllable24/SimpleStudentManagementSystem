/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author newe
 */
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
  
  @Column(name = "model")
  private Integer model;
   
  private Integer org;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
  private Collection<Departments> departmentsCollection;

  public Schools() {
  }

  public Schools(int org) {
    this.org = org;
  }

  public Schools(int org, int id) {
    this.org = org;
    this.setId(id);
  }

  public Integer getOrg() {
    return org;
  }

  public void setOrg(Integer org) {
    this.org = org;
  }
  
  public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
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
    return "edu.Schools[ org=" + org + " ]";
  }
  
}
