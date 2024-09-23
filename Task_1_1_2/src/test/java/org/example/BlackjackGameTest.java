package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
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
    public void testPlayerAlwaysTakesCard() {
        game.playRound(new Scanner("1\n1\n1\n1\n1\n1\n1\n"));
        assertTrue(game.getDeck().getCards().size() >= 41);
        assertTrue(game.getStats().contains("1:0") || game.getStats().contains("0:1") || game.getStats().contains("0:0"));

        if (game.getStats().contains("0:0")) {
            assertEquals(21, game.getPlayer().getScore());
            assertEquals(2, game.getPlayer().getHand().size());
            assertEquals(21, game.getDealer().getScore());
            assertEquals(2, game.getDealer().getHand().size());
        }

        else if (game.getStats().contains("1:0")) {
            assertEquals(21, game.getPlayer().getScore());
            assertEquals(2, game.getPlayer().getHand().size());
        }

        else if (game.getStats().contains("0:1")) {
                assertTrue(game.getPlayer().getScore() > 21);
        }
    }

    @Test
    public void testPlayerNeverTakesCard() {
        game.playRound(new Scanner("0\n"));
        assertTrue(game.getDeck().getCards().size() <= 48);

        if (game.getStats().contains("0:0")) {
            assertEquals(21, game.getPlayer().getScore());
            assertEquals(2, game.getPlayer().getHand().size());
            assertEquals(21, game.getDealer().getScore());
            assertEquals(2, game.getDealer().getHand().size());
        }

        else if (game.getStats().contains("1:0")) {
            if ((game.getDealer().getScore() > 17) && (game.getDealer().getScore() <=21) && (game.getDealer().getScore() != 21)) {
                assertTrue((game.getDealer().getScore()) < (game.getPlayer().getScore()));
            } else if (game.getPlayer().getScore() != 21) {
                assertTrue((game.getDealer().getScore() > 21));
            } else {
                assertTrue((game.getPlayer().getScore() == 21));
            }
        }

        else if (game.getStats().contains("0:1")) {
            if ((game.getDealer().getScore() > 17) && (game.getDealer().getScore() <=21) && (game.getPlayer().getScore() != 21)) {
                assertTrue((game.getDealer().getScore()) > (game.getPlayer().getScore()));
            } else {
                assertTrue((game.getDealer().getScore() == 21));
            }
        }
    }

    @Test
    public void testDeckReshufflesWhenLessThan13Cards() {
        while (game.getDeck().getCards().size() > 12) {
            game.getDeck().takeCard();
        }
        assertTrue(game.getDeck().getCards().size() < 13);
        game.playRound(new Scanner("0\n"));
        assertEquals(52, game.getDeck().getCards().size());
    }
}
