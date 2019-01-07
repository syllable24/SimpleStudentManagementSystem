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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author newe
 */
@Entity
@Table(name = "Teachers")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Teachers.findAll", query = "SELECT t FROM Teachers t")
  , @NamedQuery(name = "Teachers.findById", query = "SELECT t FROM Teachers t WHERE t.id = :id")
  , @NamedQuery(name = "Teachers.findByStaff", query = "SELECT t FROM Teachers t WHERE t.staff = :staff")})
public class Teachers implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @Column(name = "ID")
  private String id;
  @Id
  @Basic(optional = false)
  @Column(name = "Staff")
  private String staff;
  @JoinColumn(name = "Staff", referencedColumnName = "Person", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private Staff staff1;

  public Teachers() {
  }

  public Teachers(String staff) {
    this.staff = staff;
  }

  public Teachers(String staff, String id) {
    this.staff = staff;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStaff() {
    return staff;
  }

  public void setStaff(String staff) {
    this.staff = staff;
  }

  public Staff getStaff1() {
    return staff1;
  }

  public void setStaff1(Staff staff1) {
    this.staff1 = staff1;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (staff != null ? staff.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Teachers)) {
      return false;
    }
    Teachers other = (Teachers) object;
    if ((this.staff == null && other.staff != null) || (this.staff != null && !this.staff.equals(other.staff))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "edu.Teachers[ staff=" + staff + " ]";
  }
  
}
