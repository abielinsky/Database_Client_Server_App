package com.dkit.abielLopez.SDA.part1and2app;

import com.dkit.abielLopez.SDA.core.constants.Colours;
import com.dkit.abielLopez.SDA.dao.gamedao.GameDaoInterface;
import com.dkit.abielLopez.SDA.dao.gamedao.MySqlGameDao;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.dto.StoreManager;
import com.dkit.abielLopez.SDA.enums.DTOMenuOptions;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.dkit.abielLopez.SDA.validation.ValidationForEnumMenus;
import com.google.gson.Gson;

import java.util.List;

public class DTOMenu
{
    private List<Game> games;
    private GameDaoInterface gameDAO = new MySqlGameDao();
    private ValidationForEnumMenus validateDTOMenuOptionsEnum = new ValidationForEnumMenus();

    private StoreManager fleetManager = new StoreManager();
    private Gson gsonParser = new Gson();


    private CollectionsMenu collectionsMenu = new CollectionsMenu();


    public void start()
    {
        initializeListOfGames();
        MenuPrintOptions.printOptionsDTOMenu();
        setUpDTOMenu();
    }



    private void setUpDTOMenu()
    {
        DTOMenuOptions selectedOption = DTOMenuOptions.PRINT_DTO_MENU;

        boolean quit = false;

        while (selectedOption != DTOMenuOptions.QUIT_DTO_MENU)
        {
            selectedOption = validateDTOMenuOptionsEnum.validateDTOMenuOptionsEnum();

            switch (selectedOption)
            {
                case PRINT_DTO_MENU:
                    MenuPrintOptions.printOptionsDTOMenu();
                    break;

                case QUIT_DTO_MENU:
                    quit = true;
                    break;

                case DISPLAY_LIST_OF_GAMES:
                    displayListOfGames();
                    break;


            }

        }
    }




    private void initializeListOfGames()
    {
        try
        {
            games = gameDAO.findAllGames();
        }
        catch (DaoException de)
        {
            System.out.println(Colours.RED + de.getMessage() + Colours.RESET);
        }

    }

    private void displayListOfGames()
    {

        collectionsMenu.displayGameHeading();

        for (Game game : games)
        {
            game.displayGame();
        }
    }

    private void displayJsonStringListOfGames()
    {
        try
        {
            String gameJsonString = gameDAO.findAllGamesJson();

            if(gameJsonString.equals("null"))
            {
                System.out.println(Colours.RED  + "GAme ID does not exist. Please go to the Menu option to Display Games " + "\n"
                        + "and find one that is available." + Colours.RESET);
            }
            else
            {
                String[] gameJsonStringArray = gameJsonString.split("(?=[{])");

                for (String game : gameJsonStringArray)
                {
                    System.out.println(Colours.DARK_BLUE + game + Colours.RESET);
                }

            }

        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }


























}
