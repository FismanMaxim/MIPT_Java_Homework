package org.Homework6_2;

public class TraderPointsController {
    private final Trader trader;
    private int points;

    public TraderPointsController(Trader trader) {
        this.trader = trader;
        points = 0;
    }

    public void incrementPoints() {
        points++;
    }

    public int getPoints() {
        return points;
    }

    public Trader getTrader() {
        return trader;
    }
}
