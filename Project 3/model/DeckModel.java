package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * DeckModel.java
 * Represents a deck of cards, which will be used to play the game of set.
 */
public class DeckModel {

    protected final int DECK_LENGTH = 72;   //total cards in deck

    private ArrayList<CardModel> deck;      //holds 72 cards
    private int index;                      //points next unseen card

    /**
     * Creates a new deck object.
     */
    public DeckModel() {
        this.deck = new ArrayList<>(DECK_LENGTH);
        createDeck();
        this.index = DECK_LENGTH - 1;
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    /**
     * Produces 36 unique cards (no two cards are a pair).
     * Then it duplicates 36 unique cards to ensure that each card only
     * has one duplicate (together they form a pair).
     */
    private void createDeck() {
        Color[] colors = new Color[]{Color.orange, Color.magenta, Color.blue};
        for (int r = 0; r < 2; r++) {
            int i = 0;
            for (int number = 1; number <= 2; number++) {
                for (CardModel.Shape shape : CardModel.Shape.values()) {
                    for (CardModel.Shade shade : CardModel.Shade.values()) {
                        for (Color color : colors) {
                            this.deck.add(i + (r * (DECK_LENGTH / 2)), new CardModel(number, shape, shade, color));
                            i++;
                        }
                    }
                }
            }
        }
    }


    /**
     * Deals one card from the deck.
     *
     * @return first card in deck that has not yet been seen by player
     */
    public CardModel dealOne() {
        if (!isEmpty()) {
            CardModel returnCard = deck.remove(this.index);
            this.index--;
            return returnCard;
        }
        return null;
    }

    /**
     * Returns 1 if the deck is empty.
     *
     * @return true iff the deck is empty
     */
    private boolean isEmpty() {
        if (this.deck.size() > 0) {
            return false;
        }
        return true;
    }


    /**
     * Tester methods that checks if the pairs add up to 36.
     *
     * @return the number of pairs
     */
    public int hasThirtySixPairs() {
        int q = 0;
        for (int i = 0; i < 72; i++) {
            for (int j = (i + 1); j < this.deck.size(); j++) {
                CardModel a = this.deck.get(i);
                CardModel b = this.deck.get(j);

                if (isPair(a, b)) {
                    q++;
                }
            }
        }
        return q;
    }

    /**
     * Tester method that returns 1 if there is no duplicate cards in the deck.
     *
     * @return 0 or 1
     */
    public boolean halfDeckIsUnique() {
        if (this.halfDeckHasNoDuplicate() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Tester method that returns the number of duplicate cards in the half of the deck.
     *
     * @return the number of duplicate cards int he half of the deck
     */
    public int halfDeckHasNoDuplicate() {
        int r = 0;
        for (int i = 0; i < 36; i++) {
            for (int j = (i + 1); j < 36; j++) {
                CardModel a = this.deck.get(i);
                CardModel b = this.deck.get(j);

                if (isPair(a, b)) {
                    r++;
                    break;
                }
            }
            break;
        }
        return r;
    }

    /**
     * Checks if the two selected cards are a pair or not.
     * Returns 1 if they are a pair.
     *
     * @param a Card object
     * @param b Card object
     * @return 0 or 1
     */
    private boolean isPair(CardModel a, CardModel b) {
        return (checkColor(a, b) && checkShape(a, b) && checkShade(a, b) && checkNum(a, b));
    }

    /**
     * Checks the color of two cards. Returns 1 if they have the same color.
     *
     * @param a Card object
     * @param b Card object
     * @return 0 or 1
     */
    private boolean checkColor(CardModel a, CardModel b) {
        return (a.getColor() == b.getColor());
    }

    /**
     * Checks the shape of two cards. Returns 1 if they have the same shape.
     *
     * @param a Card object
     * @param b Card object
     * @return 0 or 1
     */
    private boolean checkShape(CardModel a, CardModel b) {
        return (a.getShape() == b.getShape());
    }

    /**
     * Checks the shade of the shape of two cards. Returns 1 if they have the same shade.
     *
     * @param a Card object
     * @param b Card object
     * @return 0 or 1
     */
    private boolean checkShade(CardModel a, CardModel b) {
        return (a.getShade() == b.getShade());
    }

    /**
     * Checks the number of the shapes on two cards. Returns 1 if they have the same number of shapes.
     *
     * @param a Card object
     * @param b Card object
     * @return 0 or 1
     */
    private boolean checkNum(CardModel a, CardModel b) {
        return (a.getShapeNum() == b.getShapeNum());
    }
}
