package ro.utcluj.sd.Models;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "gender")
    private String gender;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    @Column(name = "admin")
    private boolean admin;

    public User(){}

    public User ( String mail, String password,String name, int age, String gender){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.mail = mail;
        this.password = password;
    }

    public User(String mail, String password){

        this.mail = mail;
        this.password = password;
    }

    public String getGender() { return gender; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public String getMail(){
        return this.mail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
