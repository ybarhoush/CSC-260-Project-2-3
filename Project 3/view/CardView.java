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
    private List<Shape> shapeViewList;

    /**
     * Creates a new set Card
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
     * Returns the card that is represented view.
     * @return cardModel
     */
    public CardModel getCardModel() {
        return cardModel;
    }

    private void toggleSelection() {
        if (cardModel.isSelected() == false) {
            setBorder(selectedBorder);
        }
    }

//    private void toggleSelection() {
//        if (cardModel.isSelected()) {
//            setBorder(selectedBorder);
//        } else {
//            setBorder(nullBorder);
//        }
//    }
}

