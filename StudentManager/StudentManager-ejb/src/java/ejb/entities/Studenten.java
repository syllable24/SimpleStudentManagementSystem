package ejb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ralph
 */
@Entity
public class Studenten implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Vorname;

    @Basic
    private String Nachname;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date Geburtsdatum;

    @OneToOne
    private LogIns logIn;

    @ManyToOne
    private Studiengaenge studiengaenge;

    @OneToMany(mappedBy = "student")
    private List<Gruppen> gruppe;

    @ManyToMany(mappedBy = "studentens")
    private List<Kurse> kurses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String Vorname) {
        this.Vorname = Vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String Nachname) {
        this.Nachname = Nachname;
    }

    public Date getGeburtsdatum() {
        return Geburtsdatum;
    }

    public void setGeburtsdatum(Date Geburtsdatum) {
        this.Geburtsdatum = Geburtsdatum;
    }

    public LogIns getLogIn() {
        return logIn;
    }

    public void setLogIn(LogIns logIn) {
        this.logIn = logIn;
    }

    public Studiengaenge getStudiengaenge() {
        return studiengaenge;
    }

    public void setStudiengaenge(Studiengaenge studiengaenge) {
        this.studiengaenge = studiengaenge;
    }

    public List<Gruppen> getGruppe() {
        if (gruppe == null) {
            gruppe = new ArrayList<>();
        }
        return gruppe;
    }

    public void setGruppe(List<Gruppen> gruppe) {
        this.gruppe = gruppe;
    }

    public void addGruppe(Gruppen gruppe) {
        getGruppe().add(gruppe);
        gruppe.setStudent(this);
    }

    public void removeGruppe(Gruppen gruppe) {
        getGruppe().remove(gruppe);
        gruppe.setStudent(null);
    }

    public List<Kurse> getKurses() {
        if (kurses == null) {
            kurses = new ArrayList<>();
        }
        return kurses;
    }

    public void setKurses(List<Kurse> kurses) {
        this.kurses = kurses;
    }

    public void addKurse(Kurse kurse) {
        getKurses().add(kurse);
    }

    public void removeKurse(Kurse kurse) {
        getKurses().remove(kurse);
    }

}