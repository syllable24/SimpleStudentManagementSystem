/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enities;

import java.util.Collection;
import javax.enterprise.inject.Model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Model
@Table(name = "Organizations")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Organizations.findAll", query = "SELECT s FROM Organizations s")
        ,@NamedQuery(name = "Organizations.find", query = "SELECT s FROM Organizations s WHERE s.id = :id")
  , @NamedQuery(name = "Organizations.findById", query = "SELECT s FROM Organizations s WHERE s.id = :id")
  , @NamedQuery(name = "Organizations.findByOrg", query = "SELECT s FROM Organizations s WHERE s.id = :org")})
public class Organizations extends BaseEntity<Integer> {

  private static final long serialVersionUID = 1L;
  
  private String name;
  
  private Organizations parent;
  
  private Organizations organization;
  
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
  private Collection<Organizations> organizationsCollection;

  public Organizations() {
  }

  public Organizations(String name) {
    this.name = name;
  }
  
  public Organizations(int id){
    this.setId(id);
  }

  public Organizations(String name, int id) {
    this.name = name;
    this.setId(id);
  }
  
  public Organizations(String name, int id, Organizations org) {
    this.name = name;
    this.setId(id);
    this.parent=org;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public Organizations getParent() {
        return parent;
    }

    public void setParent(Organizations parent) {
        this.parent = parent;
    }


  @XmlTransient
  public Collection<Organizations> getOrganizationsCollection() {
    return organizationsCollection;
  }

  public void setOrganizationsCollection(Collection<Organizations> organizationsCollection) {
    this.organizationsCollection = organizationsCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += ( name != null ? name.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Organizations)) {
      return false;
    }
    Organizations other = (Organizations) object;
    if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entities.Organizations[ id="+this.getId()+", name=" + name + " ]";
  }
  
}
