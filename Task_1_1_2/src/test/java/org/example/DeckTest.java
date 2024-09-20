package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Deck} class.
 * This class tests the creating deck and taking card.
 */
public class DeckTest {

    @Test
    public void testInitialDeck() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCards().size());
    }

    @Test
    void testTakeCard() {
        Deck deck = new Deck();
        int initialSize = deck.getCards().size();
        deck.takeCard();
        assertEquals(initialSize - 1, deck.getCards().size());
    }

}
