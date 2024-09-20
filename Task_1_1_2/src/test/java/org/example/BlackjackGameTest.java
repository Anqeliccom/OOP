package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link BlackjackGame} class.
 * This class tests the updating deck when there are not enough cards for the next round.
 */
public class BlackjackGameTest {

    private BlackjackGame game;

    @BeforeEach
    public void setUp() {
        game = new BlackjackGame();
    }

    @Test
    public void testDeckReshufflesWhenLessThan13Cards() {
        while (game.getDeck().getCards().size() > 12) {
            game.getDeck().takeCard();
        }
        assertTrue(game.getDeck().getCards().size() < 13);
        game.playRound(new java.util.Scanner("0\n"));
        assertEquals(52, game.getDeck().getCards().size());
    }
}
