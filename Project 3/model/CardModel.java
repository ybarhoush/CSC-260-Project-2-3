package model;

import java.awt.*;

/**
 * CardModel.java
 * Represents a card to be used in the memory game.
 */
public class CardModel {


    /**
     * All the shades that can be displayed on a card.
     */
    public enum Shade {
        SOLID, OPEN
    }

    /**
     * All the shapes that can be displayed on a card.
     */
    public enum Shape {
        DIAMOND, CIRCLE, SQUARE
    }

    private int shapeNum;       //number of shapes on a card
    private Shape shape;        //shape on the card
    private Shade shade;        //shade on the shapes on the card
    private Color color;        //color of shapes on card
    private boolean isSelected; //if card is selected by player

    /**
     * Creates a new cardModel given a number that will represent
     * the number of shapes the card holds, shape on the card, shade
     * that all the shapes share, color of shapes.
     *
     * @param n  number of shapes
     * @param s  shape(s) on card
     * @param ss shading on shapes
     * @param c  color of shapes
     */
    protected CardModel(int n, Shape s, Shade ss, Color c) {
        this.shapeNum = n;
        this.shape = s;
        this.shade = ss;
        this.color = c;
    }

    /**
     * Returns the number of shapes on a card.
     * @return the number of shapes on a card
     */
    public int getShapeNum() {
        return this.shapeNum;
    }

    /**
     * Returns the shape on a card.
     * @return the shape on a card
     */
    public Shape getShape() {
        return this.shape;
    }

    /**
     * Returns the shade of the shape on a card.
     * @return the shade of the shape on a card
     */
    public Shade getShade() {
        return this.shade;
    }

    /**
     * Returns the color of a card.
     * @return the color of a card
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns 1 if the card is selected by the user.
     * @return 0 or 1
     */
    public boolean isSelected() {
        return this.isSelected;
    }

    /**
     * Sets the number of shapes on a card.
     * @param n the number of shapes
     */
    public void setShapeNum(int n) {
        this.shapeNum = n;
    }

    /**
     * Sets the shape on a card.
     * @param s the shape object
     */
    public void setShape(Shape s) {
        this.shape = s;
    }

    /**
     * Sets the shade of the shape on a card.
     * @param s the shade object
     */
    public void setShade(Shade s) {
        this.shade = s;
    }

    /**
     * Sets the color of the shape on a card.
     * @param c the color object
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Sets a card to be unselected.
     */
    public void unSelect() {
        this.isSelected = !this.isSelected;
    }
}
