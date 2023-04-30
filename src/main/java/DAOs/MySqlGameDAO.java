package DAOs;

import DTOs.Game;
import Exceptions.DaoException;
import JsonAdapter.MyCustomTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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





    @Override
    public Game addNewGame(String titleGame, String genreGame, int releaseYearGame, String publisherGame,
                           double priceGame, int rateGame) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Game game = null;

        String  name = titleGame;
        String  genre = genreGame;
        int     releaseYear = releaseYearGame;
        String  publisher = publisherGame;
        double  price = priceGame;
        int     rate = rateGame;

        try {
            //Get a connection to the database
            connection = this.getConnection();

            String query = "INSERT INTO game\n " +
                    " VALUES (NULL, ?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, genre);
            preparedStatement.setInt(3, releaseYear);
            preparedStatement.setString(4, publisher);
            preparedStatement.setDouble(5, price);
            preparedStatement.setInt(6, rate);

            preparedStatement.executeUpdate();

            game = new Game (name, genre, releaseYear, publisher, price, rate);

        } catch (SQLException sqe) {
            throw new DaoException("addNewGame() " + sqe.getMessage());
        } finally {
            try {

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("addNewGame() " + e.getMessage());
            }
        }


        return game;


    }

    @Override
    public String AllGamesJSONServer() throws DaoException {

        List<Game> gameList = displayAllGames();
        Gson gsonParser = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new MyCustomTypeAdapter())
                .create();

        String gameJsonString = gsonParser.toJson(gameList);


        return gameJsonString;     // may be empty
    }


    @Override
    public String findGameByIDJSONServer(int id) throws DaoException {
        Game game = findGameById(id);
        Gson gsonParser = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new MyCustomTypeAdapter())
                .create();

        String gameJsonStringBYID = gsonParser.toJson(game);


        return gameJsonStringBYID;

    }

    @Override
    public void deleteGameByIdServer(int id) throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;


        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "DELETE FROM game where id_Game = ?";

            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            //Using a PreparedStatement to execute SQL...
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("deleteGameByID() " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    System.out.println();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deleteGameByID() " + e.getMessage());
            }
        }




    }

    @Override
    public void addNewGameServer(Game game) throws DaoException {


        String query = "INSERT INTO game (title_Game, genre_Game, release_year_Game, " +
                "publisher_Game, price_Game, rate_Game) VALUES (?,?,?,?,?,?)";

        try (
            Connection connection = this.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
        ){
            ps.setString(1, game.getTitle_Game());
            ps.setString(2, game.getGenre_Game());
            ps.setInt(3, game.getRelease_year_Game());
            ps.setString(4, game.getPublisher_Game());
            ps.setDouble(5, game.getPrice_Game());
            ps.setDouble(6, game.getRate_Game());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("addNewGameServer() " + e.getMessage());
        }


    }

    @Override
    public List<Game> findAllGames() throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("findAllGames() " + e.getMessage());
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
    public List<Game> filterAllGamesByTitle() throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try{
            //get connection object using the methods in the super class (MySqlDao.java)
            connection = this.getConnection();

            //String query
            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //using a prepared statement to execute SQL
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("filterAllGamesByTitle() " + e.getMessage());
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
    public List<Game> filterAllGamesByGenre() throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try{
            //get connection object using the methods in the super class (MySqlDao.java)
            connection = this.getConnection();

            //String query
            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //using a prepared statement to execute SQL
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("filterAllGamesByGenre() " + e.getMessage());
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
    public List<Game> filterAllGamesByYear() throws DaoException {


        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try{
            //get connection object using the methods in the super class (MySqlDao.java)
            connection = this.getConnection();

            //String query
            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //using a prepared statement to execute SQL
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("filterAllGamesByYear() " + e.getMessage());
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
    public List<Game> filterAllGamesByPublisher() throws DaoException {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try{
            //get connection object using the methods in the super class (MySqlDao.java)
            connection = this.getConnection();

            //String query
            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //using a prepared statement to execute SQL
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("filterAllGamesByPublisher() " + e.getMessage());
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
    public List<Game> filterAllGamesByPrice() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try{
            //get connection object using the methods in the super class (MySqlDao.java)
            connection = this.getConnection();

            //String query
            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //using a prepared statement to execute SQL
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("filterAllGamesByPrice() " + e.getMessage());
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
    public List<Game> filterAllGamesByPriceDescending() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try{
            //get connection object using the methods in the super class (MySqlDao.java)
            connection = this.getConnection();

            //String query
            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //using a prepared statement to execute SQL
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("filterAllGamesByPriceDescending() " + e.getMessage());
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
    public List<Game> filterAllGamesByRate() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<>();

        try{
            //get connection object using the methods in the super class (MySqlDao.java)
            connection = this.getConnection();

            //String query
            String query = "SELECT * FROM game";
            ps = connection.prepareStatement(query);

            //using a prepared statement to execute SQL
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_Game");
                String title = resultSet.getString("title_Game");
                String genre = resultSet.getString("genre_Game");
                int releaseYear = resultSet.getInt("release_year_Game");
                String publisher = resultSet.getString("publisher_Game");
                double price = resultSet.getDouble("price_Game");
                int rate = resultSet.getInt("rate_Game");

                Game game = new Game(id, title, genre, releaseYear, publisher, price, rate);
                gameList.add(game);
            }

        } catch (SQLException e) {
            throw new DaoException("filterAllGamesByRateAscending " + e.getMessage());
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





}



