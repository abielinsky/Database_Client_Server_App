package Comparators;

import DTOs.Game;

import java.util.Comparator;

public class ComparatorGamePublisher implements Comparator<Game> {

    @Override
    public int compare(Game g1, Game g2) {
        return g1.getPublisher_Game().compareTo(g2.getPublisher_Game());
    }
}
