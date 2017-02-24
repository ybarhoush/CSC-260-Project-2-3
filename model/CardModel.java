package model;

import java.awt.*;

/**
 *
 * This class is a card model.
 */
public class CardModel {

    /** All the shades that can be displayed on a card */
    public enum Shade {SOLID, STRIPED, OPEN}

    /** All the shapes that can be displayed on a card */
    public enum Shape {DIAMOND, CIRCLE, SQUARE}

    private int shapeNum;    //number of shapes on a card
    private Shape shape;        //shape on the card
    private Shade shade;        //shade on the shapes on the card
    private Color color;        //color of shapes on card
    private boolean isSelected; //if card is selected by player

    protected CardModel(int n, Shape s, Shade ss, Color c){
        this.shapeNum = n;
        this.shape = s;
        this.shade = ss;
        this.color = c;
    }

    /** getter methods */
    public int getShapeNum(){return this.shapeNum;}
    public Shape getShape (){return this.shape;}
    public Shade getShade (){return this.shade;}
    public Color getColor(){return this.color;}
    public boolean isSelected (){return this.isSelected;}

    /** setter methods */
    public void setShapeNUm (int n){this.shapeNum = n;}
    public void setShape (Shape s){this.shape = s;}
    public void setShade (Shade s){this.shade = s;}
    public void setColor (Color c) {this.color = c;}
    public void unSelect () {this.isSelected = !this.isSelected;}
}
