package set.shape;

import set.model.CardModel;

import java.awt.*;

/**
 * Factory class that draws Shape on the card model.
 */
public class ShapeFactory {

    /**
     * Returns the corresponding ShapeView for a specific CardModel.
     *
     * @param cardModel Card model
     * @return New instance of ShapeView.
     */
    public static Shape buildShape(CardModel cardModel) {
        CardModel.Shape shape = cardModel.getShape();
        Color color = cardModel.getColor();
        CardModel.Shade shade = cardModel.getShade();
        if (shape == CardModel.Shape.SQUARE) {
            return new SquareShape(color, shade);
        } else if (shape == CardModel.Shape.CIRCLE) {
            return new CircleShape(color, shade);
        } else {
            return new DiamondShape(color, shade);
        }
    }
}

