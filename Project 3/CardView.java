import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by David on 2/27/17.
 */
public class CardView extends JPanel{
    private static final Border nullBorder = BorderFactory.createEmptyBorder();
    private static final Border selectedBorder = BorderFactory.createLineBorder(Color.BLACK);

    private CardModel cardModel;
    private Color highlightColor;
    private Color defaultColor;
    private int num;

    public CardView(CardModel cardModel) {
        super();
        this.cardModel = cardModel;
        this.highlightColor = Color.YELLOW;
        this.defaultColor = Color.WHITE;

        setLayout(new GridLayout(1, 1));
        setBackground(Color.WHITE);
        this.num = cardModel.getNum();
        toggleSelection();

    }

    public CardModel getCardModel() {
        return cardModel;
    }

    private void toggleSelection() {
        if (cardModel.isSelected()) {
            setBorder(selectedBorder);
        } else {
            setBorder(nullBorder);
        }

    }

    public void highlight() {
        setBackground(highlightColor);
    }


    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }
}
