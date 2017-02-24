package set.view;

import set.controller.TutorialGameController;
import set.model.CardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * JFrame view to cycle through highlighted sets.
 */
public class TutorialSets extends JFrame {
    private JLabel message;
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
