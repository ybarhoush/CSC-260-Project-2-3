package set;

import set.controller.SolitaireGameController;
//import set.controller.SolitaireGameControllerFactory;
import set.controller.TutorialGameController;
//import set.controller.TutorialGameControllerFactory;
import set.model.GameModel;
import set.view.GameView;
import set.commandbars.*;

import javax.swing.*;
import java.awt.*;

/**
 * "ViewController" that acts as the glue between the Game Model, View, and
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
     * Constructor for creating a new Game object.
     *
     * @param main Editor object.
     * @param mode Game mode of game type to create.
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
