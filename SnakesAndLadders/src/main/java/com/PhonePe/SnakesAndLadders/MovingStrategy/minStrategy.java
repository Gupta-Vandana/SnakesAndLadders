package com.PhonePe.SnakesAndLadders.MovingStrategy;

import java.util.concurrent.ThreadLocalRandom;

public class minStrategy implements Dice {
    int diceCount;
    int min = 1;
    int max = 6;

    public minStrategy(int diceCount) {
        this.diceCount = diceCount;
        printStrategy();
    }

    @Override
    public int rollDice() {
        int minDiceValue = Integer.MAX_VALUE;

        int[] dices = new int[diceCount];
        for (int i = 0; i < diceCount; i++) {
            dices[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
            minDiceValue = Math.min(minDiceValue, dices[i]);
        }
        System.out.println("Rolling Dice and min of all the dice values is: " + minDiceValue);
        return minDiceValue;
    }

    private static void printStrategy() {
        System.out.println("Min Strategy is used");
    }

}
