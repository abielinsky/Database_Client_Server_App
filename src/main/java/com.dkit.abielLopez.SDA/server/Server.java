package com.dkit.abielLopez.SDA.server;

import com.dkit.abielLopez.SDA.core.ServiceDetails;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;


public class Server {

    public static void main(String[] args)
    {
        try
        {
            //Set up a connection sockets that listens for connections
            ServerSocket listeningSocket = new ServerSocket(ServiceDetails.SERVER_PORT);

            boolean continueRunning = true;
            int countRequests = 0;

            while(continueRunning)
            {
                //Wait for an incoming connection and build the communications link
                System.out.println("Waiting for connections...");
                Socket dataSocket = listeningSocket.accept();

                //Create and start a new thread per client
                ThreadPerClient runnable = new ThreadPerClient(dataSocket);
                Thread clientThread = new Thread(runnable);
                clientThread.start();
            }
            listeningSocket.close();
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
