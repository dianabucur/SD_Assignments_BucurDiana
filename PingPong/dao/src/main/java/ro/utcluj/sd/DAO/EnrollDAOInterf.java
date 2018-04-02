package ro.utcluj.sd.DAO;

import java.util.ArrayList;

public interface EnrollDAOInterf {
    public void enrollPlayer(String mail, String tour);
    public int[] getId(String tour);
    public ArrayList<String> getPlayers(String tour);
    public ArrayList<String> showTours(String mail);
}
