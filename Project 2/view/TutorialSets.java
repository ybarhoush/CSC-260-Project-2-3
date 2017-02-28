package view;

import javax.swing.*;

/**
 * Window to notify user on how many sets exist in tutorial mode.
 */
public class TutorialSets extends JFrame {

    private JLabel message;

    /**
     * Creates a new window that displays a message to the user to notify them on how
     * many sets exist amongst the displayed cards.
     * @param n number of sets
     */
    public TutorialSets(int n){
        setLayout(new BoxLayout(getContentPane() , BoxLayout.Y_AXIS));
        String messageNum;
        if (n == 0){
            messageNum = "There are no sets in this layout. Please close this window and click next game.";
        }
        else if (n == 1){
            messageNum = "There is only 1 set in this layout.";
        }
        else {
            messageNum = "There are " + n + " sets in the layout. Please watch as we highlight them for you.";
        }
        message = new JLabel(messageNum);
        getContentPane().add(message);
        pack();
    }
}
