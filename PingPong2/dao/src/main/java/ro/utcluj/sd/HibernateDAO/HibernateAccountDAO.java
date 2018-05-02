package ro.utcluj.sd.HibernateDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.Connection.HibernateUtil;
import ro.utcluj.sd.Models.AccountTournament;
import ro.utcluj.sd.Models.AccountUser;

public class HibernateAccountDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void addUserAccount(AccountUser a) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(a);
        transaction.commit();
    }

    public void addTourAccount(AccountTournament a) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(a);
        transaction.commit();
    }

    public AccountUser getUserBalance(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from AccountUser where userid= :UserParameter").setInteger("UserParameter", id);
        AccountUser account = (AccountUser) query.list().get(0);
        transaction.commit();
        return account;
    }

    public AccountTournament getTourBalance(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from AccountTournament where tourid= :TourParameter").setInteger("TourParameter", id);
        AccountTournament account = (AccountTournament) query.list().get(0);
        transaction.commit();
        return account;
    }

    public void updateUserAccount(String mail, int sum){
        HibernateUserDAO u = new HibernateUserDAO();
        int userid = u.getid(mail);
        AccountUser acc = getUserBalance(userid);
        int balance = acc.getBalance() + sum;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update AccountUser set balance= :BParameter where userid= :IdParameter")
                .setInteger("BParameter", balance)
                .setInteger("IdParameter", userid).executeUpdate();
        transaction.commit();
    }

    public void updateTourAccount(String tour, int sum){
        HibernateTournamentDAO u = new HibernateTournamentDAO();
        int tourid = u.getid(tour);
        AccountTournament acc = getTourBalance(tourid);
        int balance = acc.getBalance() + sum;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update AccountTournament set balance= :BParameter where tourid= :IdParameter")
                .setInteger("BParameter", balance)
                .setInteger("IdParameter", tourid).executeUpdate();
        transaction.commit();
    }

    public void updateEnroll(String mail, String tour, int fee){
        updateUserAccount(mail, -fee);
        updateTourAccount(tour, fee);
    }

    public void updateWin(int id, String tour, int fee){
        HibernateTournamentDAO u = new HibernateTournamentDAO();
        String mail = u.getNameById(id);
        updateUserAccount(mail, 8*fee);
        updateTourAccount(tour, -8*fee);
    }


}
