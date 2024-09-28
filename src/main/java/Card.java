public class Card {
	private final String rank;
	private final String suit;
	private final Integer cardValue;
	
	public Card(String rank, String suit, Integer cardValue) {
		this.rank = rank;
		this.suit = suit;
		this.cardValue = cardValue;
	}
	
	public Integer getValue() {
		return cardValue;
	}
	
    @Override
    public String toString() {
    	String str = "%s%s (%d)";
        return String.format(str, this.rank, this.suit, this.cardValue);
    }
}
