package com.dkit.abielLopez.SDA.server;

import com.dkit.abielLopez.SDA.core.Packet;
import com.dkit.abielLopez.SDA.dao.gamedao.GameDaoInterface;
import com.dkit.abielLopez.SDA.dao.gamedao.MySqlGameDao;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface Command {

    public Packet createResponse(Packet incomingPacket);


    GameDaoInterface gameDAO = new MySqlGameDao();



    Gson gsonParser = Converters.registerLocalDateTime(new GsonBuilder()).create();


}
