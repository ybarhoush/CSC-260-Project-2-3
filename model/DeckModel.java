package model;

import java.util.Collections;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class creates a deck of cards which will be used
 * to play the game of set.
 */
public class DeckModel {

    private ArrayList<CardModel> deck;  //holds 81 cards
    private int index;                  //points next unseen card


    protected final int DECK_LENGTH = 81;   //total cards in deck

    /**
     * Creates a new deck object.
     */
    public DeckModel(){
        this.deck = new ArrayList<>(DECK_LENGTH);
        createDeck();
        this.index = 0;
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle(){
        Collections.shuffle(this.deck);
    }

    /**
     * Generates cards to fill the deck.
     */
    public void createDeck(){
        Color[] colors = new Color[]{Color.red, Color.green, Color.blue};
        for (int i = 0; i < DECK_LENGTH; i++){
            for (int number = 1; number < 4; number++){
                for (CardModel.Shape shape : CardModel.Shape.values()){
                    for (CardModel.Shade shade : CardModel.Shade.values()){
                        for (Color color : colors){
                            this.deck.add(i, new CardModel(number, shape, shade, color));
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
            CardModel returnCard = deck.get(this.index);
            this.index++;
            return returnCard;
        }
        return null;
    }

    /**
     * @return true iff deck has at least three cards left.
     */
    public boolean hasThreeCards(){
        if (this.index <= this.DECK_LENGTH - 3){
            return true;
        }
        return false;
    }

    /**
     * @return true iff deck is empty
     */
    private boolean isEmpty(){
        if (this.index < this.DECK_LENGTH - 1){
            return false;
        }
        return true;
    }
}
