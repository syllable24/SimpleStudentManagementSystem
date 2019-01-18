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
public class LogIns {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private String UsernameMD5;

    @Basic
    private String PasswortMD5;

    @OneToOne(mappedBy = "logIn")
    private Lehrer lehrer;

    @OneToOne(mappedBy = "logIn")
    private Studenten student;

    @OneToOne(mappedBy = "logIn")
    private Nutzerrechte nutzerrecht;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsernameMD5() {
        return UsernameMD5;
    }

    public void setUsernameMD5(String UsernameMD5) {
        this.UsernameMD5 = UsernameMD5;
    }

    public String getPasswortMD5() {
        return PasswortMD5;
    }

    public void setPasswortMD5(String PasswortMD5) {
        this.PasswortMD5 = PasswortMD5;
    }

    public Lehrer getLehrer() {
        return lehrer;
    }

    public void setLehrer(Lehrer lehrer) {
        this.lehrer = lehrer;
    }

    public Studenten getStudent() {
        return student;
    }

    public void setStudent(Studenten student) {
        this.student = student;
    }

    public Nutzerrechte getNutzerrecht() {
        return nutzerrecht;
    }

    public void setNutzerrecht(Nutzerrechte nutzerrecht) {
        this.nutzerrecht = nutzerrecht;
    }

}