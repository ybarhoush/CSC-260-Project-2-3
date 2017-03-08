package main;

import view.EndGameView;
import view.UserID;

import javax.swing.*;
import java.awt.*;

/**
 * Editor.java
 * Runs the entire program as the starting point by displaying the main menu screen
 * to the user.
 */

public class Editor {

    private JFrame frame;
    private static final int FRAME_WIDTH = 1200, FRAME_HEIGHT = 900;
    private static final String USER_ID_TITLE = "User ID";
    private static final String MEMORY_FRAME_TITLE = "Memory Mode";
    private static final String END_OF_GAME_FRAME_TITLE = "Game Over";

    /**
     * Represents the different game modes of the Set game.
     * This is passed around the application to indicate the current game mode.
     */
    public enum GameMode {
        Memory,
    }

    /**
     * Runs the set game when the application runs.
     *
     * @param args java arguments
     */
    public static void main(String[] args) {
        new Editor();
    }

    /**
     * Constructor that invokes Swing UI.
     */
    private Editor() {
        SwingUtilities.invokeLater(this::displayGUI);
    }

    /**
     * Displays the GUI.
     */
    private void displayGUI() {
        frame = new JFrame(USER_ID_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        goToUserID();
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Constantly repaints the window.
     *
     * @param pane
     */
    private void updateContentPane(Container pane) {
        pane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pane.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setContentPane(pane);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Screen that prompts player for and ID
     */
    public void goToUserID(){
        if (!(getContentPane() instanceof UserID)) {
            UserID userID = new UserID(this);
            frame.setTitle(USER_ID_TITLE);
            updateContentPane(userID);
        }
    }

    /**
     * Displays score to player at the end of a game and allows user to exit or play again.
     * @param fileName file to which to write score to
     * @param currentScore score for the most recent game
     */
    public void goToEndGameView(String fileName, int currentScore){
        if (!(getContentPane() instanceof EndGameView)) {
            EndGameView endGameView = new EndGameView(this, fileName, currentScore);
            frame.setTitle(END_OF_GAME_FRAME_TITLE);
            updateContentPane(endGameView);
        }
    }

    /**
     * Sets the display to game.
     *
     * @param mode
     */
    public void goToGame(GameMode mode, String fileName) {
        if (!(getContentPane() instanceof Game)) {
            Game game = new Game(this, mode, fileName);
            frame.setTitle(MEMORY_FRAME_TITLE);
            updateContentPane(game);
            game.newMemoryGame();
        }
    }

    /**
     * Retrieves the content pane layer so that you can add an object to it.
     * @return content pane
     */
    private Container getContentPane() {
        return frame.getContentPane();
    }
}