/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author newe
 */
@Entity
@Table(name = "Majors")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Majors.findAll", query = "SELECT m FROM Majors m")
  , @NamedQuery(name = "Majors.findById1", query = "SELECT m FROM Majors m WHERE m.id1 = :id1")
  , @NamedQuery(name = "Majors.findByColorDependency", query = "SELECT m FROM Majors m WHERE m.colorDependency = :colorDependency")
  , @NamedQuery(name = "Majors.findByCountSemesters", query = "SELECT m FROM Majors m WHERE m.countSemesters = :countSemesters")
  , @NamedQuery(name = "Majors.findById", query = "SELECT m FROM Majors m WHERE m.id = :id")
  , @NamedQuery(name = "Majors.findByMidday", query = "SELECT m FROM Majors m WHERE m.midday = :midday")
  , @NamedQuery(name = "Majors.findByMiddayEnde", query = "SELECT m FROM Majors m WHERE m.middayEnde = :middayEnde")
  , @NamedQuery(name = "Majors.findByMiddayStart", query = "SELECT m FROM Majors m WHERE m.middayStart = :middayStart")
  , @NamedQuery(name = "Majors.findByPauseDuration", query = "SELECT m FROM Majors m WHERE m.pauseDuration = :pauseDuration")
  , @NamedQuery(name = "Majors.findByRasterMinutes", query = "SELECT m FROM Majors m WHERE m.rasterMinutes = :rasterMinutes")
  , @NamedQuery(name = "Majors.findByStartTime", query = "SELECT m FROM Majors m WHERE m.startTime = :startTime")})
public class Majors implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID1")
  private String id1;
  @Column(name = "ColorDependency")
  private Integer colorDependency;
  @Column(name = "CountSemesters")
  private Integer countSemesters;
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Column(name = "Midday")
  private Boolean midday;
  @Column(name = "MiddayEnde")
  @Temporal(TemporalType.TIME)
  private Date middayEnde;
  @Column(name = "MiddayStart")
  @Temporal(TemporalType.TIME)
  private Date middayStart;
  @Column(name = "PauseDuration")
  private Integer pauseDuration;
  @Column(name = "RasterMinutes")
  private Integer rasterMinutes;
  @Column(name = "StartTime")
  @Temporal(TemporalType.TIME)
  private Date startTime;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "major")
  private Collection<Courses> coursesCollection;
  @JoinColumn(name = "Department", referencedColumnName = "ID")
  @ManyToOne(optional = false)
  private Departments department;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "major")
  private Collection<Classes> classesCollection;

  public Majors() {
  }

  public Majors(String id1) {
    this.id1 = id1;
  }

  public Majors(String id1, String id) {
    this.id1 = id1;
    this.id = id;
  }

  public String getId1() {
    return id1;
  }

  public void setId1(String id1) {
    this.id1 = id1;
  }

  public Integer getColorDependency() {
    return colorDependency;
  }

  public void setColorDependency(Integer colorDependency) {
    this.colorDependency = colorDependency;
  }

  public Integer getCountSemesters() {
    return countSemesters;
  }

  public void setCountSemesters(Integer countSemesters) {
    this.countSemesters = countSemesters;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Boolean getMidday() {
    return midday;
  }

  public void setMidday(Boolean midday) {
    this.midday = midday;
  }

  public Date getMiddayEnde() {
    return middayEnde;
  }

  public void setMiddayEnde(Date middayEnde) {
    this.middayEnde = middayEnde;
  }

  public Date getMiddayStart() {
    return middayStart;
  }

  public void setMiddayStart(Date middayStart) {
    this.middayStart = middayStart;
  }

  public Integer getPauseDuration() {
    return pauseDuration;
  }

  public void setPauseDuration(Integer pauseDuration) {
    this.pauseDuration = pauseDuration;
  }

  public Integer getRasterMinutes() {
    return rasterMinutes;
  }

  public void setRasterMinutes(Integer rasterMinutes) {
    this.rasterMinutes = rasterMinutes;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  @XmlTransient
  public Collection<Courses> getCoursesCollection() {
    return coursesCollection;
  }

  public void setCoursesCollection(Collection<Courses> coursesCollection) {
    this.coursesCollection = coursesCollection;
  }

  public Departments getDepartment() {
    return department;
  }

  public void setDepartment(Departments department) {
    this.department = department;
  }

  @XmlTransient
  public Collection<Classes> getClassesCollection() {
    return classesCollection;
  }

  public void setClassesCollection(Collection<Classes> classesCollection) {
    this.classesCollection = classesCollection;
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
    if (!(object instanceof Majors)) {
      return false;
    }
    Majors other = (Majors) object;
    if ((this.id1 == null && other.id1 != null) || (this.id1 != null && !this.id1.equals(other.id1))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Majors[ id1=" + id1 + " ]";
  }
  
}
