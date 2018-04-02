package ro.utcluj.sd;

import ro.utcluj.sd.DAO.EnrollDAO;
import ro.utcluj.sd.DAO.GameDAO;
import ro.utcluj.sd.DAO.MatchDAO;
import ro.utcluj.sd.Models.Match;

import java.util.ArrayList;

public class MatchBLL {
    MatchDAO m;
    MatchBLL(){
        m = new MatchDAO();
    }

    public void addMatches(String tour){
        int id;
        EnrollDAO e = new EnrollDAO();
        int[] players = e.getId(tour);
        id = m.addMatch(players[0], players[1],tour);
        GameDAO g = new GameDAO();
        g.addGame(id);
        id = m.addMatch(players[2], players[3],tour);
        g.addGame(id);
        id = m.addMatch(players[4], players[5],tour);
        g.addGame(id);
        id =m.addMatch(players[6], players[7],tour);
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
        return m.getAllMatches(tour);
    }
}
