import java.util.ArrayList;

public class Dealer extends Participant {
    Card hiddenSecondCard;

    public void drawHiddenCard(Deck deck) {
        hiddenSecondCard = deck.drawCard();
    }

    public void dealerPlay(Deck deck) {
        boolean dealerTurnOver = false;
        hand.add(hiddenSecondCard);

        checkAce();

        while (checkHandValue() < 17 && !dealerTurnOver && !isBusted()) {
            drawCard(deck);
            checkAce();

            if (isBusted()) {
                dealerTurnOver = true;
                bust = true;
            }
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
