package org.example;

import java.util.Scanner;
/**
 * This is class for managing the game.
 */
public class BlackjackGame {

    private Deck deck;
    private Player player;
    private Dealer dealer;
    private int playerWins = 0;
    private int dealerWins = 0;
    private int roundNumber = 1;

    /**
     * Initializes the game with a new deck, player and dealer.
     */
    public BlackjackGame() {
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
    }

    /**
     * Starts the game, launches rounds.
     */
    public void startGame() {
        System.out.println("Добро пожаловать в Блэкджек!");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Раунд " + roundNumber);
            System.out.println("Дилер раздал карты");

            playRound(scanner);
            roundNumber++;

            System.out.println("Хотите сыграть еще раз? (да/нет)");
        } while (scanner.nextLine().equalsIgnoreCase("да"));
    }

    /**
     * Plays a single round handling player's and dealer's turns.
     *
     * @param scanner the input scanner for reading user commands.                    .
     */
    public void playRound(Scanner scanner) {
        deck.shuffle();
        player = new Player();
        dealer = new Dealer();

        player.addCard(deck.takeCard());
        player.addCard(deck.takeCard());

        dealer.addCard(deck.takeCard());
        dealer.setHiddenCard(deck.takeCard());

        printPlayerAndDealerHands(false);

        if (player.getScore() == 21 && dealer.getScore() == 21) {
            System.out.println("Оба получили блэкджек! Раунд ничья. Счёт " + getStats());
            return;
        } else if (player.getScore() == 21) {
            playerWins++;
            System.out.println("У вас блэкджек! Вы выиграли раунд. Счёт " + getStats());
            return;
        }

        System.out.println("\nВаш ход");
        System.out.println("-------");

        while (player.getScore() <= 21) {
            System.out.println("Введите '1', чтобы взять карту, и '0', чтобы остановиться...");
            String input = scanner.nextLine();

            if ("0".equals(input)) {
                break;
            } else if ("1".equals(input)) {
                player.addCard(deck.takeCard());
                System.out.println("Вы открыли карту " +
                    player.getHandAsStrings().get(player.getHand().size() - 1));
                printPlayerAndDealerHands(false);
            } else {
                System.out.println("Некорректный ввод! " +
                    "Пожалуйста, введите '1', чтобы взять карту, или '0', чтобы остановиться...");
            }
        }

        if (player.getScore() > 21) {
            dealerWins++;
            System.out.println("Перебор! Вы проиграли. Счет " + getStats());
            return;
        }

        System.out.println("\nХод дилера");
        System.out.println("-------");

        dealer.addCard(dealer.getHiddenCard());
        dealer.setHiddenCard(null);
        System.out.println("Дилер открывает закрытую карту " +
            dealer.getHandAsStrings().get(dealer.getHand().size() - 1));
        printPlayerAndDealerHands(true);

        if (dealer.getScore() == 21) {
            dealerWins++;
            System.out.println("У дилера блэкджек! Дилер выиграл раунд. Счет " + getStats());
            return;
        }

        while (dealer.getScore() < 17) {
            dealer.addCard(deck.takeCard());
            System.out.println("Дилер открывает карту " +
                dealer.getHandAsStrings().get(dealer.getHand().size() - 1));

            printPlayerAndDealerHands(true);
        }

        if (dealer.getScore() > 21) {
            playerWins++;
            System.out.println("Перебор у дилера! Вы выиграли раунд. Счет " + getStats());
        } else if (player.getScore() > dealer.getScore()) {
            playerWins++;
            System.out.println("У вас больше очков! Вы выиграли раунд. Счет " + getStats());
        } else {
            dealerWins++;
            System.out.println("У дилера больше очков! Дилер выиграл раунд. Счет " + getStats());
        }

        if (deck.getCards().size() < 13) {
            System.out.println("В колоде осталось недостаточно карт. Перетасовка...");
            deck = new Deck();
        }
    }

    /**
     * Prints cards of the player and dealer.
     * The dealer's hidden card is shown only if specified.
     *
     * @param showDealerHiddenCard whether to show the dealer's hidden card.
     */
    private void printPlayerAndDealerHands(boolean showDealerHiddenCard) {
        System.out.println("Ваши карты: " + player.getHandAsStrings() + " => " + player.getScore());
        if (showDealerHiddenCard) {
            System.out.println("Карты дилера: " +
                dealer.getHandAsStrings() + " => " + dealer.getScore());
        } else {
            System.out.println("Карты дилера: [" +
                dealer.getHandAsStrings().getFirst() + ", <закрытая карта>]");
        }
    }

    /**
     * Returns the current score of the entire game.
     *
     * @return the score.
     */
    private String getStats() {
        return playerWins + ":" + dealerWins + getLeader();
    }

    /**
     * Returns a string indicating the current leader of game.
     *
     * @return the string indicating leader.
     */
    private String getLeader() {
        if (playerWins > dealerWins) {
            return " в вашу пользу.";
        } else if (dealerWins > playerWins) {
            return " в пользу дилера.";
        } else {
            return " - ничья.";
        }
    }

    /**
     * Returns the current deck being used in the game.
     *
     * @return the deck of cards.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * The main launch point of the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.startGame();
    }
}