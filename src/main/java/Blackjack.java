import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
	static Deck deck = new Deck();
	
	// Hands
	static ArrayList<Card> dealerHand = new ArrayList<>();
	static ArrayList<Card> playerHand = new ArrayList<>();
	static Card hiddenDealerCard; // hiddenDealerCard is for the hidden second card for the dealer's hand.
	
	// Winner tally
	static Integer dealerWins = 0;
	static Integer playerWins = 0;
	
	static Integer playerMoney = 1000;
	static Integer bet = 0;
	
	static Scanner userInput = new Scanner(System.in);
	
	// Set up the game
	public static void setUpGame() {
		dealerHand.add(deck.drawCard());
		playerHand.add(deck.drawCard());
		hiddenDealerCard = deck.drawCard();
		playerHand.add(deck.drawCard());
	}
	
	// Reset the game
	public static void resetGame() {
		dealerHand.clear();
		playerHand.clear();
		hiddenDealerCard = null;
	}
	
	public static void getBet(String prompt) {
		System.out.print(prompt);
		bet = userInput.nextInt();
		playerMoney -= bet;
		userInput.nextLine();
	}
	
	// When the player does a hit
	public static void playerHit() {
		playerHand.add(deck.drawCard());
		Integer playerHandValue = checkPlayerHandValue();
		if (playerHandValue >= 21) {
			dealerPlay();
		}
	}
	
	// When the player stands or is bust
	public static void dealerPlay() {
		dealerHand.add(hiddenDealerCard);
		while (checkDealerHandValue() < 17) {
			dealerHand.add(deck.drawCard());
		}
		endRound();
	}
	
	// When the player doubles. They can no longer hit.
	public static void doublePlay() {
		bet *= 2;
		playerHand.add(deck.drawCard());
		dealerPlay();
	}

	/*
	public static void splitHand() {
		Integer card1 = playerHand.get(0).getValue();
		Integer card2 = playerHand.get(1).getValue();
	}
	*/

    // Compare the initial dealer hand if blackjack
	public static void checkIfDealerBlackjack() {
        ArrayList<Card> actualDealerHand = new ArrayList<>(dealerHand);
		actualDealerHand.add(hiddenDealerCard);
		Integer card1 = actualDealerHand.get(0).getValue();
		Integer card2 = actualDealerHand.get(1).getValue();
		
		if (card1 + card2 == 21) {
			endRound();
		}
	}
	
	// Compare the initial player hand if blackjack
	public static void checkIfPlayerBlackjack() {
		Integer card1 = playerHand.get(0).getValue();
		Integer card2 = playerHand.get(1).getValue();
		
		if (card1 + card2 == 21) {
			endRound();
		}
	}
	
	public static Integer checkHandValue(@NotNull ArrayList<Card> hand) {
	    Integer handValue = 0;
	    for (Card card : hand) {
	        handValue += card.getValue();
	    }
	    return handValue;
	}

	public static Integer checkPlayerHandValue() {
	    return checkHandValue(playerHand);
	}

	public static Integer checkDealerHandValue() {
	    return checkHandValue(dealerHand);
	}

	
	// Displays the hands of the dealer and the player.
	public static void displayHands() {
		System.out.println("Player money: " + "$" + playerMoney);
		System.out.println("Player bet: " + "$" + bet);
		System.out.println("Dealer cards " + "(" + checkDealerHandValue() + ")" + ": " + dealerHand.toString()
				.replace("[","").replace("]",""));
		System.out.println("Player cards " + "(" + checkPlayerHandValue() + ")" + ": " + playerHand.toString()
				.replace("[","").replace("]",""));
	}
	
	// When the player wins
	public static void playerWins() {
		playerWins += 1;
		playerMoney += bet * 2;
		resetGame();
	}
	
	// When the dealer wins
	public static void dealerWins() {
		dealerWins += 1;
		resetGame();
	}

	public static void tie() {
		playerMoney += bet;
		resetGame();
	}
	
	// Compare hand values and determine the winner
    public static void endRound() {
        Integer playerHandValue = checkPlayerHandValue();
        Integer dealerHandValue = checkDealerHandValue();
        
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
        
        resetGame();
        setUpGame();
        getBet("What is your bet? ");
    }
	
	// Main method
	public static void main(String[] args) {
		setUpGame();
		getBet("What is your bet? ");
		while (true) {
			displayHands();
			checkIfDealerBlackjack();
			checkIfPlayerBlackjack();
			System.out.print("What's your move? ");
			String playerMove = userInput.nextLine().toLowerCase();
			
			switch(playerMove) {
				case "hit":
					playerHit();
					break;
				case "stand":
					dealerPlay();
					break;
				case "double":
					doublePlay();
					break;
			}
		}
	}
}