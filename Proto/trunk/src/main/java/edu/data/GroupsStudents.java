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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author newe
 */
@Entity
@Table(name = "Groups_Students")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "GroupsStudents.findAll", query = "SELECT g FROM GroupsStudents g")
  , @NamedQuery(name = "GroupsStudents.findById", query = "SELECT g FROM GroupsStudents g WHERE g.id = :id")
  , @NamedQuery(name = "GroupsStudents.findByStudents", query = "SELECT g FROM GroupsStudents g WHERE g.students = :students")
  , @NamedQuery(name = "GroupsStudents.findByElementKey", query = "SELECT g FROM GroupsStudents g WHERE g.elementKey = :elementKey")})
public class GroupsStudents implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Column(name = "Students")
  private Integer students;
  @Basic(optional = false)
  @Column(name = "element_key")
  private String elementKey;
  @JoinColumn(name = "Groups", referencedColumnName = "ID1")
  @ManyToOne(optional = false)
  private Groups groups;

  public GroupsStudents() {
  }

  public GroupsStudents(String id) {
    this.id = id;
  }

  public GroupsStudents(String id, String elementKey) {
    this.id = id;
    this.elementKey = elementKey;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getStudents() {
    return students;
  }

  public void setStudents(Integer students) {
    this.students = students;
  }

  public String getElementKey() {
    return elementKey;
  }

  public void setElementKey(String elementKey) {
    this.elementKey = elementKey;
  }

  public Groups getGroups() {
    return groups;
  }

  public void setGroups(Groups groups) {
    this.groups = groups;
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
    if (!(object instanceof GroupsStudents)) {
      return false;
    }
    GroupsStudents other = (GroupsStudents) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.GroupsStudents[ id=" + id + " ]";
  }
  
}
