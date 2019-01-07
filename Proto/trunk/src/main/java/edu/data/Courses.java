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
@Table(name = "Courses")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c")
  , @NamedQuery(name = "Courses.findByKurs", query = "SELECT c FROM Courses c WHERE c.kurs = :kurs")
  , @NamedQuery(name = "Courses.findByKursname", query = "SELECT c FROM Courses c WHERE c.kursname = :kursname")
  , @NamedQuery(name = "Courses.findBySchulungsblock", query = "SELECT c FROM Courses c WHERE c.schulungsblock = :schulungsblock")
  , @NamedQuery(name = "Courses.findByDauer", query = "SELECT c FROM Courses c WHERE c.dauer = :dauer")
  , @NamedQuery(name = "Courses.findByBlockWeeks", query = "SELECT c FROM Courses c WHERE c.blockWeeks = :blockWeeks")
  , @NamedQuery(name = "Courses.findByBlockung", query = "SELECT c FROM Courses c WHERE c.blockung = :blockung")
  , @NamedQuery(name = "Courses.findByCode", query = "SELECT c FROM Courses c WHERE c.code = :code")
  , @NamedQuery(name = "Courses.findByColor", query = "SELECT c FROM Courses c WHERE c.color = :color")
  , @NamedQuery(name = "Courses.findByExtID", query = "SELECT c FROM Courses c WHERE c.extID = :extID")
  , @NamedQuery(name = "Courses.findById", query = "SELECT c FROM Courses c WHERE c.id = :id")
  , @NamedQuery(name = "Courses.findByLink", query = "SELECT c FROM Courses c WHERE c.link = :link")
  , @NamedQuery(name = "Courses.findBySws", query = "SELECT c FROM Courses c WHERE c.sws = :sws")
  , @NamedQuery(name = "Courses.findByTeacher", query = "SELECT c FROM Courses c WHERE c.teacher = :teacher")})
public class Courses implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "KURS")
  private int kurs;
  @Column(name = "KURSNAME")
  private String kursname;
  @Column(name = "SCHULUNGSBLOCK")
  private String schulungsblock;
  @Column(name = "DAUER")
  private String dauer;
  @Column(name = "BlockWeeks")
  private Integer blockWeeks;
  @Column(name = "Blockung")
  private Integer blockung;
  @Column(name = "Code")
  private String code;
  @Column(name = "Color")
  private Integer color;
  @Column(name = "ExtID")
  private String extID;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "Link")
  private Integer link;
  @Column(name = "SWS")
  private Integer sws;
  @Column(name = "Teacher")
  private String teacher;
  @JoinColumn(name = "Major", referencedColumnName = "ID1")
  @ManyToOne(optional = false)
  private Majors major;

  public Courses() {
  }

  public Courses(Integer id) {
    this.id = id;
  }

  public Courses(Integer id, int kurs) {
    this.id = id;
    this.kurs = kurs;
  }

  public int getKurs() {
    return kurs;
  }

  public void setKurs(int kurs) {
    this.kurs = kurs;
  }

  public String getKursname() {
    return kursname;
  }

  public void setKursname(String kursname) {
    this.kursname = kursname;
  }

  public String getSchulungsblock() {
    return schulungsblock;
  }

  public void setSchulungsblock(String schulungsblock) {
    this.schulungsblock = schulungsblock;
  }

  public String getDauer() {
    return dauer;
  }

  public void setDauer(String dauer) {
    this.dauer = dauer;
  }

  public Integer getBlockWeeks() {
    return blockWeeks;
  }

  public void setBlockWeeks(Integer blockWeeks) {
    this.blockWeeks = blockWeeks;
  }

  public Integer getBlockung() {
    return blockung;
  }

  public void setBlockung(Integer blockung) {
    this.blockung = blockung;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Integer getColor() {
    return color;
  }

  public void setColor(Integer color) {
    this.color = color;
  }

  public String getExtID() {
    return extID;
  }

  public void setExtID(String extID) {
    this.extID = extID;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getLink() {
    return link;
  }

  public void setLink(Integer link) {
    this.link = link;
  }

  public Integer getSws() {
    return sws;
  }

  public void setSws(Integer sws) {
    this.sws = sws;
  }

  public String getTeacher() {
    return teacher;
  }

  public void setTeacher(String teacher) {
    this.teacher = teacher;
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
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Courses)) {
      return false;
    }
    Courses other = (Courses) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Courses[ id=" + id + " ]";
  }
  
}
