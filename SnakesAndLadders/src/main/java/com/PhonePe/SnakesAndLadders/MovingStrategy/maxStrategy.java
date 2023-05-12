package com.PhonePe.SnakesAndLadders.MovingStrategy;

import java.util.concurrent.ThreadLocalRandom;

public class maxStrategy implements Dice {
    int diceCount;
    int min = 1;
    int max = 6;

    public maxStrategy(int diceCount) {
        this.diceCount = diceCount;
        printStrategy();
    }

    @Override
    public int rollDice() {
        int maxDiceValue = Integer.MIN_VALUE;

        int[] dices = new int[diceCount];
        for (int i = 0; i < diceCount; i++) {
            dices[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
            maxDiceValue = Math.max(maxDiceValue, dices[i]);
        }
        System.out.println("Rolling Dice and max of all the dice values is: " + maxDiceValue);
        return maxDiceValue;
    }

    private static void printStrategy() {
        System.out.println("Max Strategy is used");
    }
}
