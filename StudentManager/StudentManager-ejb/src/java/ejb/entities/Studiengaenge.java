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
    private List<Studenten> student;

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

    public List<Studenten> getStudent() {
        if (student == null) {
            student = new ArrayList<>();
        }
        return student;
    }

    public void setStudent(List<Studenten> student) {
        this.student = student;
    }

    public void addStudent(Studenten student) {
        getStudent().add(student);
        student.setStudiengaenge(this);
    }

    public void removeStudent(Studenten student) {
        getStudent().remove(student);
        student.setStudiengaenge(null);
    }

}