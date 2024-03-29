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

    String AllGamesJSONServer() throws DaoException;

    String findGameByIDJSONServer(int id) throws DaoException;

    void deleteGameByIdServer(int id)  throws DaoException;

    void addNewGameServer(Game game)  throws DaoException;

    public List<Game> findAllGames() throws DaoException;

    List<Game> filterAllGamesByTitle() throws DaoException;


    List<Game> filterAllGamesByGenre() throws DaoException;

    List<Game> filterAllGamesByYear() throws DaoException;

    List<Game> filterAllGamesByPublisher() throws DaoException;

    List<Game> filterAllGamesByPrice() throws DaoException;

    List<Game> filterAllGamesByPriceDescending() throws DaoException;

    List<Game> filterAllGamesByRate() throws DaoException;
}
