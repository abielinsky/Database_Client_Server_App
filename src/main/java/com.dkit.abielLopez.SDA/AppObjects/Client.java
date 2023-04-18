package com.dkit.abielLopez.SDA.AppObjects;

import com.dkit.abielLopez.SDA.dao.Adapter;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.dkit.abielLopez.SDA.AppObjects.App.IGameDao;

//import static com.dkit.abielLopez.SDA.AppObjects.App.*;


public class Client {



    private static final Scanner kb = new Scanner(System.in);
    private static final Pattern PATTERN = Pattern.compile("^[0-9]{1,}$");
    private static final  Gson gsonParser = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new Adapter())
            .create();


    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
        
    }


    public void start() throws IOException{
        
        System.out.println("\n+++++++++ Game Simulator Store ++++++++");
        System.out.println("=========================================");


       
        try {
            displayMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }


        
        

    }

    private static void displayMenu() throws IOException {



        final int DISPLAY_OPTION = 1;
        final int DELETE_OPTION = 2;
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
                switch (option) {

                    case DISPLAY_OPTION: //Checking data farm
                        System.out.println("");
                        DisplayMenuOption();


                        break;

                    case DELETE_OPTION: //
                        System.out.println(" xxx ");
                        break;

                    case ADD_NEW_GAME:
                        System.out.println(" ** ");
                        IGameDao.insertNewGame(insertNewGame());


                        break;

                    case EXIT: //

                        break;

                    default:
                        System.out.println("*** ERROR, ENTER A VALID OPTION ***");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                //     System.out.print("*** ERROR, ENTER A VALID OPTION ***");
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }

        } while (option != EXIT);
    }


    private static void DisplayMenuOption() throws IOException {

        Socket socket = new Socket("localhost", 8081);  // connect to server socket
        OutputStream os = socket.getOutputStream();
        PrintWriter socketWriter = new PrintWriter(os, true); ;   // true => auto flush buffers
        Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
        System.out.println("Client: Port# of this client : " + socket.getLocalPort());
        System.out.println("Client: Port# of Server :" + socket.getPort());

        System.out.println("Client message: The Client is running and has connected to the server");
        System.out.println("Please choose option from the main menu: ");


        final int DISPLAY_ALL_GAMES = 1;
        final int DISPLAY_GAME_BY_ID = 2;
        final int DISPLAY_GAME_BY_NAME = 3;
        final int DISPLAY_GAME_BY_GENRE = 4;
        final int DISPLAY_GAME_BY_RELEASE_YEAR = 5;
        final int DISPLAY_GAME_BY_PUBLISHER = 6;
        final int DISPLAY_GAME_BY_PRICE = 7;
        final int DISPLAY_GAME_BY_RATING = 8;
        final int EXIT = 9;

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n************ MENU DISPLAY OPTIONS ***********");
            System.out.println("|   1.  DISPLAY ALL GAMES                     |");
            System.out.println("|   2.                                        |");
            System.out.println("|   3.                                        |");
            System.out.println("|   9.  EXIT                                  |");
            System.out.println("***********************************************\n");


            try {
                String usersInput = input.nextLine();
                option = Integer.parseInt(usersInput);

                String response = null;
                Game[] gameArray;

                switch (option) {
                    case DISPLAY_ALL_GAMES: //Checking data games

                        //this is working

//                        System.out.println(" ** Display All Games **");
//                        List<Game> gamesAll = IGameDao.findAllGames();
//                        System.out.println(gamesAll + "\n");



                        System.out.println(" ************************** Display All Games ************************** ");


                        socketWriter.println("DISPLAY_ALL_GAMES");
                        System.out.println("you are here");

                        response = socketReader.nextLine();
                        System.out.println("Client message: Response from server: \"" + "JsonData:"+ "\"");
                        System.out.println(response);

                        gameArray = gsonParser.fromJson(response, Game[].class);
                        System.out.println("\nConvert and display the JsonData to objects :\n ");
                        Game.displayAllGAmes(gameArray);




                        break;


                    case EXIT: //
                        System.out.println(" bye bye bye");
                        break;

                    default:
                        System.out.println("Enter a right option");
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                //     System.out.print("*** ERROR, ENTER A VALID OPTION ***");
            }

        } while (option != EXIT);

    }



    private static Game insertNewGame() {
        Scanner kb = new Scanner(System.in);

        String title_Game = "";
        boolean valid_Value = false;

        while (!valid_Value) {
            System.out.println("Enter the title name: ");
            title_Game = kb.nextLine();

            if (title_Game.isEmpty()) {
                System.out.println("Name is empty, try again");
            }
            else if (title_Game.matches(".*\\d.*")){
                System.out.println("Invalid title name, try again");
            }
            else
            {
                valid_Value = true;
            }
        }


        String genre_Game = "";
        boolean valid_genre_Game = false;

        while (!valid_genre_Game) {
            System.out.println("Enter the genre name: ");
            genre_Game = kb.nextLine();

            if (genre_Game.isEmpty()) {
                System.out.println("Name is empty, try again");
            }
            else if (genre_Game.matches(".*\\d.*")){
                System.out.println("Invalid genre name, try again");
            }
            else
            {
                valid_genre_Game = true;
            }
        }

        int releaseYear_Game = 0;
        valid_Value = false;

        while (!valid_Value) {
            System.out.println("Enter the release year: ");
            try {
                releaseYear_Game = Integer.parseInt(kb.nextLine());
                valid_Value = true;
            } catch (NumberFormatException e) {
                System.out.println("Team wins must be an integer. Please enter a valid number:");
            }
        }

        String publisher_Game = " ";
        boolean valid_publisher_Game = false;

        while (!valid_publisher_Game) {
            System.out.println("Enter the publisher_Game name Company: ");
            publisher_Game = kb.nextLine();

            if (publisher_Game.isEmpty()) {
                System.out.println("Name is empty, try again");
            }
            else if (publisher_Game.matches(".*\\d.*")){
                System.out.println("Invalid genre name, try again");
            }
            else
            {
                valid_publisher_Game = true;
            }
        }

        double price_Game = 0;
        valid_Value = false;

        while (!valid_Value) {
            System.out.println("Enter the price: ");
            try {
                price_Game = Double.parseDouble(kb.nextLine());
                valid_Value = true;
            } catch (NumberFormatException e) {
                System.out.println("Price must be a valid number. Please enter a valid price:");
                valid_Value = false;
            }
        }

        int rate_Game = 0;
        valid_Value = false;

        while (!valid_Value) {
            System.out.println("Enter the rate: ");
            try {
                rate_Game = Integer.parseInt(kb.nextLine());
                valid_Value = true;
            } catch (NumberFormatException e) {
                System.out.println("Team wins must be an integer. Please enter a valid number:");
            }
        }


        Game game = new Game( title_Game, genre_Game, releaseYear_Game, publisher_Game, price_Game, rate_Game);
        System.out.println("Game Created: " + game.toString());
        return game;

    }








}



















