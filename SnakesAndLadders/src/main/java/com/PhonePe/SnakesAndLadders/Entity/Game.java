package com.PhonePe.SnakesAndLadders.Entity;

import com.PhonePe.SnakesAndLadders.Entity.BoardEntity.Jump;
import com.PhonePe.SnakesAndLadders.MovingStrategy.Dice;
import com.PhonePe.SnakesAndLadders.MovingStrategy.maxStrategy;
import com.PhonePe.SnakesAndLadders.MovingStrategy.minStrategy;
import com.PhonePe.SnakesAndLadders.MovingStrategy.sumStrategy;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private Dice dice;
    private final Deque<Player> players = new LinkedList<>();
    private Player winner;

    public Game(int boardSize, List<Jump> noOfSnakes, List<Jump> noOfLadders, List<Jump> crocodiles, List<Jump> mines, int noOfDice, int movingStrategy, List<Player> noOfPlayers) {
        initializeGame(boardSize, noOfSnakes, noOfLadders, crocodiles, mines, noOfDice, movingStrategy, noOfPlayers);
    }

    private void initializeGame(int boardSize, List<Jump> noOfSnakes, List<Jump> noOfLadders, List<Jump> crocodiles, List<Jump> mines, int noOfDice, int movingStrategy, List<Player> noOfPlayers) {
        board = new Board(boardSize, noOfSnakes, noOfLadders, crocodiles, mines);
        if (movingStrategy == 1)
            dice = new sumStrategy(noOfDice);
        else if (movingStrategy == 2) {
            dice = new maxStrategy(noOfDice);
        } else {
            dice = new minStrategy(noOfDice);
        }
        winner = null;
        addPlayers(noOfPlayers);

    }

    private void addPlayers(List<Player> noOfPlayers) {
        players.addAll(noOfPlayers);
        System.out.println("All the players are added, now we can start the game");
        System.out.println();
    }

    public void startGame() {

        while (winner == null) {
            Player playerTurn = findPlayerTurn();
            int minesLeft = playerTurn.getNoOfMinesLeft();
            if (minesLeft > 0) {
                System.out.println("Oops! " + playerTurn.getName() + " still have mines left, you can't move till " + minesLeft + " turns");
                minesLeft--;
                playerTurn.setNoOfMinesLeft(minesLeft);
                System.out.println();
                continue;
            }
            System.out
                    .println("Player turn is: " + playerTurn.getName() + " current position is: " + board.getCell(playerTurn.getCurrentPosition()).getVal());
            int diceNumbers = dice.rollDice();
            int playerNewPosition = playerTurn.getCurrentPosition() + diceNumbers;
            if (isPositionInValid(playerNewPosition)) continue;

            playerNewPosition = jumpCheck(playerNewPosition, playerTurn);

            if (isPositionInValid(playerNewPosition)) continue;

            // to check if there's an already on that position
            for (Player player : players) {
                if (player.getCurrentPosition() == playerNewPosition) {
                    System.out.println("Player " + player.getName() + " has moved to 1st position as " + playerTurn.getName() + " has arrived here");
                    player.setCurrentPosition(0);
                }
            }

            playerTurn.setCurrentPosition(playerNewPosition);
            System.out.println("Player turn is: " + playerTurn.getName() + " new Position is: " + board.getCell(playerNewPosition).getVal());
            System.out.println();
            if (playerNewPosition == Board.cells.length * Board.cells.length - 1)
                winner = playerTurn;
        }

        System.out.println("Winner is: " + winner.getName());
    }

    private boolean isPositionInValid(int playerNewPosition) {
        if (playerNewPosition > Board.cells.length * Board.cells.length - 1) {
            playerNewPosition++;
            System.out.println("Oh no! Your new position is " + playerNewPosition + " which is out of board and moving out of the board is not allowed, you'd have to wait for your next turn");
            System.out.println();
            return true;
        }
        return false;
    }

    private int jumpCheck(int playerNewPosition, Player player) {
        Cell cell = board.getCell(playerNewPosition);
        if (cell.getJump() != null && cell.getJump().start == playerNewPosition) {
            int indexed = cell.getJump().end + 1;
            if (cell.getJump().start < cell.getJump().end) {
                System.out.println("Yeah! You got a ladder, your new position will be " + indexed);
            } else if (cell.getJump().start == cell.getJump().end) {
                player.setNoOfMinesLeft(2);
                System.out.println("Oho! you have stepped on a mine, you can't move till 2 turns " + indexed);
            } else if (cell.getJump().start == cell.getJump().end + 5) {
                System.out.println("Oho! you have been bitten by crocodile, you will be moving down to " + indexed);
            } else {
                System.out.println("Oho! you have been bitten by snake, you will be moving down to " + indexed);
            }
            return cell.getJump().end;
        }
        return playerNewPosition;
    }

    private Player findPlayerTurn() {
        Player playerTurn = players.removeFirst();
        players.addLast(playerTurn);
        return playerTurn;
    }
}
