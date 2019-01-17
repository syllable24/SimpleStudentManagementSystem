package ejb.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ralph
 */
@Entity
public class Lehrer {

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
    private LogIns logIn_ID;

    @ManyToMany
    private List<Kurse> kurse_ids;

    @ManyToMany
    private List<Gruppen> gruppen_ids;

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

    public LogIns getLogIn_ID() {
        return logIn_ID;
    }

    public void setLogIn_ID(LogIns logIn_ID) {
        this.logIn_ID = logIn_ID;
    }

    public List<Kurse> getKurse_ids() {
        if (kurse_ids == null) {
            kurse_ids = new ArrayList<>();
        }
        return kurse_ids;
    }

    public void setKurse_ids(List<Kurse> kurse_ids) {
        this.kurse_ids = kurse_ids;
    }

    public void addKurse_id(Kurse kurse_id) {
        getKurse_ids().add(kurse_id);
        kurse_id.getLehrer_ids().add(this);
    }

    public void removeKurse_id(Kurse kurse_id) {
        getKurse_ids().remove(kurse_id);
        kurse_id.getLehrer_ids().remove(this);
    }

    public List<Gruppen> getGruppen_ids() {
        if (gruppen_ids == null) {
            gruppen_ids = new ArrayList<>();
        }
        return gruppen_ids;
    }

    public void setGruppen_ids(List<Gruppen> gruppen_ids) {
        this.gruppen_ids = gruppen_ids;
    }

    public void addGruppen_id(Gruppen gruppen_id) {
        getGruppen_ids().add(gruppen_id);
        gruppen_id.getLehrer_ids().add(this);
    }

    public void removeGruppen_id(Gruppen gruppen_id) {
        getGruppen_ids().remove(gruppen_id);
        gruppen_id.getLehrer_ids().remove(this);
    }

}