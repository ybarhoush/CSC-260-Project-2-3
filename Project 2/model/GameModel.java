package model;

import java.util.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * GameModel.java
 * Represents the actual game as a fluent interface for GameModel
 */
public class GameModel extends Observable {

    public static int SET_NUM = 3;
    public static int Cards_ON_TABLE = 12;
    public static int MAX_CARDS_ON_TABLE = 15;

    private DeckModel deck;
    private List<CardModel> cardsOnTable;
    private List<CardModel> selectedCards;
    private boolean isPlaying;

    /**
     * Constructor that creates new GameModel.
     */
    public GameModel() {
        this.cardsOnTable = new ArrayList<>(MAX_CARDS_ON_TABLE);
        this.selectedCards = new ArrayList<>(SET_NUM);
    }

    /**
     * Creates a new game.
     */
    public void newGame() {
        this.deck = new DeckModel();
        this.isPlaying = true;
        this.cardsOnTable.clear();
        this.selectedCards.clear();
        this.deck.shuffle();
        dealTwelve();
        setChanged();
        notifyObservers();
    }

    /**
     * Deals 12 cards on the table when the game first starts.
     */
    private void dealTwelve() {
        for (int i = 0; i < Cards_ON_TABLE; i++) {
            cardsOnTable.add(this.deck.dealOne());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Removes a set of cards.
     */
    public void removeSet() {
        for (CardModel selectedCard : selectedCards) {
            cardsOnTable.remove(selectedCard);
        }
        clearSelectedCards();
        setChanged();
        notifyObservers();
    }

    /**
     * True iff the cards on the table do not exceed the maximum number of cards
     * to be served on the table and the deck has at least three cards left in it.
     *
     * @return true or false
     */
    public boolean canAddThreeCards() {
        if ((cardsOnTable.size() <= Cards_ON_TABLE) && (deck.hasThreeCards())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds three cards.
     */
    public void addThreeCards() {
        for (int i = 0; i <= 2; i++) {
            cardsOnTable.add(deck.dealOne());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Checks if the selected cards are a set.
     *
     * @return true or false
     */
    public boolean isSet() {
        if (threeCardsSelected()) {
            return (checkSet(selectedCards.get(0), selectedCards.get(1), selectedCards.get(2)));
        } else {
            return false;
        }
    }

    /**
     * Checks if the selected cards are a set.
     *
     * @param one   the first card
     * @param two   the second card
     * @param three the third card
     * @return true or false
     */
    public boolean checkSet(CardModel one, CardModel two, CardModel three) {
        return (checkColor(one, two, three) && checkShape(one, two, three)
                && checkShade(one, two, three) && checkNum(one, two, three));
    }

    /**
     * Checks if three cards are selected.
     *
     * @return true or false
     */
    public boolean threeCardsSelected() {
        return selectedCards.size() == SET_NUM;
    }

    /**
     * Public getter methods.
     */
    public List<CardModel> getCardsOnTable() {
        return this.cardsOnTable;
    }

    public List<CardModel> getSelectedCards() {
        return this.selectedCards;
    }

    /**
     * Clears the selected cards and reverts to unselected state.
     */
    public void clearSelectedCards() {
        for (CardModel selectedCard : selectedCards) {
            selectedCard.unSelect();
        }
        selectedCards.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Adds cards to the user's selection
     *
     * @param c Card selected
     */
    public void addCardToSelection(CardModel c) {
        if (!selectedCards.contains(c)) {

            selectedCards.add(c);

        } else {
            selectedCards.remove(c);
        }
        c.unSelect();
        setChanged();
        notifyObservers();
    }

    /**
     * Checks is there is a set among the selected cards and returns a set if it exists.
     *
     * @return addSet the set
     */
    public List<CardModel> getOneSet() {
        List<CardModel> addSet = new ArrayList<>();
        for (int i = 0; i < this.cardsOnTable.size(); i++) {                  // start at the first index
            for (int j = (i + 1); j < this.cardsOnTable.size(); j++) {        // start at the second index
                for (int k = (j + 1); k < this.cardsOnTable.size(); k++) {    // start at the third index

                    CardModel card1 = this.cardsOnTable.get(i);
                    CardModel card2 = this.cardsOnTable.get(j);
                    CardModel card3 = this.cardsOnTable.get(k);

                    if (checkSet(card1, card2, card3)) {

                        addSet.add(card1);
                        addSet.add(card2);
                        addSet.add(card3);

                        return addSet;
                    }
                }
            }
        }
        return addSet;
    }

    /**
     * Returns all the sets in the displayed cards.
     *
     * @return all the sets
     */
    public List<List<CardModel>> getAllSets() {
        List<List<CardModel>> allSets = new ArrayList<>();
        for (int i = 0; i < this.cardsOnTable.size(); i++) {                  // start at the first index
            for (int j = (i + 1); j < this.cardsOnTable.size(); j++) {        // start at the second index
                for (int k = (j + 1); k < this.cardsOnTable.size(); k++) {    // start at the third index

                    CardModel card1 = this.cardsOnTable.get(i);
                    CardModel card2 = this.cardsOnTable.get(j);
                    CardModel card3 = this.cardsOnTable.get(k);

                    if (checkSet(card1, card2, card3)) {
                        List<CardModel> addSet = new ArrayList<>();
                        addSet.add(card1);
                        addSet.add(card2);
                        addSet.add(card3);
                        allSets.add(addSet);
                        break;
                    }
                }
                break;
            }
        }
        return allSets;
    }

    /**
     * Checks whether the three cards selected have the same colors.
     *
     * @param a card
     * @param b card
     * @param c card
     * @return true if all the same or all different
     */
    private boolean checkColor(CardModel a, CardModel b, CardModel c) {
        return ((a.getColor() == b.getColor() &&
                b.getColor() == c.getColor() &&
                a.getColor() == c.getColor())
                || (a.getColor() != b.getColor() &&
                b.getColor() != c.getColor() &&
                a.getColor() != c.getColor()));
    }

    /**
     * Checks whether the three cards selected have the same shapes.
     *
     * @param a card
     * @param b card
     * @param c card
     * @return true if all the same or all different
     */
    private boolean checkShape(CardModel a, CardModel b, CardModel c) {
        return ((a.getShape() == b.getShape() &&
                b.getShape() == c.getShape() &&
                a.getShape() == c.getShape())
                || (a.getShape() != b.getShape() &&
                b.getShape() != c.getShape()) &&
                a.getShape() != c.getShape());
    }


    /**
     * Checks whether the three cards selected have the same shades.
     *
     * @param a card
     * @param b card
     * @param c card
     * @return true if all the same or all different
     */
    private boolean checkShade(CardModel a, CardModel b, CardModel c) {
        return ((a.getShade() == b.getShade() &&
                b.getShade() == c.getShade() &&
                a.getShade() == c.getShade())
                || (a.getShade() != b.getShade() &&
                b.getShade() != c.getShade()) &&
                a.getShade() != c.getShade());
    }


    /**
     * Checks whether the three cards selected have the same number of shapes.
     *
     * @param a card
     * @param b card
     * @param c card
     * @return true if all the same or all different
     */
    private boolean checkNum(CardModel a, CardModel b, CardModel c) {
        return ((a.getShapeNum() == b.getShapeNum() &&
                b.getShapeNum() == c.getShapeNum() &&
                a.getShapeNum() == c.getShapeNum())
                || (a.getShapeNum() != b.getShapeNum() &&
                b.getShapeNum() != c.getShapeNum() &&
                a.getShapeNum() != c.getShapeNum()));
    }
}
