package view;

import main.Editor;
import readAndWrite.ReadFromFile;
import readAndWrite.WriteToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * EndGameView.java
 * Represents the screen that indicates the end of a game. The user sees
 * the gameover screen after he or she finds 36 pairs.
 */
public class EndGameView extends JPanel {
    private static final String EXIT_BUTTON = "Exit";
    private static final String PLAY_AGAIN_BUTTON = "PLAY AGAIN!";
    private static final String SCORE_LABEL = "Score:";
    private static final String TOP_TEN_SCORES_LABEL = "Your Top Ten Scores:";

    private String fileName;
    private String topTenScores;
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
        addCurrentScore();
        currentScoreIndicator = new JLabel(SCORE_LABEL);
        currentScoreData = new JLabel(Integer.toString(score));

        JPanel p2 = new JPanel();
        GroupLayout layout = new GroupLayout(p2);
        layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(currentScoreIndicator).addComponent(currentScoreData));
        p1.add(p2);

        // Sets the JLabel for the user's top ten game scores
        topTenScores = allScoresFromBestToWorst();
        topTenScoresIndicator = new JLabel(TOP_TEN_SCORES_LABEL);
        topTenScoresData = new JLabel(topTenScores);

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

    /**
     * Adds player's most recent score to data sheet.
     */
    private void addCurrentScore(){
        WriteToFile addScore = new WriteToFile(fileName, Integer.toString(this.score),true);
    }

    /**
     * Reads the user's data file, turns the strings into integers, then sorts the
     * scores from lowest to highest, then cuts down the array to make sure only the
     * top ten scores are displayed.
     * @return string with only top ten scores
     */
    private String allScoresFromBestToWorst(){
        ArrayList<String> scoreStrings;
        ArrayList<Integer> scoreInts = new ArrayList<Integer>();
        ReadFromFile readScores = new ReadFromFile(fileName);
        scoreStrings = readScores.returnStrings();

        for(String item : scoreStrings){
            scoreInts.add(Integer.parseInt(item.replace("[^0-9]", "")));
        }
        scoreInts = removeDuplicates(scoreInts);
        Collections.sort(scoreInts);
        if (scoreInts.size() > 10){
            scoreInts = makeTen(scoreInts);
        }
        String topTenScores = "";
        for (Integer score : scoreInts){
            topTenScores += score + " ";
        }
        return topTenScores.toString();
    }

    /**
     * Makes an array to length ten.
     * @param list to shorten
     * @return list with 10 elements
     */
    private ArrayList<Integer> makeTen(ArrayList<Integer> list){
        ArrayList<Integer> subList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            subList.add(list.get(i));
        }
        return subList;
    }

    /**
     * Removes all duplicates in a given list.
     * @param list list to remove duplicates from
     * @return list with no duplicates
     */
    private ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j--);
                    size--;
                }
            }
        }
        return list;
    }

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
