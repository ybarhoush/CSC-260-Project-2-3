package view;

import main.Editor;

import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 3/3/17.
 */
public class UserID extends JPanel{
    private static final String ENTER_BUTTON = "Enter";
    private static final String USER_ID = "Enter Your Three Digit User ID:";

    private Editor mainListener;
    private JPanel p1;
    private JLabel image1;
    private JLabel image2;
    private JTextField threeDigitID;
    private JButton buttonOne;
    private JLabel id;



    public UserID(Editor mainListener) {
        this.mainListener = mainListener;

        p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));

        image1 = new JLabel(new ImageIcon("memory-challenge.png"));
        setCenterAlignment(image1);
        p1.add(image1);

        id = new JLabel(USER_ID);

        threeDigitID = new JTextField(3);
        threeDigitID.setDocument(new JTextFieldLimitThreeChars());

        JPanel p2 = new JPanel();
        GroupLayout layout = new GroupLayout(p2);
        layout.setHorizontalGroup(
                layout.createSequentialGroup().addComponent(id).addComponent(threeDigitID)
        );
        p1.add(p2);

        buttonOne = new JButton(ENTER_BUTTON);
        setCenterAlignment(buttonOne);
        p1.add(buttonOne);

        image2 = new JLabel(new ImageIcon("memory_game.jpg"));
        setCenterAlignment(image2);
        p1.add(image2);

        add(p1);
    }
    private void setCenterAlignment(JComponent component) {
        component.setAlignmentX(Component.CENTER_ALIGNMENT);
        component.setAlignmentY(Component.CENTER_ALIGNMENT);
    }
}