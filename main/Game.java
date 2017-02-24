package main;

import controller.SolitaireGameController;
import controller.TutorialGameController;
import model.GameModel;
import view.GameView;
import commandbars.*;

import javax.swing.*;
import java.awt.*;

/**
 * Game.java
 * "ViewController" that acts as the glue between the main.Game Model, View, and
 * Controller.
 */
public class Game extends JPanel {

    private final Editor main;
    private SolitaireGameController SolitaireGameController;
    private TutorialGameController TutorialGameController;
    private GameModel gameModel;
    private GameView gameView;
    private final Editor.GameMode gameMode;

    /**
     * Constructor for creating a new main.Game object. The mode depends on
     * what the user chooses.
     *
     * @param main main.Editor object
     * @param mode main.Game mode of game type to create
     */
    public Game(Editor main, Editor.GameMode mode) {
        super();
        this.main = main;
        this.gameMode = mode;
        setLayout(new BorderLayout());

        this.gameModel = new GameModel();
        this.gameView = new GameView(gameModel);

        if (mode == Editor.GameMode.TUTORIAL) {
            TutorialGameController = new TutorialGameController(gameModel, gameView, main);
            gameView.attachListener(TutorialGameController);
        } else {
            SolitaireGameController = new SolitaireGameController(gameModel, gameView, main);
            gameView.attachListener(SolitaireGameController);
        }

        add(gameView, BorderLayout.CENTER);
        if (mode == Editor.GameMode.TUTORIAL) {
            TutorialCmdbarView.attachListener(TutorialGameController);
            TutorialCmdbarView TutorialCmdbarView = new TutorialCmdbarView();
            add(TutorialCmdbarView, BorderLayout.NORTH);
        } else {
            SolitaireCmdbarView.attachListener(SolitaireGameController);
            SolitaireCmdbarView SolitaireCmdbarView = new SolitaireCmdbarView();
            add(SolitaireCmdbarView, BorderLayout.NORTH);
        }
    }

    /**
     * Calls the controller to initialize a new game.
     */
    public void newTutorialGame(){
        TutorialGameController.newGame();
    }
    public void newSolitaireGame(){
        SolitaireGameController.newGame();
    }
}
