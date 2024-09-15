package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> hand;
    private int score;

    public Player() {
        hand = new ArrayList<>();
        score = 0;
    }

    public void addCard(Card card) {
        hand.add(card);
        calculateScore();
    }

    public int getScore() {
        return score;
    }

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

    public List<Card> getHand() {
        return hand;
    }

    public List<String> getHandAsStrings() {
        List<String> handAsStrings = new ArrayList<>();
        for (Card card : hand) {
            handAsStrings.add(card.rank() + " " + card.suit());
        }
        return handAsStrings;
    }
}
