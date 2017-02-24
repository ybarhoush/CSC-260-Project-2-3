package model;
import java.util.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * GameModel is the backbone of the game. Using the observer pattern
 * the gamemodel is able to see how the gameView changes (user input)
 * and reacts appropriately with proper methods.
 */
public class GameModel extends Observable{

    public static int SET_NUM = 3;
    public static int Cards_ON_TABLE = 12;
    public static int MAX_CARDS_ON_TABLE = 15;

    private DeckModel deck;
    private List<CardModel> cardsOnTable;
    private List<CardModel> selectedCards;
    private boolean isPlaying;

    /**
     * This creates a new GameModel that holds the cards displayed
     * and the cards that the user selects.
     */
    public GameModel() {
        this.cardsOnTable = new ArrayList<>(MAX_CARDS_ON_TABLE);
        this.selectedCards = new ArrayList<>(SET_NUM);
    }

    /**
     * Creates a new Game by making a fresh deck, clearing any
     * previously displayed and selected cards. Next it shuffles
     * the deck then deals 12 cards to the player.
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
     * This methods deals 12 cards on the table
     * when the game first starts.
     */
    private void dealTwelve() {
        for (int i = 0; i < Cards_ON_TABLE; i++) {
            cardsOnTable.add(this.deck.dealOne());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * If the three cards that the user selects are a set,
     * this method removes those cards.
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
     * Checks to see if there are less than 15 cards on the table
     * and that at least 3 cards exist in the deck.
     * @return true iff <15 cards on table && deck has >3 cards
     */
    public boolean canAddThreeCards() {
        if ((cardsOnTable.size() <= Cards_ON_TABLE) && (deck.hasThreeCards())) {
            return true;
        }
        return false;
    }

    /**
     * Adds three cards to the table when requested by player and
     * there are less then 15 cards on the table, or when a set
     * has been removed and there are 9 cards on the table.
     */
    public void addThreeCards() {
        for (int i = 0; i <= 2; i++) {
            cardsOnTable.add(deck.dealOne());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Checks to see if the 3 cards selected by the player are a set.
     * @return true iff the three selected cards are a set
     */
    public boolean isSet() {
        if (threeCardsSelected()) {
            return (checkSet(selectedCards.get(0), selectedCards.get(1), selectedCards.get(2)));
        }
        return false;
    }

    /**
     * Helper method that checks all attributes of three cards against each other
     * to determine if they can be declared as a set.
     * @param one cardModel
     * @param two cardModel
     * @param three cardModel
     * @return true iff the 3 given cards are a set
     */
    public boolean checkSet(CardModel one, CardModel two, CardModel three) {
        return (checkColor(one, two, three) && checkShape(one, two, three)
                && checkShade(one, two, three) && checkNum(one, two, three));
    }

    /**
     * Checks to see if the player has selected three cards.
     * @return true iff 3 cards are selected
     */
    public boolean threeCardsSelected() {
        return selectedCards.size() == SET_NUM;
    }

    /**
     * Returns the cards that are currently displayed to the player
     * @return list of cards
     */
    public List<CardModel> getCardsOnTable() {
        return this.cardsOnTable;
    }

    /**
     * Returns the cards that the user has currently selected.
     * @return list of cards
     */
    public List <CardModel> getSelectedCards() {
        return this.selectedCards;
    }
    public void clearSelectedCards() {
        for (CardModel selectedCard : selectedCards) {
            selectedCard.unSelect();
        }
        selectedCards.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Adds card to list of cards that the user has selected.
     * @param c cardModel
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
     * Returns one possible set out of all the cards being currently
     * displayed to the user.
     * @return list of cards
     */
    public List <CardModel> getOneSet() {
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
     * Returns all the possible sets out of all the cards being currently
     * displayed to the user.
     * @return List of lists of cards
     */
    public List <List <CardModel>> getAllSets() {
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
     * Check whether one individual feature of three given cards is the same or all different.
     * @param a card1
     * @param b card2
     * @param c card3
     * @return true iff the feature is the same amongst all three cards or all different.
     */
    private boolean checkColor(CardModel a, CardModel b, CardModel c) {
        return ((a.getColor() == b.getColor() &&
                b.getColor() == c.getColor() &&
                a.getColor() == c.getColor())
                || (a.getColor() != b.getColor() &&
                b.getColor() != c.getColor() &&
                a.getColor() != c.getColor()));
    }

    private boolean checkShape(CardModel a, CardModel b, CardModel c) {
        return ((a.getShape() == b.getShape() &&
                b.getShape() == c.getShape() &&
                a.getShape() == c.getShape())
                || (a.getShape() != b.getShape() &&
                b.getShape() != c.getShape()) &&
                a.getShape() != c.getShape());
    }

    private boolean checkShade(CardModel a, CardModel b, CardModel c) {
        return ((a.getShade() == b.getShade() &&
                b.getShade() == c.getShade() &&
                a.getShade() == c.getShade())
                || (a.getShade() != b.getShade() &&
                b.getShade() != c.getShade()) &&
                a.getShade() != c.getShade());
    }

    private boolean checkNum(CardModel a, CardModel b, CardModel c) {
        return ((a.getShapeNum() == b.getShapeNum() &&
                b.getShapeNum() == c.getShapeNum() &&
                a.getShapeNum() == c.getShapeNum())
                || (a.getShapeNum() != b.getShapeNum() &&
                b.getShapeNum() != c.getShapeNum() &&
                a.getShapeNum() != c.getShapeNum()));
    }
}
