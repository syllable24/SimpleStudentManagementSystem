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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Subjects")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Subjects.findAll", query = "SELECT s FROM Subjects s")
  , @NamedQuery(name = "Subjects.findBySchool", query = "SELECT s FROM Subjects s WHERE s.school = :school")
  , @NamedQuery(name = "Subjects.findById1", query = "SELECT s FROM Subjects s WHERE s.id1 = :id1")
  , @NamedQuery(name = "Subjects.findById", query = "SELECT s FROM Subjects s WHERE s.id = :id")
  , @NamedQuery(name = "Subjects.findByName", query = "SELECT s FROM Subjects s WHERE s.name = :name")})
public class Subjects implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "School")
  private int school;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID1")
  private String id1;
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Column(name = "Name")
  private String name;

  public Subjects() {
  }

  public Subjects(String id1) {
    this.id1 = id1;
  }

  public Subjects(String id1, int school, String id) {
    this.id1 = id1;
    this.school = school;
    this.id = id;
  }

  public int getSchool() {
    return school;
  }

  public void setSchool(int school) {
    this.school = school;
  }

  public String getId1() {
    return id1;
  }

  public void setId1(String id1) {
    this.id1 = id1;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id1 != null ? id1.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Subjects)) {
      return false;
    }
    Subjects other = (Subjects) object;
    if ((this.id1 == null && other.id1 != null) || (this.id1 != null && !this.id1.equals(other.id1))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Subjects[ id1=" + id1 + " ]";
  }
  
}
