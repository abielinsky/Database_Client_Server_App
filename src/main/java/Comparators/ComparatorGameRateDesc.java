package Comparators;

import DTOs.Game;
import Enumerators.SortType;

import java.util.Comparator;

public class ComparatorGameRateDesc implements Comparator<Game> {


    private SortType sortType;

    public ComparatorGameRateDesc(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare(Game g1, Game g2)
    {
        double rate1 = g1.getRate_Game();
        double rate2 = g2.getRate_Game();
        if (rate1 > rate2) {
            return -1;
        } else if (rate1 < rate2) {
            return 1;
        } else {
            return 0;
        }
    }

}
