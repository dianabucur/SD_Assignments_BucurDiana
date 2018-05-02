package ro.utcluj.sd.Factory;

import ro.utcluj.sd.DAO.*;

public class JdbcDaoFactory extends DaoFactory {

    @Override
    public UserDAOInterf getUserDao() {
        return new UserDAO();
    }

    public TournamentDAOInterf getTournamentDao() {
        return new TournamentDAO();
    }

    public MatchDAOInterf getMatchDao() {
        return new MatchDAO();
    }

    public GameDAOInterf getGameDao() {
        return new GameDAO();
    }

    public EnrollDAOInterf getEnrolledDao() {
        return new EnrollDAO();
    }

}
