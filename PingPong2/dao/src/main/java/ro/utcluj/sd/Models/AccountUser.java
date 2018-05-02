package ro.utcluj.sd.Models;

import javax.persistence.*;

@Entity
@Table(name = "pingpong.accountuser")
public class AccountUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userid")
    private int userid;

    @Column(name = "balance")
    private int balance;

    public AccountUser(){}

    public AccountUser(int userid, int balance){
        this.userid = userid;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
