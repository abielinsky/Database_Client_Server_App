package com.dkit.abielLopez.SDA.dto;

public class Game implements Comparable<Game>
{

    private int game_ID;

    private int gameStore_ID;

    private String title_Game;
    private String genre_Game;
    private int release_year_Game;
    private String publisher_Game;
    private double price_Game;
    private double  rate_Game;


    public Game(int game_ID, int gameStore_ID, String title_Game, String genre_Game,
                int release_year_Game, String publisher_Game, double price_Game,
                int rate_Game)
    {
        this.game_ID = game_ID;
        this.gameStore_ID = gameStore_ID;
        this.title_Game = title_Game;
        this.genre_Game = genre_Game;
        this.release_year_Game = release_year_Game;
        this.publisher_Game = publisher_Game;
        this.price_Game = price_Game;
        this.rate_Game = rate_Game;
    }

    public Game() {

    }

    public int getGame_ID() {return game_ID;}

    public int getGameStore_ID() {return gameStore_ID;}

    public String getTitle_Game() {return title_Game;}

    public String getGenre_Game() {return genre_Game;}

    public int getRelease_year_Game() {return release_year_Game;}

    public String getPublisher_Game() {return publisher_Game;}

    public double getPrice_Game() {return price_Game;}

    public double getRate_Game() {return rate_Game;}


    public void setGame_ID(int game_ID) {
        this.game_ID = game_ID;
    }

    public void setGameStore_ID(int gameStore_ID) {
        this.gameStore_ID = gameStore_ID;
    }

    public void setTitle_Game(String title_Game) {
        this.title_Game = title_Game;
    }

    public void setGenre_Game(String genre_Game) {
        this.genre_Game = genre_Game;
    }

    public void setRelease_year_Game(int release_year_Game) {
        this.release_year_Game = release_year_Game;
    }

    public void setPublisher_Game(String publisher_Game) {
        this.publisher_Game = publisher_Game;
    }

    public void setPrice_Game(double price_Game) {
        this.price_Game = price_Game;
    }

    public void setRate_Game(double rate_Game) {
        this.rate_Game = (int) rate_Game;
    }

    @Override
    public String toString() {
        return "Game{" +
                "game_ID=" + game_ID +
                ", title_Game='" + title_Game + '\'' +
                ", genre_Game='" + genre_Game + '\'' +
                ", release_year_Game=" + release_year_Game +
                ", publisher_Game='" + publisher_Game + '\'' +
                ", price_Game=" + price_Game +
                ", rate_Game=" + rate_Game +
                '}';
    }

    @Override
    public int compareTo(Game game) {
        if (this.title_Game.equals(game.getTitle_Game())) {
            return this.gameStore_ID - game.getGameStore_ID();
        } else {
            return this.title_Game.compareTo(game.getTitle_Game());
        }
    }


    public void displayGame()
    {
        String leftAlignFormat = "| %-14s| %-14d| %-14d| %-14d| %-29s  |%n";
        System.out.format( leftAlignFormat, game_ID, gameStore_ID, title_Game, genre_Game, release_year_Game, publisher_Game, price_Game, rate_Game);
        System.out.print(("-").repeat(98) + "\n");

    }





}
