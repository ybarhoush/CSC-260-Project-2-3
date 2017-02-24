package view;
import main.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MainMenuWindow.java
 * JPanel class that represents the main.Editor Menu of the game.
 */
public class MainMenuWindow extends JPanel {

    private static final String Solitaire_BUTTON_LABEL = "Solitaire Mode";
    private static final String TUTORIAL_BUTTON_LABEL = "Tutorial Mode";
    private static final String QUIT_BUTTON_LABEL = "Quit";

    private Editor mainListener;
    private JButton tutorial;
    private JButton Solitaire;
    private JButton quit;

    /**
     * Constructor for creating a new main.Editor Menu.
     *
     * @param mainListener Listener callbacks for the main.Editor Japplet.
     */
    public MainMenuWindow(Editor mainListener) {
        this.mainListener = mainListener;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initGUI();
    }

    private void initGUI() {
        tutorial = new JButton(TUTORIAL_BUTTON_LABEL);
        tutorial.setPreferredSize(new Dimension(200, 200));
        tutorial.addActionListener(new TutorialButtonListener());
        add(tutorial);


        Solitaire = new JButton(Solitaire_BUTTON_LABEL);
        Solitaire.addActionListener(new SolitaireButtonListener());
        add(Solitaire);

        quit = new JButton(QUIT_BUTTON_LABEL);
        quit.addActionListener(new QuitButtonListener());
        add(quit);
    }

    /**
     * What to do when Solitaire button is pressed.
     */
    private class SolitaireButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainListener.goToGame(Editor.GameMode.Solitaire);
        }
    }

    /**
     * What to do when Tutorial button is pressed.
     */
    private class TutorialButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainListener.goToGame(Editor.GameMode.TUTORIAL);
        }
    }

    /**
     * What to do when Quit button is pressed.
     */
    private class QuitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
