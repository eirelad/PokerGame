package com.pokergame;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Poker {

    private static TreeMap<String, Integer> playerScoreBoardMap = new TreeMap<>();

    private final static String STR_PATH = "/Users/chris/IdeaProjects/PokerGame/src/main/resources/poker-hands.txt";

    private final static String PLAYER_1 = "Player 1";
    private final static String PLAYER_2 = "Player 2";

    /*
    Method to increment players score count and insert new score into a map by increment the value by 1
     */
    public static void incrementPlayerWinCount(String strPlayer) {

        Integer playerScoreCount = playerScoreBoardMap.get(strPlayer);
        if (playerScoreCount == null) {
            playerScoreBoardMap.put(strPlayer, 1);
        } else {
            playerScoreBoardMap.put(strPlayer, playerScoreCount + 1);
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path path = Paths.get(STR_PATH);
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(line -> {

                Game game = new Game(line.split(" "));

                int playerOneOrdinalScore = game.getP1Hand().getHandScores().getHandScore().ordinal();
                int playerTwoOrdinalScore = game.getP2Hand().getHandScores().getHandScore().ordinal();

                int playerOneCardVal = 0, playerTwoCardVal = 0;

                TreeMap<Integer, Integer> playerOneCardRankMap = HandScore.generateMapOfRankOccurrences(game.getP1Hand().getCards());
                TreeMap<Integer, Integer> playerTwoCardRankMap = HandScore.generateMapOfRankOccurrences(game.getP2Hand().getCards());

                /*
                If conditional which checks if playerOne or playerTwo has better hand
                based on the ordinal of HandResult type and increments winners score by one
                 */
                if (playerOneOrdinalScore > playerTwoOrdinalScore) {
                    incrementPlayerWinCount(PLAYER_1);
                }
                if (playerTwoOrdinalScore > playerOneOrdinalScore) {
                    incrementPlayerWinCount(PLAYER_2);
                }

                /*
                If conditional which checks if playerOne and playerTwo have the same ranking hand
                and if they do will call other methods to resolve the winner such as finding high card
                 */
                if (playerOneOrdinalScore == playerTwoOrdinalScore) {
                    if (game.getP1Hand().getHandScores().isHighCard(playerOneCardRankMap)) {
                        playerOneCardVal = playerOneCardRankMap.lastKey();
                    }

                    if (game.getP2Hand().getHandScores().isHighCard(playerTwoCardRankMap)) {
                        playerTwoCardVal = playerTwoCardRankMap.lastKey();
                    }

                    for (Map.Entry<Integer, Integer> rankEntry : playerOneCardRankMap.entrySet()) {
                        if (game.getP1Hand().getHandScores().checkPairVSPair(rankEntry, playerOneCardRankMap)) {
                            playerOneCardVal = rankEntry.getKey();
                        }
                    }

                    for (Map.Entry<Integer, Integer> rankEntry : playerTwoCardRankMap.entrySet()) {
                        if (game.getP2Hand().getHandScores().checkPairVSPair(rankEntry, playerTwoCardRankMap)) {
                            playerTwoCardVal = rankEntry.getKey();
                        }
                    }

                    for (Map.Entry<Integer, Integer> rankEntry : playerOneCardRankMap.entrySet()) {
                        if (game.getP1Hand().getHandScores().isFullHouse(playerOneCardRankMap)) {
                            playerOneCardVal = rankEntry.getValue();
                        }
                    }
                    for (Map.Entry<Integer, Integer> rankEntry2 : playerTwoCardRankMap.entrySet()) {
                        if (game.getP2Hand().getHandScores().isFullHouse(playerTwoCardRankMap)) {
                            playerTwoCardVal = rankEntry2.getValue();
                        }
                    }

                    int val1 = 1;
                    int val2 = 1;

                    /*
                    While loop runs if players have same ranking hand such as (pairs,trips,full house, etc) and have
                    same value of cards example if two players have pair of Aces will traverse the array and assign
                    high card to both players until high cards are unique
                     */
                    while (playerOneCardVal == playerTwoCardVal) {
                        Integer[] playerOneTempArray = playerOneCardRankMap.keySet().toArray(new Integer[playerOneCardRankMap.size()]);
                        Integer[] playerTwoTempArray = playerTwoCardRankMap.keySet().toArray(new Integer[playerTwoCardRankMap.size()]);

                        playerOneCardVal = playerOneTempArray[playerOneTempArray.length - val1];
                        playerTwoCardVal = playerTwoTempArray[playerTwoTempArray.length - val2];

                        val1 = val1 + 1;
                        val2 = val2 + 1;
                    }
                }
                if (playerOneCardVal > playerTwoCardVal) {
                    incrementPlayerWinCount(PLAYER_1);
                }
                if (playerTwoCardVal > playerOneCardVal) {
                    incrementPlayerWinCount(PLAYER_2);
                }
            });
        } catch (IOException ex) {
        }

        /*
        Code to print out leader board at end of program
         */
        String playerOneScore = playerScoreBoardMap.firstKey() + ": " + playerScoreBoardMap.firstEntry().getValue();
        String playerTwoScore = playerScoreBoardMap.lastKey() + ": " + playerScoreBoardMap.lastEntry().getValue();

        System.out.println(playerOneScore);
        System.out.println(playerTwoScore);
    }
}