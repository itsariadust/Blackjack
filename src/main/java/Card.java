public class Card {
	private String rank;
	private String suit;
	private Integer cardValue;
	
	public Card(String rank, String suit, Integer cardValue) {
		this.rank = rank;
		this.suit = suit;
		this.cardValue = cardValue;
	}
	
	public Integer getValue() {
		return cardValue;
	}

	public void setValue(int newValue) {
		this.cardValue = newValue;
	}
	
    @Override
    public String toString() {
    	String str = "%s%s (%d)";
        return String.format(str, this.rank, this.suit, this.cardValue);
    }
}
