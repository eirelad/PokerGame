package com.pokergame;

import java.util.Arrays;

public class Game {

    private Hand p1Hand;
    private Hand p2Hand;

    public Game(String[] strHands) {
        this.p1Hand = new Hand(Arrays.copyOfRange(strHands, 0, 5));
        this.p2Hand = new Hand(Arrays.copyOfRange(strHands, 5, 10));
    }

    public Hand getP1Hand() {
        return p1Hand;
    }

    public Hand getP2Hand() {
        return p2Hand;
    }
}
