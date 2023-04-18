package DAOs;

import DTOs.Game;
import Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlGameDAO extends MySqlDAO implements GameDAOInterface {

    @Override
    public List<Game> displayAllGames() throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try {
            //Get a connection to the database
            connection = this.getConnection();

            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //Use the prepared statement to execute the sql
            resultSet = ps.executeQuery();

            while (resultSet.next()) {

                int gameID = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(gameID, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }
        } catch (Exception e) {
            throw new DaoException("findAllGameResultSet() " + e.getMessage());
        }

        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                 throw new DaoException("findAllGames() " + e.getMessage());
            }
        }
            return gameList;
    }




    @Override
    public Game findGameById(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int game_ID = id;
        Game game = null;

        try {
            //Get a connection to the database
            connection = this.getConnection();
            String query = "SELECT * FROM game where id_Game = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(id));
            resultSet = ps.executeQuery();


            if (resultSet.next()) {

                int gameID = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                game = new Game(gameID, title, genre, releaseYear, publisher, price, rate);

            }
        } catch (Exception e) {
            throw new DaoException("findGameByID() " + e.getMessage());
        }

        finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllGames() " + e.getMessage());
            }
        }


        return game;
    }

    @Override
    public void deleteGameById(int id) throws DaoException {

            Connection connection = null;
            PreparedStatement ps = null;
            ResultSet resultSet = null;
            int game_ID = id;

            try {
                //Get a connection to the database
                connection = this.getConnection();
                String query = "DELETE FROM game where id_Game = ?";
                ps = connection.prepareStatement(query);
                ps.setString(1, String.valueOf(id));
                ps.executeUpdate();

            } catch (Exception e) {
                throw new DaoException("deleteGameByID() " + e.getMessage());
            }

            finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (connection != null) {
                        freeConnection(connection);
                    }
                } catch (SQLException e) {
                    throw new DaoException("findAllGames() " + e.getMessage());
                }
            }
    }


}
