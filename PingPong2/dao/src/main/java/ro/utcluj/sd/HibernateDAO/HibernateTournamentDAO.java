package ro.utcluj.sd.HibernateDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.DAO.TournamentDAOInterf;
import ro.utcluj.sd.Connection.HibernateUtil;
import ro.utcluj.sd.Models.Tournament;

import java.util.ArrayList;
import java.util.List;

public class HibernateTournamentDAO implements TournamentDAOInterf {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public ArrayList<Tournament> findAllTours() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Tournament");
        List<Tournament> tours = query.list();
        ArrayList<Tournament> tt = new ArrayList<>();
        for(Tournament t : tours){
            tt.add(t);
            System.out.println(t.getName());
        }
        transaction.commit();
        return tt;
    }

    public Tournament getTournament(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Tournament tour = (Tournament) currentSession.get(Tournament.class, id);
        transaction.commit();
        return tour;
    }

    public int getid(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select id from Tournament where name= :NameParameter").setString("NameParameter", name);
        int id = (int) query.list().get(0);
        transaction.commit();
        //sessionFactory.close();
        return id;
    }

    public String getNameById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select name from Tournament where id= :IdParameter").setInteger("IdParameter", id);
        String name = (String) query.list().get(0);
        transaction.commit();
        //sessionFactory.close();
        return name;
    }

    public int addTour(Tournament tour) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(tour);
        transaction.commit();
        //sessionFactory.close();
        return 1;
    }

    public void updateName(String status, String sel) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update Tournament set name= :StatusParameter where name= :SelParameter")
                .setString("StatusParameter", status)
                .setString("SelParameter", sel).executeUpdate();
        transaction.commit();
        //essionFactory.close();
    }

    public void updateLevel(String status, String sel) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update Tournament set level= :StatusParameter where name= :SelParameter")
                .setString("StatusParameter", status)
                .setString("SelParameter", sel).executeUpdate();
        transaction.commit();
        //essionFactory.close();
    }

    public void updateFee(int fee, String sel) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update Tournament set fee= :StatusParameter where name= :SelParameter")
                .setInteger("StatusParameter", fee)
                .setString("SelParameter", sel).executeUpdate();
        transaction.commit();
        //essionFactory.close();
    }

    public void deleteTournament(String tour, String level) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("delete from Tournament where name= :NameParameter").setString("NameParameter", tour).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
    }
    public void closeConnection() {
        sessionFactory.close();
    }
}
