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
public class Kursnoten implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Note;

    @ManyToOne
    private Kurse kurse;

    @ManyToOne
    private Studenten student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        if(Note == null){
            return "Kein Eintrag";
        }
        else return Note;
    }

    public void setNote(String Note) {        
        try{
            int note = Integer.parseInt(Note);
            if(note < 1 || note > 5){
                this.Note = null;
            }
            else this.Note = String.valueOf(note);
        }
        catch(NumberFormatException e){
            this.Note = null;
        }                
    }

    public Kurse getKurse() {
        return kurse;
    }

    public void setKurse(Kurse kurse) {
        this.kurse = kurse;
    }

    public Studenten getStudent() {
        return student;
    }

    public void setStudent(Studenten student) {
        this.student = student;
    }

}