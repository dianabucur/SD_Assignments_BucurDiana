package ro.utcluj.sd.DAO;

import java.util.ArrayList;

public interface EnrollDAOInterf {
    public void enrollPlayer(String mail, String tour);
    public ArrayList<Integer> getId(String tour);
    public ArrayList<String> getPlayers(String tour);
    public ArrayList<String> showTours(String mail);
    public void closeConnection();
}
