package com.dkit.abielLopez.SDA.client.clientmenus;

import com.dkit.abielLopez.SDA.client.clientconstants.ClientPrintMenuOptions;
import com.dkit.abielLopez.SDA.core.Packet;
import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.dto.StoreManager;
import com.dkit.abielLopez.SDA.dto.Store;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.*;

public class StoreMenu extends Menu
{

    private StoreManager storeManager;
    Type arrayListGames = new TypeToken<ArrayList<Game>>(){}.getType();
    public StoreMenu(Scanner input, PrintWriter output)
    {
        super(input, output);
    }


    @Override
    public void start()
    {
        if(storeManager == null)
        {
            initializeGamesStoreManager();
        }
        ClientPrintMenuOptions.printOptionsClientGameMenu();
         setUpStoresMenu();
    }


    private void setUpStoresMenu()
    {
        ProtocolMenuOptions.StoreMenuOptions selectedOption = ProtocolMenuOptions.StoreMenuOptions.PRINT_STORE_MENU;

        boolean quit = false;

        String message = "";
        Game game = null;
        while (selectedOption != ProtocolMenuOptions.StoreMenuOptions.QUIT_STORE_MENU)
        {
            selectedOption = getValidateMenuOptionsEnum().validateStoreMenuOptions();
            Packet outgoingPacket = new Packet(selectedOption, message);
            Packet responsePacket = new Packet(ProtocolMenuOptions.ClientMainMenuOptions.NONE, null);

            switch (selectedOption)
            {
                case PRINT_STORE_MENU:
                    ClientPrintMenuOptions.printOptionsClientGameMenu();
                    break;

                case QUIT_STORE_MENU:
                    quit = true;
                    break;

                case DISPLAY_LIST_OF_GAMES:
                    displayListOfGames(outgoingPacket, responsePacket);
            }
        }
    }

    private void displayListOfGames(Packet outgoingPacket, Packet responsePacket) {
        super.outputPacket(outgoingPacket);
        super.responsePacket(responsePacket);
        List<Game> game = getGsonParser().fromJson(responsePacket.getPayload(), arrayListGames);
        displayListOfTrains(game);
    }


    private void displayListOfTrains(List<Game> games) {
        Collections.sort(games);
        getDisplayHeading().displayGameHeading();

        for (Game game : games)
        {
            game.displayGame();
        }
    }


    public void setGamesStoreManager(StoreManager storeManager)
    {
        this.storeManager = storeManager;
    }

    public StoreManager initializeGamesStoreManager()
    {
        Packet getStorePacket = new Packet(ProtocolMenuOptions.StoresGamesMenuOptions.DISPLAY_LIST_OF_STORES, "");
        Packet responseStorePacket = new Packet(ProtocolMenuOptions.ClientMainMenuOptions.NONE, null);

        super.outputPacket(getStorePacket);
        super.responsePacket(responseStorePacket);

        Type arrayListStoreType = new TypeToken<Set<Store>>() {}.getType();
        System.out.println(responseStorePacket.getPayload());
        Set<Store> stores = getGsonParser().fromJson(responseStorePacket.getPayload(), arrayListStoreType);

        getStorePacket.setMessageType(ProtocolMenuOptions.StoresGamesMenuOptions.DISPLAY_LIST_OF_GAMES);
        responseStorePacket.setPayload("");

        super.outputPacket(getStorePacket);
        super.responsePacket(responseStorePacket);

        ArrayList<Game> games = getGsonParser().fromJson(responseStorePacket.getPayload(), arrayListGames);


        StoreManager startStoreManager = new StoreManager(stores, games);
        return startStoreManager;
    }


}
