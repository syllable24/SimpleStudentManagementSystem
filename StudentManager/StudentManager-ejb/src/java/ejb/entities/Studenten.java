package ejb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

    @ManyToOne
    private Gruppen gruppe;

    @OneToMany(mappedBy = "student")
    private List<Kursnoten> kursnoten;

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

    public Gruppen getGruppe() {
        return gruppe;
    }

    public void setGruppe(Gruppen gruppe) {
        this.gruppe = gruppe;
    }

    public List<Kursnoten> getKursnoten() {
        if (kursnoten == null) {
            kursnoten = new ArrayList<>();
        }
        return kursnoten;
    }

    public void setKursnoten(List<Kursnoten> kursnoten) {
        this.kursnoten = kursnoten;
    }

    public void addKursnoten(Kursnoten kursnoten) {
        getKursnoten().add(kursnoten);
        kursnoten.setStudent(this);
    }

    public void removeKursnoten(Kursnoten kursnoten) {
        getKursnoten().remove(kursnoten);
        kursnoten.setStudent(null);
    }

}