/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enities;

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
@Table(name = "Classes")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Classes.findAll", query = "SELECT c FROM Classes c")
  , @NamedQuery(name = "Classes.findById1", query = "SELECT c FROM Classes c WHERE c.id1 = :id1")
  , @NamedQuery(name = "Classes.findByCountSemester", query = "SELECT c FROM Classes c WHERE c.countSemester = :countSemester")
  , @NamedQuery(name = "Classes.findById", query = "SELECT c FROM Classes c WHERE c.id = :id")
  , @NamedQuery(name = "Classes.findByMaxStudents", query = "SELECT c FROM Classes c WHERE c.maxStudents = :maxStudents")
  , @NamedQuery(name = "Classes.findByStartYear", query = "SELECT c FROM Classes c WHERE c.startYear = :startYear")})
public class Classes implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID1")
  private String id1;
  @Column(name = "CountSemester")
  private Integer countSemester;
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Column(name = "MaxStudents")
  private Integer maxStudents;
  @Column(name = "StartYear")
  private Integer startYear;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "class1")
  private Collection<Groups> groupsCollection;
  @JoinColumn(name = "Major", referencedColumnName = "ID1")
  @ManyToOne(optional = false)
  private Majors major;

  public Classes() {
  }

  public Classes(String id1) {
    this.id1 = id1;
  }

  public Classes(String id1, String id) {
    this.id1 = id1;
    this.id = id;
  }

  public String getId1() {
    return id1;
  }

  public void setId1(String id1) {
    this.id1 = id1;
  }

  public Integer getCountSemester() {
    return countSemester;
  }

  public void setCountSemester(Integer countSemester) {
    this.countSemester = countSemester;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getMaxStudents() {
    return maxStudents;
  }

  public void setMaxStudents(Integer maxStudents) {
    this.maxStudents = maxStudents;
  }

  public Integer getStartYear() {
    return startYear;
  }

  public void setStartYear(Integer startYear) {
    this.startYear = startYear;
  }

  @XmlTransient
  public Collection<Groups> getGroupsCollection() {
    return groupsCollection;
  }

  public void setGroupsCollection(Collection<Groups> groupsCollection) {
    this.groupsCollection = groupsCollection;
  }

  public Majors getMajor() {
    return major;
  }

  public void setMajor(Majors major) {
    this.major = major;
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
    if (!(object instanceof Classes)) {
      return false;
    }
    Classes other = (Classes) object;
    if ((this.id1 == null && other.id1 != null) || (this.id1 != null && !this.id1.equals(other.id1))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Classes[ id1=" + id1 + " ]";
  }
  
}
