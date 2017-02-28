/**
 * Created by David on 2/27/17.
 */
public class SolitaireGameController extends SolitaireCmdbarView implements GameViewListener {

    protected static GameModel gameModel;
    protected static GameView gameView;
    protected Editor mainListener;

    /**
     * Constructor that initializes the game controller of the solitaire mode.
     *
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

        if (gameModel.hasTwoCardsSelected())
            if (gameModel.isSet()) {
                //remove the set and deal three more;
                gameModel.removeSet();
            } else {
                gameModel.clearSelectedCards();
            }
    }

}

