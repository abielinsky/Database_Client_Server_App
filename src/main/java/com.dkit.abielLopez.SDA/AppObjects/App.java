package com.dkit.abielLopez.SDA.AppObjects;

import com.dkit.abielLopez.SDA.dao.GameDaoInterface;
import com.dkit.abielLopez.SDA.dao.MySqlGameDao;
import com.dkit.abielLopez.SDA.dto.Game;
import com.dkit.abielLopez.SDA.exceptions.DaoException;

import java.io.IOException;
import java.util.*;

public class App {

    private static final Scanner kb = new Scanner(System.in);
    private static Scanner scanner = new Scanner(System.in);
    private static final GameDaoInterface IGameDao = new MySqlGameDao();

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("\n+++++++++ Game Simulator Store ++++++++");
        System.out.println("=========================================");
        try {
            displayMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayMenu() throws IOException
    {
        final int DISPLAY_OPTION = 1;
        final int DELETE_OPTION = 2;
        final int ADD_NEW_GAME = 3;
        final int EXIT = 4;

        Scanner input = new Scanner(System.in);
        int option = 0 ;
        do {
            System.out.println("\n********* MENU ***********");
            System.out.println("|   1.  DISPLAY            |");
            System.out.println("|   2.  DELETE             |");
            System.out.println("|   3.  ADD NEW GAME       |");
            System.out.println("|   4.  EXIT               |");
            System.out.println("****************************\n");


            try
            {
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
                        insertNewGame();
                        break;

                    case EXIT: //

                        break;

                    default:
                        System.out.println("Enter a right option");
                        break;
                }
            }
            catch (InputMismatchException | NumberFormatException e) {
           //     System.out.print("*** ERROR, ENTER A VALID OPTION ***");
            }

        } while (option != EXIT);


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
        int option = 0 ;

        do {
            System.out.println("\n************ MENU DISPLAY OPTIONS ***********");
            System.out.println("|   1.  DISPLAY ALL GAMES                     |");
            System.out.println("|   2.                                        |");
            System.out.println("|   3.                                        |");
            System.out.println("|   9.  EXIT                                  |");
            System.out.println("***********************************************\n");


            try
            {
                String usersInput = input.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAY_ALL_GAMES: //Checking data farm
                        System.out.println(" ** Display All Games **");
                        List<Game> gamesAll = IGameDao.findAllGames();

                        System.out.println(gamesAll + "\n");

                        break;


                    case EXIT: //
                        System.out.println(" bye bye bye");
                        break;

                    default:
                        System.out.println("Enter a right option");
                        break;
                }
            }catch (InputMismatchException | NumberFormatException | DaoException e) {
                //     System.out.print("*** ERROR, ENTER A VALID OPTION ***");
            }

        } while (option != EXIT);

    }


    private static Game insertNewGame() {

        Game game = new Game();

        System.out.println("***** ADDING A NEW GAME *****");
        System.out.println("Enter Game ID: ");
        game.setGame_ID(kb.nextInt());
        kb.nextLine();

        System.out.println("Enter Game Name: ");
        game.setTitle_Game(kb.nextLine());

        System.out.println("Enter Genre Game: ");
        game.setGenre_Game(kb.nextLine());

        System.out.println("Enter Release Year Game: ");
        game.setRelease_year_Game(kb.nextInt());
        kb.nextLine();

        System.out.println("Enter name of company Publisher Game: ");
        game.setPublisher_Game(kb.nextLine());

        System.out.println("Enter Game Price: ");
        game.setPrice_Game(kb.nextDouble());
        kb.nextLine();

        System.out.println("Enter Game Rating: ");
        game.setRate_Game(kb.nextDouble());
        kb.nextLine();

        System.out.println("* Game Added Successfully *");
        System.out.println("***************************y");

        return game;
    }



}