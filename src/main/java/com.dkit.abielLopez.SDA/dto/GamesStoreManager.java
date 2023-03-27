package com.dkit.abielLopez.SDA.dto;

import java.util.*;

public class GamesStoreManager {

    private Map<Store, List<Game>> gameStoreManager = new TreeMap<>();

    public GamesStoreManager()
    {
//        initializeHashMap();
    }



    public GamesStoreManager(Set<Store> stores, ArrayList<Game> games)
    {
        initializeHashMap(stores, games);
    }

    private void initializeHashMap(Set<Store> stores, List<Game> games)
    {

        for(Store store : stores)
        {
            for(Game game : games)
            {
//                if(game.getGameStore_ID() == game.getFLEET_ID()) neeeee tooo dooooooo the storeeeeeee to get id
                {
                    List<Game> gamesInStore = null;

                    if(gameStoreManager.containsKey(store))
                    {
                        gamesInStore = gameStoreManager.get(store);

                        gamesInStore.add(game);

                        gameStoreManager.put(store, gamesInStore);
                    }
                    else
                    {
                        gamesInStore = new ArrayList<>();
                        gamesInStore.add(game);


                        gameStoreManager.put(store, gamesInStore);
                    }
                }
            }

        }
    }










}
