import java.util.Scanner;

public class Player extends Participant {
    private double money;
    private double bet;
    Scanner userInput = new Scanner(System.in);

    public Player(double initialMoney) {
        super();
        this.money = initialMoney;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
        this.money -= this.bet;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money += money;
    }

    // Player's move
    public void playerMove(Deck deck) {
        boolean playerTurnOver = false;
        while (!playerTurnOver && !isBusted()) {
            Blackjack.displayHands();
            System.out.print("What's your move? ");
            String playerMove = userInput.nextLine().toLowerCase();
            switch(playerMove) {
                case "hit":
                    hit(deck);
                    break;
                case "stand":
                    playerTurnOver = true;
                    break;
                case "double":
                    doublePlay(deck);
                    break;
            }

            if (isBusted()) {
                playerTurnOver = true;
                bust = true;
            }
        }
    }

    public void hit(Deck deck) {
        drawCard(deck);
        checkAce();
    }

    public void doublePlay(Deck deck) {
        setBet(bet);
        drawCard(deck);
        checkAce();
    }

    // Compare the initial player hand if blackjack
    public Boolean checkIfPlayerBlackjack() {
        Integer card1 = hand.get(0).getValue();
        Integer card2 = hand.get(1).getValue();

        if (card1 + card2 == 21) {
            System.out.println("Blackjack!");
            return true;
        } else {
            return false;
        }
    }
}
