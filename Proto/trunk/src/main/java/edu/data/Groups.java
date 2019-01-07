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
@Table(name = "Groups")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g")
  , @NamedQuery(name = "Groups.findById1", query = "SELECT g FROM Groups g WHERE g.id1 = :id1")
  , @NamedQuery(name = "Groups.findById", query = "SELECT g FROM Groups g WHERE g.id = :id")
  , @NamedQuery(name = "Groups.findByChildsub", query = "SELECT g FROM Groups g WHERE g.childsub = :childsub")})
public class Groups implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID1")
  private String id1;
  @Column(name = "ID")
  private String id;
  @Basic(optional = false)
  @Column(name = "childsub")
  private int childsub;
  @JoinColumn(name = "Class", referencedColumnName = "ID1")
  @ManyToOne(optional = false)
  private Classes class1;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "groups")
  private Collection<GroupsStudents> groupsStudentsCollection;

  public Groups() {
  }

  public Groups(String id1) {
    this.id1 = id1;
  }

  public Groups(String id1, int childsub) {
    this.id1 = id1;
    this.childsub = childsub;
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

  public int getChildsub() {
    return childsub;
  }

  public void setChildsub(int childsub) {
    this.childsub = childsub;
  }

  public Classes getClass1() {
    return class1;
  }

  public void setClass1(Classes class1) {
    this.class1 = class1;
  }

  @XmlTransient
  public Collection<GroupsStudents> getGroupsStudentsCollection() {
    return groupsStudentsCollection;
  }

  public void setGroupsStudentsCollection(Collection<GroupsStudents> groupsStudentsCollection) {
    this.groupsStudentsCollection = groupsStudentsCollection;
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
    if (!(object instanceof Groups)) {
      return false;
    }
    Groups other = (Groups) object;
    if ((this.id1 == null && other.id1 != null) || (this.id1 != null && !this.id1.equals(other.id1))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Groups[ id1=" + id1 + " ]";
  }
  
}
