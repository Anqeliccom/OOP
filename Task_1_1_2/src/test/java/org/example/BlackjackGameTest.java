package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameTest {

    @Test
    public void testInitialDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCards().size());
    }

    @Test
    public void testCardValue() {
        Card card = new Card("Пики", "Туз", 11);
        assertEquals(11, card.value());
    }

    @Test
    public void testPlayerHandValue() {
        Player player = new Player();
        player.addCard(new Card("Черви", "Туз", 11));
        player.addCard(new Card("Пики", "Десятка", 10));
        assertEquals(21, player.getScore());
    }

    @Test
    public void testDealerHiddenCard() {
        Dealer dealer = new Dealer();
        Card hiddenCard = new Card("Бубны", "Туз", 11);
        dealer.setHiddenCard(hiddenCard);
        assertEquals(hiddenCard, dealer.getHiddenCard());
    }
}
