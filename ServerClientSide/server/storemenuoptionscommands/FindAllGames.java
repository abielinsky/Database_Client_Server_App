package com.dkit.abielLopez.SDA.server.storemenuoptionscommands;

import com.dkit.abielLopez.SDA.core.Packet;
import com.dkit.abielLopez.SDA.core.constants.Colours;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.dkit.abielLopez.SDA.server.Command;

public class FindAllGames implements Command {

    @Override
    public Packet createResponse(Packet incomingPacket)
    {

        String jsonStringTrains = "";
        try
        {
            jsonStringTrains = gameDAO.findAllGamesJson();

        }
        catch (DaoException de)
        {
            System.out.println(Colours.RED + de.getMessage() + Colours.RESET);
        }

        return new Packet(incomingPacket.getMessageType(),jsonStringTrains);
    }


}
