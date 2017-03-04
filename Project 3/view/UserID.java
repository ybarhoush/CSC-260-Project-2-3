package view;

import com.sun.org.apache.regexp.internal.RE;
import main.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserID extends JPanel {

    private static final String MEMORY_LABEL = "Welcome to Memory";
    private static final String RETURNING_PLAYER_BUTTON = "Returning Player";
    private static final String NEW_PLAYER_BUTTON = "New Player";

    private Editor mainListener;
    private JButton returningPlayer;
    private JButton newPlayer;
    private JPanel mem;

    /**
     * Constructor for creating a new main.Editor Menu.
     * @param mainListener Listener callbacks for the main.Editor.
     */
    public UserID(Editor mainListener) {
        this.mainListener = mainListener;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initGUI();
    }

    /**
     * Initializes the GUI
     */
    private void initGUI() {

        JLabel memory = new JLabel(MEMORY_LABEL);
        memory.setFont(new Font(getFont().getName(), Font.BOLD, 50));
        setCenterAlignment(memory);
        add(memory);

        returningPlayer = new JButton(RETURNING_PLAYER_BUTTON);
        returningPlayer.addActionListener(new ReturningPlayerButtonListener());

        newPlayer = new JButton(NEW_PLAYER_BUTTON);
        newPlayer.addActionListener(new NewPlayerButtonListener());

        mem = new JPanel();
        mem.add(returningPlayer);
        mem.add(newPlayer);
        add(mem, BorderLayout.CENTER);
    }

    private void setCenterAlignment(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }

    /**
     * Returning Player button is pressed.
     */
    private class ReturningPlayerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            ;
        }
    }


    /**
     * New Player button is pressed.
     */
    private class NewPlayerButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            ;
        }
    }
}
