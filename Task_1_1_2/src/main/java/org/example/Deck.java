package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a deck contains 52 cards.
 * Supports shuffling, taking the top card and getting cards.
 */
public class Deck {
    private final LinkedList<Card> cards;

    private static final String[] suits = {"Пики", "Черви", "Бубны", "Трефы"};

    private static final Card.RankAndValues[] rankAndValues = {
        new Card.RankAndValues("Двойка", 2),
        new Card.RankAndValues("Тройка", 3),
        new Card.RankAndValues("Четверка", 4),
        new Card.RankAndValues("Пятерка", 5),
        new Card.RankAndValues("Шестерка", 6),
        new Card.RankAndValues("Семерка", 7),
        new Card.RankAndValues("Восьмерка", 8),
        new Card.RankAndValues("Девятка", 9),
        new Card.RankAndValues("Десятка", 10),
        new Card.RankAndValues("Валет", 10),
        new Card.RankAndValues("Дама", 10),
        new Card.RankAndValues("Король", 10),
        new Card.RankAndValues("Туз", 11)
    };

    /**
     * Initializes and shuffles the deck.
     */
    public Deck() {
        cards = new LinkedList<>();

        for (String suit : suits) {
            for (Card.RankAndValues rv : rankAndValues) {
                cards.add(new Card(suit, rv.rank(), rv.value()));
            }
        }
        shuffle();
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Removes and returns the top card from the deck.
     *
     * @return the card that is taken from the deck.
     */
    public Card takeCard() {
        return cards.removeFirst();
    }

    /**
     * Returns the list of cards currently in the deck.
     */
    public List<Card> getCards() {
        return cards;
    }
}
