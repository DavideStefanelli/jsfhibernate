/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import managedbeans.UtenteMB;
import entities.Utente;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author web4e
 */
public class UtenteDAOImpl implements UtenteDAO {

    @Override
    public boolean save(UtenteMB u) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Date dataAttuale = Date.from(Instant.now());
        String sale = Long.toUnsignedString(ThreadLocalRandom.current().nextLong());
        String passwordHash = DigestUtils.sha256Hex(u.getPassword() + sale); 
        Utente utente = new Utente(u.getEmail(), u.getCf(), u.getNome(), u.getCognome(), u.getDatanascita(), passwordHash, sale, dataAttuale, u.getSesso());
        
        Transaction t = session.beginTransaction();
        session.save(utente);
        t.commit();
        boolean status = t.wasCommitted();
        session.close();
        return status;
    }

    @Override
    public Utente getByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Utente u WHERE u.email = :email");
        query.setParameter("email", email);
        Utente utente = ((List<Utente>) query.list()).get(0);
        session.close();
        return utente;
    }
    
    
}
