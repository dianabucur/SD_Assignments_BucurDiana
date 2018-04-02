package ro.utcluj.sd;

import ro.utcluj.sd.DAO.EnrollDAO;
import ro.utcluj.sd.DAO.TournamentDAO;
import ro.utcluj.sd.Models.Tournament;

import java.util.ArrayList;

public class TournamentBLL {
    TournamentDAO t;

    TournamentBLL() {
        t = new TournamentDAO();
    }

    public ArrayList<String> getAllTours() {
        ArrayList<String> tours = new ArrayList<String>();
        for(Tournament tournament: t.findAllTours())
            tours.add(tournament.getName());
        return tours;
    }

    public ArrayList<String> getAllTourLevel() {
        ArrayList<String> tours = new ArrayList<String>();
        for(Tournament tournament: t.findAllTours())
            tours.add(tournament.getLevel());
        return tours;
    }

    public ArrayList<String> getAllTourStatus() {
        ArrayList<String> tours = new ArrayList<String>();
        for(Tournament tournament: t.findAllTours())
            tours.add(tournament.getStatus());
        return tours;
    }

    public void addTour(String name, String level) {
        Tournament tour = new Tournament(name, level);
        t.addTour(tour);
    }

    public void updateName(String name, String sel) {
        t.updateName(name, sel);
    }

    public void updateLevel(String level, String sel) {
        t.updateName(level, sel);
    }

    public void deleteTour(String tour, String level){
        t.deleteTournament(tour, level);
    }

}
