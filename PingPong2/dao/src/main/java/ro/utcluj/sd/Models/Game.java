package ro.utcluj.sd.Models;
import javax.persistence.*;

@Entity
@Table(name = "pingpong.game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "score1")
    private int score1;

    @Column(name = "score2")
    private int score2;

    @Column(name = "matchid")
    private int matchid;

    public Game() {}

    public Game(int id, int score1, int score2){
        this.id = id;
        this.score1 = score1;
        this.score2 = score2;
    }

    public int getId() {
        return id;
    }

    public int getMatch() {
        return matchid;
    }

    public void setMatch(int match) {
        this.matchid = match;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }
}
