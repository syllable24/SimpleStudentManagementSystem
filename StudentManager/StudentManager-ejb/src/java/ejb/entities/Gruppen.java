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
public class Gruppen {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Bezeichnung;

    @OneToMany(mappedBy = "gruppe")
    private List<KurseZuGruppen> kursZuGruppe;

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

    public List<KurseZuGruppen> getKursZuGruppe() {
        if (kursZuGruppe == null) {
            kursZuGruppe = new ArrayList<>();
        }
        return kursZuGruppe;
    }

    public void setKursZuGruppe(List<KurseZuGruppen> kursZuGruppe) {
        this.kursZuGruppe = kursZuGruppe;
    }

    public void addKursZuGruppe(KurseZuGruppen kursZuGruppe) {
        getKursZuGruppe().add(kursZuGruppe);
        kursZuGruppe.setGruppe(this);
    }

    public void removeKursZuGruppe(KurseZuGruppen kursZuGruppe) {
        getKursZuGruppe().remove(kursZuGruppe);
        kursZuGruppe.setGruppe(null);
    }

}