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
import java.util.*;

public class StoreMenu extends Menu
{

    private GamesStoreManager gamesStoreManager;


    Type arrayListGames = new TypeToken<ArrayList<Game>>(){}.getType();

    public StoreMenu(Scanner input, PrintWriter output)
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





    private void setUpFleetMenu()
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
                    break;

//                case DISPLAY_LIST_OF_FLEETS:
//                    fleetManager.displayListOfFleets();
//                    break;
//
//                case DISPLAY_TREEMAP_FLEETS:
//                    fleetManager.displayFleet();
//                    break;
//
//                case DISPLAY_TRAIN_BY_TRAIN_ID:
//                    train = searchTrain(outgoingPacket,responsePacket);
//                    displayTrainByTrainID(train);
//                    break;
//
//                case REMOVE_TRAIN:
//                    train = searchTrain(outgoingPacket,responsePacket);
//                    removeTrain(train, outgoingPacket,responsePacket);
//                    break;
//
//                case ADD_NEW_TRAIN:
//                    String newTrainJson = addNewTrain();
//
//                    if(newTrainJson != null)
//                    {
//                        outgoingPacket.setPayload(newTrainJson);
//                        super.outputPacket(outgoingPacket);
//                        super.responsePacket(responsePacket);
//
//                        train = getGsonParser().fromJson(responsePacket.getPayload(), Train.class);
//                        displayNewAddedTrain(train);
//                    }
//
//                    break;
//
//                case DISPLAY_LIST_OF_TRAINS_LESS_THAN_CAPACITY_LIMIT:
//                    displayListOfTrainsLessThanCapacity(outgoingPacket,responsePacket);
//                    break;

//                case DISPLAY_AVERAGE_SEAT_CAPACITY_FOR_ALL_TRAINS_BELONGING_TO_A_FLEET:
//                    displayAverageSeatCapacityForAllTrainsBelongingToAFleet(outgoingPacket,responsePacket);
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
