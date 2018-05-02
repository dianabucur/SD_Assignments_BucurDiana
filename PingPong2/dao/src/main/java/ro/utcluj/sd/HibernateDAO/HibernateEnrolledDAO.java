package ro.utcluj.sd.HibernateDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.DAO.EnrollDAOInterf;
import ro.utcluj.sd.Connection.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class HibernateEnrolledDAO implements EnrollDAOInterf {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void enrollPlayer(String mail, String tour) {
        HibernateTournamentDAO h = new HibernateTournamentDAO();
        int tourid = h.getid(tour);
        HibernateUserDAO h2 = new HibernateUserDAO();
        int playerid = h2.getid(mail);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createSQLQuery("insert into pingpong.enrolled (playerid,tournamentid) values (?,?)")
                .setParameter(0, playerid).setParameter(1, tourid).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
    }

    public ArrayList<Integer> getId(String tour) {
        HibernateTournamentDAO h = new HibernateTournamentDAO();
        int tourid = h.getid(tour);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select playerid from Enrolled where tournamentid= :TourParameter").setInteger("TourParameter",  tourid);
        List<Integer> list =  query.list();
        ArrayList<Integer> ids = new ArrayList<>();
        for(Integer a: list){
            ids.add(a);
            System.out.println(a);
        }
        transaction.commit();
        return ids;
    }

    public ArrayList<Integer> getTours(String mail) {
        HibernateUserDAO h = new HibernateUserDAO();
        int playerid = h.getid(mail);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select tournamentid from Enrolled where playerid= :PlayerParameter").setInteger("PlayerParameter",  playerid);
        List<Integer> list =  query.list();
        ArrayList<Integer> ids = new ArrayList<>();
        for(Integer a: list){
            ids.add(a);
            System.out.println(a);
        }
        transaction.commit();
        return ids;
    }

    public ArrayList<String> getPlayers(String tour) {
        ArrayList<Integer> ids = getId(tour);
        System.out.println(ids.size());
        HibernateUserDAO h = new HibernateUserDAO();
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < ids.size(); i++){
            names.add(h.getNameById(ids.get(i)));
        }
        return names;
    }

    public ArrayList<String> showTours(String mail) {
        ArrayList<Integer> ids = getTours(mail);
        HibernateTournamentDAO h = new HibernateTournamentDAO();
        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i < ids.size(); i++){
            names.add(h.getNameById(ids.get(i)));
            System.out.println(h.getNameById(ids.get(i)));
        }
        return names;
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
