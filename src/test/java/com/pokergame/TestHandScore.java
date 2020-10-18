package com.pokergame;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class TestHandScore {

    Hand royalFlush = new Hand(new String[]{"AH", "JH", "TH", "KH", "QH"});
    TreeMap<Integer, Integer> rankMap = HandScore.generateMapOfRankOccurrences(royalFlush.getCards());
    TreeMap<Character, Integer> suitMap = HandScore.generateMapOfSuitOccurrences(royalFlush.getCards());

    Hand notRoyalFlush = new Hand(new String[]{"AH", "JH", "TH", "KH", "9H"});
    TreeMap<Integer, Integer> rankMap2 = HandScore.generateMapOfRankOccurrences(notRoyalFlush.getCards());
    TreeMap<Character, Integer> suitMap2 = HandScore.generateMapOfSuitOccurrences(notRoyalFlush.getCards());

    Hand pair = new Hand(new String[]{"4H", "4C", "6S", "7S", "KD"});
    TreeMap<Integer, Integer> rankMap3 = HandScore.generateMapOfRankOccurrences(pair.getCards());

    Hand notPair = new Hand(new String[]{"4H", "9C", "6S", "7S", "KD"});
    TreeMap<Integer, Integer> rankMap4 = HandScore.generateMapOfRankOccurrences(notPair.getCards());

    Hand highCard = new Hand(new String[]{"5D", "8C", "9S", "JS", "AC"});
    TreeMap<Integer, Integer> rankMap5 = HandScore.generateMapOfRankOccurrences(highCard.getCards());

    Hand fullHouse = new Hand(new String[]{"2H", "2D", "4C", "4D", "4S"});
    TreeMap<Integer, Integer> rankMap6 = HandScore.generateMapOfRankOccurrences(fullHouse.getCards());

    @Test
    public void testIsRoyalFlush() {

        assertEquals(true, royalFlush.getHandScores().isRoyalFlush(rankMap,suitMap));
    }

    @Test
    public void testIsRoyalFlushNeg() {

        assertEquals(false, notRoyalFlush.getHandScores().isRoyalFlush(rankMap2,suitMap2));
    }

    @Test
    public void testIsPair() {

        assertEquals(true, pair.getHandScores().isPair(rankMap3));
    }

    @Test
    public void testIsPairNeg() {

        assertEquals(false, notPair.getHandScores().isPair(rankMap4));
    }

    @Test
    public void testIsHighCard() {

        assertEquals(true, highCard.getHandScores().isHighCard(rankMap5));
    }

    @Test
    public void testIsFullHouse() {

        assertEquals(true, fullHouse.getHandScores().isFullHouse(rankMap6));
    }
}
