public class Player extends Participant {
    private double money;
    private double bet;

    public Player(double initialMoney) {
        super();
        this.money = initialMoney;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
        setMoney(this.money - this.bet);
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money += money;
    }

    public void hit(Deck deck) {
        drawCard(deck);
    }

    public void doublePlay(Deck deck) {
        setBet(bet);
        drawCard(deck);
    }

    // Compare the initial player hand if blackjack
    public Boolean checkIfPlayerBlackjack() {
        Integer card1 = hand.get(0).getValue();
        Integer card2 = hand.get(1).getValue();

        return card1 + card2 == 21;
    }
}
