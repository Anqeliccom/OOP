package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Dealer} class.
 */
public class DealerTest {

    @Test
    public void testAddCardUpdateScore() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card("Пики", "Тройка", 3));
        assertEquals(3, dealer.getScore());
    }

    @Test
    public void testHiddenCard() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card("Черви", "Тройка", 3));
        dealer.setHiddenCard(new Card("Пики", "Туз", 11));
        assertEquals(14, dealer.getScore());
    }

    @Test
    public void testAceSetupWithHiddenCard() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card("Черви", "Десятка", 10));
        dealer.setHiddenCard(new Card("Пики", "Туз", 11));
        dealer.addCard(new Card("Бубны", "Восьмерка", 8));
        assertEquals(19, dealer.getScore());
    }

    @Test
    public void testHandAsStrings() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card("Пики", "Десятка", 10));
        dealer.addCard(new Card("Черви", "Туз", 11));
        assertEquals("Десятка Пики (10)", dealer.getHandAsStrings().get(0));
        assertEquals("Туз Черви (11)", dealer.getHandAsStrings().get(1));
    }
}
