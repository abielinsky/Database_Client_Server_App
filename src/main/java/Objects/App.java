package Objects;


import DAOs.GameDAOInterface;
import DAOs.MySqlGameDAO;
import DTOs.Game;
import Exceptions.DaoException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App { ///start app

    private static List<Game> games;
    private static GameDAOInterface IGameDAO = new MySqlGameDAO();




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
                        System.out.println("========================= DISPLAY ALL THE GAMES =========================");
                        displayAllGames();

                        break;

                    case FIND_GAME_BY_ID:
                        System.out.println("============================ FIND GAME BY ID =============================");
                        break;

                    case DELETE_GAME_BY_ID:
                        System.out.println("============================ DELETE GAME BY ID ============================");
                        break;

                    case INSERT_NEW_GAME:
                        System.out.println("============================= INSERT NEW GAME =============================");
                        break;

                    case MENU_FILTERS:
                        System.out.println("============================= MENU FILTERS =============================");
                        break;

                    case EXIT:
                        System.out.println("================================== EXIT ==================================");
                        break;

                    default:
                        System.out.println("XXXXXXXXXXXXXXXXXX '  Please, select a valid option. ' XXXXXXXXXXXXXXXXXX");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(" INVALID OPTION ");
            }


        } while (option != EXIT);

    } /// finish displayManuApp

    private void displayAllGames() throws DaoException {

        games = IGameDAO.displayAllGames();

        if (games.isEmpty()) {
            System.out.println("There are no games in the database.");
        } else {
            System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%s %-5s %-20s %-20s %-10s %-12s %-10s %-12s %s","|", "Id", "Name", "Genre", "Release Year", "Publisher Company", "Price", "Rate","|");
            System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------");
            for (Game game : games) {
                System.out.println(game.displayAllGames());
            }
        }
        System.out.println("---------------------------------------------------------------------------");




    }


} ///finish app


































