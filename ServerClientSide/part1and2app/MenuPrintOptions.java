package com.dkit.abielLopez.SDA.part1and2app;

import com.dkit.abielLopez.SDA.core.constants.Colours;


public class MenuPrintOptions
{
    public static void printOptionsMainMenu()
    {
        System.out.println(Colours.CYAN
                +"\n====================== MAIN MENU ======================\n\n"

                + "Available options:" + Colours.RESET);

        System.out.println("0 - to print all options \n" +
                "1 - Quit \n" +
                "2 - Enter  Menu \n" +
                "3 - Enter Data Transfer(DTO) Menu"
                + Colours.RESET);
    }

    public static void printOptionsCollectionsMenu()
    {
        System.out.println(Colours.CYAN
                +"\n====================== COLLECTIONS MENU ======================\n\n"

                + "Available options:" + Colours.RESET);

        System.out.println("0 - to print all options \n" +
                "1 - Quit \n" +
                "2 - Display List of Trains \n" +

                Colours.RESET);
    }

    public static void printOptionsDTOMenu()
    {
        System.out.println(Colours.CYAN
                +"\n====================== DTO MENU ======================\n\n"

                + "Available options:" + Colours.RESET);

        System.out.println("0 - to print all options \n" +
                "1 - Quit \n" +
                "2 - Display List of Games \n" +

                "3 - Display JSON String List of Games \n"
                + Colours.RESET);
    }

}
