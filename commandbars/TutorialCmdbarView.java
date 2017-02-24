package commandbars;

import controller.TutorialGameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialCmdbarView extends JPanel{

    private static JButton getHintButton = new JButton("Show All Sets");
    private static final String NEW_GAME_LABEL = "New Game";
    protected JButton newGameButton;
    private static Object listener;
    private static Object TutorialCmdbarListener;

    public TutorialCmdbarView() {
        super();
        this.newGameButton = new JButton(NEW_GAME_LABEL);
        iniTutoCmdBar();
    }



    private void iniTutoCmdBar() {
        getHintButton.addActionListener(new HightLightListener());
        newGameButton.addActionListener(new TutorialCmdbarView.NewGameListener());
        this.add(getHintButton);
        this.add(newGameButton);
    }


    public static void attachListener(TutorialGameController tutorialGameController) {

        TutorialCmdbarListener = listener;
    }

    /**
     * NameGameButton is pressed.
     */
    public static class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TutorialGameController.newGame();

        }
    }

    /**
     * HighLightButton is pressed.
     */
    protected class HightLightListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            TutorialGameController.showAllSets();
        }
    }
}
