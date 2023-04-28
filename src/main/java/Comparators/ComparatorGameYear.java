package Comparators;

import DTOs.Game;

import java.util.Comparator;

public class ComparatorGameYear implements Comparator<Game> {

    @Override
    public int compare(Game g1, Game g2) {
        return g1.getRelease_year_Game() - g2.getRelease_year_Game();
    }

}
