package com.dkit.abielLopez.SDA.AppObjects;

import com.dkit.abielLopez.SDA.dao.GameDaoInterface;
import com.dkit.abielLopez.SDA.dao.MySqlGameDao;
import com.dkit.abielLopez.SDA.exceptions.DaoException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {



    public static void main(String[] args)
    {
        Server server = new Server();
        server.start();
    }

    private void start() {

        GameDaoInterface IGameDao = new MySqlGameDao();

        try
        {
            ServerSocket ss = new ServerSocket(8081);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8081...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

//                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
//                t.start();                                                  // and run it in its own thread

                Thread t1 = new Thread(new ClientHandler(socket, clientNumber, IGameDao)); // create a new ClientHandler for the client,
                t1.start();                                                  // and run it in its own thread


                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");

    }


    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        GameDaoInterface IGameDao;

        public ClientHandler(Socket clientSocket, int clientNumber, GameDaoInterface IGameDao)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        @Override
        public void run()
        {
            String message;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

//                    if (message.startsWith("Time"))
//                    {
//                        LocalTime time =  LocalTime.now();
//                        socketWriter.println(time);  // sends current time to client
//                    }
//                    else if (message.startsWith("Echo"))
//                    {
//                        message = message.substring(5); // strip off the 'Echo ' part
//                        socketWriter.println(message);  // send message to client
//                    }
//                    else
//                    {
//                        socketWriter.println("I'm sorry I don't understand :(");
//                    }


                    if (message.startsWith("DISPLAY_ALL_GAMES")) {

                        String allGames = IGameDao.findAllGamesJSONServer();
                        System.out.println("Return JSON string of the Games list");
                        socketWriter.println(allGames);

                    }







                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }





}
