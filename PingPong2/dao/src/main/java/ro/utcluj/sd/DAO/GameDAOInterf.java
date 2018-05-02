package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Models.Game;

import java.util.ArrayList;

public interface GameDAOInterf {
    public int addGame(int matchid);
    public ArrayList<Game> getGames(int matchid);
    public void updateGame(int score1, int score2, int gameid);
    public void closeConnection();
}
