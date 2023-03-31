package com.dkit.abielLopez.SDA.core;

import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import org.json.JSONObject;

public class Packet {

    private Enum messageType;

    private String payload;


    public Packet(Enum messageType, String payload)
    {
        if(messageType instanceof ProtocolMenuOptions.ClientMainMenuOptions)
        {
            this.messageType = messageType;
        }

        else if(messageType instanceof ProtocolMenuOptions.StoresGamesMenuOptions)
        {
            this.messageType = messageType;
            System.out.println("Hello");
        }

//        else if(messageType instanceof StationMenuOptions)
//        {
//            this.messageType = messageType;
//        }
//        else if(messageType instanceof ScheduleMenuOptions)
//        {
//            this.messageType = messageType;
//        }
//
//        else if(messageType instanceof JourneyRouteMenuOptions)
//        {
//            this.messageType = messageType;
//        }

        this.payload = payload;
    }


    public JSONObject writeJSON()
    {
        JSONObject jo = new JSONObject();
        jo.put("messageType", this.messageType);
        jo.put("payload", this.payload);
        return jo;
    }


    public void readFromJSON(JSONObject jo)
    {

        if
        (
                jo.get("messageType").toString().equals("PRINT_STORE_MAIN_MENU") ||
                jo.get("messageType").toString().equals("PRINT_GAME_MAIN_MENU") ||
                jo.get("messageType").toString().equals("QUIT_STORE_MENU") ||
                jo.get("messageType").toString().equals("QUIT_GAME_MENU") ||
                jo.get("messageType").toString().equals("NONE")||

        jo.get("messageType").toString().equals("DISPLAY_LIST_OF_STORES")||
        jo.get("messageType").toString().equals("DISPLAY_LIST_OF_GAMES")


        )
        {
            this.setMessageType(ProtocolMenuOptions.StoresGamesMenuOptions.valueOf(jo.get("messageType").toString()));
        }

        this.setPayload(jo.get("payload").toString());
    }



    public void setPayload(String payload)
    {
        this.payload = payload;
    }

    public String getPayload()
    {
        return payload;
    }

    public Enum getMessageType()
    {
        return messageType;
    }


    public void setMessageType(Enum messageType)
    {
        if(messageType instanceof ProtocolMenuOptions.ClientMainMenuOptions)
        {
            this.messageType = messageType;
        }

        else if(messageType instanceof ProtocolMenuOptions.StoresGamesMenuOptions)
        {
            this.messageType = messageType;
        }

        else if(messageType instanceof ProtocolMenuOptions.StoreMenuOptions)
        {
            this.messageType = messageType;
        }

    }



}
