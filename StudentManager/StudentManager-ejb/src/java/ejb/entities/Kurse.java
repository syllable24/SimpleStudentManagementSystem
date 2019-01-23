package ejb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

    @ManyToOne
    private Lehrer lehrer;

    @OneToMany(mappedBy = "kurse")
    private List<Kursnoten> kursnoten;

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

    public Lehrer getLehrer() {
        return lehrer;
    }

    public void setLehrer(Lehrer lehrer) {
        this.lehrer = lehrer;
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
        kursnoten.setKurse(this);
    }

    public void removeKursnoten(Kursnoten kursnoten) {
        getKursnoten().remove(kursnoten);
        kursnoten.setKurse(null);
    }

}