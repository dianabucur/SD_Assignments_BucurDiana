package ro.utcluj.sd.Factory;

import ro.utcluj.sd.DAO.*;

public abstract class DaoFactory {

    public enum Type {
        HIBERNATE,
        JDBC;
    }


    protected DaoFactory(){

    }

    public static DaoFactory getInstance(Type factoryType) {
        switch (factoryType) {
            case HIBERNATE:
                return new HibernateDaoFactory();
            case JDBC:
                return new JdbcDaoFactory();
            default:
                throw new IllegalArgumentException("Invalid factory");
        }
    }

    public abstract UserDAOInterf getUserDao();
    public abstract TournamentDAOInterf getTournamentDao();
    public abstract MatchDAOInterf getMatchDao();
    public abstract GameDAOInterf getGameDao();
    public abstract EnrollDAOInterf getEnrolledDao();


}