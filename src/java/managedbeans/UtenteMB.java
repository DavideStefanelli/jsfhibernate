package managedbeans;

import dao.UtenteDAO;
import dao.UtenteDAOImpl;
import entities.Utente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.digest.DigestUtils;

@Named(value = "utenteMB")
@SessionScoped
public class UtenteMB implements Serializable {
    
    private String email;
    private String password;
    
    private String cf;
    private String nome;
    private String cognome;
    private Character sesso;
    private Date datanascita;
    
    UtenteDAO utenteDao = new UtenteDAOImpl();
    
    public UtenteMB() {
    }
    
     public String register(){
        String targetPage = "login.xhtml";
        try {
            if(utenteDao.save(this) == false){
                targetPage = "register.xhtml";
            }
        } catch (Exception ex) {
            Logger.getLogger(UtenteMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return targetPage;
    }
    
    public String login(){
        String targetPage = "login.xhtml";
        try {
            Utente u = utenteDao.getByEmail(email);
            String passwordHash = DigestUtils.sha256Hex(password + u.getSale());
            if(u.getPassword().equals(passwordHash)){
                targetPage = "home.xhtml";
            }
        } catch (Exception ex) {
            Logger.getLogger(UtenteMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return targetPage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Character getSesso() {
        return sesso;
    }

    public void setSesso(Character sesso) {
        this.sesso = sesso;
    }

    public Date getDatanascita() {
        return datanascita;
    }

    public void setDatanascita(Date datanascita) {
        this.datanascita = datanascita;
    }
    
    
    
}
