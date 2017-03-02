package main;

import controller.MemoryGameController;
import model.GameModel;
import view.EndGameView;
import view.GameView;
import commandbars.*;

import javax.swing.*;
import java.awt.*;

/**
 * ViewController that allows communcation between the GameModel, View, and
 * Controller.
 */
public class Game extends JPanel {

    private final Editor main;
    private final EndGameView endGameView;
    private MemoryGameController MemoryGameController;
//    private TutorialGameController TutorialGameController;
    private GameModel gameModel;
    private GameView gameView;
    private final Editor.GameMode gameMode;

    /**
     * Constructor for creating a new main.Game object.
     *
     * @param main main.Editor object.
     * @param mode main.Game mode of game type to create.
     */
    public Game(Editor main, Editor.GameMode mode) {
        super();
        this.main = main;
        this.gameMode = mode;
        setLayout(new BorderLayout());

        this.gameModel = new GameModel();
        this.gameView = new GameView(gameModel);
        this.endGameView = new EndGameView();

//        if (mode == Editor.GameMode.TUTORIAL) {
//            TutorialGameController = new TutorialGameController(gameModel, gameView, main);
//            gameView.attachListener(TutorialGameController);
//        } else {
            MemoryGameController = new MemoryGameController(gameModel, gameView, main);
            gameView.attachListener(MemoryGameController);
//        }

        add(gameView, BorderLayout.CENTER);
//        if (mode == Editor.GameMode.TUTORIAL) {
//            TutorialCmdbarView.attachListener(TutorialGameController);
//            TutorialCmdbarView TutorialCmdbarView = new TutorialCmdbarView();
//            add(TutorialCmdbarView, BorderLayout.NORTH);
//        } else {
            MemoryCmdbarView.attachListener(MemoryGameController);
            MemoryCmdbarView MemoryCmdbarView = new MemoryCmdbarView();
            add(MemoryCmdbarView, BorderLayout.NORTH);
        }
//    }

    /**
     * Calls the controller to initialize a new game.
     */
//    public void newTutorialGame(){
//        TutorialGameController.newGame();
//    }
    public void newMemoryGame(){
        MemoryGameController.newGame();
    }
}
