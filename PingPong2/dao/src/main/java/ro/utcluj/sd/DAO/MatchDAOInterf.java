package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Models.Match;

import java.util.ArrayList;

public interface MatchDAOInterf {
    public int addMatch(int player1, int player2, String tour);
    public Match getPlayerMatch(String player);
    public Match getLastMatch();
    public void updateMatch(int player2, int matchid);
    public int getCountMatches(String tour);
    public ArrayList<Match> getAllMatches(String tour);
    public void closeConnection();
}
