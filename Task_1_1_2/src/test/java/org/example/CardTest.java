package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    public void testCardValue() {
        Card card = new Card("Черви", "Туз", 11);
        assertEquals(11, card.value());
    }

    @Test
    public void testCardToString() {
        Card card = new Card("Черви", "Туз", 11);
        assertEquals("Туз Черви", card.toString());
    }
}
