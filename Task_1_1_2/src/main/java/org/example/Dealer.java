package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a dealer in the game and supports card operations and scoring.
 */
public class Dealer {
    private final List<Card> hand;
    private Card hiddenCard;
    private int score;

    /**
     * Initializes the dealer with an empty hand and a score of 0.
     */
    public Dealer() {
        hand = new ArrayList<>();
        score = 0;
    }

    /**
     * Adds a card to the dealer's hand and updates the score.
     *
     * @param card the card to add to the hand.
     */
    public void addCard(Card card) {
        hand.add(card);
        calculateScore();
    }

    /**
     * Sets the dealer's hidden card and recalculates the score.
     *
     * @param card the hidden card to set.
     */
    public void setHiddenCard(Card card) {
        hiddenCard = card;
        calculateScore();
    }

    /**
     * Returns the dealer's hidden card.
     *
     * @return the hidden card.
     */
    public Card getHiddenCard() {
        return hiddenCard;
    }

    /**
     * Returns the dealer's current score (taking into account aces).
     *
     * @return the dealer's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Recalculates the dealer's score based on the cards in their hand.
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

        if (hiddenCard != null) {
            score += hiddenCard.value();
            if (hiddenCard.rank().equals("Туз")) {
                aces++;
            }
        }

        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }
    }

    /**
     * Returns the dealer's hand of cards.
     *
     * @return the list of cards in the dealer's hand.
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Returns the dealer's hand as a list of formatted strings, showing the rank, suit and value.
     *
     * @return a list of strings representing the dealer's hand.
     */
    public List<String> getHandAsStrings() {
        List<String> handAsStrings = new ArrayList<>();
        for (Card card : hand) {
            handAsStrings.add(card.rank() + " " + card.suit() + " (" + card.value() + ")");
        }
        return handAsStrings;
    }
}
