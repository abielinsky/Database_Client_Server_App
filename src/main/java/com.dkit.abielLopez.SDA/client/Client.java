package com.dkit.abielLopez.SDA.client;

import com.dkit.abielLopez.SDA.client.clientconstants.ClientPrintMenuOptions;
import com.dkit.abielLopez.SDA.client.clientmenus.StoreMenu;
import com.dkit.abielLopez.SDA.client.clientmenus.Menu;
import com.dkit.abielLopez.SDA.core.constants.Colours;
import com.dkit.abielLopez.SDA.core.ProtocolMenuOptions;
import com.dkit.abielLopez.SDA.dto.StoreManager;
import com.dkit.abielLopez.SDA.validation.ValidationForEnumMenus;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static com.dkit.abielLopez.SDA.core.ServiceDetails.SERVER_PORT;

public class Client {

    private static final ValidationForEnumMenus validateClientMenuOptionsEnum = new ValidationForEnumMenus();


    public static void main(String[] args) {

        try
        {
            //Step 1: Establish a connection with the server
            InetAddress serverIP = InetAddress.getByName("localhost");
            Socket dataSocket = new Socket(serverIP, SERVER_PORT);

            //Step 2: Build input and output streams linked to the socket
            OutputStream out = dataSocket.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            InputStream in = dataSocket.getInputStream();
            System.out.println("IM THE CLIENT");

            //An example of the Decorator design pattern
            Scanner input = new Scanner(new InputStreamReader(in));

            ProtocolMenuOptions.ClientMainMenuOptions
                    selectedOption = ProtocolMenuOptions.ClientMainMenuOptions.PRINT_CLIENT_MAIN_MENU;

            boolean quit = false;
            Menu storeMenu = new StoreMenu(input, output);

            System.out.println("selecting option ");

            StoreManager storeManager = ((StoreMenu) storeMenu).initializeGamesStoreManager();


            System.out.println("initialized games store manager");


            start();

            while (selectedOption != ProtocolMenuOptions.ClientMainMenuOptions.QUIT_CLIENT_MENU) {
                selectedOption = validateClientMenuOptionsEnum.validateClientMenuOptionsEnum();

                switch (selectedOption) {
                    case PRINT_CLIENT_MAIN_MENU:
                        System.out.println("print client main menu");
                        ClientPrintMenuOptions.printOptionsMainMenu();
                        break;

                    case QUIT_CLIENT_MENU:
                        quit = true;
                        break;


                    case START_STORE_MENU:
                        ((StoreMenu) storeMenu).setGamesStoreManager(storeManager);
                        storeMenu.start();
                        break;


//
//                    case ADD_GAME:
//                        // Code to add a new game to the database
//                        break;
//
//                    case DELETE_GAME:
//                        // Code to delete a game from the database
//                        break;
//
//                    case UPDATE_GAME:
//                        // Code to update a game in the database
//                        break;
//
//                    case VIEW_GAMES:
//                        // Code to view all games in the database
//                        break;
//
//                    case ADD_PLATFORM:
//                        // Code to add a new platform to the database
//                        break;
//
//                    case DELETE_PLATFORM:
//                        // Code to delete a platform from the database
//                        break;
//
//                    case UPDATE_PLATFORM:
//                        // Code to update a platform in the database
//                        break;
//
//                    case VIEW_PLATFORMS:
//                        // Code to view all platforms in the database
//                        break;

                    default:
                        System.out.println("Invalid option selected.");
                        break;
                }
            }

            System.out.println(Colours.GREEN_BOLD_BRIGHT + "Thank you for using the database system."
                    + Colours.RESET);
            dataSocket.close();
            System.exit(0);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void start()
    {
        System.out.println("in start");
        welcomeScreen();
        ClientPrintMenuOptions.printOptionsMainMenu();
    }

    public static void welcomeScreen()
    {
        System.out.println(Colours.GREEN_BOLD_BRIGHT + "\nWelcome to the Games Selection System.");
    }





}
