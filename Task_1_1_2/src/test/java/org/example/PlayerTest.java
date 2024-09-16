package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testAddCardUpdateScore() {
        Player player = new Player();
        player.addCard(new Card("Пики", "Тройка", 3));
        assertEquals(3, player.getScore());
    }

    @Test
    public void testSeveralCards() {
        Player player = new Player();
        player.addCard(new Card("Бубны", "Двойка", 2));
        player.addCard(new Card("Пики", "Семерка", 7));
        assertEquals(9, player.getScore());
    }

    @Test
    public void testAceSetup() {
        Player player = new Player();
        player.addCard(new Card("Бубны", "Туз", 11));
        player.addCard(new Card("Пики", "Десятка", 10));
        player.addCard(new Card("Черви", "Пятерка", 5));
        assertEquals(16, player.getScore());
    }

    @Test
    public void testHandAsStrings() {
        Player player = new Player();
        player.addCard(new Card("Пики", "Туз", 11));
        player.addCard(new Card("Черви", "Десятка", 10));
        assertEquals("Туз Пики", player.getHandAsStrings().get(0));
        assertEquals("Десятка Черви", player.getHandAsStrings().get(1));
    }
}
