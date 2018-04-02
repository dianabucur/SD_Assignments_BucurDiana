package ro.utcluj.sd.Models;

public class User {
    private String gender;
    private String name;
    private int age;
    private String mail;
    private String password;

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

}
