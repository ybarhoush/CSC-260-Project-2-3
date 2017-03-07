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
//    private final EndGameView endGameView;
    private MemoryGameController MemoryGameController;
    private GameModel gameModel;
    private GameView gameView;
    private final Editor.GameMode gameMode;
    private String fileName;

    /**
     * Constructor for creating a new main.Game object.
     *
     * @param main main.Editor object.
     * @param mode main.Game mode of game type to create.
     */
    public Game(Editor main, Editor.GameMode mode, String fileName) {
        super();
        this.main = main;
        this.gameMode = mode;
        this.fileName = fileName;
        setLayout(new BorderLayout());

        this.gameModel = new GameModel();
        this.gameView = new GameView(gameModel);
//        this.endGameView = new EndGameView(main, fileName);

        MemoryGameController = new MemoryGameController(gameModel, gameView, main, fileName);
        gameView.attachListener(MemoryGameController);

        add(gameView, BorderLayout.CENTER);

        MemoryCmdbarView.attachListener(MemoryGameController);
        MemoryCmdbarView MemoryCmdbarView = new MemoryCmdbarView();
        add(MemoryCmdbarView, BorderLayout.NORTH);
        }

    /**
     * Calls the controller to initialize a new game.
     */

    public void newMemoryGame(){
        MemoryGameController.newGame();
    }
}
