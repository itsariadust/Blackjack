import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Participant {
    protected ArrayList<Card> hand;

    static Boolean bust = false;

    public Participant() {
        hand = new ArrayList<>();
    }

    public void drawCard(@NotNull Deck deck) {
        hand.add(deck.drawCard());
    }

    public static Integer calcHandValue(@NotNull ArrayList<Card> hand) {
        Integer handValue = 0;
        for (Card card : hand) {
            handValue += card.getValue();
        }
        return handValue;
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
