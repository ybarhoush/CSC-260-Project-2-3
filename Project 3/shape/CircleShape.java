package shape;

import model.CardModel;

import java.awt.*;
/**
 * Creates a circle for a card.
 */
public class CircleShape extends Shape {

    /**
     * Constructs a circle shape.
     * @param color Color of the circle.
     * @param shade Shade of the circle.
     */
    public CircleShape(Color color, CardModel.Shade shade) {
        super(color, shade);
    }

    /**
     * Paints the circle based on the shading being
     * set by the card that holds all the shapes.
     * @param g Graphics to draw on
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (shade != CardModel.Shade.OPEN) {
            drawCircle(g, true);

//            if (shade == CardModel.Shade.STRIPED) {
//                shadeStripped(g);
//            }
        }
        drawCircle(g, false);
    }

    /**
     * Draws a circle on a card.
     * @param g Graphics to draw on
     * @param filled if shape is solid color or empty
     */
    private void drawCircle(Graphics g, boolean filled) {
        g.setColor(color);

        int xPoints = getWidth()/2 - SHAPE_WIDTH/2;
        int yPoints = getHeight()/2 - SHAPE_HEIGHT/2;

        if (!filled) {
            g.drawOval(xPoints,yPoints,SHAPE_WIDTH,SHAPE_HEIGHT);
        } else {
            g.fillOval(xPoints,yPoints,SHAPE_WIDTH,SHAPE_HEIGHT);
        }
    }
}
