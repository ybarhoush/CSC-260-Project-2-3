package controller;

import main.Editor;
import model.CardModel;
import model.GameModel;
import view.GameView;
import view.GameViewListener;
import commandbars.TutorialCmdbarView;
import view.TutorialSets;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * TutorialGameController.java
 * Represents the controls of the game screen of the Solitaire mode.
 * This class holds the view and the model together and allows communication
 * between the data and what the user sees.
 */

public class TutorialGameController extends TutorialCmdbarView implements GameViewListener {

    private static TutorialSets window;
    protected static GameModel gameModel;
    protected static GameView gameView;
    protected Editor mainListener;

    /**
     * Constructor that initializes the game controller of the tutorial mode.
     * @param m GameModel
     * @param v GameView
     * @param main  Editor
     */
    public TutorialGameController(GameModel m, GameView v, Editor main) {
        super();
        gameModel = m;
        gameView = v;
        mainListener = main;
    }

    /**
     * Empty method of cardClicked in GameViewListener.
     *
     * @param c the card that the user clicks
     */
    public void cardClicked(CardModel c) {
    }

    /**
     * Makes a new tutorial for the player.
     */
    public static void newGame() {
        gameModel.newGame();
    }

    /**
     * Lets a window pop up and tells the user how many sets are existing
     * among the displayed cards. Then it will play a short video by
     * highlighting sets that exist with 3 second intervals.
     */
    public static void showAllSets() {
        List<List<CardModel>> sets = gameModel.getAllSets();
        int setSize = sets.size();

        window = new TutorialSets(setSize);
        window.setVisible(true);

        if (setSize != 0) {
            int delay = 0;
            int period = 3000;

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int count = 0;

                public void run() {
                    gameView.highlightOneSet(sets.get(count));
                    count++;
                    if (count == setSize)
                        timer.cancel();
                }
            }, delay, period);
        }
    }
}