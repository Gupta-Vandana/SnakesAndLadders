package com.PhonePe.SnakesAndLadders.Entity;

import com.PhonePe.SnakesAndLadders.Entity.BoardEntity.Jump;

public class Cell {
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    private int val;
    private Jump jump;

    public Jump getJump() {
        return jump;
    }

    public void setJump(Jump jump) {
        this.jump = jump;
    }
}
