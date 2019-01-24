/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enities;

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
@Table(name = "Courses_Groups")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "CoursesGroups.findAll", query = "SELECT c FROM CoursesGroups c")
  , @NamedQuery(name = "CoursesGroups.findByCourses", query = "SELECT c FROM CoursesGroups c WHERE c.courses = :courses")
  , @NamedQuery(name = "CoursesGroups.findByGroups", query = "SELECT c FROM CoursesGroups c WHERE c.groups = :groups")
  , @NamedQuery(name = "CoursesGroups.findByElementKey", query = "SELECT c FROM CoursesGroups c WHERE c.elementKey = :elementKey")})
public class CoursesGroups implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "Courses")
  private String courses;
  @Column(name = "Groups")
  private String groups;
  @Id
  @Basic(optional = false)
  @Column(name = "element_key")
  private String elementKey;

  public CoursesGroups() {
  }

  public CoursesGroups(String elementKey) {
    this.elementKey = elementKey;
  }

  public CoursesGroups(String elementKey, String courses) {
    this.elementKey = elementKey;
    this.courses = courses;
  }

  public String getCourses() {
    return courses;
  }

  public void setCourses(String courses) {
    this.courses = courses;
  }

  public String getGroups() {
    return groups;
  }

  public void setGroups(String groups) {
    this.groups = groups;
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
    hash += (elementKey != null ? elementKey.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof CoursesGroups)) {
      return false;
    }
    CoursesGroups other = (CoursesGroups) object;
    if ((this.elementKey == null && other.elementKey != null) || (this.elementKey != null && !this.elementKey.equals(other.elementKey))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.CoursesGroups[ elementKey=" + elementKey + " ]";
  }
  
}
