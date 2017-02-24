package commandbars;

import controller.TutorialGameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TutorialCmdbarView.java
 * Represents the command bar of the tutorial mode.
 */
public class TutorialCmdbarView extends JPanel{

    private static JButton getHintButton = new JButton("Show All Sets");
    private static final String NEW_GAME_LABEL = "New main.Game";
    protected JButton newGameButton;
    private static Object listener;
    private static Object TutorialCmdbarListener;

    /**
     * Constructor that initializes the command bar of the tutorial mode.
     */
    public TutorialCmdbarView() {
        super();
        this.newGameButton = new JButton(NEW_GAME_LABEL);
        iniTutoCmdBar();
    }

    /**
     * Sets up the buttons and registers the listeners.
     */
    private void iniTutoCmdBar() {
        getHintButton.addActionListener(new HighLightListener());
        newGameButton.addActionListener(new TutorialCmdbarView.NewGameListener());
        this.add(getHintButton);
        this.add(newGameButton);
    }

    /**
     * Attaches the TutorialGameController to Listener when Game Controller calls the method.
     *
     * @param tutorialGameController Listener to attach.
     */
    public static void attachListener(TutorialGameController tutorialGameController) {

        TutorialCmdbarListener = listener;
    }

    /**
     * What to do when NameGameButton is pressed.
     */
    public static class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TutorialGameController.newGame();

        }
    }

    /**
     * What to do when HighLightButton is pressed.
     */
    protected class HighLightListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TutorialGameController.showAllSets();
        }
    }
}
