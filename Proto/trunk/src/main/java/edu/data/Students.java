/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author newe
 */
@Entity
@Table(name = "Students")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s")
  , @NamedQuery(name = "Students.findById", query = "SELECT s FROM Students s WHERE s.id = :id")
  , @NamedQuery(name = "Students.findByExtID", query = "SELECT s FROM Students s WHERE s.extID = :extID")
  , @NamedQuery(name = "Students.findByPerson", query = "SELECT s FROM Students s WHERE s.person = :person")})
public class Students implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "ID")
  private int id;
  @Column(name = "ExtID")
  private String extID;
  @Id
  @Basic(optional = false)
  @Column(name = "Person")
  private Integer person;

  public Students() {
  }

  public Students(Integer person) {
    this.person = person;
  }

  public Students(Integer person, int id) {
    this.person = person;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getExtID() {
    return extID;
  }

  public void setExtID(String extID) {
    this.extID = extID;
  }

  public Integer getPerson() {
    return person;
  }

  public void setPerson(Integer person) {
    this.person = person;
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
    if (!(object instanceof Students)) {
      return false;
    }
    Students other = (Students) object;
    if ((this.person == null && other.person != null) || (this.person != null && !this.person.equals(other.person))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Students[ person=" + person + " ]";
  }
  
}
