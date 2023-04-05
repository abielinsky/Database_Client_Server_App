package com.dkit.abielLopez.SDA.dao;

import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.google.gson.Gson;

import java.util.List;

public interface GameDaoInterface

{

    Gson gsonParser = new Gson();
    List<Game> findAllGames() throws DaoException;

    List<Game> findGameById() throws DaoException;





    public String findAllGamesJson() throws DaoException;

    public List<Game> findAllGamesInStore(int storeIDToBeFound) throws DaoException;


    void insertNewGame(Game game) throws DaoException;


}
