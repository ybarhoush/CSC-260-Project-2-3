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
 * UserID.java
 * Represents the first window that the user sees when running the program.
 * It prompts the player with a window that requires the user to enter an
 * ID to continue.
 */
public class UserID extends JPanel {
    private static final String ENTER_BUTTON = "Enter";
    private static final String USER_ID = "Enter Your Three Letter User ID:";
    private static final String ALL_PLAYERS = "AllPlayers.txt";

    private Editor mainListener;
    private JPanel p1;
    private JLabel image1;
    private JLabel image2;
    private JTextField threeDigitID;
    private JButton enter;
    private JLabel id;

    /**
     * Initiates GUI
     *
     * @param mainListener
     */
    public UserID(Editor mainListener) {
        this.mainListener = mainListener;

        p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

        // top image
        image1 = new JLabel(new ImageIcon("Images/memory-challenge.png"));
        setCenterAlignment(image1);
        p1.add(image1);

        //holds the line where user enters id
        id = new JLabel(USER_ID);

        threeDigitID = new JTextField(3);
        threeDigitID.setDocument(new JTextFieldLimitThreeChars());

        JPanel p2 = new JPanel();
        GroupLayout layout = new GroupLayout(p2);
        layout.setHorizontalGroup(
                layout.createSequentialGroup().addComponent(id).addComponent(threeDigitID)
        );
        p1.add(p2);

        //enter button
        enter = new JButton(ENTER_BUTTON);
        setCenterAlignment(enter);
        enter.addActionListener(new EnterButtonListener());
        p1.add(enter);

        //bottom image
        image2 = new JLabel(new ImageIcon("Images/memory_game.jpg"));
        setCenterAlignment(image2);
        p1.add(image2);

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
     * What to do when the enter button is pressed.
     */
    private class EnterButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (threeDigitID.getText().length() == 3) {
                if (!userExists()) {
                    WriteToFile addPlayer = new WriteToFile(ALL_PLAYERS, threeDigitID.getText(), true);
                    WriteToFile newText = new WriteToFile(threeDigitID.getText() + ".txt", "", false);
                }
                mainListener.goToGame(Editor.GameMode.Memory, threeDigitID.getText() + ".txt");
            } else {
                JOptionPane.showMessageDialog(null, "Please Enter three characters for your ID to continue to game.");
            }
        }
    }

    /**
     * Returns true if the user has entered three letters for his / her ID
     *
     * @return true or false
     */
    private boolean userExists() {
        ReadFromFile checkIfPlayerExists = new ReadFromFile(ALL_PLAYERS);
        ArrayList<String> allPlayers = checkIfPlayerExists.returnLine();
        if (allPlayers.contains(threeDigitID.getText())) {
            return true;
        }
        return false;
    }
}