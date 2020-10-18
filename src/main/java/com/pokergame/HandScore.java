package com.pokergame;

import java.util.Map;
import java.util.TreeMap;

public class HandScore {

    public Card[] cards;

    public HandScore(Card[] cards) {
        this.cards = cards;
    }

    public HandResult getHandScore() {

        TreeMap<Integer, Integer> rankMap = generateMapOfRankOccurrences(cards);
        TreeMap<Character, Integer> suitMap = generateMapOfSuitOccurrences(cards);

        /* Conditional if to check if hand is a Royal Flush */
        if (isRoyalFlush(rankMap, suitMap)) {
            return HandResult.RoyalFlush;
        }

        /* Conditional if to check if hand is a Straight Flush */
        if (isStraightFlush(rankMap, suitMap)) {
            return HandResult.StraightFlush;
        }

        /* Conditional if to check if hand is a 4 of a Kind */
        if (isFourOfaKind(rankMap)) {
            return HandResult.FourOfakind;
        }

        /* Conditional if to check if hand is a Full House */
        if (isFullHouse(rankMap)) {
            return HandResult.FullHouse;
        }

        /* Conditional if to check if hand is a Flush */
        if (isFlush(rankMap, suitMap)) {
            return HandResult.Flush;
        }

        /* Conditional if to check if hand is a Straight */
        if (isStraight(rankMap, suitMap)) {
            return HandResult.Straight;
        }

        /* Conditional if to check if hand is a 3 of a Kind */
        if (isThreeOfaKind(rankMap)) {
            return HandResult.ThreeOfaKind;
        }

        /* Conditional if to check if hand is Two Pairs */
        if (isTwoPairs(rankMap)) {
            return HandResult.TwoPairs;
        }

        /* Conditional if to check if hand is a Pair */
        if (isPair(rankMap)) {
            return HandResult.Pair;
        }

        /* Else statement if hand is none of the above */
        else {
            return HandResult.HighCard;
        }
    }

    public static TreeMap<Integer, Integer> generateMapOfRankOccurrences(Card[] cards) {
        /* to get occurrences of rank */
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < cards.length; i++) {
            Integer rankFrequency = 0;

            if (map.get(cards[i].getRank()) != null) {
                rankFrequency = map.get(cards[i].getRank());
            }
            map.put(cards[i].getRank(), rankFrequency + 1);
        }
        return map;
    }

    public static TreeMap<Character, Integer> generateMapOfSuitOccurrences(Card[] cards) {
        /* to get occurrences of suit */
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < cards.length; i++) {
            Integer suitFrequency = 0;

            if (map.get(cards[i].getSuit()) != null) {
                suitFrequency = map.get(cards[i].getSuit());
            }
            map.put(cards[i].getSuit(), suitFrequency + 1);
        }
        return map;
    }

    public boolean isRoyalFlush(TreeMap<Integer, Integer> rankMap, TreeMap<Character, Integer> suitMap) {
        return suitMap.size() == 1 && rankMap.size() == 5 && rankMap.lastKey() - rankMap.firstKey() == 4 && rankMap.firstKey() == 10;
    }

    public boolean isStraightFlush(TreeMap<Integer, Integer> rankMap, TreeMap<Character, Integer> suitMap) {
        return suitMap.size() == 1 && rankMap.size() == 5 && rankMap.lastKey() - rankMap.firstKey() == 4 && rankMap.firstKey() != 10;
    }

    public boolean isFourOfaKind(TreeMap<Integer, Integer> rankMap) {
        return rankMap.containsValue(4);
    }

    public boolean isFullHouse(TreeMap<Integer, Integer> rankMap) {
        return rankMap.containsValue(3) && rankMap.containsValue(2);
    }

    public boolean isFlush(TreeMap<Integer, Integer> rankMap, TreeMap<Character, Integer> suitMap) {
        return suitMap.size() == 1 && rankMap.lastKey() - rankMap.firstKey() != 4;
    }

    public boolean isStraight(TreeMap<Integer, Integer> rankMap, TreeMap<Character, Integer> suitMap) {
        return rankMap.size() == 5 && rankMap.lastKey() - rankMap.firstKey() == 4 && suitMap.size() != 1;
    }

    public boolean isThreeOfaKind(TreeMap<Integer, Integer> rankMap) {
        return rankMap.containsValue(3) && !rankMap.containsValue(2);
    }

    public boolean isTwoPairs(TreeMap<Integer, Integer> rankMap) {
        return rankMap.containsValue(2) && rankMap.size() == 3;
    }

    public boolean isPair(TreeMap<Integer, Integer> rankMap) {
        return !rankMap.containsValue(3) && rankMap.containsValue(2);
    }

    public boolean checkPairVSPair(Map.Entry<Integer, Integer> rankEntry, TreeMap<Integer, Integer> rankMap) {
        return rankEntry.getValue().equals(2) && !rankMap.containsValue(3);
    }

    public boolean isHighCard(TreeMap<Integer, Integer> rankMap) {
        return rankMap.containsValue(1) && !rankMap.containsValue(2);
    }
}
