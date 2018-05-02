package ro.utcluj.sd.DAO;

import com.mysql.jdbc.Statement;
import ro.utcluj.sd.Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollDAO implements EnrollDAOInterf{

    protected static final Logger LOGGER = Logger.getLogger(TournamentDAO.class.getName());

    private final static String insertStatementString = "INSERT INTO enrolled (playerid, tournamentid)" + " VALUES (?,?) ";
    private final static String selectStatementString = "SELECT name FROM user JOIN enrolled WHERE user.id = enrolled.playerid AND tournamentid = ?";
    private final static String selectToursString = "SELECT name FROM tournament JOIN enrolled WHERE tournament.id = enrolled.tournamentid AND playerid = ?";
    private final static String selectid = "select playerid from enrolled where tournamentid = ?";

    public void enrollPlayer(String mail, String tour) {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            UserDAO u = new UserDAO();
            int playerid = u.getid(mail);
            TournamentDAO t = new TournamentDAO();
            int tourid = t.getid(tour);
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, playerid);
            insertStatement.setInt(2, tourid);
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
    }

    public ArrayList<Integer> getId(String tour){
        TournamentDAO t = new TournamentDAO();
        int id = t.getid(tour);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        ArrayList<Integer> ids = new ArrayList<>();
        try {
            findStatement = connection.prepareStatement(selectid);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            while(rs.next()){
                int id2 = rs.getInt(1);
                System.out.println(id2);
                ids.add(id2);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return ids;
    }

    public ArrayList<String> getPlayers(String tour) {
        TournamentDAO t = new TournamentDAO();
        int id = t.getid(tour);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        ArrayList<String> players = new ArrayList<String>();
        try {
            findStatement = connection.prepareStatement(selectStatementString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            while(rs.next()){
                String name = rs.getString(1);
                players.add(name);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return players;
    }

    public ArrayList<String> showTours(String mail) {
        UserDAO t = new UserDAO();
        int id = t.getid(mail);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        ArrayList<String> tours = new ArrayList<String>();
        try {
            findStatement = connection.prepareStatement(selectToursString);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            while(rs.next()){
                String tour = rs.getString(1);
                tours.add(tour);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return tours;
    }

    @Override
    public void closeConnection() {

    }
}
