package ex4.main;

import java.util.Scanner;

/**
 * 
 * @author Pascal Gepperth (4005085)
 *
 */
public class BlackJack {

	// Java-constants
	private static final int MIN_CARD_VALUE = 1;
	private static final int MAX_CARD_VALUE = 11;

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// Das Spiel soll mit dem Aufruf der Funktion startGame(int startMoney)
		// gestartet werden.
		startGame(1000);

	}

	public static void startGame(int startMoney) {
		System.out.println("Welcome to BlackJack");
		int currentBalance = startMoney;
		int cardValuePlayer = 0;
		int cardValueBank = 0;
		int bet;

		// get bet
		System.out.println("Player's bet:");
		bet = sc.nextInt();

		if (validBetPlayer(currentBalance, bet) == -1) {
			startGame(currentBalance);
			return;
		}
		currentBalance -= bet;

		// player plays
		do {
			cardValuePlayer += giveCard();
			System.out.println("Player's card value: " + cardValuePlayer);
		} while (cardValuePlayer < 21 && oneMoreCard());

		// if player not exceeding 21
		if (cardValuePlayer < 21) {
			// bank plays
			while (cardValueBank <= 17 && cardValueBank < cardValuePlayer) {
				cardValueBank += giveCard();
				System.out.println("Bank's card value: " + cardValueBank);
			}
		}

		String winner = evaluateWinner(cardValuePlayer, cardValueBank);
		currentBalance = updateMoney(currentBalance, bet, winner);

		if (currentBalance > 0 && yesNoDialogue("Next round?")) {
			startGame(currentBalance);
		}
	}

	public static int validBetPlayer(int currentBalance, int bet) {
		if (bet < 1 || currentBalance - bet < 0) {
			System.out.println("Invalid input.");
			return -1;
		}
		return bet;
	}

	public static String evaluateWinner(int cardValuePlayer, int cardValueBank) {

		/*
		 * If player has Black Jack, or bank exceeding 21, or player has more than bank
		 * while both not exceeding 21
		 */
		if (cardValuePlayer == 21 || cardValuePlayer < 21 && cardValueBank > 21
				|| cardValuePlayer < 21 && cardValuePlayer > cardValueBank) {
			return "player";
		}
		/*
		 * If bank not exceeding 21 and both has same cardValue
		 */
		else if (cardValueBank < 21 && cardValuePlayer == cardValueBank) {
			return "both";
		}
		/*
		 * Otherwise bank wins
		 */
		return "bank";
	}

	public static int updateMoney(int currentBalance, int bet, String winner) {
		int change = 0;

		if (winner == "player") {
			change = 2 * bet;
		} else if (winner == "both") {
			change = bet;
		}
		int newBalance = currentBalance + change;
		System.out.println("New balance: " + newBalance);
		return newBalance;
	}

	/**
	 * Asks the player for one more card.
	 * 
	 * @return Player's decision.
	 */
	public static boolean oneMoreCard() {
		return yesNoDialogue("One more Card?");
	}

	/**
	 * Asks a question that can be answered with "yes" or "no". The following user
	 * inputs returns a positive answer:
	 * 
	 * j, y, ja, yes, 1, true
	 * 
	 * The following input will return a negative answer:
	 * 
	 * n, no, nein, 0, false
	 * 
	 * @return Player's decision.
	 */
	private static boolean yesNoDialogue(String question) {
		System.out.println(question + "(y/n)");
		String userInput = sc.next();

		// prepare string
		userInput = userInput.toLowerCase();
		userInput = userInput.trim();

		switch (userInput) {
		case "j":
		case "y":
		case "ja":
		case "yes":
		case "1":
		case "true":
			return true;
		case "n":
		case "no":
		case "nein":
		case "0":
		case "false":
			return false;
		default:
			return yesNoDialogue(question);
		}
	}

	/**
	 * 
	 * @return A random card value.
	 */
	public static int giveCard() {
		return (int) (Math.random() * (MAX_CARD_VALUE - MIN_CARD_VALUE + 1) + MIN_CARD_VALUE);
	}

}