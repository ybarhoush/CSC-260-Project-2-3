package commandbars;

import controller.SolitaireGameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SolitaireCmdbarView.java
 * Represents the command bar of the solitaire mode.
 */
public class SolitaireCmdbarView extends JPanel {

    private static JButton addThreeButton = new JButton("Add Three Cards");
    private static JButton showHintButton = new JButton("Hint");
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
        addThreeButton.addActionListener(new AddThreeListener());
        showHintButton.addActionListener(new ShowHintListener());

        this.add(addThreeButton);
        this.add(showHintButton);
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

    /**
     * What to do when ShowHintButton is pressed.
     */
    private class ShowHintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SolitaireGameController.showHint();
        }
    }

    /**
     * What to do when AddThreeButton is pressed.
     */
    private class AddThreeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SolitaireGameController.drawThreeCards();
        }
    }
}