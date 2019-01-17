package ejb.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author ralph
 */
@Entity
public class Studiengaenge {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Bezeichnung;

    @OneToMany(mappedBy = "studiengaenge")
    private List<Studenten> studenten_ids;

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
        studenten_id.setStudiengaenge(this);
    }

    public void removeStudenten_id(Studenten studenten_id) {
        getStudenten_ids().remove(studenten_id);
        studenten_id.setStudiengaenge(null);
    }

}