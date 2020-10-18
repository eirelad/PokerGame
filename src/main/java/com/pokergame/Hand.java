package com.pokergame;

import java.util.Arrays;

public class Hand {

    private Card[] cards;
    private HandScore handScore;

    public Hand(String[] strCards) {
        cards = new Card[5];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new Card(strCards[i]);
        }
        Arrays.sort(cards);
        this.handScore = new HandScore(cards);
    }

    public Card[] getCards() {
        return cards;
    }

    public HandScore getHandScores() {
        return handScore;
    }
}
