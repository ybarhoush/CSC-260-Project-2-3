package model;
import java.util.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * GameModel is the backbone of the game. Using the observer pattern
 * the gameModel is able to see how the gameView changes (user input)
 * and reacts appropriately with proper methods.
 */
public class GameModel extends Observable{

    public static int PAIR_NUM = 2;
    public static int CARDS_ON_TABLE = 72;

    private DeckModel deck;
    private List<CardModel> cardsOnTable;
    private List<CardModel> selectedCards;
    private boolean isPlaying;

    /**
     * This creates a new GameModel that holds the cards displayed
     * and the cards that the user selects.
     */
    public GameModel() {
        this.cardsOnTable = new ArrayList<>(CARDS_ON_TABLE);
        this.selectedCards = new ArrayList<>(PAIR_NUM);
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
        dealSeventyTwo();
        setChanged();
        notifyObservers();
    }

    /**
     * This methods deals 12 cards on the table
     * when the game first starts.
     */
    private void dealSeventyTwo() {
        for (int i = 0; i < CARDS_ON_TABLE; i++) {
            cardsOnTable.add(this.deck.dealOne());
        }
        setChanged();
        notifyObservers();
    }

    /**
     * If the two cards that the user selects are a pair,
     * this method removes those cards.
     */
    public void removePair() {
        for (CardModel selectedCard : selectedCards) {
            cardsOnTable.remove(selectedCard);
        }
        clearSelectedCards();
        setChanged();
        notifyObservers();
    }

    /**
     * Checks to see if the 2 cards selected by the player are a pair.
     * @return true iff the two selected cards are a pair
     */
    public boolean isPair() {
        if (twoCardsSelected()) {
            return (isPair(selectedCards.get(0), selectedCards.get(1)));
        }
        return false;
    }

    /**
     * Helper method that checks all attributes of two cards against each other
     * to determine if they can be declared as a pair.
     * @param one cardModel
     * @param two cardModel
     * @return true iff the 2 given cards are a pair
     */
    public boolean isPair(CardModel one, CardModel two) {
        return (checkColor(one, two) && checkShape(one, two)
                && checkShade(one, two) && checkNum(one, two));
    }

    /**
     * Check whether one individual feature of two given cards is the same
     * @param a card1
     * @param b card2
     * @return true iff the feature is the same amongst both cards
     */
    private boolean checkColor(CardModel a, CardModel b){
        return (a.getColor() == b.getColor());
    }
    private boolean checkShape(CardModel a, CardModel b){
        return (a.getShape() == b.getShape());
    }
    private boolean checkShade(CardModel a, CardModel b){
        return (a.getShade() == b.getShade());
    }
    private boolean checkNum(CardModel a, CardModel b){
        return (a.getShapeNum() == b.getShapeNum());
    }

    /**
     * Checks to see if the player has selected two cards.
     * @return true iff 2 cards are selected
     */
    public boolean twoCardsSelected() {
        return selectedCards.size() == PAIR_NUM;
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

    /**
     *
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
}
