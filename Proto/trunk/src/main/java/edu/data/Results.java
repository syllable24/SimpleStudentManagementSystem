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
@Table(name = "Results")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Results.findAll", query = "SELECT r FROM Results r")
  , @NamedQuery(name = "Results.findByStudent", query = "SELECT r FROM Results r WHERE r.student = :student")
  , @NamedQuery(name = "Results.findById1", query = "SELECT r FROM Results r WHERE r.id1 = :id1")
  , @NamedQuery(name = "Results.findByExtID", query = "SELECT r FROM Results r WHERE r.extID = :extID")
  , @NamedQuery(name = "Results.findByGrade", query = "SELECT r FROM Results r WHERE r.grade = :grade")
  , @NamedQuery(name = "Results.findById", query = "SELECT r FROM Results r WHERE r.id = :id")
  , @NamedQuery(name = "Results.findByTitle", query = "SELECT r FROM Results r WHERE r.title = :title")
  , @NamedQuery(name = "Results.findByType", query = "SELECT r FROM Results r WHERE r.type = :type")})
public class Results implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "Student")
  private int student;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID1")
  private String id1;
  @Column(name = "ExtID")
  private String extID;
  @Column(name = "Grade")
  private String grade;
  @Basic(optional = false)
  @Column(name = "ID")
  private int id;
  @Column(name = "Title")
  private String title;
  @Column(name = "Type")
  private String type;

  public Results() {
  }

  public Results(String id1) {
    this.id1 = id1;
  }

  public Results(String id1, int student, int id) {
    this.id1 = id1;
    this.student = student;
    this.id = id;
  }

  public int getStudent() {
    return student;
  }

  public void setStudent(int student) {
    this.student = student;
  }

  public String getId1() {
    return id1;
  }

  public void setId1(String id1) {
    this.id1 = id1;
  }

  public String getExtID() {
    return extID;
  }

  public void setExtID(String extID) {
    this.extID = extID;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
    if (!(object instanceof Results)) {
      return false;
    }
    Results other = (Results) object;
    if ((this.id1 == null && other.id1 != null) || (this.id1 != null && !this.id1.equals(other.id1))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Results[ id1=" + id1 + " ]";
  }
  
}
