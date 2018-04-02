package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Connection.ConnectionFactory;
import ro.utcluj.sd.Models.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatchDAO implements MatchDAOInterf {
    protected static final Logger LOGGER = Logger.getLogger(MatchDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO pingpong.match (player1, player2, tournament)" + " VALUES (?,?,?)";
    private static final String selectStatementString = "SELECT * from pingpong.match WHERE player1 = ? OR player2 = ?";
    private final static String getLastMatch = "SELECT * FROM pingpong.match ORDER BY id DESC LIMIT 1";
    private final static String updateStatementString = "UPDATE pingpong.match SET player2 = ? WHERE id = ?";
    private final static String getAllMatches = "SELECT COUNT(id) FROM pingpong.match WHERE tournament = ?";
    private final static String getAllMatches2 = "SELECT id,player1, player2 FROM pingpong.match WHERE tournament = ?";

    public int addMatch(int player1, int player2, String tour){
        TournamentDAO t = new TournamentDAO();
        int id = t.getid(tour);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, player1);
            insertStatement.setInt(2, player2);
            insertStatement.setInt(3, id);
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

    public Match getPlayerMatch(String player) {
        UserDAO t = new UserDAO();
        int id = t.getid(player); int p1 = 0, p2 = 0;
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; Match m = null;
        try {
            findStatement = connection.prepareStatement(selectStatementString);
            findStatement.setInt(1, id);
            findStatement.setInt(2, id);
            rs = findStatement.executeQuery();
            while(rs.next()) {
                id = rs.getInt(1);
                p1 = rs.getInt(2);
                p2 = rs.getInt(3);
            }
            m = new Match(id, p1, p2);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return m;
    }

    public Match getLastMatch() {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; Match m = null;
        try {
            findStatement = connection.prepareStatement(getLastMatch);
            rs = findStatement.executeQuery();
            if(rs.next()) {
                int id = rs.getInt(1);
                int p1 = rs.getInt(2);
                int p2 = rs.getInt(3);
                m = new Match(id, p1, p2);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return m;
    }

    public void updateMatch(int player2, int matchid){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateStatementString);
            updateStatement.setInt(1, player2);
            updateStatement.setInt(2, matchid);
            System.out.println(updateStatement);
            updateStatement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    public int getCountMatches(String tour) {
        TournamentDAO t = new TournamentDAO();
        int id = t.getid(tour);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; int count = 0;
        try {
            findStatement = connection.prepareStatement(getAllMatches);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            if(rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return count;
    }

    public ArrayList<Match> getAllMatches(String tour) {
        TournamentDAO t = new TournamentDAO();
        int id = t.getid(tour);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; ArrayList<Match> matches = new ArrayList<Match>();
        try {
            findStatement = connection.prepareStatement(getAllMatches2);
            findStatement.setInt(1, id);
            rs = findStatement.executeQuery();
            while(rs.next()) {
                id = rs.getInt(1);
                int player1 = rs.getInt(2);
                int player2 = rs.getInt(3);
                Match m = new Match(id, player1, player2);
                matches.add(m);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return matches;
    }




}
