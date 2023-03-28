package com.dkit.abielLopez.SDA.server;

import com.dkit.abielLopez.SDA.client.clientmenus.StoreMenu;
import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import com.dkit.abielLopez.SDA.server.storemenuoptionscommands.FindAllGames;

public class CommandFactory {


    public CommandFactory()
    {

    }


    public Command createCommand(Enum command)
    {
        Command newCommand = null;

        if(command instanceof ProtocolMenuOptions.StoreMenuOptions)
        {
            if(command == ProtocolMenuOptions.StoreMenuOptions.DISPLAY_LIST_OF_GAMES)
            {
                newCommand = new FindAllGames();
            }
        }



        return newCommand;
    }


}
