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
@Table(name = "Courses_Locations")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "CoursesLocations.findAll", query = "SELECT c FROM CoursesLocations c")
  , @NamedQuery(name = "CoursesLocations.findByCourses", query = "SELECT c FROM CoursesLocations c WHERE c.courses = :courses")
  , @NamedQuery(name = "CoursesLocations.findByLocations", query = "SELECT c FROM CoursesLocations c WHERE c.locations = :locations")
  , @NamedQuery(name = "CoursesLocations.findByElementKey", query = "SELECT c FROM CoursesLocations c WHERE c.elementKey = :elementKey")})
public class CoursesLocations implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "Courses")
  private String courses;
  @Column(name = "Locations")
  private Integer locations;
  @Id
  @Basic(optional = false)
  @Column(name = "element_key")
  private String elementKey;

  public CoursesLocations() {
  }

  public CoursesLocations(String elementKey) {
    this.elementKey = elementKey;
  }

  public CoursesLocations(String elementKey, String courses) {
    this.elementKey = elementKey;
    this.courses = courses;
  }

  public String getCourses() {
    return courses;
  }

  public void setCourses(String courses) {
    this.courses = courses;
  }

  public Integer getLocations() {
    return locations;
  }

  public void setLocations(Integer locations) {
    this.locations = locations;
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
    if (!(object instanceof CoursesLocations)) {
      return false;
    }
    CoursesLocations other = (CoursesLocations) object;
    if ((this.elementKey == null && other.elementKey != null) || (this.elementKey != null && !this.elementKey.equals(other.elementKey))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.CoursesLocations[ elementKey=" + elementKey + " ]";
  }
  
}
