package Objects;

import DTOs.Game;
import Exceptions.DaoException;
import JsonAdapter.MyCustomTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {


//    private Gson gsonParser;
    private static final  Gson gsonParser = new GsonBuilder().registerTypeAdapter(LocalDate.class, new MyCustomTypeAdapter()).create();


    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    private void start() throws IOException {

        Socket socket = new Socket("localhost", 8081);  // connect to server socket
        OutputStream os = socket.getOutputStream();
        PrintWriter socketWriter = new PrintWriter(os, true); ;   // true => auto flush buffers
        Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
        System.out.println("Client: Port# of this client : " + socket.getLocalPort());
        System.out.println("Client: Port# of Server :" + socket.getPort());

        System.out.println("Client message: The Client is running and has connected to the server");
        System.out.println("Please choose option from the main menu: ");

        final int DISPLAY_GAMES = 1;
        final int DELETE_GAME = 2;
        final int ADD_NEW_GAME = 3;
        final int EXIT = 4;

        Scanner input = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n********* MENU ***********");
            System.out.println("|   1.  DISPLAY            |");
            System.out.println("|   2.  DELETE             |");
            System.out.println("|   3.  ADD NEW GAME       |");
            System.out.println("|   4.  EXIT               |");
            System.out.println("****************************\n");

                    try {
                        String usersInput = input.nextLine();
                        option = Integer.parseInt(usersInput);


                        option = Integer.parseInt(usersInput);
                        String command;
                        Game game;
                        String response=null;
                        Game[] gameArray;

                        switch (option) {

                            case DISPLAY_GAMES:
                                System.out.println("********************************************************************************************************************************");
                                System.out.println("==================================================== DISPLAY ALL THE GAMES =====================================================");

                                socketWriter.println("DISPLAY_ALL_GAMES");

                                response = socketReader.nextLine();
                                System.out.println("Client message: Response from server: \"" + "JsonData:"+ "\"");
                                System.out.println(response);

                                gameArray = gsonParser.fromJson(response, Game[].class);
                                System.out.println("\nConvert and display the JsonData to objects :\n ");
                                Game.displayAllGames(gameArray);

                                break;

                            case DELETE_GAME: //
                                System.out.println("********************************************************************************************************************************");
                                System.out.println("====================================================== DELETE GAME BY ID =======================================================");
                                break;

                            case ADD_NEW_GAME:
                                System.out.println("********************************************************************************************************************************");
                                System.out.println("======================================================== INSERT NEW GAME =======================================================");

                                break;

                            case EXIT: //
                                System.out.println("********************************************************************************************************************************");
                                System.out.println("======================================================== EXIT =======================================================");

                                break;

                            default:
                                System.out.println("*** ERROR, ENTER A VALID OPTION ***");
                                break;
                        }
                    } catch (InputMismatchException | NumberFormatException e) {
                        //     System.out.print("*** ERROR, ENTER A VALID OPTION ***");
                    }

        } while (option != EXIT);







    }












}












