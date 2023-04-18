package DAOs;

import DTOs.Game;
import Exceptions.DaoException;

import java.util.List;

public interface GameDAOInterface {


    List<Game> displayAllGames() throws DaoException;

    Game findGameById(int id) throws DaoException;



}
