package Comparators;

import DTOs.Game;

import java.util.Comparator;

public class ComparatorGameGenre implements Comparator<Game> {

    @Override
    public int compare(Game g1, Game g2) {
        return g1.getGenre_Game().compareTo(g2.getGenre_Game());
    }



}
