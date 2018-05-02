package ro.utcluj.sd.Models;

import javax.persistence.*;

@Entity
@Table(name = "pingpong.accounttour")
public class AccountTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tourid")
    private int tourid;

    @Column(name = "balance")
    private int balance;

    public AccountTournament(){}

    public AccountTournament(int tourid, int balance){
        this.tourid = tourid;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTourid() {
        return tourid;
    }

    public void setTourid(int tourid) {
        this.tourid = tourid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
