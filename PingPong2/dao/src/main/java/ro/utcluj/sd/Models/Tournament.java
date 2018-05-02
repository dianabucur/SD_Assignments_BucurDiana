package ro.utcluj.sd.Models;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "level")
    private String level;

    @Column(name = "fee")
    private int fee;

    public Tournament(){}
    public Tournament(String name, String status, String level, int fee){
        this.name = name;
        this.status = status;
        this.level = level;
        this.fee = fee;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Tournament(String name, String level){
        this.name = name;
        this.level = level;

    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLevel(){
        return this.level;
    }

    public String getStatus(){
        return this.status;
    }
}
