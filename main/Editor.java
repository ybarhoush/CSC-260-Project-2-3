package main;

import view.MainMenuWindow;

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
    private static final String MENU_FRAME_TITLE = "Game of Set";
    private static final String SOLO_FRAME_TITLE = "!!! Solitaire Mode !!!";
    private static final String TUT_FRAME_TITLE = "!!! Tutorial Mode !!!";

    /**
     * Represents the different game modes of the Set game.
     * This is passed around the application to indicate the current game mode.
     */
    public enum GameMode {
        TUTORIAL,
        Solitaire,
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
        frame = new JFrame(MENU_FRAME_TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        goToMainMenu();
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
        if (mode == GameMode.Solitaire) {
            return SOLO_FRAME_TITLE;
        } else if (mode == GameMode.TUTORIAL) {
            return TUT_FRAME_TITLE;
        } else {
            return MENU_FRAME_TITLE;
        }
    }

    /**
     * Sets the display to MainMenu screen.
     */
    public void goToMainMenu() {
        if (!(getContentPane() instanceof MainMenuWindow)) {
            MainMenuWindow mainMenu = new MainMenuWindow(this);
            frame.setTitle(MENU_FRAME_TITLE);
            updateContentPane(mainMenu);
        }
    }

    /**
     * Sets the display to game.
     *
     * @param mode
     */
    public void goToGame(GameMode mode) {
        if (!(getContentPane() instanceof Game)) {
            Game game = new Game(this, mode);
            frame.setTitle(getFrameTitle(mode));
            updateContentPane(game);
            if (mode == Editor.GameMode.TUTORIAL) {
                game.newTutorialGame();
            } else {
                game.newSolitaireGame();
            }
        }
    }

    /**
     * 
     * @return
     */
    private Container getContentPane() {
        return frame.getContentPane();
    }
}