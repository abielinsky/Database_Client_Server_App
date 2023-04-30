import DAOs.MySqlGameDAO;
import DTOs.Game;
import Exceptions.DaoException;
import Objects.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void main() {
    }

    @Test //Test for - FindGameByID - ID = 2
    void findGameById2() throws DaoException {
        System.out.println("Test for  - FindGameByID");

        int id = 2;
        Game expectedGame = new Game(2, "The Witcher 3", "Action RPG", 2015, "CD Projekt Red", 39.99, 9.0);
        MySqlGameDAO mySqlGameDao = new MySqlGameDAO();
        Game game = mySqlGameDao.findGameById(id);
        assertEquals(expectedGame.getGame_ID(), game.getGame_ID());
        assertEquals(expectedGame.getTitle_Game(), game.getTitle_Game());
        assertEquals(expectedGame.getGenre_Game(), game.getGenre_Game());
        assertEquals(expectedGame.getRelease_year_Game(), game.getRelease_year_Game());
        assertEquals(expectedGame.getPublisher_Game(), game.getPublisher_Game());
        assertEquals(expectedGame.getPrice_Game(), game.getPrice_Game());
        assertEquals(expectedGame.getRate_Game(), game.getRate_Game());
    }


    @Test //Test for - FindGameByID - ID = 5
    void findGameById5() throws DaoException {
        System.out.println("Test for  - FindGameByID");

        int id = 5;
        Game expectedGame = new Game(5, "Half-Life 2", "First-person shooter", 2004, "Valve Corporation", 9.99, 9.0);
        MySqlGameDAO mySqlGameDao = new MySqlGameDAO();
        Game game = mySqlGameDao.findGameById(id);
        assertEquals(expectedGame.getGame_ID(), game.getGame_ID());
        assertEquals(expectedGame.getTitle_Game(), game.getTitle_Game());
        assertEquals(expectedGame.getGenre_Game(), game.getGenre_Game());
        assertEquals(expectedGame.getRelease_year_Game(), game.getRelease_year_Game());
        assertEquals(expectedGame.getPublisher_Game(), game.getPublisher_Game());
        assertEquals(expectedGame.getPrice_Game(), game.getPrice_Game());
        assertEquals(expectedGame.getRate_Game(), game.getRate_Game());
    }

    @Test
    void testDisplayGame() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream); // Redirigimos la salida estándar al stream de bytes

        Game game = new Game(1, "The Witcher 3", "Action RPG", 2015, "CD Projekt Red", 39.99, 9.0);
        App.displayGame(game);

        String expectedOutput = "\n" +
                "********************************************************************************************************************************\n" +
                "* Id   Name                  Genre                    Release Year    Publisher Company         Price      Rate       *\n" +
                "********************************************************************************************************************************\n" +
                "* 1    The Witcher 3         Action RPG               2015           CD Projekt Red            39.99      9.0        *\n" +
                "********************************************************************************************************************************\n" +
                "\n";
        String actualOutput = outputStream.toString();
      //  assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void testDisplayAGame() {

        Game game = new Game(1, "The Witcher 3", "Action RPG", 2015, "CD Projekt Red", 39.99, 9.0);

        App app = new App();
        app.displayGame(game);
    }







}