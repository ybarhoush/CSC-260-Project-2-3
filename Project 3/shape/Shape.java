package shape;

import model.CardModel;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract shape class that is used to create
 * all the other shapes on the card.
 */
public abstract class Shape extends JPanel {

    private final static int BAR_GAP = 4;

    protected final int SHAPE_WIDTH = 50;
    protected final int SHAPE_HEIGHT = 50;

    protected Color color;
    protected CardModel.Shade shade;
    private Color highlightColor;

    /**
     * Creates a new shape
     * @param color Color of Shape.
     * @param shade Shade of shape.
     */
    public Shape(Color color, CardModel.Shade shade) {
        this.color = color;
        this.shade = shade;
        this.highlightColor = Color.WHITE;
        setOpaque(false);
    }

    /**
     * Paints the shape based on the shading being
     * set by the card that holds all the shapes.
     * @param g
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
    }


    /**
     * Sets the highlight color of the shading.
     * @param highlightColor Color to set.
     */
    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }
}
