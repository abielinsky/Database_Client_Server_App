package com.dkit.abielLopez.SDA.dto;

import com.dkit.abielLopez.SDA.core.constants.Colours;
import com.dkit.abielLopez.SDA.dao.gamedao.GameDaoInterface;
import com.dkit.abielLopez.SDA.dao.gamedao.MySqlGameDao;
import com.dkit.abielLopez.SDA.dao.storedao.MySqlStoreDao;
import com.dkit.abielLopez.SDA.dao.storedao.StoreDaoInterface;
import com.dkit.abielLopez.SDA.exceptions.DaoException;


import java.util.*;

public class StoreManager {

    private Map<Store, List<Game>> StoreManager = new TreeMap<>();
//    private Map<Store, List<Game>> StoreManager = new TreeMap<>();

    public StoreManager()
    {
        initializeHashMap();
    }



    public StoreManager(Set<Store> stores, List<Game> games)
    {
        initializeHashMap(stores, games);
    }

    private void initializeHashMap(Set<Store> stores, List<Game> games)
    {

        for(Store store : stores)
        {
            for(Game game : games)
            {
                if(game.getGameStore_ID() == game.getGameStore_ID())
                {
                    List<Game> gamesInStore = null;

                    if(StoreManager.containsKey(store))
                    {
                        gamesInStore = StoreManager.get(store);

                        gamesInStore.add(game);

                        StoreManager.put(store, gamesInStore);
                    }
                    else
                    {
                        gamesInStore = new ArrayList<>();
                        gamesInStore.add(game);

                        StoreManager.put(store, gamesInStore);
                    }
                }
            }

        }
    }



    public void initializeHashMap()
    {
        StoreDaoInterface storeDAO = new MySqlStoreDao();


        GameDaoInterface gameDAO = new MySqlGameDao();

        Set<Game> games = new HashSet<>();

        try
        {
            games = (Set<Game>) gameDAO.findAllGames(); /////cassst Game
        }
        catch (DaoException de)
        {
            System.out.println(Colours.RED + de.getMessage() + Colours.RESET);
        }



        for (Game game : games)
        {
            List<Game> gamesInStore = new ArrayList<>();

            try
            {
                gamesInStore = gameDAO.findAllGamesInStore(game.getGameStore_ID());
            }
            catch (DaoException de)
            {
                System.out.println(Colours.RED + de.getMessage() + Colours.RESET);
            }

            System.out.println("getting here");
//            StoreManager.put(game, gamesInStore);
        }

    }








}
