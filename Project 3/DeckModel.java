import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by David on 2/27/17.
 */
public class DeckModel {
    private ArrayList<CardModel> deck;
    private int index;
    protected  final int DECK_LENGTH = 72;

    public DeckModel(){
        this.deck = new ArrayList<>(DECK_LENGTH);
        createDeck();
        this.index = 0;
    }

    public void shuffle(){
        Collections.shuffle(this.deck);
    }

    public void createDeck(){
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 36; j++){
                this.deck.add((36*i)+j, new CardModel((36*i)+j));
            }
        }
    }
    public CardModel dealOne(){
        if (!isEmpty()){
            CardModel returnCard = deck.get(this.index);
            this.index++;
            return returnCard;
        }
        return null;
    }
    private boolean isEmpty(){
        if (this.index < this.DECK_LENGTH - 1){
            return false;
        }
        return true;
    }
}
