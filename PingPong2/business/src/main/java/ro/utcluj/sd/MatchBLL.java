package ro.utcluj.sd;

import ro.utcluj.sd.DAO.*;
import ro.utcluj.sd.Factory.DaoFactory;
import ro.utcluj.sd.Models.Match;

import java.util.ArrayList;

public class MatchBLL {
    MatchDAOInterf m;
    MatchBLL(){
        m = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
    }

    public void addMatches(String tour){
        int id;
        EnrollDAOInterf e = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getEnrolledDao();
        GameDAOInterf g = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
        ArrayList<Integer> players = e.getId(tour);
        m.addMatch(players.get(0), players.get(1),tour);
        id = m.getLastMatch().getId();
        g.addGame(id);
        m.addMatch(players.get(2), players.get(3),tour);
        id = m.getLastMatch().getId();
        g.addGame(id);
        m.addMatch(players.get(4), players.get(5),tour);
        id = m.getLastMatch().getId();
        g.addGame(id);
        m.addMatch(players.get(6), players.get(7),tour);
        id = m.getLastMatch().getId();
        g.addGame(id);
    }

    public Match getMatch(String user){
        return m.getPlayerMatch(user);
    }

    public Match getLastMatch(){
        return m.getLastMatch();
    }

    public void updateMatch(int player2, int matchid){
        m.updateMatch(player2, matchid);
    }

    public int getCountMatches(String tour){
        return m.getCountMatches(tour);
    }

    public ArrayList<Match> getAllMacthes(String tour){
        System.out.println("AM AJUNS AICI FA");
        return m.getAllMatches(tour);
    }
}
