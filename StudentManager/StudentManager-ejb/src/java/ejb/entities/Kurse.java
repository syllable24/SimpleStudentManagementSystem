package ejb.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author ralph
 */
@Entity
public class Kurse {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Bezeichnung;

    @ManyToMany(mappedBy = "kurse_ids")
    private List<Studenten> studenten_ids;

    @ManyToMany(mappedBy = "kurse_ids")
    private List<Lehrer> lehrer_ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return Bezeichnung;
    }

    public void setBezeichnung(String Bezeichnung) {
        this.Bezeichnung = Bezeichnung;
    }

    public List<Studenten> getStudenten_ids() {
        if (studenten_ids == null) {
            studenten_ids = new ArrayList<>();
        }
        return studenten_ids;
    }

    public void setStudenten_ids(List<Studenten> studenten_ids) {
        this.studenten_ids = studenten_ids;
    }

    public void addStudenten_id(Studenten studenten_id) {
        getStudenten_ids().add(studenten_id);
    }

    public void removeStudenten_id(Studenten studenten_id) {
        getStudenten_ids().remove(studenten_id);
    }

    public List<Lehrer> getLehrer_ids() {
        if (lehrer_ids == null) {
            lehrer_ids = new ArrayList<>();
        }
        return lehrer_ids;
    }

    public void setLehrer_ids(List<Lehrer> lehrer_ids) {
        this.lehrer_ids = lehrer_ids;
    }

    public void addLehrer_id(Lehrer lehrer_id) {
        getLehrer_ids().add(lehrer_id);
    }

    public void removeLehrer_id(Lehrer lehrer_id) {
        getLehrer_ids().remove(lehrer_id);
    }

}