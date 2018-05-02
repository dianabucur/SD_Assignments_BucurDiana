package ro.utcluj.sd;

import ro.utcluj.sd.HibernateDAO.HibernateAccountDAO;
import ro.utcluj.sd.HibernateDAO.HibernateTournamentDAO;
import ro.utcluj.sd.Models.AccountTournament;
import ro.utcluj.sd.Models.AccountUser;

public class AccountBLL {
    HibernateAccountDAO h;

    public AccountBLL(){
        h = new HibernateAccountDAO();
    }

    public void addUserAccount(int userid, int balance){
        AccountUser a = new AccountUser(userid, balance);
        h.addUserAccount(a);
    }

    public void addTourAccount(int tourid, int balance){
        AccountTournament a = new AccountTournament(tourid, balance);
        h.addTourAccount(a);
    }

    public int getUserBalance(int id){
        return h.getUserBalance(id).getBalance();
    }

    public int getTourBalance(int id){
        return h.getTourBalance(id).getBalance();
    }

    public void updateEnroll(String mail, String tour, int fee){
        h.updateEnroll(mail, tour, fee);
    }

    public void updateUserAccount(String mail, int balance){
        h.updateUserAccount(mail, balance);
    }

    public void updateWin(int id, String tour, int fee){
        h.updateWin(id, tour, fee);
    }
}
