package ro.utcluj.sd.Factory;


import ro.utcluj.sd.DAO.*;
import ro.utcluj.sd.HibernateDAO.*;

public class HibernateDaoFactory extends DaoFactory {

    @Override
    public UserDAOInterf getUserDao() {
        return new HibernateUserDAO();
    }

    public TournamentDAOInterf getTournamentDao() {
        return new HibernateTournamentDAO();
    }

    public MatchDAOInterf getMatchDao() {
        return new HibernateMatchDAO();
    }

    public GameDAOInterf getGameDao() {
        return new HibernateGameDAO();
    }

    public EnrollDAOInterf getEnrolledDao() {
        return new HibernateEnrolledDAO();
    }

}
