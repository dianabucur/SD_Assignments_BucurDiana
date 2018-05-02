package ro.utcluj.sd.Models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "pingpong.match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "player1")
    private int player1;

    @Column(name = "player2")
    private int player2;

    @Column(name = "tournament")
    private int tournament;

    public Match(){}
    public Match(int id, int p1, int p2){
        this.id = id;
        this.player1 = p1;
        this.player2 = p2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP1() {
        return player1;
    }

    public int getTournamentid() {
        return tournament;
    }

    public void setTournamentid(int tournamentid) {
        this.tournament = tournamentid;
    }

    public void setP1(int p1) {
        this.player1 = p1;
    }

    public int getP2() {
        return player2;
    }

    public void setP2(int p2) {
        this.player2 = p2;
    }
}
