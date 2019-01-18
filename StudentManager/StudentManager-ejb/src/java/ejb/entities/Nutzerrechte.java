package ejb.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author ralph
 */
@Entity
public class Nutzerrechte {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String Lesen;

    @Basic
    private String Schreiben;

    @OneToOne
    private LogIns logIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLesen() {
        return Lesen;
    }

    public void setLesen(String Lesen) {
        this.Lesen = Lesen;
    }

    public String getSchreiben() {
        return Schreiben;
    }

    public void setSchreiben(String Schreiben) {
        this.Schreiben = Schreiben;
    }

    public LogIns getLogIn() {
        return logIn;
    }

    public void setLogIn(LogIns logIn) {
        this.logIn = logIn;
    }

}