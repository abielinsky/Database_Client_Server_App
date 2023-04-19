package Objects;

import DAOs.GameDAOInterface;
import DAOs.MySqlGameDAO;
import Exceptions.DaoException;
import JsonAdapter.MyCustomTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class Server {

    private static final Gson gsonParser = new GsonBuilder().registerTypeAdapter(LocalDate.class, new MyCustomTypeAdapter()).create();




    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    private void start() {

        GameDAOInterface IGameDao = new MySqlGameDAO();
        try {

            ServerSocket ss = new ServerSocket(8081);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

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
        } catch (IOException e) {
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
        GameDAOInterface IGameDao;

        public ClientHandler(Socket clientSocket, int clientNumber, GameDAOInterface IGameDao) {
            this.IGameDao = IGameDao;
            try {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            String[] tokens ;




            try {
                while ((message = socketReader.readLine()) != null) {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                          if (message.startsWith("DISPLAY_ALL_GAMES")) {

                                String allGames = IGameDao.AllGamesJSONServer();

                                System.out.println("Return JSON string of the Games list");
                                socketWriter.println(allGames);

                          }

                          else
                          {
                                socketWriter.println("Invalid request :(");
                          }
                }

                socket.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (DaoException e) {
                e.printStackTrace();
            }



            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }



    }










}
