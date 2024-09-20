package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game and supports card operations and scoring.
 */
public class Player {
    private final List<Card> hand;
    private int score;


    /**
     * Initializes the player with an empty hand and score of 0.
     */
    public Player() {
        hand = new ArrayList<>();
        score = 0;
    }

    /**
     * Adds a card to the player's hand and updates score.
     *
     * @param card the card to add to the hand.
     */
    public void addCard(Card card) {
        hand.add(card);
        calculateScore();
    }

    /**
     * Returns the player's current score (taking into account aces).
     *
     * @return the player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Recalculates the player's score based on the cards in their hand.
     * Aces add 1 or 11 points depending on the total score.
     */
    private void calculateScore() {
        score = 0;
        int aces = 0;

        for (Card card : hand) {
            score += card.value();
            if (card.rank().equals("Туз")) {
                aces++;
            }
        }

        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
    }

    /**
     * Returns the player's hand of cards.
     *
     * @return the list of cards in the player's hand.
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Returns the player's hand as a list of formatted strings, showing the rank, suit and value.
     *
     * @return a list of strings representing the player's hand.
     */
    public List<String> getHandAsStrings() {
        List<String> handAsStrings = new ArrayList<>();
        for (Card card : hand) {
            handAsStrings.add(card.rank() + " " + card.suit() + " (" + card.value() + ")");
        }
        return handAsStrings;
    }
}
