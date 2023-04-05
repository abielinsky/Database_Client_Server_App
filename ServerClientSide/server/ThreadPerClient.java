package com.dkit.abielLopez.SDA.server;

import com.dkit.abielLopez.SDA.core.Packet;
import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ThreadPerClient implements Runnable
{

    private Socket dataSocket;

    public ThreadPerClient(Socket dataSocket)
    {
        this.dataSocket = dataSocket;
    }

    @Override
    public void run()
    {
        // Build the input and output streams linked to the datasocket
        try
        {
            OutputStream out = dataSocket.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();

            //An example of the Decorator design pattern
            Scanner input = new Scanner(new InputStreamReader(in));

            Packet incomingPacket = new Packet(ProtocolMenuOptions.ClientMainMenuOptions.NONE,null);
            Packet response = null;

            System.out.println("error commiiiiinggggg ------------------");

            while (!incomingPacket.getMessageType().equals(ProtocolMenuOptions.ClientMainMenuOptions.QUIT_CLIENT_MENU))
            {

                //take information from the client
              //  System.out.println(input.nextLine());



                incomingPacket.readFromJSON(new JSONObject(input.nextLine()));
                System.out.println("Received message " + incomingPacket.getMessageType());

                CommandFactory factory = new CommandFactory();
                //Figure out what command was sent by the client
                Command command =factory.createCommand(incomingPacket.getMessageType());

                if(command != null)
                {
                    response = command.createResponse(incomingPacket);
                }

                //Send the response to the client
                if(response != null){
                    output.println(response.writeJSON());
                    output.flush();
                }
                else System.out.println("in run()  - response was null");

                //output.println(response.writeJSON());
                //output.flush();
            }
            dataSocket.close();
        }
        catch(NoSuchElementException nse)
        {
            System.out.println(nse.getMessage());
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }



}
