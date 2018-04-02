package ro.utcluj.sd;

import ro.utcluj.sd.DAO.EnrollDAO;
import ro.utcluj.sd.DAO.UserDAO;

import java.util.ArrayList;

public class EnrollBLL {
    EnrollDAO e;

    EnrollBLL(){
        e = new EnrollDAO();
    }

    public ArrayList<String> getPlayers(String tour){
        ArrayList<String> players = e.getPlayers(tour);
        return players;
    }

    public ArrayList<String> showTours(String player){
        ArrayList<String> tours = e.showTours(player);
        return tours;
    }

    public int enrollPlayer(String mail, String tour) {
            EnrollDAO e = new EnrollDAO();
            UserDAO userDAO = new UserDAO();
            int id = userDAO.getid(mail);
            int[] players = e.getId(tour);
            for(int i = 0; i < players.length; i++)
                if(players[i] == id){
                    return -1;
                }
            e.enrollPlayer(mail, tour);
            if (e.getPlayers(tour).size() == 8) {
                MatchBLL m = new MatchBLL();
                m.addMatches(tour);
            }
        return 1;
    }
}
