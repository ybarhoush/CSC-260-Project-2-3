package set.model;
import java.util.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Fluent interface for GameModel
 */
public class GameModel extends Observable{

    public static int SET_NUM = 3;
    public static int Cards_ON_TABLE = 12;
    public static int MAX_CARDS_ON_TABLE = 15;

    private DeckModel deck;
    private List<CardModel> cardsOnTable;
    private List<CardModel> selectedCards;
    private boolean isPlaying;

    public GameModel() {
        this.cardsOnTable = new ArrayList<>(MAX_CARDS_ON_TABLE);
        this.selectedCards = new ArrayList<>(SET_NUM);
    }

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
    public void removeSet() {
        for (CardModel selectedCard : selectedCards) {
            cardsOnTable.remove(selectedCard);
        }
        clearSelectedCards();
        setChanged();
        notifyObservers();
    }
    public boolean canAddThreeCards() {
        if ((cardsOnTable.size() <= Cards_ON_TABLE) && (deck.hasThreeCards())) {
            return true;
        } else {
            return false;
        }
    }
    public void addThreeCards() {
        for (int i = 0; i <= 2; i++) {
            cardsOnTable.add(deck.dealOne());
        }
        setChanged();
        notifyObservers();
    }
    public boolean isSet() {
        if (threeCardsSelected()) {
            return (checkSet(selectedCards.get(0), selectedCards.get(1), selectedCards.get(2)));
        } else {
            return false;
        }
    }
    public boolean checkSet(CardModel one, CardModel two, CardModel three) {
        return (checkColor(one, two, three) && checkShape(one, two, three)
                && checkShade(one, two, three) && checkNum(one, two, three));
    }

    public boolean threeCardsSelected() {
        return selectedCards.size() == SET_NUM;
    }
    /** Getters */
    public List<CardModel> getCardsOnTable() {
        return this.cardsOnTable;
    }
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
     * the following methods check whether
     * "A set msut be either all the same or all different in each individual feature"
     * from: http://www.setgame.com/sites/default/files/instructions/SET%20INSTRUCTIONS%20-%20ENGLISH.pdf
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
