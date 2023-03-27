package com.dkit.abielLopez.SDA.client.clientmenus;

import com.dkit.abielLopez.SDA.core.Packet;
import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Menu
{

    private final PrintWriter output;
    private final Scanner input;
    private final Gson gsonParser = Converters.registerLocalDateTime(new GsonBuilder()).create();


    //Constructor
    public Menu(Scanner input, PrintWriter output)
    {
        this.input = input;
        this.output = output;
    }


    public void start()
    {
    }




    public PrintWriter getOutput()
    {
        return output;
    }

    public Scanner getInput()
    {
        return input;
    }

    public Gson getGsonParser()
    {
        return gsonParser;
    }

    public void outputPacket(Packet outgoingPacket)
    {
        getOutput().println(outgoingPacket.writeJSON());
        getOutput().flush();
    }

    public void responsePacket(Packet responsePacket)
    {
        responsePacket.readFromJSON(new JSONObject(getInput().nextLine()));
    }


}
