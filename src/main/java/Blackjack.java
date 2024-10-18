import java.util.Scanner;

public class Blackjack {
	// Initialize objects
	private static Deck deck;
	private static Player player;
	private static Dealer dealer;

	// Winner tally
	Integer dealerWins = 0;
	Integer playerWins = 0;

	// User input
	Scanner userInput = new Scanner(System.in);

	public Blackjack() {
		deck = new Deck();
		player = new Player(1000);
		dealer = new Dealer();
	}

	// Start the game
	public void startGame() {
		while (player.getMoney() > 0 && deck.checkRemainingCards() > 10) {
			round();
		}

		if (player.getMoney() <= 0) {
			System.out.println("You're out of money! Game over.");
		} else {
			System.out.println("Not enough cards left in the deck. Game over.");
		}

		endGame();
	}

	// Game round
	public void round() {
		getPlayerBet("What is your bet? ");

		dealer.drawCard(deck);
		player.drawCard(deck);
		dealer.drawHiddenCard(deck);
		player.drawCard(deck);
		player.checkAce();

		if (!dealer.checkIfDealerBlackjack() && !player.checkIfPlayerBlackjack()) {
			player.playerMove(deck);
			if (player.bust) {
				dealer.hand.add(dealer.hiddenSecondCard);
			} else {
				dealer.dealerPlay(deck);
			}
		}
		endRound();
	}

	// Reset the hands
	private void resetHands() {
		dealer.clearHand();
		player.clearHand();
		removeBust();
	}

	private void removeBust() {
		dealer.bust = false;
		player.bust = false;
	}

	// Get the player's bet
	public void getPlayerBet(String prompt) {
		System.out.print(prompt);
		double bet = userInput.nextDouble();
		player.setBet(bet);
		player.deductMoney();
		userInput.nextLine();
	}

	// Displays the hands of the dealer and the player.
	public static void displayHands() {
		clearScreen();
		System.out.println("Remaining cards: " + deck.checkRemainingCards());
		System.out.println("Player money: " + "$" + player.getMoney());
		System.out.println("Player bet: " + "$" + player.getBet());
		System.out.println("Dealer cards " + "(" + dealer.checkHandValue() + ")" + ": " + dealer.hand.toString()
				.replace("[","").replace("]",""));
		System.out.println("Player cards " + "(" + player.checkHandValue() + ")" + ": " + player.hand.toString()
				.replace("[","").replace("]",""));
	}

	// When the player wins
	public void playerWins() {
		playerWins += 1;
		if (player.checkIfPlayerBlackjack()) {
			player.setMoney(player.getBet() * 1.5);
		} else {
			player.setMoney(player.getBet() * 2);
		}
		resetHands();
	}

	// When the dealer wins
	public void dealerWins() {
		dealerWins += 1;
		resetHands();
	}

	// When it's a tie
	public void tie() {
		player.setMoney(player.getBet());
		resetHands();
	}

	// Compare hand values and determine the winner
    public void endRound() {
        Integer dealerHandValue = dealer.checkHandValue();
		Integer playerHandValue = player.checkHandValue();

		displayHands(); // Display final hands

        if (playerHandValue > 21) {
            System.out.println("Player busts! Dealer wins.\n");
            dealerWins();
        } else if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
            System.out.println("Player wins!\n");
            playerWins();
        } else if (dealerHandValue > playerHandValue) {
            System.out.println("Dealer wins!\n");
            dealerWins();
        } else {
            System.out.println("It's a tie!\n");
			tie();
        }
    }

	// End game
	public void endGame() {
		System.out.println("Dealer wins: " + dealerWins);
		System.out.println("Player wins: " + playerWins);
		String winner = (playerWins > dealerWins) ? "Player" : "Dealer";
		System.out.println(winner + " wins!");
	}

	// Primitive clear screen
	public static void clearScreen() {
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}

	// Main method
	public static void main(String[] args) {
		Blackjack game = new Blackjack();
		game.startGame();
	}
}