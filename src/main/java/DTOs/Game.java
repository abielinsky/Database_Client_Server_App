package DTOs;



public class Game implements Comparable<Game>{

    private int game_ID;
    private String title_Game;
    private String genre_Game;
    private int release_year_Game;
    private String publisher_Game;
    private double price_Game;
    private double  rate_Game;

    //TODO CONSTRUCTOR____ adding a new game______________________________________________________________________________
    public Game(String titleGame, String genreGame, int releaseYearGame, String publisherGame, double priceGame, int rateGame) {
        this.title_Game = titleGame;
        this.genre_Game = genreGame;
        this.release_year_Game = releaseYearGame;
        this.publisher_Game = publisherGame;
        this.price_Game = priceGame;
        this.rate_Game = rateGame;
    }


    @Override
    public int compareTo(Game o) {
        return 0;
    }

    //TODO CONSTRUCTOR_________________________________________________________________________________________________
    public Game(int game_ID, String title_Game, String genre_Game, int release_year_Game,
                String publisher_Game, double price_Game, double rate_Game) {
        this.game_ID = game_ID;
        this.title_Game = title_Game;
        this.genre_Game = genre_Game;
        this.release_year_Game = release_year_Game;
        this.publisher_Game = publisher_Game;
        this.price_Game = price_Game;
        this.rate_Game = rate_Game;
    }

    //TODO GETTERS_____________________________________________________________________________________________________
    public int getGame_ID() {return game_ID;}
    public String getTitle_Game() {return title_Game;}
    public String getGenre_Game() {return genre_Game;}
    public int getRelease_year_Game() {return release_year_Game;}
    public String getPublisher_Game() {return publisher_Game;}
    public double getPrice_Game() {return price_Game;}
    public double getRate_Game() {return rate_Game;}

    //TODO SETTERS_____________________________________________________________________________________________________
    public void setGame_ID(int game_ID) {this.game_ID = game_ID;}
    public void setTitle_Game(String title_Game) {this.title_Game = title_Game;}
    public void setGenre_Game(String genre_Game) {this.genre_Game = genre_Game;}
    public void setRelease_year_Game(int release_year_Game) {this.release_year_Game = release_year_Game;}
    public void setPublisher_Game(String publisher_Game) {this.publisher_Game = publisher_Game;}
    public void setPrice_Game(double price_Game) {this.price_Game = price_Game;}
    public void setRate_Game(double rate_Game) {this.rate_Game = rate_Game;}

    //TODO TO STRING___________________________________________________________________________________________________

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

    //TODO ____________________________________________________________________________________________________________

    public String displayAllGames() {

        return String.format("%s %-5s %-22s %-25s %-15s %-30s %-10s %-12s %s","|",
                getGame_ID(), getTitle_Game(), getGenre_Game(), getRelease_year_Game(),
                getPublisher_Game(), getPrice_Game(), getRate_Game(), "|");

    }









}
