package controller;

import commandbars.MemoryCmdbarView;
import main.Editor;
import model.CardModel;
import model.GameModel;
import view.GameView;
import view.GameViewListener;

/**
 * MemoryGameController.java
 * Represents the controls of the game screen of the Memory mode.
 * This class holds the view and the model together and allows communication
 * between the data and what the user sees.
 */

public class MemoryGameController extends MemoryCmdbarView implements GameViewListener {

    protected static GameModel gameModel;
    protected static GameView gameView;
    protected Editor mainListener;


    /**
     * Constructor that initializes the game controller of the solitaire mode.
     * @param m    GameModel
     * @param v    GameView
     * @param main Editor
     */
    public MemoryGameController(GameModel m, GameView v, Editor main) {
        super();
        gameModel = m;
        gameView = v;
        mainListener = main;
    }

    /**
     * Makes a new solitaire game for the user (this includes a new a shuffled deck of cards).
     */
    public static void newGame() {
        gameModel.newGame();
    }

    /**
     * What to do when the user clicks a card. If two cards are selected, and if
     * they are a pair, then wait for user's next button click
     *
     * @param cardModel the card that the user clicks
     */
    public void cardClicked(CardModel cardModel) {
        if (!gameModel.twoCardsSelected() && !gameModel.getSelectedCards().contains(cardModel)) {
            gameModel.addCardToSelection(cardModel);
        }
    }

    /**
     * causes the two cards to be removed from the playing field and the pair counter to increase by 1
     * (the system must verify that the two cards really are a pair).
     */
    public static void removePair() {
        if ( gameModel.isPair()) {
                pairCounter.setText(gameModel.getPairCounter());
                //--implement >> toggle Highlight Color Green
                gameModel.removePair();
                turnOverCards();

            }
            else {
                //--implement >> toggle Highlight Color Red
        }
    }

    /**
     * Shows a hint by highlighting a set if a set exists amongst the displayed cards.
     */
    public static void turnOverCards() {
        if(gameModel.twoCardsSelected()){
            turnCounter.setText(gameModel.getTurnCounter());
            gameModel.turnOverCards();
        }
    }
}

