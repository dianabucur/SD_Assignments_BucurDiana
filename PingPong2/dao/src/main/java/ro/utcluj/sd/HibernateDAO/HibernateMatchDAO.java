package ro.utcluj.sd.HibernateDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.DAO.MatchDAOInterf;
import ro.utcluj.sd.Connection.HibernateUtil;
import ro.utcluj.sd.Models.Match;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class HibernateMatchDAO implements MatchDAOInterf {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public int addMatch(int player1, int player2, String tour) {
        HibernateTournamentDAO h = new HibernateTournamentDAO();
        int tourid = h.getid(tour);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createSQLQuery("insert into pingpong.match (player1,player2,tournament) values (?,?,?)")
                .setParameter(0, player1).setParameter(1, player2).setParameter(2, tourid).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
        return 1;
    }

    public Match getPlayerMatch(String player) {
        HibernateUserDAO h = new HibernateUserDAO();
        int id = h.getid(player);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Match where player1= :PlayerParameter or player2= :PlayerParameter").setInteger("PlayerParameter", id);
        List<Match> list = query.list();
        System.out.println(list.get(list.size()-1).getId());
        transaction.commit();
        //sessionFactory.close();
        return list.get(list.size()-1);
    }

    public Match getLastMatch() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Match ORDER BY id DESC");
        List<Match> list = query.list();
        System.out.println(list.get(0).getId());
        transaction.commit();
        //sessionFactory.close();
        return list.get(0);
    }

    public void updateMatch(int player2, int matchid) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update Match set player2= :PlayerParameter where id= :IdParameter")
                .setInteger("PlayerParameter", player2)
                .setInteger("IdParameter", matchid).executeUpdate();;
        transaction.commit();
        //sessionFactory.close();
    }

    public int getCountMatches(String tour) {
        HibernateTournamentDAO h = new HibernateTournamentDAO();
        int tourid = h.getid(tour);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createSQLQuery("select count(id) FROM pingpong.match WHERE tournament = ?")
                .setParameter(0, tourid);
        int count = ((BigInteger) query.list().get(0)).intValue();
        transaction.commit();
        //sessionFactory.close();
        return count;
    }

    public ArrayList<Match> getAllMatches(String tour) {
        HibernateTournamentDAO h = new HibernateTournamentDAO();
        int tourid = h.getid(tour);
        System.out.println(tourid);
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Match where tournament= :TourParameter").setInteger("TourParameter", tourid);
        List<Match> list = query.list();
        ArrayList<Match> matches = new ArrayList<>();
        for(Match m : list){
            matches.add(m);
        }
        transaction.commit();
        return matches;
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
