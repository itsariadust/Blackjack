import java.util.Collections;
import java.util.Stack;

class Deck {
	private final Stack<Card> cards;
	
	public Deck() {
		cards = new Stack<>();
		String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"H", "D", "C", "S"};
        Integer[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
        
        for (String suit : suits) {
        	for (int i = 0; i < ranks.length; i++) {
        		cards.push(new Card(ranks[i], suit, values[i]));
        	}
        }
        Collections.shuffle(cards);
	}
	
	public Card drawCard() {
		return cards.isEmpty() ? null : cards.pop();
	}
	
	public boolean isEmpty() {
        return cards.isEmpty();
    }
}
