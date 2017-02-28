import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 2/27/17.
 */
public class Game extends JPanel {

    private final Editor main;
    private SolitaireGameController SolitaireGameController;
    private GameModel gameModel;
    private GameView gameView;

    /**
     * Constructor for creating a new main.Game object. The mode depends on
     * what the user chooses.
     *
     * @param main main.Editor object
     */
    public Game(Editor main) {
        super();
        this.main = main;
        setLayout(new BorderLayout());


        this.gameModel = new GameModel();
        this.gameView = new GameView(gameModel);

        SolitaireGameController = new SolitaireGameController(gameModel, gameView, main);
        gameView.attachListener(SolitaireGameController);

        SolitaireCmdbarView.attachListener(SolitaireGameController);
        SolitaireCmdbarView SolitaireCmdbarView = new SolitaireCmdbarView();
        add(SolitaireCmdbarView, BorderLayout.NORTH);

    }

    public void newSolitaireGame(){
        SolitaireGameController.newGame();
    }
}
