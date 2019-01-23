package ejb.entities;

import java.io.Serializable;
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
public class Gruppen implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Bezeichnung;

    @OneToMany(mappedBy = "gruppe")
    private List<Studenten> studenten;

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

    public List<Studenten> getStudenten() {
        if (studenten == null) {
            studenten = new ArrayList<>();
        }
        return studenten;
    }

    public void setStudenten(List<Studenten> studenten) {
        this.studenten = studenten;
    }

    public void addStudenten(Studenten studenten) {
        getStudenten().add(studenten);
        studenten.setGruppe(this);
    }

    public void removeStudenten(Studenten studenten) {
        getStudenten().remove(studenten);
        studenten.setGruppe(null);
    }

}