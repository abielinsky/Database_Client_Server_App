import DAOs.MySqlGameDAO;
import DTOs.Game;
import Exceptions.DaoException;
import org.junit.jupiter.api.Test;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MySqlGameDAOTest {

        @Test
        void testSingleGame() throws DaoException {
                // Initialize the DAO object
                MySqlGameDAO dao = new MySqlGameDAO();

                // Get a single game from the database by ID
                Game game = dao.findGameById(1);

                // Check that the game returned matches the expected values
                assertEquals(game.getGame_ID(), 1);
                assertEquals(game.getTitle_Game(), "The Legend of Zelda");
                assertEquals(game.getGenre_Game(), "Action-Adventure");
                assertEquals(game.getRelease_year_Game(), 2017);
                assertEquals(game.getPublisher_Game(), "Nintendo");
                assertEquals(game.getPrice_Game(), 59.99);
                assertEquals(game.getRate_Game(), 10.0);
        }


        @Test
        void testSingleGameTwo() throws DaoException {
                // Initialize the DAO object
                MySqlGameDAO dao = new MySqlGameDAO();

                // Get a single game from the database by ID
                Game game = dao.findGameById(5);

                // Check that the game returned matches the expected values
                assertEquals(game.getGame_ID(), 5);
                assertEquals(game.getTitle_Game(), "Half-Life 2");
                assertEquals(game.getGenre_Game(), "First-person shooter");
                assertEquals(game.getRelease_year_Game(), 2004);
                assertEquals(game.getPublisher_Game(), "Valve Corporation");
                assertEquals(game.getPrice_Game(), 9.99);
                assertEquals(game.getRate_Game(), 9.0);
        }


        @Test
        void testDeleteGame() throws DaoException {
                // Initialize the DAO object
                MySqlGameDAO dao = new MySqlGameDAO();

                // Insert a test game into the database
                Game testGame = new Game(0, "Test Game", "Test Genre", 2022, "Test Publisher", 9.99, 8.0);
                dao.displayAllGames();

                // Get the ID of the test game
                int testGameId = testGame.getGame_ID();

                // Delete the test game from the database
                dao.deleteGameById(testGameId);

                // Attempt to retrieve the test game from the database
                Game deletedGame = dao.findGameById(testGameId);

                // Check that the deleted game is null (i.e. it does not exist in the database)

                assertEquals(deletedGame, null);
        }





}
