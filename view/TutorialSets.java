package view;

import javax.swing.*;

/**
 * JFrame view to cycle through highlighted sets.
 */
public class TutorialSets extends JPanel {

    private JFrame frame;
    private JLabel message;

    public TutorialSets(int n){
//        setLayout(new BoxLayout(getContentPane() , BoxLayout.Y_AXIS));
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
//        getContentPane().add(message);


        frame = new JFrame("Sets");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.pack();
        frame.setVisible(true);


//        pack();
    }
}
