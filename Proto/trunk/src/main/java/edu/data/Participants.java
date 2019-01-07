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
@Table(name = "Participants")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Participants.findAll", query = "SELECT p FROM Participants p")
  , @NamedQuery(name = "Participants.findById", query = "SELECT p FROM Participants p WHERE p.id = :id")
  , @NamedQuery(name = "Participants.findByKurs", query = "SELECT p FROM Participants p WHERE p.kurs = :kurs")
  , @NamedQuery(name = "Participants.findByPsnr", query = "SELECT p FROM Participants p WHERE p.psnr = :psnr")})
public class Participants implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "ID")
  private Integer id;
  @Column(name = "KURS")
  private Integer kurs;
  @Column(name = "PSNR")
  private Integer psnr;

  public Participants() {
  }

  public Participants(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getKurs() {
    return kurs;
  }

  public void setKurs(Integer kurs) {
    this.kurs = kurs;
  }

  public Integer getPsnr() {
    return psnr;
  }

  public void setPsnr(Integer psnr) {
    this.psnr = psnr;
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
    if (!(object instanceof Participants)) {
      return false;
    }
    Participants other = (Participants) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Participants[ id=" + id + " ]";
  }
  
}
