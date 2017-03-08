package view;

import model.CardModel;
import shape.Shape;
import shape.ShapeFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CardView.java
 * Represents a card that the user sees on the game screen.
 */
public class CardView extends JPanel {

    private static final Border raisedbevel = BorderFactory.createRaisedBevelBorder();

    private static JLabel image;
    private static ImageIcon icon = new ImageIcon("Images/index.jpg");
    //private static final Border loweredbevel = BorderFactory.createMatteBorder(0, 50, 50, 0, icon );
    //private static final Border selectedBorder = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);


    private CardModel cardModel;
    private List<Shape> shapeViewList;

    /**
     * Creates a new set of cards.
     *
     * @param cardModel CardModel data to display as view.
     */
    public CardView(CardModel cardModel) {
        super();
        this.cardModel = cardModel;
        this.shapeViewList = new ArrayList<>();

        setLayout(new GridLayout(1, 3));
        setBackground(Color.WHITE);
        addShapes();
        toggleSelection();
    }

    /**
     * Adds shapes to the card.
     */
    public void addShapes() {
        for (int i = 0; i < cardModel.getShapeNum(); i++) {
            Shape shape = ShapeFactory.buildShape(cardModel);
            add(shape);
            shapeViewList.add(shape);
        }
    }


    /**
     * Getter method for a card.
     *
     * @return cardModel
     */
    public CardModel getCardModel() {
        return cardModel;
    }

    /**
     * Sets the border of the face of a card object with a strokeBorder
     * when it is not selected by the user.
     */
    private void toggleSelection() {
        if (cardModel.isSelected() == false) {
            //setBorder(loweredbevel);
            image = new JLabel(new ImageIcon("Images/index.jpg"));
            add(image, "Center");

        }
    }

    public void greenBackground() {
        if (cardModel.isSelected() == true && cardModel.getShapeNum() != 0) {
            setBackground(Color.green);
        }
    }

    public void redBackground() {
        if (cardModel.isSelected() == true && cardModel.getShapeNum() != 0) {
            setBackground(Color.red);
        }
    }

}

