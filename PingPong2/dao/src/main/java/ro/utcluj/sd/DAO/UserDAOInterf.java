package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Models.User;

import java.util.ArrayList;

public interface UserDAOInterf {
    public int checkUser(String mail, String pass);
    public int getid(String mail);
    public ArrayList<String> findAllUsers();
    public int insertUser(User u);
    public void updateName(String mail, String name);
    public void updateAge(String mail, int age);
    public void updatePassword(String mail, String pass);
    public void deleteUser(String mail);
    public String getNameById(int id);
    public void closeConnection();
}
