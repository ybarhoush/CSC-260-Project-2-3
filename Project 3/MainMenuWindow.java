import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by David on 2/27/17.
 */
public class MainMenuWindow extends JPanel {

    private static final String Solitaire_BUTTON_LABEL = "Solitaire Mode";
    private static final String QUIT_BUTTON_LABEL = "Quit";

    private Editor mainListener;
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
            mainListener.goToSolitaire();
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
