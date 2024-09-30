import java.util.ArrayList;

public class Dealer extends Participant {
    Card hiddenSecondCard;

    public void drawHiddenCard(Deck deck) {
        hiddenSecondCard = deck.drawCard();
    }

    public void dealerPlay(Deck deck) {
        while (checkHandValue() < 17) {
            drawCard(deck);
        }
    }

    // Compare the initial dealer hand if blackjack
    public Boolean checkIfDealerBlackjack() {
        ArrayList<Card> actualDealerHand = new ArrayList<>(hand);
        actualDealerHand.add(hiddenSecondCard);
        Integer card1 = actualDealerHand.get(0).getValue();
        Integer card2 = actualDealerHand.get(1).getValue();

        if (card1 + card2 == 21) {
            System.out.println("Blackjack!");
            return true;
        } else {
            return false;
        }
    }
}
