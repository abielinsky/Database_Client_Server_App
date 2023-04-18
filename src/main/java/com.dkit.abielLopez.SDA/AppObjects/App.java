package com.dkit.abielLopez.SDA.AppObjects;

import com.dkit.abielLopez.SDA.dao.GameDaoInterface;
import com.dkit.abielLopez.SDA.dao.MySqlGameDao;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.exceptions.DaoException;
import com.dkit.abielLopez.SDA.exceptions.Query;

import java.io.IOException;
import java.util.*;

public class App {

    private static Scanner input = new Scanner(System.in);
    private static final Scanner kb = new Scanner(System.in);
    private static Scanner scanner = new Scanner(System.in);
//    public static final GameDaoInterface IGameDao = new MySqlGameDao();

    private static ArrayList<Game> gamesList = new ArrayList<>();

    private static List<Game> games;
    static GameDaoInterface IGameDao = new MySqlGameDao();



    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("\n******** Game Simulator Store *********");
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
                        System.out.println("************************* DELETE A GAME ************************");
                        deleteOptions();
                        break;

                    case ADD_NEW_GAME:
                        System.out.println("************************* ADD NEW GAME *************************");
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

    private static void deleteOptions() {

        final int DELETE_GAME_BY_ID = 1;
        final int DELETE_GAME_BY_NAME = 2;
        final int DELETE_GAME_BY_GENRE = 3;
        final int DELETE_GAME_BY_RELEASE_YEAR = 4;
        final int DELETE_GAME_BY_PUBLISHER = 5;
        final int DELETE_GAME_BY_PRICE = 6;
        final int DELETE_GAME_BY_RATING = 7;
        final int EXIT = 8;

        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n************ MENU DELETE OPTIONS ***********");
            System.out.println("|   1.  DELETE GAME BY ID                     |");
            System.out.println("|   2.  DELETE GAME BY NAME                   |");
            System.out.println("|   3.  DELETE GAME BY GENRE                  |");
            System.out.println("|   4.  DELETE GAME BY RELEASE YEAR           |");
            System.out.println("|   5.  DELETE GAME BY PUBLISHER              |");
            System.out.println("|   6.  DELETE GAME BY PRICE                  |");
            System.out.println("|   7.  DELETE GAME BY RATING                 |");
            System.out.println("|   8.  EXIT                                  |");
            System.out.println("**********************************************\n");

            try {
                String usersInput = input.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {

                    case DELETE_GAME_BY_ID: //Checking data farm
                        System.out.println("*************************** DELETE GAME BY ID ***************************");
                      //  deleteGameByIdDB();


                        System.out.println("Deleting Game By ID");
                        System.out.println("Enter Game ID:");
                        int id_Game = kb.nextInt();
                        Query query = new Query("SELECT * FROM game WHERE id_Game = ?", Integer.toString(id_Game));

                        Game game = IGameDao.findGameById(query);
                        System.out.println("Are you sure that you want to delete " + game.getTitle_Game() + "?");
                        if (kb.nextLine().equalsIgnoreCase("yes"))
                        {
                            IGameDao.deleteGameById(id_Game);
                            System.out.println(game + " has been deleted");
                            break;
                        }






                        break;

                    case DELETE_GAME_BY_NAME: //
                        System.out.println("");
                        break;

                    case DELETE_GAME_BY_GENRE:
                        System.out.println("");
                        break;

                    case DELETE_GAME_BY_RELEASE_YEAR:
                        System.out.println("");
                        break;

                    case DELETE_GAME_BY_PUBLISHER:
                        System.out.println("");
                        break;

                    case DELETE_GAME_BY_PRICE:
                        System.out.println("");
                        break;

                    case DELETE_GAME_BY_RATING:
                        System.out.println("");
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

    private static void deleteGameByIdDB() throws DaoException
    {
        games = IGameDao.findAllGames();

        if (games.isEmpty())
            System.out.println("There are no GAmes in the list");
        else {

            System.out.println("\n -------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf(" %s %-5s %-20s %-15s %-20s %-25s %-10s %-35s %s","|", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","|");
            System.out.println("\n -------------------------------------------------------------------------------------------------------------------------------");

            for (Game singer : games)
                System.out.println(singer.displayAllGAmes());
            System.out.println("---------------------------------------------------------------------------");

        }

        input = new Scanner(System.in);
        boolean isID1 = false;
        do {
            try {
                System.out.println("Please choose singer ID to be deleted: ");
                int id = input.nextInt();
                isID1 = true;

                Game deleteGameID = (Game) IGameDao.findGameById(id);
                if (deleteGameID == null) {
                    System.out.println("There are no GAmes with id " + id);
                } else {
                    System.out.println("\nAre you sure you want to delete game: ");
                    System.out.println("\n -------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf(" %s %-5s %-20s %-15s %-20s %-25s %-10s %-35s %s","|", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","|");
                    System.out.println("\n -------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(deleteGameID.displayAllGAmes());
                    System.out.println("---------------------------------------------------------------------------");

                    //System.out.println("Please choose 1 for YES or 2 for NO");

                    final int YES = 1;
                    final int NO = 2;
                    int chose = 0;
                    boolean isNum = false;
                    do {

                        try {
                            String inputID = input.nextLine();
                            chose = Integer.parseInt(inputID);
                            isNum = true;

                            switch (chose) {

                                case YES:
                                    IGameDao.deleteGameById(id);
                                    System.out.println("GAme deleted!!!");
                                    break;
                                case NO:
                                    System.out.println("Game is NOT deleted!!!");

                                    break;
                                default:
                                    System.out.print("Invalid option - please enter number in range");
                                    break;
                            }
                            // keyboard.nextLine();
                        } catch (InputMismatchException | NumberFormatException e) {
                            // sc1.nextLine();
                            System.out.println("Please enter a number 1 for YES or 2 for NO!!!");
                        }
                    } while (isNum != true);
                }

            } catch (InputMismatchException e) {
                // sc1.nextLine();
                System.out.println("Please enter a number for ID!!!");
            }
        } while ((isID1 != true));
        System.out.println("Press Enter to continue...");



    }


    private static void DisplayMenuOption() {

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
                switch (option) {
                    case DISPLAY_ALL_GAMES: //Checking data games
                        System.out.println(" ********************** Display All Games ********************** ");
                        List<Game> gamesAll = IGameDao.findAllGames();
                        System.out.println(gamesAll + "\n");


                        ///todo, display all games is not printing the games trough the method displayAllGames
                        displayAllGames(gamesList);

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
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }

        } while (option != EXIT);

    }

    private static void displayAllGames(ArrayList<Game> gamesList) {

        System.out.println("\n -------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf(" %s %-5s %-20s %-15s %-20s %-25s %-10s %-35s %s","|", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","|");
        System.out.println("\n -------------------------------------------------------------------------------------------------------------------------------");
        for (Game game : gamesList) {

            System.out.printf("%s %-5s %-20s %-15s %-20s %-25s %-10s %-35s %s\n","|", game.getGame_ID(), game.getTitle_Game(), game.getGenre_Game(),
                    game.getRelease_year_Game(), game.getPublisher_Game(), game.getPrice_Game(), game.getRate_Game(), "|");
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");


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


        Game game = new Game( title_Game, genre_Game, releaseYear_Game, publisher_Game, price_Game, rate_Game);
        System.out.println("Game Created: " + game.toString());
        return game;

    }




}

