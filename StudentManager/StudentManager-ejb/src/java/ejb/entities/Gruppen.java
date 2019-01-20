package ejb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    private Studenten student;

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

    public Studenten getStudent() {
        return student;
    }

    public void setStudent(Studenten student) {
        this.student = student;
    }

}