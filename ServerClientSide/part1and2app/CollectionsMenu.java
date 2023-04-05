package com.dkit.abielLopez.SDA.part1and2app;

import com.dkit.abielLopez.SDA.core.constants.Colours;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.enums.CollectionsMenuOptions;
import com.dkit.abielLopez.SDA.validation.ValidationForEnumMenus;

import java.util.*;

public class CollectionsMenu
{
//    private UserValidation userValidation = new UserValidation();
    private ValidationForEnumMenus validateCollectionsMenuOptionsEnum = new ValidationForEnumMenus();
    private List<Game> games = new ArrayList<>();

    private Map<String, Game> gameIDGameTreeMap = new TreeMap<>();

    private Queue<Game> gameQueue = new PriorityQueue<>();

    public CollectionsMenu()
    {
    }

    public void start()
    {
        initializeListOfGames();
        MenuPrintOptions.printOptionsCollectionsMenu();
        setUpCollectionsMenu();
    }






    private void setUpCollectionsMenu()
    {
        CollectionsMenuOptions selectedOption = CollectionsMenuOptions.PRINT_COLLECTIONS_MENU;

        boolean quit = false;

        while (selectedOption != CollectionsMenuOptions.QUIT_COLLECTIONS_MENU)
        {
            selectedOption = validateCollectionsMenuOptionsEnum.validateCollectionsMenuOptionsEnum();

            switch (selectedOption)
            {
                case PRINT_COLLECTIONS_MENU:
                    MenuPrintOptions.printOptionsCollectionsMenu();
                    break;

                case QUIT_COLLECTIONS_MENU:
                    quit = true;
                    break;

                case DISPLAY_LIST_OF_GAMES:
                    displayListOfGames();
                    break;


            }
        }

    }






    public void displayGameHeading()
    {
        System.out.print(Colours.LIGHT_BLUE + ("-").repeat(98) + "\n");
        System.out.format(Colours.DARK_BLUE + "| game ID\t\t| store ID\t\t| Carriages\t\t| Capacity\t\t| Model\t\t\t\t\t\t\t |%n");
        System.out.print(Colours.LIGHT_BLUE + ("-").repeat(98) + "\n");

    }



    private void displayListOfGames()
    {
        displayGameHeading();
        for(Game game : games)
        {
            game.displayGame();
        }
    }

    public void initializeListOfGames()
    {
        games.add(new Game(1, 1,"Mario", "-Adventure", 2000, "Nintendo", 59.99, 100 ));
        games.add(new Game(2, 1, "Legend of Zelda", "-Adventure", 1998, "Nintendo", 49.99, 95));

    }


}
