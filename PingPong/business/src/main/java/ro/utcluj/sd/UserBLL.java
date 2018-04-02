package ro.utcluj.sd;

import ro.utcluj.sd.DAO.UserDAO;
import ro.utcluj.sd.Models.User;

import java.util.ArrayList;

public class UserBLL {
    UserDAO userDAO;

    public UserBLL(){
        userDAO = new UserDAO();
    };

    public int validateUser(String mail, String pass){
        return userDAO.checkUser(mail, pass);
    }

    public ArrayList<String> getAllPlayers(){
        ArrayList<String> a = userDAO.findAllUsers();
        return a;
    }

    public int addUser(String mail, String pass, String name, int age, String gender){
        User user = new User(mail, pass,name,age,gender);
        return userDAO.insertUser(user);
    }

    public void updateAge(String mail, int age){
        userDAO.updateAge(mail,age);
    }

    public void updateName(String mail, String name){
        userDAO.updateName(mail,name);
    }

    public void updatePass(String mail, String pass){
        userDAO.updatePassword(mail,pass);
    }

    public void deleteUser(String mail){
        userDAO.deleteUser(mail);
    }

    public int getNameByMail(String mail) {
        return userDAO.findUserByMail(mail);
    }

    public String getNameById(int id){
        return userDAO.getNameById(id);
    }
}
