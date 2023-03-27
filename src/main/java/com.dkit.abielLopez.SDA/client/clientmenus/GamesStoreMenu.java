package com.dkit.abielLopez.SDA.client.clientmenus;

import com.dkit.abielLopez.SDA.client.clientconstants.ClientPrintMenuOptions;
import com.dkit.abielLopez.SDA.core.Packet;
import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.dto.GamesStoreManager;
import com.dkit.abielLopez.SDA.dto.Store;
import com.google.gson.reflect.TypeToken;


import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class GamesStoreMenu extends Menu
{

    private GamesStoreManager gamesStoreManager;

    Type arrayListGames = new com.google.gson.reflect.TypeToken<ArrayList<Game>>(){}.getType();

    public GamesStoreMenu(Scanner input, PrintWriter output)
    {
        super(input, output);
    }

    @Override
    public void start()
    {
        if(gamesStoreManager == null)
        {
            initializeGamesStoreManager();
        }
        ClientPrintMenuOptions.printOptionsClientGameMenu();
        // setUpStoresMenu(); for menu stores..
    }


    public GamesStoreManager initializeGamesStoreManager()
    {
        Packet getFleetPacket = new Packet(ProtocolMenuOptions.StoresGamesMenuOptions.DISPLAY_LIST_OF_STORES, "");
        Packet responseFleetPacket = new Packet(ProtocolMenuOptions.ClientMainMenuOptions.NONE, null);

        super.outputPacket(getFleetPacket);
        super.responsePacket(responseFleetPacket);

        Type arrayListFleetType = new TypeToken<Set<Store>>() {}.getType();

        Set<Store> stores = getGsonParser().fromJson(responseFleetPacket.getPayload(), arrayListFleetType);

        getFleetPacket.setMessageType(ProtocolMenuOptions.StoresGamesMenuOptions.DISPLAY_LIST_OF_GAMES);
        responseFleetPacket.setPayload("");

        super.outputPacket(getFleetPacket);
        super.responsePacket(responseFleetPacket);

        ArrayList<Game> games = getGsonParser().fromJson(responseFleetPacket.getPayload(), arrayListGames);

        GamesStoreManager startGamesStoreManager= new GamesStoreManager(stores, games);
        return startGamesStoreManager;
    }


}
