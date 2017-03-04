package shape;

import model.CardModel;

import java.awt.*;

/**
 * Creates a square for a card
 */
public class SquareShape extends Shape {

    /**
     * Constructs a square shape
     * @param color Color of the square.
     * @param shade Shade of the square.
     */
    public SquareShape(Color color, CardModel.Shade shade) {
        super(color, shade);
    }

    /**
     * Paints the square based on the shading being
     * set by the card that holds all the shapes.
     * @param g Graphics to draw on
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shade != CardModel.Shade.OPEN) {
            drawSquare(g, true);

//            if (shade == CardModel.Shade.STRIPED) {
//                shadeStripped(g);
//            }
        }
        drawSquare(g, false);
    }

    /**
     * Draws a square on a card.
     * @param g Graphics to draw on
     * @param filled if shape is solid color or empty
     */
    private void drawSquare(Graphics g, boolean filled) {
        g.setColor(color);
        int xPoints = (getWidth()/2 - SHAPE_WIDTH/2);
        int yPoints = (getHeight()/2 - SHAPE_HEIGHT/2);

        if (!filled) {
            g.drawRect(xPoints,yPoints,SHAPE_WIDTH,SHAPE_HEIGHT);
        } else {
            g.fillRect(xPoints,yPoints, SHAPE_WIDTH, SHAPE_HEIGHT);
        }
    }
}
