package ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 */
@Entity
public class Admin implements Serializable {

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

}