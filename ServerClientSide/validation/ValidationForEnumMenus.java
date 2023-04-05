package com.dkit.abielLopez.SDA.validation;

import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import com.dkit.abielLopez.SDA.core.constants.Colours;
import com.dkit.abielLopez.SDA.enums.CollectionsMenuOptions;
import com.dkit.abielLopez.SDA.enums.DTOMenuOptions;
import com.dkit.abielLopez.SDA.enums.MainMenuOptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidationForEnumMenus {

    private Scanner keyboard = new Scanner(System.in);

    private String menuOptionAction = Colours.CYAN_BRIGHT + "\nEnter action: (0 to show available options) > " + Colours.RESET;



    public void printMenuOptionAction()
    {
        System.out.print(menuOptionAction);
    }

    public ProtocolMenuOptions.ClientMainMenuOptions validateClientMenuOptionsEnum()
    {
        ProtocolMenuOptions.ClientMainMenuOptions selectedOption = null;
        boolean validClientMenuOption = false;
        while (!validClientMenuOption)
        {
            try
            {
                printMenuOptionAction();
                selectedOption = ProtocolMenuOptions.ClientMainMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];
                validClientMenuOption = true;
            }
            catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e)
            {
                System.out.print(Colours.RED + "Invalid input. Number inputted must be between 0 - 6.\n" + Colours.RESET);
            }

        }

        return selectedOption;
    }





    public ProtocolMenuOptions.StoreMenuOptions validateStoreMenuOptions()
    {
        ProtocolMenuOptions.StoreMenuOptions selectedOption = null;
        boolean validFleetMenuOption = false;
        while (!validFleetMenuOption)
        {
            try
            {
                printMenuOptionAction();
                selectedOption = ProtocolMenuOptions.StoreMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];
                validFleetMenuOption = true;
            }
            catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e)
            {
                System.out.print(Colours.RED + "Invalid input. Number inputted must be between 0 - 9.\n" + Colours.RESET);
            }

        }

        return selectedOption;
    }




    public MainMenuOptions validateMainMenuOptionsEnum()
    {
        MainMenuOptions selectedOption = null;
        boolean validMainMenuOption = false;
        while (!validMainMenuOption)
        {
            try
            {
                printMenuOptionAction();
                selectedOption = MainMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];
                validMainMenuOption = true;
            }
            catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e)
            {
                System.out.print(Colours.RED + "Invalid input. Number inputted must be between " +
                        "0 - 3.\n" + Colours.RESET);
            }

        }

        return selectedOption;
    }



    public CollectionsMenuOptions validateCollectionsMenuOptionsEnum()
    {
        CollectionsMenuOptions selectedOption = null;
        boolean validCollectionsMenuOption = false;
        while (!validCollectionsMenuOption)
        {
            try
            {
                printMenuOptionAction();
                selectedOption = CollectionsMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];
                validCollectionsMenuOption = true;
            }
            catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e)
            {
                System.out.print(Colours.RED + "Invalid input. Number inputted must be between 0 - 8.\n" + Colours.RESET);
            }

        }

        return selectedOption;
    }



    public DTOMenuOptions validateDTOMenuOptionsEnum()
    {
        DTOMenuOptions selectedOption = null;
        boolean validDtoMenuOption = false;
        while (!validDtoMenuOption)
        {
            try
            {
                printMenuOptionAction();
                selectedOption = DTOMenuOptions.values()[Integer.parseInt(keyboard.nextLine().trim())];
                validDtoMenuOption = true;
            }
            catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e)
            {
                System.out.print(Colours.RED + "Invalid input. Number inputted must be between 0 - 3.\n" + Colours.RESET);
            }

        }

        return selectedOption;
    }


}
