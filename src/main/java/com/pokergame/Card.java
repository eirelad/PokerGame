package com.pokergame;

public class Card implements Comparable<Card> {

    private Integer rank;
    private char suit;

    public Card(String string) {
        this.suit = string.charAt(1);
        setRank(string);
    }

    public int getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    /*
    This method is used for setting court cards A,K,Q,J,10 to Integers
     */
    private void setRank(String string) {
        if (Character.isDigit(string.charAt(0))) {
            this.rank = Integer.parseInt(String.valueOf(string.charAt(0)));
        }

        if (string.charAt(0) == 'T') {
            this.rank = 10;
        }
        if (string.charAt(0) == 'J') {
            this.rank = 11;
        }
        if (string.charAt(0) == 'Q') {
            this.rank = 12;
        }

        if (string.charAt(0) == 'K') {
            this.rank = 13;
        }

        if (string.charAt(0) == 'A') {
            this.rank = 14;
        }
    }

    @Override
    public int compareTo(Card other) {
        return rank.compareTo(other.rank);
    }
}