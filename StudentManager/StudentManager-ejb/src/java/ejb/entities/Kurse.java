package ejb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author ralph
 */
@Entity
public class Kurse implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Bezeichnung;

    @Basic
    private int Note = 0;

    @ManyToOne
    private Lehrer lehrer;

    @ManyToMany
    private List<Studenten> studentens;

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

    public int getNote() {
        return Note;
    }

    public void setNote(int Note) {
        this.Note = Note;
    }

    public Lehrer getLehrer() {
        return lehrer;
    }

    public void setLehrer(Lehrer lehrer) {
        this.lehrer = lehrer;
    }

    public List<Studenten> getStudentens() {
        if (studentens == null) {
            studentens = new ArrayList<>();
        }
        return studentens;
    }

    public void setStudentens(List<Studenten> studentens) {
        this.studentens = studentens;
    }

    public void addStudenten(Studenten studenten) {
        getStudentens().add(studenten);
        studenten.getKurses().add(this);
    }

    public void removeStudenten(Studenten studenten) {
        getStudentens().remove(studenten);
        studenten.getKurses().remove(this);
    }

}