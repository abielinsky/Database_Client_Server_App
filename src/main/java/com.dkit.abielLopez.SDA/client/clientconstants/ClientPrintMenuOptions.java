package com.dkit.abielLopez.SDA.client.clientconstants;

import com.dkit.abielLopez.SDA.core.constants.Colours;

public class ClientPrintMenuOptions {

    public static void printOptionsMainMenu()
    {
        System.out.println(Colours.CYAN
                +"\n====================== MAIN MENU ======================\n\n"

                + "Available options:" + Colours.RESET);

        System.out.println("0 - to print all options \n" +
                "1 - Quit \n" +
                "2 - Enter Game Menu \n"

                + Colours.RESET);
    }

    public static void printOptionsClientGameMenu()
    {
        System.out.println(Colours.CYAN
                +"\n====================== FLEET MENU ======================\n\n"

                + "Available options:" + Colours.RESET);

        System.out.println("0 - to print all options \n" +
                "1 - Quit \n" +
                "2 - Display List of Games \n" +
                "3 - Display Games \n" +
                "4 - Display Games details by a Train ID \n" +
                "5 - Remove a Games \n" +
                "6 - Add a new Games  \n" +
                "7 - Display List of Games less than 4 rate \n"
                + Colours.RESET);
    }




}
