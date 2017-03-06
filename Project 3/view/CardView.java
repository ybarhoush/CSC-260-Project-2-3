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
 * View that displays a card.
 */
public class CardView extends JPanel {

    private static final Border nullBorder = BorderFactory.createEmptyBorder();
    private static final Border selectedBorder = BorderFactory.createStrokeBorder(new BasicStroke(50.0f));

    private CardModel cardModel;
    private Color highlightColor;
    private Color defaultColor;
    private List<Shape> shapeViewList;

    /**
     * Creates a new set Card
     * @param cardModel CardModel data to display as view.
     */
    public CardView(CardModel cardModel) {
        super();
        this.cardModel = cardModel;
        this.shapeViewList = new ArrayList<>();
        this.defaultColor = Color.WHITE;

        setLayout(new GridLayout(1, 3));
        setBackground(Color.WHITE);
        addShapes();
        toggleSelection();
    }

    /**
     * Adds shapes to the card.
     */
    private void addShapes() {
        for (int i = 0; i < cardModel.getShapeNum(); i++) {
            Shape shape = ShapeFactory.buildShape(cardModel);
            add(shape);
            shapeViewList.add(shape);
        }
    }

    /**
     * Returns the card that is represented view.
     * @return cardModel
     */
    public CardModel getCardModel() {
        return cardModel;
    }

    private void toggleSelection() {
        if (cardModel.isSelected()) {
            setBorder(selectedBorder);
        } else {
            setBorder(nullBorder);
        }
    }


    /**
     * Highlights a card when being used
     * in the algorithm that displays a hint
     * or a set of cards to the player.
     */
    public void highlight() {
        setBackground(highlightColor);
        for (Shape shapeView : shapeViewList) {
            shapeView.setHighlightColor(highlightColor);
        }
    }

    /**
     * Unhighlights a card.
     */
    public void unHighlight() {
        setHighlightColor(defaultColor);
        highlight();
    }


    /**
     * Highlights by changing the background color.
     * @param highlightColor Color to highlight with
     */
    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }
}

