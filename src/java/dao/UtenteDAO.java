package dao;

import entities.Utente;
import managedbeans.UtenteMB;

public interface UtenteDAO {
    
    public boolean save(UtenteMB u);
    
    public Utente getByEmail(String email);
    
}
