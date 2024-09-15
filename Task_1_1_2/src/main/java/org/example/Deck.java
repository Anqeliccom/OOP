package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
    private final LinkedList<Card> cards;

    public Deck() {
        cards = new LinkedList<>();

        String[] suits = {
                "Пики", "Черви", "Бубны", "Трефы"
        };

        String[] ranks = {
                "Двойка", "Тройка", "Четверка", "Пятерка",
                "Шестерка", "Семерка", "Восьмерка", "Девятка",
                "Десятка", "Валет", "Дама", "Король", "Туз"
        };

        int[] values = {
                2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11
        };

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card takeCard() {
        return cards.removeLast();
    }

    public List<Card> getCards() {
        return cards;
    }
}
