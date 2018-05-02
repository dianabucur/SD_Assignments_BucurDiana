package ro.utcluj.sd.HibernateDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.DAO.GameDAOInterf;
import ro.utcluj.sd.Connection.HibernateUtil;
import ro.utcluj.sd.Models.Game;

import java.util.ArrayList;
import java.util.List;

public class HibernateGameDAO implements GameDAOInterf {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public int addGame(int matchid) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createSQLQuery("insert into pingpong.game (score1,score2,matchid) values (?,?,?)")
                .setParameter(0, 0).setParameter(1, 0).setParameter(2, matchid).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
        return 1;
    }

    public ArrayList<Game> getGames(int matchid) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("from Game where matchid= :MatchParameter").setInteger("MatchParameter", matchid);
        List<Game> list = query.list();
        ArrayList<Game> games = new ArrayList<>();
        for(Game m : list){
            games.add(m);
            System.out.println(m.getId());
        }
        transaction.commit();
        //sessionFactory.close();
        return games;
    }

    public void updateGame(int score1, int score2, int gameid) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update Game set score1= :S1Parameter, score2= :S2Parameter where id= :IdParameter")
                .setInteger("S1Parameter", score1)
                .setInteger("S2Parameter", score2)
                .setInteger("IdParameter", gameid).executeUpdate();;
        transaction.commit();
        //sessionFactory.close();
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
