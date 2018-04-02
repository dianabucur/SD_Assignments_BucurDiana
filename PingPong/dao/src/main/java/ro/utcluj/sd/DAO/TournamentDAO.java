package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Connection.ConnectionFactory;
import ro.utcluj.sd.Models.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TournamentDAO implements TournamentDAOInterf{
    protected static final Logger LOGGER = Logger.getLogger(TournamentDAO.class.getName());
    private final static String findAll = "SELECT * FROM tournament";
    private final static String findid = "SELECT id FROM tournament WHERE name = ?";
    private static final String insertStatementString = "INSERT INTO tournament (name, status, level)" + " VALUES (?,?,?)";
    private final static String updateStatementString = "UPDATE tournament SET name = ? WHERE name = ?";
    private final static String updateLevStatementString = "UPDATE tournament SET name = ? WHERE name = ?";
    private final static String deleteStatementString = "DELETE FROM tournament WHERE name = ? AND level = ?";

    public ArrayList<Tournament> findAllTours(){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement viewStatement = null;
        ResultSet rs = null;
        ArrayList<Tournament> tours = new ArrayList<Tournament>();
        try {
            viewStatement = connection.prepareStatement(findAll);
            rs = viewStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString(2);
                String status = rs.getString(3);
                String level = rs.getString(4);
                Tournament t = new Tournament(name, status, level);
                tours.add(t);
            }
        }catch(SQLException e){
        }finally {
            ConnectionFactory.close(viewStatement);
            ConnectionFactory.close(connection);
        }
        return tours;
    }

    public int getid(String name) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; int id = -1;
        try {
            findStatement = connection.prepareStatement(findid);
            findStatement.setString(1, name);
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

    public int addTour(Tournament tour) {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, tour.getName());
            insertStatement.setString(2, "Open" );
            insertStatement.setString(3, tour.getLevel());
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

    public void updateName(String name, String sel){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateStatementString);
            updateStatement.setString(1, name);
            updateStatement.setString(2, sel);
            System.out.println(updateStatement);
            updateStatement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    public void updateLevel(String level, String sel){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateLevStatementString);
            updateStatement.setString(1, level);
            updateStatement.setString(2, sel);
            System.out.println(updateStatement);
            updateStatement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    public void deleteTournament(String tour, String level){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = connection.prepareStatement(deleteStatementString);
            deleteStatement.setString(1, tour);
            deleteStatement.setString(2, level);
            System.out.println(deleteStatement);
            deleteStatement.execute();

        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(connection);
        }
    }

}
