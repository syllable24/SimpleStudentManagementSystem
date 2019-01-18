package ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author ralph
 */
@Entity
public class KurseZuGruppen implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Kurse kurs;

    @ManyToOne
    private Gruppen gruppe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kurse getKurs() {
        return kurs;
    }

    public void setKurs(Kurse kurs) {
        this.kurs = kurs;
    }

    public Gruppen getGruppe() {
        return gruppe;
    }

    public void setGruppe(Gruppen gruppe) {
        this.gruppe = gruppe;
    }

}