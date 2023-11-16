package org.Homework6_2;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Judge implements Observable<EventType> {
    record GeneratedNumber(int value, Trader generator) {
    }

    private int countGeneratedNumbers;
    private final Boolean[] generatedNumbers;
    private final List<TraderPointsController> tradersPoints;
    private final int maxNumber;
    private final List<Trader> traders;

    Queue<GeneratedNumber> generationQueue;

    public Judge(int countTraders, int maxNumber) {
        traders = new ArrayList<>();
        generationQueue = new ArrayBlockingQueue<>(maxNumber);
        tradersPoints = new ArrayList<>();
        generatedNumbers = new Boolean[maxNumber + 1];
        countGeneratedNumbers = 0;

        this.maxNumber = maxNumber;
        for (int i = 0; i < countTraders; i++) {
            Trader trader = new Trader(maxNumber, this, "Trader #" + i);
            traders.add(trader);
            tradersPoints.add(new TraderPointsController(trader));
        }
        for (int i = 0; i <= maxNumber; i++) generatedNumbers[i] = false;
    }

    public void startGame() {
        for (Trader trader : traders) {
            new Thread(trader).start();
        }

        controlGame();
    }

    private void controlGame() {
        while (countGeneratedNumbers < maxNumber) {
            if (!generationQueue.isEmpty()) {
                GeneratedNumber generatedNumber = generationQueue.poll();
                if (!generatedNumbers[generatedNumber.value]) {
                    tradersPoints.stream().filter(g -> g.getTrader() == generatedNumber.generator).findFirst().get().incrementPoints();
                    System.out.println("1 point to " + generatedNumber.generator.getName());
                    generatedNumbers[generatedNumber.value] = true;
                    countGeneratedNumbers++;
                }
            }
        }

        stopAllTraders();
        makeResults();
    }

    private void stopAllTraders() {
        NotifyListeners(EventType.TURN_OFF);
    }

    private void makeResults() {
        tradersPoints.sort((o1, o2) -> {
            if (o1.getPoints() != o2.getPoints()) return o1.getPoints() < o2.getPoints() ? -1 : 1;
            else return 0;
        });

        System.out.println("Winner of the game is " + tradersPoints.get(tradersPoints.size() - 1).getTrader().getName());
    }

    public synchronized void addGeneratedNumber(int number, Trader generator) {
        if (generationQueue.size() == maxNumber - 1) return;

        generationQueue.add(new GeneratedNumber(number, generator));
    }

    @Override
    public void NotifyListeners(EventType eventType) {
        for (Trader trader : traders) trader.HandleEvent(eventType);
    }
}
