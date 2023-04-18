package com.dkit.abielLopez.SDA.dao;

import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MySqlGameDao extends MySqlDao implements GameDaoInterface {

    @Override
    public List<Game> findAllGames() throws DaoException {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Game> games = new ArrayList<>();

        try {
            //Get a connection to the database
            con = this.getConnection();
            String query = "SELECT * FROM game";
            ps = con.prepareStatement(query);

            //Use the prepared statement to execute the sql
            rs = ps.executeQuery();

            while (rs.next()) {
                int gameID = rs.getInt("id_Game");
                int gameStoreID =0;// rs.getInt("gameStore_ID");
                String title = rs.getString("title_Game");
                String genre = rs.getString("genre_Game");
                int releaseYear = rs.getInt("release_year_Game");
                String publisher = rs.getString("publisher_Game");
                double price = rs.getDouble("price_Game");
                int rate = rs.getInt("rate_Game");

                Game game = new Game(gameID, gameStoreID, title, genre, releaseYear, publisher, price, rate);

                games.add(game);
            }
        } catch (SQLException sqe) {
            throw new DaoException("findAllGames() " + sqe.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {

                    freeConnection(con);
                }
            } catch (SQLException sqe) {
                throw new DaoException("findAllGames() " + sqe.getMessage());
            }
        }
        return games;
    }

    @Override
    public List<Game> findGameById() throws DaoException {
        return null;
    }

    @Override
    public String findAllGamesJson() throws DaoException {

        List<Game> games = findAllGames();

        String jsonStringGames = GameDaoInterface.gsonParser.toJson(games);

        return jsonStringGames;
    }

    @Override
    public List<Game> findAllGamesInStore(int storeIDToBeFound) throws DaoException {
        return null;
    }

    @Override
    public void insertNewGame(Game game) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Get a connection to the database
            con = this.getConnection();
            String query = "INSERT INTO game (id_Game, title_Game, genre_Game, release_year_Game, " +
                    "publisher_Game, price_Game, rate_Game) VALUES (?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);

            ps.setInt(   1, game.getGame_ID());
            ps.setString(2, game.getTitle_Game());
            ps.setString(3, game.getGenre_Game());
            ps.setInt(   4, game.getRelease_year_Game());
            ps.setString(5, game.getPublisher_Game());
            ps.setDouble(6, game.getPrice_Game());
            ps.setDouble(7, game.getRate_Game());


            //Use the prepared statement to execute the sql
            ps.executeUpdate();

        } catch (SQLException sqe) {
            throw new DaoException("insertNewGame() " + sqe.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {

                    freeConnection(con);
                }
            } catch (SQLException sqe) {
                throw new DaoException("insertNewGame() " + sqe.getMessage());
            }
        }
    }

    @Override
    public String findAllGamesJSONServer() throws DaoException {
        List<Game> gameList = findAllGames();


        Gson gsonParser = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new Adapter())
                .create();




        String gameJsonString = gsonParser.toJson(gameList);


        return gameJsonString;
    }



}






