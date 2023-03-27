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

}
