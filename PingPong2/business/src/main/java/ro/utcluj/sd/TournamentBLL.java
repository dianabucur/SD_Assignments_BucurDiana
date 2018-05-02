package ro.utcluj.sd;

import ro.utcluj.sd.DAO.EnrollDAO;
import ro.utcluj.sd.DAO.MatchDAOInterf;
import ro.utcluj.sd.DAO.TournamentDAO;
import ro.utcluj.sd.DAO.TournamentDAOInterf;
import ro.utcluj.sd.Factory.DaoFactory;
import ro.utcluj.sd.HibernateDAO.HibernateTournamentDAO;
import ro.utcluj.sd.Models.Tournament;

import java.util.ArrayList;

public class TournamentBLL {
    TournamentDAOInterf t;

    TournamentBLL() {
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
    }

    public ArrayList<String> getAllTours() {
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ArrayList<String> tours = new ArrayList<String>();
        for(Tournament tournament: t.findAllTours())
            tours.add(tournament.getName());
        return tours;
    }

    public ArrayList<String> getAllTourLevel() {
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ArrayList<String> tours = new ArrayList<String>();
        for(Tournament tournament: t.findAllTours())
            tours.add(tournament.getLevel());
        return tours;
    }

    public ArrayList<String> getAllTourStatus() {
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ArrayList<String> tours = new ArrayList<String>();
        for(Tournament tournament: t.findAllTours())
            tours.add(tournament.getStatus());
        return tours;
    }

    public ArrayList<Integer> getAllTourFee() {
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ArrayList<Integer> fees = new ArrayList<>();
        for(Tournament tournament: t.findAllTours())
            fees.add(tournament.getFee());
        return fees;
    }

    public ArrayList<Tournament> getTourByFee(String fee) {
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ArrayList<Tournament> fees = new ArrayList<>();
        for(Tournament tournament: t.findAllTours())
            if (fee == "Free"){
                if(tournament.getFee() == 0)
                    fees.add(tournament);
            }else
            if(tournament.getFee() != 0)
                fees.add(tournament);
        return fees;
    }

    public ArrayList<Tournament> getTourByStatus(String sts) {
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ArrayList<Tournament> statuses = new ArrayList<>();
        for(Tournament tournament: t.findAllTours()) {
            if (sts == "Open") {
                if (tournament.getStatus().equals("Open")){
                    statuses.add(tournament);
                }
            } else if (tournament.getStatus().equals("Finished"))
                statuses.add(tournament);
        }
        return statuses;
    }

    public ArrayList<String> getSearched(String text){
        t = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        ArrayList<String> searched = new ArrayList<>();
        for(Tournament tournament: t.findAllTours()) {
            if (tournament.getName().toLowerCase().contains(text.toLowerCase()))
                searched.add(tournament.getName());
        }
        return searched;
    }

    public void addTour(String name, String level, int fee) {
        Tournament tour = new Tournament(name, "Open", level, fee);
        t.addTour(tour);
    }

    public void updateName(String name, String sel) {
        t.updateName(name, sel);
    }

    public void updateLevel(String level, String sel) {
        t.updateName(level, sel);
    }

    public void updateFee(int fee, String sel) {
        t.updateFee(fee, sel);
    }

    public void deleteTour(String tour, String level){
        t.deleteTournament(tour, level);
    }

    public int getIdByName(String tour){
        return t.getid(tour);
    }

    public int getFee(String tour){
        HibernateTournamentDAO tt = new HibernateTournamentDAO();
        Tournament tournament = tt.getTournament(t.getid(tour));
        return tournament.getFee();
    }

    /*public void updateTournament(String tour, int fee){
        HibernateTournamentDAO tt = new HibernateTournamentDAO();
        Tournament tournament = tt.getTournament(t.getid(tour));
        tournament.setFee(fee);
        tt.updateTournament(tournament,fee);
    }*/

}
