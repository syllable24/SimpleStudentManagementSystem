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
@Table(name = "Scheduler")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Scheduler.findAll", query = "SELECT s FROM Scheduler s")
  , @NamedQuery(name = "Scheduler.findById", query = "SELECT s FROM Scheduler s WHERE s.id = :id")
  , @NamedQuery(name = "Scheduler.findByCourse", query = "SELECT s FROM Scheduler s WHERE s.course = :course")
  , @NamedQuery(name = "Scheduler.findByCourseDate", query = "SELECT s FROM Scheduler s WHERE s.courseDate = :courseDate")
  , @NamedQuery(name = "Scheduler.findByEndTime", query = "SELECT s FROM Scheduler s WHERE s.endTime = :endTime")
  , @NamedQuery(name = "Scheduler.findByOrt", query = "SELECT s FROM Scheduler s WHERE s.ort = :ort")
  , @NamedQuery(name = "Scheduler.findByParticipant", query = "SELECT s FROM Scheduler s WHERE s.participant = :participant")
  , @NamedQuery(name = "Scheduler.findByStartTime", query = "SELECT s FROM Scheduler s WHERE s.startTime = :startTime")
  , @NamedQuery(name = "Scheduler.findByTeacher", query = "SELECT s FROM Scheduler s WHERE s.teacher = :teacher")
  , @NamedQuery(name = "Scheduler.findByType", query = "SELECT s FROM Scheduler s WHERE s.type = :type")})
public class Scheduler implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Column(name = "Course")
  private String course;
  @Basic(optional = false)
  @Column(name = "CourseDate")
  @Temporal(TemporalType.DATE)
  private Date courseDate;
  @Column(name = "EndTime")
  @Temporal(TemporalType.TIME)
  private Date endTime;
  @Column(name = "Ort")
  private Integer ort;
  @Basic(optional = false)
  @Column(name = "Participant")
  private String participant;
  @Basic(optional = false)
  @Column(name = "StartTime")
  @Temporal(TemporalType.TIME)
  private Date startTime;
  @Column(name = "Teacher")
  private String teacher;
  @Column(name = "Type")
  private String type;

  public Scheduler() {
  }

  public Scheduler(String id) {
    this.id = id;
  }

  public Scheduler(String id, Date courseDate, String participant, Date startTime) {
    this.id = id;
    this.courseDate = courseDate;
    this.participant = participant;
    this.startTime = startTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public Date getCourseDate() {
    return courseDate;
  }

  public void setCourseDate(Date courseDate) {
    this.courseDate = courseDate;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Integer getOrt() {
    return ort;
  }

  public void setOrt(Integer ort) {
    this.ort = ort;
  }

  public String getParticipant() {
    return participant;
  }

  public void setParticipant(String participant) {
    this.participant = participant;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
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
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Scheduler)) {
      return false;
    }
    Scheduler other = (Scheduler) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Scheduler[ id=" + id + " ]";
  }
  
}
