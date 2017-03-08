package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class creates a deck of cards which will be used
 * to play the game of set.
 */
public class DeckModel {

    protected final int DECK_LENGTH = 72;   //total cards in deck

    private ArrayList<CardModel> deck;      //holds 72 cards
    private int index;                      //points next unseen card

    /**
     * Creates a new deck object.
     */
    public DeckModel(){
        this.deck = new ArrayList<>(DECK_LENGTH);
        createHalfDeck();
//        duplicateDeck();
        this.index = DECK_LENGTH - 1;
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle(){
        Collections.shuffle(this.deck);
    }

    /**
     * This method duplicates 36 unique cards to ensure that
     * each card only has one duplicate (together they form a pair).
     */
    private void duplicateDeck(){
        createHalfDeck();
        for (int j = 0; j < 36; j++){
            this.deck.add(36+j, this.deck.get(j));
        }
    }

    /**
     * This method produces 36 unique cards (no two cards are a pair).
     */
    private void createHalfDeck(){
        Color[] colors = new Color[]{Color.orange, Color.magenta, Color.blue};
        for (int r = 0; r < 2; r++){
            int i = 0;
            for (int number = 1; number <= 2; number++){
                for (CardModel.Shape shape : CardModel.Shape.values()){
                    for (CardModel.Shade shade : CardModel.Shade.values()){
                        for (Color color : colors){
                            this.deck.add(i+(r*(DECK_LENGTH/2)), new CardModel(number, shape, shade, color));
                            i++;
                        }
                    }
                }
            }
        }
    }



    /**
     * Deals one card from the deck.
     * @return first card in deck that has not yet been seen by player
     */
    public CardModel dealOne(){
        if (!isEmpty()){
            CardModel returnCard = deck.remove(this.index);
            this.index--;
            return returnCard;
        }
        return null;
    }

    /**
     * @return true iff deck is empty
     */
    private boolean isEmpty(){
        if (this.deck.size() > 0){
            return false;
        }
        return true;
    }


    /**
     * Beyond this point are tester methods
     * @return
     */
    public int hasThirtySixPairs(){
        int q = 0;
        for (int i = 0; i < 72; i++){
            for (int j = (i+1); j < this.deck.size(); j++){
                CardModel a = this.deck.get(i);
                CardModel b = this.deck.get(j);

                if (isPair(a, b)){
                    q++;
                }
            }
        }
        return q;
    }
    public boolean halfDeckIsUnique(){
        if (this.halfDeckHasNoDuplicate() == 0){
            return true;
        }
        return false;
    }
    public int halfDeckHasNoDuplicate(){
        int r = 0;
        for (int i = 0; i<36; i++){
            for (int j = (i+1); j < 36; j++){
                    CardModel a = this.deck.get(i);
                    CardModel b = this.deck.get(j);

                    if (isPair(a, b)){
                        r++;
                        break;
                    }
            }
            break;
        }
        return r;
    }


    private boolean isPair(CardModel a, CardModel b){
        return (checkColor(a, b) && checkShape(a, b) && checkShade(a, b) && checkNum(a, b));
    }

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
}
