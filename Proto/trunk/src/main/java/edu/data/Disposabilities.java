/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author newe
 */
@Entity
@Table(name = "Disposabilities")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Disposabilities.findAll", query = "SELECT d FROM Disposabilities d")
  , @NamedQuery(name = "Disposabilities.findByTeacher", query = "SELECT d FROM Disposabilities d WHERE d.teacher = :teacher")
  , @NamedQuery(name = "Disposabilities.findById", query = "SELECT d FROM Disposabilities d WHERE d.id = :id")
  , @NamedQuery(name = "Disposabilities.findByEndtime", query = "SELECT d FROM Disposabilities d WHERE d.endtime = :endtime")
  , @NamedQuery(name = "Disposabilities.findByStarttime", query = "SELECT d FROM Disposabilities d WHERE d.starttime = :starttime")
  , @NamedQuery(name = "Disposabilities.findByWorkday", query = "SELECT d FROM Disposabilities d WHERE d.workday = :workday")})
public class Disposabilities implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "Teacher")
  private String teacher;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Column(name = "Endtime")
  @Temporal(TemporalType.TIME)
  private Date endtime;
  @Basic(optional = false)
  @Column(name = "Starttime")
  @Temporal(TemporalType.TIME)
  private Date starttime;
  @Basic(optional = false)
  @Column(name = "Workday")
  @Temporal(TemporalType.DATE)
  private Date workday;

  public Disposabilities() {
  }

  public Disposabilities(String id) {
    this.id = id;
  }

  public Disposabilities(String id, String teacher, Date starttime, Date workday) {
    this.id = id;
    this.teacher = teacher;
    this.starttime = starttime;
    this.workday = workday;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getEndtime() {
    return endtime;
  }

  public void setEndtime(Date endtime) {
    this.endtime = endtime;
  }

  public Date getStarttime() {
    return starttime;
  }

  public void setStarttime(Date starttime) {
    this.starttime = starttime;
  }

  public Date getWorkday() {
    return workday;
  }

  public void setWorkday(Date workday) {
    this.workday = workday;
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
    if (!(object instanceof Disposabilities)) {
      return false;
    }
    Disposabilities other = (Disposabilities) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Disposabilities[ id=" + id + " ]";
  }
  
}
