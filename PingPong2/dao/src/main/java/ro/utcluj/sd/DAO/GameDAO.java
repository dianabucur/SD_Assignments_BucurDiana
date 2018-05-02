package ro.utcluj.sd.DAO;

import ro.utcluj.sd.Connection.ConnectionFactory;
import ro.utcluj.sd.Models.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameDAO implements GameDAOInterf{

    protected static final Logger LOGGER = Logger.getLogger(GameDAO.class.getName());
    private final static String insertStatementString = "INSERT INTO pingpong.game (score1, score2, matchid) VALUES (?,?,?) ";
    private static final String selectStatementString = "SELECT * from pingpong.game WHERE matchid = ?";
    private final static String updateStatementString = "UPDATE pingpong.game SET score1 = ?, score2 = ? WHERE id = ?";

    public int addGame(int matchid){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, 0);
            insertStatement.setInt(2, 0);
            insertStatement.setInt(3, matchid);
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

    public ArrayList<Game> getGames(int matchid) {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null; ArrayList<Game> games = new ArrayList<Game>();
        try {
            findStatement = connection.prepareStatement(selectStatementString);
            findStatement.setInt(1, matchid);
            rs = findStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                int s1 = rs.getInt(2);
                int s2 = rs.getInt(3);
                Game g = new Game(id, s1, s2);
                games.add(g);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(connection);
        }
        return games;
    }

    public void updateGame(int score1, int score2, int gameid){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = connection.prepareStatement(updateStatementString);
            updateStatement.setInt(1, score1);
            updateStatement.setInt(2, score2);
            updateStatement.setInt(3, gameid);
            System.out.println(updateStatement);
            updateStatement.execute();
        }catch(SQLException e){
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(connection);
        }
    }

    @Override
    public void closeConnection() {

    }


}
