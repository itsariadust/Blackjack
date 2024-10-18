import java.util.Collections;
import java.util.Stack;

public class Deck {
	private final Stack<Card> cards;
	
	public Deck() {
		cards = new Stack<>();
		// Arrays of the ranks, suits, and values
		// Note: Find a way to make the ace a 1 or an 11
		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"♥", "♦", "♣", "♠"};
        Integer[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

		// Create a deck equivalent to eight 52-card decks
		int decksCount = 8;
		for (int deck = 0; deck < decksCount; deck++) {
			for (String suit : suits) {
				for (int rankIndex = 0; rankIndex < ranks.length; rankIndex++) {
					String rank = ranks[rankIndex];
					int value = values[rankIndex];
					cards.push(new Card(rank, suit, value));
				}
			}
		}

		// Shuffle the deck
		Collections.shuffle(cards);
	}

	// Draw a card from the deck
	public Card drawCard() {
		return cards.isEmpty() ? null : cards.pop();
	}

	// Checks the remaining cards in the deck
	public Integer checkRemainingCards() {
		return cards.size();
	}
}
