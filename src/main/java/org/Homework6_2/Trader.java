package org.Homework6_2;

import java.util.Random;

public class Trader implements Runnable, Observer<EventType> {
    private boolean isAlive;
    private final Random random;
    private final int maxNumber;
    private final String name;
    private final Judge judge;

    public Trader(int maxNumber, Judge judge,  String name) {
        random = new Random();
        this.maxNumber = maxNumber;
        this.judge = judge;
        this.name = name;
        isAlive = true;
    }

    @Override
    public void run() {
        while (isAlive) {
            int randInt = random.nextInt(maxNumber+1);
            judge.addGeneratedNumber(randInt, this);
            System.out.println(name + " has generated number " + randInt);
        }
    }

    @Override
    public void HandleEvent(EventType eventType) {
        switch (eventType) {
            case TURN_OFF -> isAlive = false;
            default -> throw new UnsupportedOperationException();
        }
    }
    public String getName() {
        return name;
    }
}
