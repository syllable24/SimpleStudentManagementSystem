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
    private LogIns logIn_ID;

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

    public LogIns getLogIn_ID() {
        return logIn_ID;
    }

    public void setLogIn_ID(LogIns logIn_ID) {
        this.logIn_ID = logIn_ID;
    }

}