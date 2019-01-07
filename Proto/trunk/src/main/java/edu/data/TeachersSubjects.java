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
@Table(name = "Teachers_Subjects")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TeachersSubjects.findAll", query = "SELECT t FROM TeachersSubjects t")
  , @NamedQuery(name = "TeachersSubjects.findByTeachers", query = "SELECT t FROM TeachersSubjects t WHERE t.teachers = :teachers")
  , @NamedQuery(name = "TeachersSubjects.findById", query = "SELECT t FROM TeachersSubjects t WHERE t.id = :id")
  , @NamedQuery(name = "TeachersSubjects.findBySubjects", query = "SELECT t FROM TeachersSubjects t WHERE t.subjects = :subjects")
  , @NamedQuery(name = "TeachersSubjects.findByElementKey", query = "SELECT t FROM TeachersSubjects t WHERE t.elementKey = :elementKey")})
public class TeachersSubjects implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "Teachers")
  private String teachers;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Column(name = "Subjects")
  private String subjects;
  @Basic(optional = false)
  @Column(name = "element_key")
  private String elementKey;

  public TeachersSubjects() {
  }

  public TeachersSubjects(String id) {
    this.id = id;
  }

  public TeachersSubjects(String id, String teachers, String elementKey) {
    this.id = id;
    this.teachers = teachers;
    this.elementKey = elementKey;
  }

  public String getTeachers() {
    return teachers;
  }

  public void setTeachers(String teachers) {
    this.teachers = teachers;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSubjects() {
    return subjects;
  }

  public void setSubjects(String subjects) {
    this.subjects = subjects;
  }

  public String getElementKey() {
    return elementKey;
  }

  public void setElementKey(String elementKey) {
    this.elementKey = elementKey;
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
    if (!(object instanceof TeachersSubjects)) {
      return false;
    }
    TeachersSubjects other = (TeachersSubjects) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.TeachersSubjects[ id=" + id + " ]";
  }
  
}
