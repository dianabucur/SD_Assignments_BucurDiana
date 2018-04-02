package ro.utcluj.sd.Models;

public class Tournament {
    private String name;
    private String status;
    private String level;

    public Tournament(String name, String status, String level){
        this.name = name;
        this.status = status;
        this.level = level;
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
