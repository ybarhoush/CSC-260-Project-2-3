import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 2/27/17.
 */
public class Editor {
    private JFrame frame;
    private static final int FRAME_WIDTH = 700, FRAME_HEIGHT = 700;
    private static final String MENU_FRAME_TITLE = "The main.Game of Set";
    private static final String SOLO_FRAME_TITLE = "!!! Solitaire Mode !!!";

    public enum GameMode {
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
     * @param pane ContentPane
     */
    private void updateContentPane(Container pane) {
        pane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        pane.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setContentPane(pane);
        frame.revalidate();
        frame.repaint();
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
    public void goToSolitaire() {
            Game game = new Game(this);
            frame.setTitle(getFrameTitle(GameMode.Solitaire));
            updateContentPane(game);
            game.newSolitaireGame();
    }
    private String getFrameTitle(GameMode mode) {
        if (mode == GameMode.Solitaire) {
            return SOLO_FRAME_TITLE;
        }
            return MENU_FRAME_TITLE;
    }


    /**
     * private getter method of the frame.
     * @return ContentPane
     */
    private Container getContentPane() {
        return frame.getContentPane();
    }
}

