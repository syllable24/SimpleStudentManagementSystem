/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author newe
 */
@Entity
@Table(name = "Staff")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s")
  , @NamedQuery(name = "Staff.findById", query = "SELECT s FROM Staff s WHERE s.id = :id")
  , @NamedQuery(name = "Staff.findByPerson", query = "SELECT s FROM Staff s WHERE s.person = :person")
  , @NamedQuery(name = "Staff.findByExtID", query = "SELECT s FROM Staff s WHERE s.extID = :extID")})
public class Staff implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Id
  @Basic(optional = false)
  @Column(name = "Person")
  private String person;
  @Column(name = "extID")
  private String extID;
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "staff1")
  private Teachers teachers;

  public Staff() {
  }

  public Staff(String person) {
    this.person = person;
  }

  public Staff(String person, String id) {
    this.person = person;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPerson() {
    return person;
  }

  public void setPerson(String person) {
    this.person = person;
  }

  public String getExtID() {
    return extID;
  }

  public void setExtID(String extID) {
    this.extID = extID;
  }

  public Teachers getTeachers() {
    return teachers;
  }

  public void setTeachers(Teachers teachers) {
    this.teachers = teachers;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (person != null ? person.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Staff)) {
      return false;
    }
    Staff other = (Staff) object;
    if ((this.person == null && other.person != null) || (this.person != null && !this.person.equals(other.person))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Staff[ person=" + person + " ]";
  }
  
}
