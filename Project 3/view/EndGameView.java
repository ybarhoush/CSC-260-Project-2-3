package view;

import main.Editor;
import readAndWrite.ReadFromFile;
import readAndWrite.WriteToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EndGameView extends JPanel {
    private static final String EXIT_BUTTON = "Exit";
    private static final String PLAY_AGAIN_BUTTON = "PLAY AGAIN!";
    private static final String SCORE_LABEL = "Score:";
    private static final String TOP_TEN_SCORES_LABEL = "Your Top Ten Scores";

    private String fileName;
    private JPanel p1;
    private JLabel image;
    private Editor mainListener;
    private JButton playAgain;
    private JButton exit;
    private JLabel currentScoreIndicator;
    private JLabel currentScoreData;
    private JLabel topTenScoresIndicator;
    private JLabel topTenScoresData;

    /**
     * Initiates GUI
     * @param mainListener
     */
    public EndGameView(Editor mainListener, String fileName, int currentScore) {
        this.mainListener = mainListener;
        this.fileName = fileName;

        p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

        // top image
        image = new JLabel(new ImageIcon("Images/GameOver.png"));
        setCenterAlignment(image);
        p1.add(image);

        currentScoreIndicator = new JLabel(SCORE_LABEL);
        currentScoreData = new JLabel();

        JPanel p2 = new JPanel();
        GroupLayout layout = new GroupLayout(p2);
        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(currentScoreIndicator).addComponent(currentScoreData));
        p1.add(p2);

        topTenScoresIndicator = new JLabel(TOP_TEN_SCORES_LABEL);
        topTenScoresData = new JLabel();

        JPanel p3 = new JPanel();
        GroupLayout layout1 = new GroupLayout(p3);
        layout.setHorizontalGroup(layout1.createSequentialGroup().addComponent(topTenScoresIndicator).addComponent(topTenScoresData));
        p1.add(p3);

        //exit button
        exit = new JButton(EXIT_BUTTON);
        setCenterAlignment(exit);
        exit.addActionListener(new ExitButtonListener());
        p1.add(exit);

        //playAgain button
        playAgain = new JButton(PLAY_AGAIN_BUTTON);
        setCenterAlignment(playAgain);
        playAgain.addActionListener(new PlayAgainButtonListener());
        p1.add(playAgain);

        add(p1);
    }
    private void setCenterAlignment(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

//    private String readTopTenScores(){
//        WriteToFile addScore = new WriteToFile(fileName, Integer.toString(), true);
//    }

    /**
     * Enter button is pressed.
     */
    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Play again is pressed.
     */
    private class PlayAgainButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            mainListener.goToGame(Editor.GameMode.Memory, fileName);
        }
    }
}
