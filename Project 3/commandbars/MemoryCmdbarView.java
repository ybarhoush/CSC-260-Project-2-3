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
    private static final String REMOVE_PAIR = "Remove Pair";
    private static final String TURN_OVER_PAIR = "Turn Over Cards";

    private static final String TURN_COUNTER_LABEL = "Turn over count:";
    private static final String PAIR_COUNTER_LABEL = "Number of pairs:";

    private static JButton removePairButton;
    private static JButton turnOverCardsButton;
    protected JButton newGameButton;

    private static JLabel turnCounterLabel;
    private static JLabel pairCounterLabel;
    protected static JLabel turnCounter;
    protected static JLabel pairCounter;

    private static Object MemoryCmdbarListener;
    private static Object listener;

    /**
     * Constructor that initializes the command bar of the solitaire mode.
     */
    public MemoryCmdbarView() {
        super();
        this.removePairButton = new JButton(REMOVE_PAIR);
        this.turnOverCardsButton = new JButton(TURN_OVER_PAIR);
        this.newGameButton = new JButton(NEW_GAME_LABEL);

        this.turnCounterLabel = new JLabel(TURN_COUNTER_LABEL);
        this.pairCounterLabel = new JLabel(PAIR_COUNTER_LABEL);
        this.turnCounter = new JLabel();
        this.turnCounter.setText("0");
        this.pairCounter = new JLabel();
        this.pairCounter.setText("0");


        iniMemoryCmdBar();
    }

    /**
     * Sets up the buttons and registers the listeners.
     */
    private void iniMemoryCmdBar() {
        newGameButton.addActionListener(new newGameListener());
        removePairButton.addActionListener(new removePairListener());
        turnOverCardsButton.addActionListener(new turnOverCardsListener());
        add(removePairButton);
        add(turnOverCardsButton);
        add(newGameButton);

        add(turnCounterLabel);
        add(turnCounter);
        add(pairCounterLabel);
        add(pairCounter);
    }


    /**
     * Attaches the MemoryGameController to Listener when Game Controller calls the method.
     *
     * @param memoryGameController Listener to attach.
     */
    public static void attachListener(MemoryGameController memoryGameController) {
        MemoryCmdbarListener = listener;
    }

    /**
     * What to do when NameGameButton is pressed.
     */
    public static class newGameListener implements ActionListener {
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