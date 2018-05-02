package ro.utcluj.sd.HibernateDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.utcluj.sd.DAO.UserDAOInterf;
import ro.utcluj.sd.Connection.HibernateUtil;
import ro.utcluj.sd.Models.User;

import java.util.ArrayList;
import java.util.List;

public class HibernateUserDAO implements UserDAOInterf {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public int checkUser(String mail, String pass) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select admin from User where mail= :MailParameter and password= :PassParameter")
                .setString("MailParameter", mail)
                .setString("PassParameter", pass);
        boolean admin = (boolean) query.list().get(0);
        transaction.commit();
        //sessionFactory.close();
        if (admin == true)
            return 1;
        else
            return 0;

    }

    public int getid(String mail) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select id from User where mail= :MailParameter").setString("MailParameter", mail);
        int id = (int) query.list().get(0);
        transaction.commit();
        return id;
    }

    public ArrayList<String> findAllUsers() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select mail from User");
        List<String> p = query.list();
        ArrayList<String> players = new ArrayList<>();
        for(String s: p) {
            players.add(s);
        }
        transaction.commit();
        //sessionFactory.close();
        return players;
    }

    public int insertUser(User u) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(u);
        transaction.commit();
        //sessionFactory.close();
        return 1;
    }

    public void updateName(String mail, String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update User set name= :NameParameter where mail= :MailParameter")
                .setString("NameParameter", name)
                .setString("MailParameter", mail).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
    }

    public void updateAge(String mail, int age) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update User set age= :AgeParameter where mail= :MailParameter")
                .setInteger("AgeParameter", age)
                .setString("MailParameter", mail).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
    }

    public void updatePassword(String mail, String pass) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("update User set password= :PassParameter where mail= :MailParameter")
                .setString("PassParameter", pass)
                .setString("MailParameter", mail).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
    }

    public void deleteUser(String mail) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.createQuery("delete from User where mail= :MailParameter").setString("MailParameter", mail).executeUpdate();
        transaction.commit();
        //sessionFactory.close();
    }

    @Override
    public String getNameById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query query = currentSession.createQuery("select name from User where id= :IdParameter").setInteger("IdParameter", id);
        String name = null;
        if(!query.list().isEmpty())
            name = (String) query.list().get(0);
        transaction.commit();
        return name;
    }

    public void closeConnection() {
        sessionFactory.close();
    }


}
