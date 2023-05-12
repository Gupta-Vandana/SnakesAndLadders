package com.PhonePe.SnakesAndLadders.Entity;

public class Player {

    private String name;
    private int currentPosition;

    public Player(String name, int currentPosition) {
        this.name = name;
        this.currentPosition = currentPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    public int getNoOfMinesLeft() {
        return noOfMinesLeft;
    }

    public void setNoOfMinesLeft(int noOfMinesLeft) {
        this.noOfMinesLeft = noOfMinesLeft;
    }

    private int noOfMinesLeft;

}
