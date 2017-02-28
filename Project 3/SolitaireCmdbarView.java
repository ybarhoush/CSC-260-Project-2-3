import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by David on 2/27/17.
 */
public class SolitaireCmdbarView extends JPanel {

    private static final String NEW_GAME_LABEL = "New Game";
    protected JButton newGameButton;
    private static Object SolitaireCmdbarListener;
    private static Object listener;

    /**
     * Constructor that initializes the command bar of the solitaire mode.
     */
    public SolitaireCmdbarView() {
        super();
        this.newGameButton = new JButton(NEW_GAME_LABEL);
        iniSolitaireCmdBar();
    }

    /**
     * Sets up the buttons and registers the listeners.
     */
    private void iniSolitaireCmdBar() {

        newGameButton.addActionListener(new SolitaireCmdbarView.NewGameListener());

        this.add(newGameButton);

    }

    /**
     * Attaches the SolitaireGameController to Listener when Game Controller calls the method.
     *
     * @param solitaireGameController Listener to attach.
     */
    public static void attachListener(SolitaireGameController solitaireGameController) {
        SolitaireCmdbarListener = listener;
    }

    /**
     * What to do when NameGameButton is pressed.
     */
    public static class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SolitaireGameController.newGame();

        }
    }


}