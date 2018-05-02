package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Models.Tournament;

import java.util.ArrayList;

public interface TournamentDAOInterf {
    public ArrayList<Tournament> findAllTours();
    public int getid(String name);
    public int addTour(Tournament tour);
    public void updateName(String name, String sel);
    public void updateLevel(String level, String sel);
    public void updateFee(int fee, String sel);
    public void deleteTournament(String tour, String level);
    public void closeConnection();
}
