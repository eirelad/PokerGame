package com.pokergame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestCard {

    Card card = new Card("AH");

    @Test
    public void testGetRank() {
        assertEquals(14, card.getRank());
    }

    @Test
    public void testGetRankNeg() {
        assertNotEquals(13, card.getRank());
    }

    @Test
    public void testGetSuit() {
        assertEquals('H', card.getSuit());
    }

    @Test
    public void testGetSuitNeg() {
        assertNotEquals('S', card.getSuit());
    }
}
