package org.example;

public record Card(String suit, String rank, int value) {
    @Override
    public String toString() {
        return rank + " " + suit;
    }
}
