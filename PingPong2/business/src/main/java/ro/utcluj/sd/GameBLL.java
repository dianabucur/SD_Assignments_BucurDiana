package ro.utcluj.sd;

import javafx.scene.control.Alert;
import ro.utcluj.sd.DAO.*;
import ro.utcluj.sd.Factory.DaoFactory;
import ro.utcluj.sd.Models.Game;
import ro.utcluj.sd.Models.Match;

import java.util.ArrayList;

public class GameBLL {
    private GameDAOInterf g;

    GameBLL(){
        g = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
    }

    public ArrayList<Game> getGames(int matchid){
        return g.getGames(matchid);
    }

    public String updateGame(int score1, int score2, int gameid, int matchid, String tour, int player1id, int player2id){
        String res = null;
        if(score1 <= 11 & score2 <= 11)
            g.updateGame(score1, score2, gameid);
        else if((score1 > 11 || score2 > 11) && (Math.abs(score1 - score2) < 2))
            g.updateGame(score1, score2, gameid);
        else {
            if(g.getGames(matchid).size() == 3){
                int p1 = 0, p2 = 0;
                ArrayList<Game> games = g.getGames(matchid);
                for(Game game : games) {
                    if (game.getScore1() > game.getScore2())
                        p1++;
                    else
                        p2++;
                }
                if(p1 == 3 || p2 == 3) {
                    g.addGame(matchid);
                    g.addGame(matchid);
                }
                if(p1 > p2){
                    MatchDAOInterf m = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
                    Match match = m.getLastMatch();
                    if(match.getP2() == 0) {
                        res = "Player1S";
                        m.updateMatch(player1id, match.getId());
                        g.addGame(match.getId());
                    }else {
                        res = "Player1F";
                        m.addMatch(player1id, 0, tour);
                    }
                }
                else{
                    MatchDAOInterf m = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
                    Match match = m.getLastMatch();
                    if(match.getP2() == 0) {
                        res = "Player2S";
                        m.updateMatch(player2id, match.getId());
                        GameDAOInterf g = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
                        g.addGame(match.getId());
                    }else {
                        res = "Player2F";
                        m.addMatch(player2id, 0, tour);
                    }
                }
            }
            else if(g.getGames(matchid).size() < 5)
                g.addGame(matchid);
            else{
                int p1 = 0, p2 = 0;
                ArrayList<Game> games = g.getGames(matchid);
                for(Game game : games) {
                    if (game.getScore1() > game.getScore2())
                        p1++;
                    else
                        p2++;
                }
                if(p1 > p2){
                    MatchDAOInterf m = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
                    Match match = m.getLastMatch();
                    if(match.getP2() == 0) {
                        res = "Player1S";
                        m.updateMatch(player1id, match.getId());
                        g.addGame(match.getId());
                        MatchBLL b = new MatchBLL();
                        if (b.getCountMatches(tour) == 7){
                            TournamentBLL b1 = new TournamentBLL();
                            b1.updateName("Finished", tour);
                            b1.updateName("Finished", tour);
                            UserBLL u = new UserBLL();
                            AccountBLL a = new AccountBLL();
                            int userbal = a.getUserBalance(match.getP1());
                            int tourfee = b1.getFee(tour);
                            if(userbal >= tourfee){
                                a.updateWin(match.getP1(), tour, tourfee);
                            }
                        }
                    }else {
                        res = "Player1F";
                        m.addMatch(player1id, 0, tour);
                    }
                }
                else{
                    MatchDAOInterf m = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
                    Match match = m.getLastMatch();
                    if(match.getP2() == 0) {
                        res = "Player2S";
                        m.updateMatch(player2id, match.getId());
                        GameDAOInterf g = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
                        g.addGame(match.getId());
                        MatchBLL b = new MatchBLL();
                        if (b.getCountMatches(tour) == 7){
                            TournamentBLL b1 = new TournamentBLL();
                            b1.updateName("Finished", tour);
                            UserBLL u = new UserBLL();
                            AccountBLL a = new AccountBLL();
                            int userbal = a.getUserBalance(match.getP2());
                            int tourfee = b1.getFee(tour);
                            if(userbal >= tourfee){
                                a.updateWin(match.getP2(), tour, tourfee);
                            }
                        }


                    }else {
                        res = "Player2F";
                        m.addMatch(player2id, 0, tour);
                    }
                }

            }

        }
        return res;
    }

}
