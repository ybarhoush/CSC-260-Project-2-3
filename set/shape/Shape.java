package set.shape;

import set.model.CardModel;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that acts a base for all other Shapes.
 */
public abstract class Shape extends JPanel {

    private final static int BAR_GAP = 4;

    protected final int SHAPE_WIDTH = 50;
    protected final int SHAPE_HEIGHT = 50;

    protected Color color;
    protected CardModel.Shade shade;
    private Color highlightColor;

    /**
     * Constructor for creating a new ShapeView.
     *
     * @param color Color of Shape.
     * @param shade Shade of shape.
     */
    public Shape(Color color, CardModel.Shade shade) {
        this.color = color;
        this.shade = shade;
        this.highlightColor = Color.WHITE;

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
    }

    /**
     * Renders stripes using the {@link #highlightColor} to emulate stripped
     * shading.
     *
     * @param g Graphics to draw on.
     */
    protected void shadeStripped(Graphics g) {
        g.setColor(highlightColor);
        for (int i = 0; i < getWidth(); i+= BAR_GAP){
            g.drawLine(getWidth(), i, 0, i);
        }
    }

    /**
     * Sets the highlight color of the shading.
     *
     * @param highlightColor Color to set.
     */
    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }
}
