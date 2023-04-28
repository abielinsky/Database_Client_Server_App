package Comparators;

import DTOs.Game;
import Enumerators.SortType;

import java.util.Comparator;

public class ComparatorGamePriceDesc implements Comparator<Game> {

    private SortType sortType;

    public ComparatorGamePriceDesc(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare(Game g1, Game g2)
    {
        double price1 = g1.getPrice_Game();
        double price2 = g2.getPrice_Game();
        if (price1 > price2) {
            return -1;
        } else if (price1 < price2) {
            return 1;
        } else {
            return 0;
        }
    }

}
