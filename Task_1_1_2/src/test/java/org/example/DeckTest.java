package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeckTest {

    @Test
    public void testInitialDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCards().size());
    }

    @Test
    public void testShuffle() {
        Deck deck = new Deck();
        deck.shuffle();
        assertEquals(52, deck.getCards().size());
    }
}
