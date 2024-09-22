package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Card} class.
 * This class tests the value retrieval and string representation of card.
 */
public class CardTest {

    @Test
    public void testCardInitialization() {
        Card card = new Card("Черви", "Туз", 11);
        assertEquals("Черви", card.suit());
        assertEquals("Туз", card.rank());
        assertEquals(11, card.value());
    }
}
