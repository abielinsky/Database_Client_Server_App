package com.dkit.abielLopez.SDA.dao.gamedao;

import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.google.gson.Gson;

import java.util.List;

public interface GameDaoInterface {

    Gson gsonParser = new Gson();
    List<Game> findAllGames() throws DaoException;

    public String findAllGamesJson() throws DaoException;


    String findAllTrainsJson() throws DaoException;
}
