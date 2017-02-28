import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by David on 2/27/17.
 */
public class GameModel extends Observable{
    public static int SET_NUM = 2;
    public static int MAX_CARDS_ON_TABLE = 72;

    private DeckModel deck;
    private ArrayList<CardModel> cardsOnTable;
    private ArrayList<CardModel> selectedCards;
    private boolean isPlaying;


    public GameModel(){
        this.cardsOnTable = new ArrayList<>(MAX_CARDS_ON_TABLE);
        this.selectedCards = new ArrayList<>(SET_NUM);
    }

    public void newGame() {
        this.deck = new DeckModel();
        this.isPlaying = true;
        this.cardsOnTable.clear();
        this.selectedCards.clear();
        this.deck.shuffle();
        dealAll();
        setChanged();
        notifyObservers();
    }

    private void dealAll() {
        for (int i = 0; i < MAX_CARDS_ON_TABLE; i++) {
            cardsOnTable.add(this.deck.dealOne());
        }
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

    public void clearSelectedCards() {
        for (CardModel selectedCard : selectedCards) {
            selectedCard.unSelect();
        }
        selectedCards.clear();
        setChanged();
        notifyObservers();
    }

    public boolean hasTwoCardsSelected(){
        return selectedCards.size() == SET_NUM;
    }

    public List<CardModel> getSelectedCards() {
        return this.selectedCards;
    }
    public List<CardModel> getCardsOnTable() {
        return this.cardsOnTable;
    }

    public boolean isSet() {
        if (selectedCards.get(0).getNum() == selectedCards.get(1).getNum()) {
            return true;
        } else {
            return false;
        }
    }
    public void removeSet() {
        for (CardModel selectedCard : selectedCards) {
            cardsOnTable.remove(selectedCard);
        }
        clearSelectedCards();
        setChanged();
        notifyObservers();
    }
}
