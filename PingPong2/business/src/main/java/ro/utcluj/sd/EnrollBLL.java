package ro.utcluj.sd;

import ro.utcluj.sd.DAO.EnrollDAO;
import ro.utcluj.sd.DAO.EnrollDAOInterf;
import ro.utcluj.sd.DAO.UserDAO;
import ro.utcluj.sd.DAO.UserDAOInterf;
import ro.utcluj.sd.Factory.DaoFactory;

import java.util.ArrayList;

public class EnrollBLL {
    EnrollDAOInterf e;

    EnrollBLL(){
       e = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getEnrolledDao();
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
            UserDAOInterf userDAO = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
            int id = userDAO.getid(mail);
            ArrayList<Integer> players = e.getId(tour);
            for(int i = 0; i < players.size(); i++)
                if(players.get(i) == id){
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
