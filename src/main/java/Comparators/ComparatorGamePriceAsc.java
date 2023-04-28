package Comparators;

import DTOs.Game;
import Enumerators.SortType;

import java.util.Comparator;

public class ComparatorGamePriceAsc implements Comparator<Game> {

    private SortType sortType;

    public ComparatorGamePriceAsc(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare(Game g1, Game g2)
    {
        int direction = (int) sortType.getValue();
        return (int) (direction * (g1.getPrice_Game() - g2.getPrice_Game()));

    }
}
