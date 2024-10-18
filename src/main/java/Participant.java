import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Participant {
    final protected ArrayList<Card> hand;

    Boolean bust = false;

    public Participant() {
        hand = new ArrayList<>();
    }

    public void drawCard(@NotNull Deck deck) {
        hand.add(deck.drawCard());
    }

    public Integer calcHandValue(@NotNull ArrayList<Card> hand) {
        Integer handValue = 0;
        for (Card card : hand) {
            handValue += card.getValue();
        }
        return handValue;
    }

    public void checkAce() {
        Card lastCard = hand.getLast();
        Integer lastCardValue = lastCard.getValue();
        if (lastCardValue == 11) {
            ace(lastCard);
        }
    }

    public void ace(Card aceCard) {
        Integer handValue = checkHandValue();
        if (handValue > 21) {
            aceCard.setValue(1);
        }
    }

    public Integer checkHandValue() {
        return calcHandValue(hand);
    }

    public Boolean isBusted() {
        return calcHandValue(hand) > 21;
    }

    public void clearHand() {
        hand.clear();
    }
}
