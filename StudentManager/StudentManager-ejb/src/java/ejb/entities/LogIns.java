package ejb.entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.DatatypeConverter;

/**
 * @author ralph
 */
@Entity
public class LogIns implements Serializable {

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

    public void setUsernameMD5(String Username) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Username.getBytes("UTF-8"));        
        byte[] dig = md.digest();
        String result = DatatypeConverter.printHexBinary(dig);                
        this.UsernameMD5 = result;
    }

    public String getPasswortMD5() {        
        return PasswortMD5;
    }

    public void setPasswortMD5(String Passwort) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(Passwort.getBytes("UTF-8")); 
        byte[] dig = md.digest();
        String result = DatatypeConverter.printHexBinary(dig);        
        this.PasswortMD5 = result;
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