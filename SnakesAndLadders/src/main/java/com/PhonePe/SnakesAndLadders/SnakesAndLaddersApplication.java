package com.PhonePe.SnakesAndLadders;

import com.PhonePe.SnakesAndLadders.Entity.BoardEntity.Crocodile;
import com.PhonePe.SnakesAndLadders.Entity.BoardEntity.Jump;
import com.PhonePe.SnakesAndLadders.Entity.BoardEntity.Mine;
import com.PhonePe.SnakesAndLadders.Entity.BoardEntity.Snake;
import com.PhonePe.SnakesAndLadders.Entity.Game;
import com.PhonePe.SnakesAndLadders.Entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SnakesAndLaddersApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SnakesAndLaddersApplication.class, args);

        Scanner scn = new Scanner(System.in);
        int boardSize = scn.nextInt();
        if (boardSize <= 0) throw new InvalidInputException("board size should not be equal or less than 0");
        int max = boardSize * boardSize;

        int noOfCrocodiles = scn.nextInt();
        List<Jump> crocodiles = new ArrayList<>(noOfCrocodiles);
        for (int i = 0; i < noOfCrocodiles; i++) {
            Jump crocodile = new Crocodile();
            int start = scn.nextInt();
            int end = start - 5;
            if (start <= 5 || start >= max) throw new InvalidInputException("Crocodile should not be at less than 5 ");
            crocodile.start = start;
            crocodile.end = end;
            crocodiles.add(crocodile);
        }

        int noOfMines = scn.nextInt();
        List<Jump> mines = new ArrayList<>(noOfMines);
        for (int i = 0; i < noOfMines; i++) {
            Jump mine = new Mine();
            int start = scn.nextInt();
            if (start <= 0 || start >= max) throw new InvalidInputException("Oops! mine's start can't be out of board");
            mine.start = start;
            mine.end = start;
            mines.add(mine);
        }
        int noOfSnakes = scn.nextInt();
        List<Jump> snakes = new ArrayList<>(noOfSnakes);
        for (int i = 0; i < noOfSnakes; i++) {
            Jump snake = new Snake();
            int start = scn.nextInt();
            int end = scn.nextInt();
            if (start <= end) throw new InvalidInputException("Oops! Snake's start should be greater than end");
            if (start >= max) throw new InvalidInputException("Oops! Snake's should be in board");
            snake.start = start;
            snake.end = end;
            snakes.add(snake);
        }

        int noOfLadders = scn.nextInt();
        List<Jump> ladders = new ArrayList<>(noOfLadders);
        for (int i = 0; i < noOfLadders; i++) {
            Jump ladder = new Snake();
            int start = scn.nextInt();
            int end = scn.nextInt();
            if (start >= end) throw new InvalidInputException("Oops! Ladder's start should be less than end");
            if (start >= max) throw new InvalidInputException("Oops! Ladder's should be in board");
            ladder.start = start;
            ladder.end = end;
            snakes.add(ladder);
        }

        int noOfPlayers = scn.nextInt();
        List<Player> players = new ArrayList<>(noOfPlayers);
        for (int i = 1; i <= noOfPlayers; i++) {
            String name = scn.next();
            int currentPosition = scn.nextInt();
            if (currentPosition <= 0) throw new InvalidInputException("Oops! player's position should be valid");
            Player player = new Player(name, currentPosition - 1);
            players.add(player);
        }
        String noOfDice = scn.next();
        if (noOfDice.isBlank() || noOfDice.isEmpty()) noOfDice = String.valueOf(2);
        int movingStrategy = scn.nextInt();
        if (movingStrategy > 4 || movingStrategy < 1)
            throw new InvalidInputException("value of moving strategy should lie be 1, 2 or 3");
        Game game = new Game(boardSize, snakes, ladders, crocodiles, mines, Integer.parseInt(noOfDice), movingStrategy, players);
        game.startGame();
    }

    static class InvalidInputException extends Exception {
        InvalidInputException(String s) {
            super(s);
        }
    }

}
