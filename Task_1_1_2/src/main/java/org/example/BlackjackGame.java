package org.example;

import java.util.Scanner;

public class BlackjackGame {

    private final Deck deck;
    private Player player;
    private Dealer dealer;
    private int playerWins = 0;
    private int dealerWins = 0;
    private int roundNumber = 1;

    public BlackjackGame() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
    }

    public void startGame() {
        System.out.println("Добро пожаловать в Блэкджек!");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Раунд " + roundNumber);
            System.out.println("Дилер раздал карты");

            playRound(scanner);
            roundNumber++;

            System.out.println("Счет: Игрок " + playerWins + ":" + dealerWins + " Дилер");
            System.out.println("Хотите сыграть еще раз? (да/нет)");
        } while (scanner.nextLine().equalsIgnoreCase("да"));
    }

    private void playRound(Scanner scanner) {
        deck.shuffle();
        player = new Player();
        dealer = new Dealer();

        player.addCard(deck.takeCard());
        player.addCard(deck.takeCard());

        dealer.addCard(deck.takeCard());
        dealer.setHiddenCard(deck.takeCard());

        printPlayerAndDealerHands(false);

        System.out.println("\nВаш ход");
        System.out.println("-------");

        while (player.getScore() < 21) {
            System.out.println("Введите '1', чтобы взять карту, и '0', чтобы остановиться...");
            String input = scanner.nextLine();

            if ("0".equals(input)) {
                break;
            } else if ("1".equals(input)) {
                player.addCard(deck.takeCard());
                System.out.println("Вы открыли карту " + player.getHandAsStrings().get(player.getHand().size() - 1));
                printPlayerAndDealerHands(false);
            } else {
                System.out.println("Некорректный ввод! Пожалуйста, введите '1', чтобы взять карту, или '0', чтобы остановиться...");
            }
        }

        if (player.getScore() > 21) {
            System.out.println("Перебор! Вы проиграли.");
            dealerWins++;
            return;
        }

        System.out.println("\nХод дилера");
        System.out.println("-------");

        while (dealer.getScore() < 17) {
            dealer.addCard(deck.takeCard());
            System.out.println("Дилер открывает карту " + dealer.getHandAsStrings().get(dealer.getHand().size() - 1));

            printPlayerAndDealerHands(true);
        }

        if (dealer.getScore() > 21) {
            System.out.println("Перебор у дилера! Вы выиграли раунд.");
            playerWins++;
        } else if (player.getScore() > dealer.getScore()) {
            System.out.println("Вы выиграли раунд!");
            playerWins++;
        } else {
            System.out.println("Дилер выиграл раунд.");
            dealerWins++;
        }
    }

    private void printPlayerAndDealerHands(boolean showDealerHiddenCard) {
        System.out.println("Ваши карты: " + player.getHandAsStrings() + " => " + player.getScore());
        if (showDealerHiddenCard) {
            System.out.println("Карты дилера: " + dealer.getHandAsStrings() + " => " + dealer.getScore());
        } else {
            System.out.println("Карты дилера: [" + dealer.getHandAsStrings().getFirst() + ", <закрытая карта>]");
        }
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.startGame();
    }
}