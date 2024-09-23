package org.example;

/**
 * Represents a card in a deck containing a suit, rank and value.
 * This class is implemented as a record to be immutable.
 *
 * @param suit the suit of the card.
 * @param rank the rank of the card.
 * @param value the numeric value associated with the card for scoring purposes.
 */
public record Card(String suit, String rank, int value) {

    /**
     * Represents the rank and associated value of card.
     * Used for initializing cards with their corresponding values.
     *
     * @param rank the rank of the card
     * @param value the numeric value of the card
     */
    public record RankAndValues(String rank, int value) {
    }
}
