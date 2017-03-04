package shape;

import model.CardModel;

import java.awt.*;

/**
 * Creates a diamond for a card.
 */
public class DiamondShape extends Shape {

    /**
     * Constructs a diamond shape
     * @param color Color of Diamond
     * @param shade Shade of Diamond
     */
    public DiamondShape(Color color, CardModel.Shade shade) {
        super(color, shade);
    }

    /**
     * Paints the diamond based on the shading being
     * set by the card that holds all the shapes.
     * @param g Graphics to draw on
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shade != CardModel.Shade.OPEN) {
            drawDiamond(g, true);
//            if (shade == CardModel.Shade.STRIPED) {
//                shadeStripped(g);
//            }
        }
        drawDiamond(g, false);

    }

    /**
     * Draws a diamond on a card.
     * @param g Graphics to draw on
     * @param filled if shape is solid color or empty
     */
    private void drawDiamond(Graphics g, boolean filled) {
        g.setColor(color);
        int paddingX = getWidth() / 2 - SHAPE_WIDTH / 2;
        int paddingY = getHeight() / 2 - SHAPE_HEIGHT / 2;
        int[] xPoints = {paddingX + SHAPE_WIDTH / 2, paddingX + SHAPE_WIDTH, paddingX + SHAPE_WIDTH / 2, paddingX};
        int[] yPoints = {paddingY, paddingY + SHAPE_HEIGHT / 2, paddingY + SHAPE_HEIGHT, paddingY + SHAPE_HEIGHT / 2};

        if (!filled) {
            g.drawPolygon(xPoints, yPoints, 4);
        } else {
            g.fillPolygon(xPoints, yPoints, 4);
        }
    }
}
