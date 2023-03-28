package com.dkit.abielLopez.SDA.validation;

import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import com.dkit.abielLopez.SDA.core.constants.Colours;

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



}
