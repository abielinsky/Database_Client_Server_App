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
import java.util.regex.Pattern;

import static Objects.App.displayGame;

public class Client {

    private static final Pattern PATTERN = Pattern.compile("^[0-9]{1,}$");
    private static final Scanner kb = new Scanner(System.in);
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
        final int DISPLAY_GAME_BY_ID = 2;
        final int DELETE_GAME = 3;
        final int ADD_NEW_GAME = 4;
        final int EXIT = 5;

        Scanner input = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n********* MENU ***********");
            System.out.println("|   1.  DISPLAY            |");
            System.out.println("|   2.  DISPLAY by ID      |");
            System.out.println("|   3.  DELETE             |");
            System.out.println("|   4.  ADD NEW GAME       |");
            System.out.println("|   5.  EXIT               |");
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

                            case DISPLAY_GAME_BY_ID:
                                System.out.println("********************************************************************************************************************************");
                                System.out.println("==================================================== DISPLAY GAME BY ID =====================================================");

                                System.out.println("Please Enter GAME ID");
                                String id = kb.nextLine();

                                if (isIDExist(id)) {
                                    socketWriter.println("DISPLAY_GAME_BY_ID "+ id);
                                    response = socketReader.nextLine();
                                    if (response == null)
                                        System.out.println("Client message: There is and Error in response " );
                                    else {
                                        System.out.println("Client message: Response from server:\n Game: " + response);

                                        //todo, line shows the json data from server
                                        game = gsonParser.fromJson(response, Game.class);
                                        //todo, convert the json data to object


                                        System.out.println("\nConvert and display a game object by ID: "+id+" :\n ");
                                        displayGame(game);
                                        System.out.println("continue.....");
                                    }

                                } else
                                    System.out.println("\nClient message: The ID is not valid, please enter a valid ID\n");

                                break;


                            case DELETE_GAME: //
                                System.out.println("********************************************************************************************************************************");
                                System.out.println("====================================================== DELETE GAME BY ID =======================================================");

                                boolean ShowingData;
                                do{
                                    socketWriter.println("DISPLAY_ALL_GAMES");
                                    ShowingData = false;
                                    String response1 = socketReader.nextLine();
                                    System.out.println("Client message: Response from server: \"" + "JsonData:"+ "\"");
                                    System.out.println(response1);
                                    gameArray = gsonParser.fromJson(response1, Game[].class);
                                    System.out.println("\nConvert and display the JsonData to objects :\n ");
                                    Game.displayAllGames(gameArray);

                                }while(ShowingData == true);


                                System.out.println("\nPlease Enter GAME ID to delete from the list above");
                                id = kb.nextLine();
                                command = "DELETE_GAME_BY_ID";


                                if (isIDExist(id)) {
                                    command = command + " " + id;
                                    socketWriter.println(command);
                                    response = socketReader.nextLine();
                                    System.out.println("Client message: Deleting a game by the ID...");
                                    System.out.println("Client message: " + response);
                                } else
                                    System.out.println("\nINVALID ID, please enter a valid ID\n");
                                break;


                            case ADD_NEW_GAME:
                                System.out.println("********************************************************************************************************************************");
                                System.out.println("======================================================== INSERT NEW GAME =======================================================");

                                System.out.println("\nClient message: Please enter the game details: ");

                                game = gameFields();

                                if(game !=null){
                                    System.out.println("Adding a new game to the data base..");
                                    String gameJson = gsonParser.toJson(game);

                                    socketWriter.println("ADD_NEW_GAME;" + gameJson);
                                    response = socketReader.nextLine();
                                    System.out.println("Client message: " + response);

                                }else{
                                    System.out.println("\nClient message: Invalid GAME fields");
                                }



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

    private Game gameFields() {

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
                System.out.println("GAme must be an integer. Please enter a valid number:");
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
                System.out.println("Game must be an integer. Please enter a valid number:");
            }
        }

        return new Game(title_Game, genre_Game, releaseYear_Game, publisher_Game, price_Game, rate_Game);

    }


    private boolean isIDExist(String id) {
        return PATTERN.matcher(id).find();
    }


}












