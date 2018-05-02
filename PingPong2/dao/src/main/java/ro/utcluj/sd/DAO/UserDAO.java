package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Connection.ConnectionFactory;
import ro.utcluj.sd.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDAO implements UserDAOInterf {

    protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    private static final String insertStatementString = "INSERT INTO user (name, age, gender, mail, password, admin)" + " VALUES (?,?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM user WHERE mail = ? AND password = ?";
    private final static String findAll = "SELECT mail FROM user";
    private final static String getIdByMail = "SELECT id FROM user WHERE mail = ?";
    private final static String findid = "SELECT id FROM user WHERE mail = ?";
    private final static String updateStatementString = "UPDATE user SET name = ? WHERE mail = ?";
    private final static String updateAgeStatementString = "UPDATE user SET age = ? WHERE mail = ?";
    private final static String updatePassStatementString = "UPDATE user SET password = ? WHERE mail = ?";
    private final static String deleteStatementString = "DELETE FROM user WHERE mail = ?";
    private final static String getNameById = "SELECT name from pingpong.user where id = ?";

    public UserDAO(){};

    public int checkUser(String mail, String pass) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; boolean res = false; int found = -1;
        try {
            findStatement = connection.prepareStatement(findStatementString);
            findStatement.setString(1, mail);
            findStatement.setString(2, pass);
            rs = findStatement.executeQuery();
            if(rs.next()) {
                res = rs.getBoolean(7);
                if (res == true)
                    found = 1;
                else
                    found = 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return found;
    }

    public int getid(String mail) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; int id = -1;
        try {
            findStatement = connection.prepareStatement(findid);
            findStatement.setString(1, mail);
            rs = findStatement.executeQuery();
            if(rs.next())
                id = rs.getInt(1);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return id;
    }

    public ArrayList<String> findAllUsers(){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement viewStatement = null;
        ResultSet rs = null;
        ArrayList<String> a = new ArrayList<String>();
        try {
            viewStatement = connection.prepareStatement(findAll);
            rs = viewStatement.executeQuery();
            while(rs.next()){
                a.add(rs.getString(1));
            }
        }catch(SQLException e){
        }finally {
            ConnectionFactory.close(viewStatement);
            ConnectionFactory.close(connection);
        }
        return a;
    }

    public int insertUser(User u) {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, u.getName());
            insertStatement.setInt(2, u.getAge());
            insertStatement.setString(3, u.getGender());
            insertStatement.setString(4, u.getMail());
            insertStatement.setString(5, u.getPassword());
            insertStatement.setInt(6, 0);
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(connection);
        }
        return insertedId;
    }

    public void updateName(String mail, String name){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateStatementString);
            updateStatement.setString(1, name);
            updateStatement.setString(2, mail);
            System.out.println(updateStatement);
            updateStatement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    public void updateAge(String mail, int age){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateAgeStatementString);
            updateStatement.setInt(1, age);
            updateStatement.setString(2, mail);
            System.out.println(updateStatement);
            updateStatement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    public void updatePassword(String mail, String pass){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updatePassStatementString);
            updateStatement.setString(1, pass);
            updateStatement.setString(2, mail);
            System.out.println(updateStatement);
            updateStatement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    public void deleteUser(String mail){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = connection.prepareStatement(deleteStatementString);
            deleteStatement.setString(1, mail);
            System.out.println(deleteStatement);
            deleteStatement.execute();

        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(connection);
        }
    }


    public String getNameById(int id) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; String name = null;
        try {
            findStatement = connection.prepareStatement(getNameById);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            if(rs.next())
                name = rs.getString(1);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return name;
    }

    @Override
    public void closeConnection() {

    }


}
