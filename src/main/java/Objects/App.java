package Objects;


import DAOs.GameDAOInterface;
import DAOs.MySqlGameDAO;
import DTOs.Game;
import Exceptions.DaoException;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App { ///start app

    private static List<Game> games;
    private static GameDAOInterface IGameDAO = new MySqlGameDAO();
    private static Scanner IDinput = new Scanner(System.in);




    public static void main(String[] args) throws IOException { /// start main
        App app = new App();
        app.start();
    } /// finish main

    private void start() { /// start, start
        try {
            displayMenuApp();
        } catch (IOException | DaoException e)
        {
            e.printStackTrace();
        }

    } /// finish, start

    private void displayMenuApp() throws IOException, DaoException { /// start displayManuApp

        final int DISPLAY_ALL_GAMES = 1;
        final int FIND_GAME_BY_ID = 2;
        final int DELETE_GAME_BY_ID = 3;
        final int INSERT_NEW_GAME = 4;
        final int MENU_FILTERS = 5;
        final int EXIT = 6;
        Scanner input = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n\n");
            System.out.println("____________________ MENU ________________");
            System.out.println("||   1.   DISPLAY ALL THE GAMES  ==>    ||");
            System.out.println("||   2.   FIND GAME BY ID        ==>    ||");
            System.out.println("||   3.   DELETE GAME BY ID      ==>    ||");
            System.out.println("||   4.   INSERT NEW GAME        ==>    ||");
            System.out.println("||   5.   MENU FILTERS           ==>    ||");
            System.out.println("||   6.   EXIT                   ==>    ||");
            System.out.println("******************************************");
            System.out.println("    Option [1 - 6]");
            try {

                String usersInput = input.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {

                    case DISPLAY_ALL_GAMES:
                        System.out.println("********************************************************************************************************************************");
                        System.out.println("==================================================== DISPLAY ALL THE GAMES =====================================================");
                        displayAllGames();

                        break;

                    case FIND_GAME_BY_ID:
                        System.out.println("********************************************************************************************************************************");
                        System.out.println("======================================================== FIND GAME BY ID =======================================================");
                        findGameByID();
                        break;

                    case DELETE_GAME_BY_ID:
                        System.out.println("********************************************************************************************************************************");
                        System.out.println("====================================================== DELETE GAME BY ID =======================================================");
                        deleteGameByID();
                        break;

                    case INSERT_NEW_GAME:
                        System.out.println("********************************************************************************************************************************");
                        System.out.println("======================================================== INSERT NEW GAME =======================================================");
                        addNewGame();
                        break;

                    case MENU_FILTERS:
                        System.out.println("********************************************************************************************************************************");
                        System.out.println("======================================================== MENU FILTERS =======================================================");

                        break;

                    case EXIT:
                        System.out.println("********************************************************************************************************************************");
                        System.out.println("======================================================== EXIT =======================================================");
                        break;

                    default:
                        System.out.println("XXXXXXXXXXXXXXXXXX '  Please, select a valid option. ' XXXXXXXXXXXXXXXXXX");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(" INVALID OPTION ");
            }


        } while (option != EXIT);

    } /// finish displayMenuApp



    //todo, ================================================ ADD NEW GAMES ================================================
    private void addNewGame()  throws DaoException {

        games = IGameDAO.displayAllGames();

        if (games.isEmpty()) {
            System.out.println("There are no games in the database.");
        } else {
            System.out.println("\n********************************************************************************************************************************");
            System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","*", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","*");
            System.out.println("\n********************************************************************************************************************************");
            for (Game game : games) {
                System.out.println(game.displayAllGames());
            }
        }
        System.out.println("********************************************************************************************************************************");


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

        Game game = IGameDAO.addNewGame(title_Game, genre_Game, releaseYear_Game, publisher_Game, price_Game, rate_Game);

        if (game != null) {
            System.out.println("\n********************************************************************************************************************************");
            System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","*", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","*");
            System.out.println("\n********************************************************************************************************************************");
            System.out.println(game.displayAllGames());
            System.out.println("\n********************************************************************************************************************************");
            System.out.println("Game added successfully");
        } else {
            System.out.println("Game not added");
        }




    }







    //todo, ============================================ DELETE game BY THE ID ============================================
    private void deleteGameByID() throws DaoException {

        games = IGameDAO.displayAllGames();

        if (games.isEmpty()) {
            System.out.println("There are no games in the database.");
        } else {
            System.out.println("\n********************************************************************************************************************************");
            System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","*", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","*");
            System.out.println("\n********************************************************************************************************************************");
            for (Game game : games) {
                System.out.println(game.displayAllGames());
            }
        }
        System.out.println("********************************************************************************************************************************");


        IDinput = new Scanner(System.in);

        boolean IDtrue = false;
        do {
            try {
                System.out.println("Enter the ID of the game you want to delete: ");

                int id = IDinput.nextInt();
                IDtrue = true;
                Game gamebyID = IGameDAO.findGameById(id);

                if (gamebyID == null) {
                    System.out.println("No games found with the ID: " + id);
                } else {
                    System.out.println("The game with the ID: " + id + " has been deleted.");
                    IGameDAO.deleteGameById(id);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please, enter a number.");
                IDinput.nextLine();
            }
        } while (!IDtrue);

    }





    //todo, ============================================ DISPLAY A GAME BY ID ============================================
    private void findGameByID() throws DaoException {

        games = IGameDAO.displayAllGames();

        if (games.isEmpty()) {
            System.out.println("There are no games in the database.");
        } else {
            System.out.println("\n********************************************************************************************************************************");
            System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","*", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","*");
            System.out.println("\n********************************************************************************************************************************");
            for (Game game : games) {
                System.out.println(game.displayAllGames());
            }
        }
        System.out.println("********************************************************************************************************************************");


        IDinput = new Scanner(System.in);

        boolean IDtrue = false;
        do {
            try {
                System.out.println("Enter the ID of the game you want to find: ");

                int id = IDinput.nextInt();
                IDtrue = true;
                Game gamebyID = IGameDAO.findGameById(id);

                if (gamebyID == null) {
                    System.out.println("No games found with the ID" + id);
                    System.out.println("continue");
                } else {

                    System.out.println("\n********************************************************************************************************************************");
                    System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","*", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","*");
                    System.out.println("\n********************************************************************************************************************************");
                    System.out.println(gamebyID.displayAllGames());
                    System.out.println("********************************************************************************************************************************");

                    System.out.println("continue...");
                }

            } catch (InputMismatchException | DaoException e) {
                IDinput.nextLine();
                System.out.println("Enter a Valid ID number");

            }
        } while ((IDtrue != true));




    }

    //todo, ============================================ DISPLAY A GAME BY ID ============================================





    //todo, ============================================ DISPLAY ALL THE GAMES ============================================
    private void displayAllGames() throws DaoException {

        games = IGameDAO.displayAllGames();

        if (games.isEmpty()) {
            System.out.println("There are no games in the database.");
        } else {
            System.out.println("\n********************************************************************************************************************************");
            System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","*", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","*");
            System.out.println("\n********************************************************************************************************************************");
            for (Game game : games) {
                System.out.println(game.displayAllGames());
            }
        }
        System.out.println("********************************************************************************************************************************");
    }
    //todo, ============================================ DISPLAY ALL THE GAMES ============================================




    public static Game displayGame(Game game) {

        System.out.println("\n ********************************************************************************************************************************");
        System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","*", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","*");
        System.out.println("\n********************************************************************************************************************************");
        System.out.printf("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s", "*", game.getGame_ID(), game.getTitle_Game(), game.getGenre_Game(), game.getRelease_year_Game(), game.getPublisher_Game(), game.getPrice_Game(), game.getRate_Game(), "*");
        System.out.println("********************************************************************************************************************************");

        return game;
    }











} ///finish app


































