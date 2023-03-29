package com.dkit.abielLopez.SDA.part1and2app;



import com.dkit.abielLopez.SDA.core.constants.Colours;
import com.dkit.abielLopez.SDA.enums.MainMenuOptions;
import com.dkit.abielLopez.SDA.validation.ValidationForEnumMenus;

import java.util.Scanner;

public class App
{
    private static ValidationForEnumMenus validateMainMenuOptionsEnum = new ValidationForEnumMenus();
    private static CollectionsMenu collectionsMenu = new CollectionsMenu();
    private static DTOMenu dtoMenu = new DTOMenu();

    public static void main( String[] args )
    {
        start();
    }

    private static void start()
    {
        welcomeScreen();
        setUpMainMenu();
    }


    private static void setUpMainMenu()
    {
        MainMenuOptions selectedOption = MainMenuOptions.PRINT_MAIN_MENU;


        boolean quit = false;

        while (selectedOption != MainMenuOptions.QUIT_MAIN_MENU)
        {
            MenuPrintOptions.printOptionsMainMenu();
            selectedOption = validateMainMenuOptionsEnum.validateMainMenuOptionsEnum();

            switch (selectedOption)
            {
                case PRINT_MAIN_MENU:
                    break;

                case QUIT_MAIN_MENU:
                    quit = true;
                    System.out.println(Colours.CYAN + "Shutting down ..........." + Colours.RESET);
                    break;

                case START_COLLECTIONS_MENU:
                    collectionsMenu.start();
                    break;

                case START_DTO_MENU:
                    dtoMenu.start();
                    break;
            }
        }

        System.exit(0);
    }

    public static void welcomeScreen()
    {
        System.out.print(Colours.GREEN + "\nWelcome to the Irish Railway System." +
                "\nPlease Press ENTER to start the application:" + Colours.RESET);
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();
    }

}
