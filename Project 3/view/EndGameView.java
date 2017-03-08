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
 * EndGameView.java
 * Represents the screen that indicates the end of a game. The user sees
 * the gameover screen after he or she finds 36 pairs.
 */
public class EndGameView extends JPanel {
    private static final String EXIT_BUTTON = "Exit";
    private static final String PLAY_AGAIN_BUTTON = "PLAY AGAIN!";
    private static final String SCORE_LABEL = "Score:";
    private static final String TOP_TEN_SCORES_LABEL = "Your Top Ten Scores";

    private String fileName;
    private ReadFromFile readFile;
    private JPanel p1;
    private JLabel image;
    private Editor mainListener;
    private int score;
    private JButton playAgain;
    private JButton exit;
    private JLabel currentScoreIndicator;
    private JLabel currentScoreData;
    private JLabel topTenScoresIndicator;
    private JLabel topTenScoresData;

    /**
     * Initiates GUI.
     *
     * @param mainListener Editor object
     */
    public EndGameView(Editor mainListener, String fileName, int finalScore) {
        this.mainListener = mainListener;
        this.fileName = fileName;
        this.score = finalScore;

        p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

        // top image of the screen
        image = new JLabel(new ImageIcon("Images/GameOver.png"));
        setCenterAlignment(image);
        p1.add(image);

        // Sets the JLabel for the user's game score
        currentScoreIndicator = new JLabel(SCORE_LABEL);
        currentScoreData = new JLabel(Integer.toString(score));

        JPanel p2 = new JPanel();
        GroupLayout layout = new GroupLayout(p2);
        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(currentScoreIndicator).addComponent(currentScoreData));
        p1.add(p2);

        // Sets the JLabel for the user's top ten game scores
        topTenScoresIndicator = new JLabel(TOP_TEN_SCORES_LABEL);
        readFile = new ReadFromFile(fileName);
        StringBuilder listTenString = new StringBuilder();
        for (String scoreString : readFile.returnLine()) {
            listTenString.append(scoreString + " ");
        }
        topTenScoresData = new JLabel(listTenString.toString());

        JPanel p3 = new JPanel();
        GroupLayout layout1 = new GroupLayout(p3);
        layout.setHorizontalGroup(layout1.createSequentialGroup().addComponent(topTenScoresIndicator).addComponent(topTenScoresData));
        p1.add(p3);

        // Adds an exit button
        exit = new JButton(EXIT_BUTTON);
        setCenterAlignment(exit);
        exit.addActionListener(new ExitButtonListener());
        p1.add(exit);

        //Adds playAgain button
        playAgain = new JButton(PLAY_AGAIN_BUTTON);
        setCenterAlignment(playAgain);
        playAgain.addActionListener(new PlayAgainButtonListener());
        p1.add(playAgain);

        add(p1);
    }

    /**
     * Aligns the component object to the designated X and Y coordinates.
     *
     * @param component
     */
    private void setCenterAlignment(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    //private String readTopTenScores(){
    //    WriteToFile addScore = new WriteToFile(fileName, score, true);
    //}

    /**
     * What to do when Enter button is pressed.
     */
    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * What to do when Play again is pressed.
     */
    private class PlayAgainButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainListener.goToGame(Editor.GameMode.Memory, fileName);
        }
    }
}
