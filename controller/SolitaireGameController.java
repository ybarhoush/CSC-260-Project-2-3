package controller;

import main.Editor;
import model.CardModel;
import model.GameModel;
import view.GameView;
import view.GameViewListener;
import commandbars.SolitaireCmdbarView;

import java.util.List;

/**
 * SolitaireGameController.java
 * Represents the controls of the game screen of the Solitaire mode.
 * This class holds the view and the model together and allows communication
 * between the data and what the user sees.
 */

public class SolitaireGameController extends SolitaireCmdbarView implements GameViewListener {

    protected static GameModel gameModel;
    protected static GameView gameView;
    protected Editor mainListener;

    /**
     * Constructor that initializes the game controller of the solitaire mode.
     * @param m    GameModel
     * @param v    GameView
     * @param main Editor
     */
    public SolitaireGameController(GameModel m, GameView v, Editor main) {
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
     * What to do when the user clicks a card. If three cards are selected, and if
     * they are a set, then it removes the set and deals three cards.
     *
     * @param cardModel the card that the user clicks
     */
    public void cardClicked(CardModel cardModel) {
        gameModel.addCardToSelection(cardModel);

        if (gameModel.threeCardsSelected())
            if (gameModel.isSet()) {
                //remove the set and deal three more;
                gameModel.removeSet();
                if (gameModel.getCardsOnTable().size() < GameModel.Cards_ON_TABLE) {
                    gameModel.addThreeCards();
                }
            } else {
                gameModel.clearSelectedCards();
            }
    }

    /**
     * Adds three cards to the displayed cards.
     */
    public static void drawThreeCards() {
        if (gameModel.canAddThreeCards()) {
            gameModel.addThreeCards();
        }
    }

    /**
     * Shows a hint by highlighting a set if a set exists amongst the displayed cards.
     */
    public static void showHint() {
        List<CardModel> set = gameModel.getOneSet();
        if (set.size() != 0)
            gameView.highlightOneSet(set);
    }
}

