package DAOs;

import DTOs.Game;
import Exceptions.DaoException;

import java.util.List;

public interface GameDAOInterface {


    List<Game> displayAllGames() throws DaoException;

    Game findGameById(int id) throws DaoException;

    void deleteGameById(int id)  throws DaoException;


    Game addNewGame(String titleGame, String genreGame, int releaseYearGame,
                    String publisherGame, double priceGame, int rateGame)  throws DaoException;





}
