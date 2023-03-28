package com.dkit.abielLopez.SDA.client.clientconstants;


import com.dkit.abielLopez.SDA.core.constants.Colours;

public class DisplayHeadingsForEntities {


    public void displayGameHeading() {
        System.out.print(Colours.LIGHT_BLUE + ("-").repeat(98) + "\n");
        System.out.format(Colours.DARK_BLUE + "| Train ID\t\t| Fleet ID\t\t| Carriages\t\t| Capacity\t\t| Model\t\t\t\t\t\t\t |%n");
        System.out.print(Colours.LIGHT_BLUE + ("-").repeat(98) + "\n");
    }


}
