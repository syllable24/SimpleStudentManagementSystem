/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Departments")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Departments.findAll", query = "SELECT d FROM Departments d")
  , @NamedQuery(name = "Departments.findById", query = "SELECT d FROM Departments d WHERE d.id = :id")
  , @NamedQuery(name = "Departments.findByOrg", query = "SELECT d FROM Departments d WHERE d.org = :org")})
public class Departments implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Basic(optional = false)
  @Column(name = "Org")
  private int org;
  @JoinColumn(name = "School", referencedColumnName = "Org")
  @ManyToOne(optional = false)
  private Schools school;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
  private Collection<Majors> majorsCollection;

  public Departments() {
  }

  public Departments(String id) {
    this.id = id;
  }

  public Departments(String id, int org) {
    this.id = id;
    this.org = org;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getOrg() {
    return org;
  }

  public void setOrg(int org) {
    this.org = org;
  }

  public Schools getSchool() {
    return school;
  }

  public void setSchool(Schools school) {
    this.school = school;
  }

  @XmlTransient
  public Collection<Majors> getMajorsCollection() {
    return majorsCollection;
  }

  public void setMajorsCollection(Collection<Majors> majorsCollection) {
    this.majorsCollection = majorsCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Departments)) {
      return false;
    }
    Departments other = (Departments) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Departments[ id=" + id + " ]";
  }
  
}
