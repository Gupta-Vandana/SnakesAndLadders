package com.PhonePe.SnakesAndLadders.Entity;

import com.PhonePe.SnakesAndLadders.Entity.BoardEntity.Jump;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    static Cell[][] cells;
    private static int n;

    public Board(int boardSize, List<Jump> noOfSnakes, List<Jump> noOfLadders, List<Jump> crocodiles, List<Jump> mines) {
        initializeCells(boardSize);
        addBoardEntities(noOfSnakes, noOfLadders, crocodiles, mines);
    }

    private void addBoardEntities(List<Jump> noOfSnakes, List<Jump> noOfLadders, List<Jump> crocodiles, List<Jump> mines) {

        for (Jump snake : noOfSnakes) {
            Cell cell = getCell(snake.start);
            cell.setJump(snake);
        }
        for (Jump ladder : noOfLadders) {
            Cell cell = getCell(ladder.start);
            cell.setJump(ladder);
        }
        for (Jump crocodile : crocodiles) {
            Cell cell = getCell(crocodile.start);
            cell.setJump(crocodile);
        }
        for (Jump mine : mines) {
            Cell cell = getCell(mine.start);
            cell.setJump(mine);
        }
        System.out.println("Board has been created");
    }

    public Cell getCell(int playerPosition) {
        int boardRow = playerPosition / n;
        int boardColumn = playerPosition % n;
        return cells[boardRow][boardColumn];
    }

    private void initializeCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];
        n = cells.length;
        int val = 1;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Cell newCell = new Cell();
                cells[i][j] = newCell;
                newCell.setVal(val);
                val++;
            }
        }
    }
}
