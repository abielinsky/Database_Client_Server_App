package com.dkit.abielLopez.SDA.dao.gamedao;

import com.dkit.abielLopez.SDA.dao.MySqlDao;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
    public Set<Store> findAllStores() throws DaoException {
        return null;
    }
}
