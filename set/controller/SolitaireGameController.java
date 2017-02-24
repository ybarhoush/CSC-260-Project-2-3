package set.controller;

import set.Editor;
import set.model.CardModel;
import set.model.GameModel;
import set.view.GameView;
import set.view.GameViewListener;
import set.commandbars.SolitaireCmdbarView;

import java.util.List;

/**
 * THis class holds the view and the model togther and allows communication
 * between the data and what the user sees.
 *
 * This also listens to user input (mouse clicks and if they select cars) then
 * gives that information to the model class and the model deals with it
 * then responds back to the controller which then displays the result back to
 * the player.
 */

public class SolitaireGameController extends SolitaireCmdbarView implements GameViewListener {

    protected static GameModel gameModel;
    protected static GameView gameView;
    protected Editor mainListener;

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
     * Add three cards to the displayed cards.
     */
    public static void drawThreeCards() {
        if (gameModel.canAddThreeCards()) {
            gameModel.addThreeCards();
        }
    }

    /**
     * If a set exists then this method shows one possible set
     * that exists amongst the displayed cards.
     */
    public static void showHint() {
        List<CardModel> set = gameModel.getOneSet();
        if (set.size() != 0)
            gameView.highlightOneSet(set);
    }
}

