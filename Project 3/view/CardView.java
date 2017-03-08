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

    private static final Border nullBorder = BorderFactory.createEmptyBorder();
    private static final Border selectedBorder = BorderFactory.createStrokeBorder(new BasicStroke(50.0f));

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
     * Sets the color of the face of a card to white.
     */
    public void changeBackgroundtoGreen() {
        for (int i = 0; i < cardModel.getShapeNum(); i++) {
            setBackground(Color.WHITE);
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
            setBorder(selectedBorder);
        }
    }
}

