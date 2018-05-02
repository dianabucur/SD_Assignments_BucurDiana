package ro.utcluj.sd.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pingpong.enrolled")
public class Enrolled implements Serializable{

    @Id
    @Column(name = "playerid")
    private int playerid;

    @Id
    @Column(name = "tournamentid")
    private int tournamentid;

    public Enrolled(){}

    public Enrolled(int playerid, int tournamentid){
        this.playerid = playerid;
        this.tournamentid = tournamentid;
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public int getTournamentid() {
        return tournamentid;
    }

    public void setTournamentid(int tournamentid) {
        this.tournamentid = tournamentid;
    }
}
