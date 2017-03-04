package commandbars;

import controller.MemoryGameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MemoryCmdbarView.java
 * Represents the command bar of the Memory Game.
 */
public class MemoryCmdbarView extends JPanel {
    private static final String NEW_GAME_LABEL = "New Game";

    private static JButton removePairButton = new JButton("Remove Pair");
    private static JButton turnOverCardsButton = new JButton("Turn Over Cards");
    protected JButton newGameButton;
    private static Object MemoryCmdbarListener;
    private static Object listener;

    /**
     * Constructor that initializes the command bar of the solitaire mode.
     */
    public MemoryCmdbarView() {
        super();
        this.newGameButton = new JButton(NEW_GAME_LABEL);
        iniMemoryCmdBar();
    }

    /**
     * Sets up the buttons and registers the listeners.
     */
    private void iniMemoryCmdBar() {

        newGameButton.addActionListener(new MemoryCmdbarView.NewGameListener());
        removePairButton.addActionListener(new removePairListener());
        turnOverCardsButton.addActionListener(new turnOverCardsListener());

        this.add(removePairButton);
        this.add(turnOverCardsButton);
        this.add(newGameButton);

    }

    /**
     * Attaches the MemoryGameController to Listener when Game Controller calls the method.
     *
     * @param solitaireGameController Listener to attach.
     */
    public static void attachListener(MemoryGameController solitaireGameController) {
        MemoryCmdbarListener = listener;
    }

    /**
     * What to do when NameGameButton is pressed.
     */
    public static class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MemoryGameController.newGame();

        }
    }

    /**
     * What to do when removePairButton is pressed.
     */
    private class removePairListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MemoryGameController.removePair();
        }
    }

    /**
     * What to do when turnOverCardsButton is pressed.
     */
    private class turnOverCardsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            MemoryGameController.turnOverCards();
        }
    }
}