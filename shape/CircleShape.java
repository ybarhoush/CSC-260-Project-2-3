package shape;

import model.CardModel;

import java.awt.*;
/**
 * CircleShape.java
 * Represents a circle on the card.
 */
public class CircleShape extends Shape {

    /**
     * Constructor for the CircleShapeView.
     * @param color Color of the circle.
     * @param shade Shade of the circle.
     */
    public CircleShape(Color color, CardModel.Shade shade) {
        super(color, shade);
    }

    /**
     * Paints a circle.
     * @param g Graphics
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (shade != CardModel.Shade.OPEN) {
            drawCircle(g, true);

            if (shade == CardModel.Shade.STRIPED) {
                shadeStripped(g);
            }
        }
        drawCircle(g, false);
    }

    /**
     * Draws a circle.
     * @param g Graphics
     * @param filled true if filled / false if not
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
