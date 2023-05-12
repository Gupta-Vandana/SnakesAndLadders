package com.PhonePe.SnakesAndLadders.MovingStrategy;

import com.PhonePe.SnakesAndLadders.MovingStrategy.Dice;

import java.util.concurrent.ThreadLocalRandom;

public class sumStrategy implements Dice {

    int diceCount;
    int min = 1;
    int max = 6;

    public sumStrategy(int diceCount) {
        this.diceCount = diceCount;
        printStrategy();
    }

    @Override
    public int rollDice() {
        int totalSum = 0;
        int diceUsed = 0;
        while (diceUsed < diceCount) {
            totalSum += ThreadLocalRandom.current().nextInt(min, max + 1);
            diceUsed++;
        }
        System.out.println("Rolling Dice and sum of all the dice values is: " + totalSum);
        return totalSum;
    }

    private static void printStrategy() {
        System.out.println("Sum Strategy is used");
    }

}
