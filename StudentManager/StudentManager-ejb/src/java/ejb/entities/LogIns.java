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

    @OneToOne(mappedBy = "logIn_ID")
    private Lehrer lehrer_ID;

    @OneToOne(mappedBy = "logIn_ID")
    private Studenten student_ID;

    @OneToOne(mappedBy = "logIn_ID")
    private Nutzerrechte nutzerrecht_ID;

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

    public Lehrer getLehrer_ID() {
        return lehrer_ID;
    }

    public void setLehrer_ID(Lehrer lehrer_ID) {
        this.lehrer_ID = lehrer_ID;
    }

    public Studenten getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(Studenten student_ID) {
        this.student_ID = student_ID;
    }

    public Nutzerrechte getNutzerrecht_ID() {
        return nutzerrecht_ID;
    }

    public void setNutzerrecht_ID(Nutzerrechte nutzerrecht_ID) {
        this.nutzerrecht_ID = nutzerrecht_ID;
    }

}