package main;

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
    private static final int FRAME_WIDTH = 700, FRAME_HEIGHT = 700;
    private static final String USER_ID_TITLE = "User ID";
//    private static final String MENU_FRAME_TITLE = "Game of Set";
    private static final String MEMORY_FRAME_TITLE = "Memory Mode";

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
//        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
     * Based on the screen that this user is in, this method
     * returns the proper title for that frame.
     *
     * @param mode
     * @return title
     */
    private String getFrameTitle(GameMode mode) {
//        if (mode == GameMode.Memory) {
            return MEMORY_FRAME_TITLE;
//        }
//        return MENU_FRAME_TITLE;
    }

    public void goToUserID(){
        if (!(getContentPane() instanceof UserID)) {
            UserID userID = new UserID(this);
            frame.setTitle(USER_ID_TITLE);
            updateContentPane(userID);
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
            frame.setTitle(getFrameTitle(mode));
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