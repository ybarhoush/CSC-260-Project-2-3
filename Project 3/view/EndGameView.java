package view;

import main.Editor;
import readAndWrite.ReadFromFile;
import readAndWrite.WriteToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by fedora on 3/1/17.
 */
public class EndGameView extends JPanel {
//    private static final String
    private static final String EXIT_BUTTON = "Exit";
    private static final String PLAY_AGAIN_BUTTON = "Play Again";
    private static final String SCORE_LABEL = "Score:";
    private static final String TOP_TEN_SCORES_LABEL = "Your Top Ten Scores";

    private JLabel image;
    private Editor mainListener;
    private JButton playAgain;
    private JButton exit;
    private JLabel currentScore;
    private JLabel topTenScores;

    /**
     * Initiates GUI
     * @param mainListener
     */
    public EndGameView(Editor mainListener) {
        this.mainListener = mainListener;

//        p1 = new JPanel();
//        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
//
//        // top image
//        image1 = new JLabel(new ImageIcon("Images/memory-challenge.png"));
//        setCenterAlignment(image1);
//        p1.add(image1);
//
//        //holds the line where user enters id
//        id = new JLabel(USER_ID);
//
//        threeDigitID = new JTextField(3);
//        threeDigitID.setDocument(new JTextFieldLimitThreeChars());
//
//        JPanel p2 = new JPanel();
//        GroupLayout layout = new GroupLayout(p2);
//        layout.setHorizontalGroup(
//                layout.createSequentialGroup().addComponent(id).addComponent(threeDigitID)
//        );
//        p1.add(p2);
//
//        //exit button
//        exit = new JButton(EXIT_BUTTON);
//        setCenterAlignment(exit);
//        exit.addActionListener(new ExitButtonListener());
////        p1.add(enter);
//
//        //playAgain button
//        playAgain = new JButton(PLAY_AGAIN_BUTTON);
//        setCenterAlignment(playAgain);
//        playAgain.addActionListener(new );
//        p1.add(image2);
//
//        add(p1);
    }
    private void setCenterAlignment(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    /**
     * Enter button is pressed.
     */
    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    private void displayscore() {}
    private void PlayAgainOrQuit() {}

}
