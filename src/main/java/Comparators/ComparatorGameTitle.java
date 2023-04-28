package Comparators;

import DTOs.Game;

import java.util.Comparator;

public class ComparatorGameTitle implements Comparator<Game> {

    @Override
    public int compare(Game g1, Game g2) {
        return g1.getTitle_Game().compareTo(g2.getTitle_Game());
    }


}

