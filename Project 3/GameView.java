import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by David on 2/27/17.
 */
public class GameView extends JPanel implements Observer {

    private static final int GRID_COL = 8;
    private static final int GRID_ROW = 9;
    private static final int GRID_Y_SPACING = 30;
    private static final int GRID_X_SPACING = 30;
    private static final int GRID_OUTER_SPACING = 30;

    private GameModel gameModel;
    private Map<CardModel, CardView> cardViewMap;
    private GameViewListener controller;
    private boolean attachListeners;

    public GameView(GameModel gameModel) {
        super();
        this.gameModel = gameModel;
        this.cardViewMap = new HashMap<>();
        this.attachListeners = true;
        gameModel.addObserver(this);

        setBorder(new EmptyBorder(GRID_OUTER_SPACING, GRID_OUTER_SPACING, GRID_OUTER_SPACING, GRID_OUTER_SPACING));
        setLayout(new GridLayout(GRID_ROW, GRID_COL, GRID_X_SPACING, GRID_Y_SPACING));
        updateCardViews(gameModel.getCardsOnTable());
    }

    private void attachClickListeners(CardView view) {
        if (attachListeners) {
            view.addMouseListener(new CardClickHandler());
        }
    }

    /**
     * Updates the cards on the screen.
     * @param cardModels Card
     */
    private void updateCardViews(List<CardModel> cardModels) {
        removeAll();
        revalidate();

        cardViewMap.clear();

        for (CardModel cardModel : cardModels) {
            CardView cardView = new CardView(cardModel);
            cardViewMap.put(cardModel, cardView);

            attachClickListeners(cardView);
            add(cardView);
        }

        int size = cardModels.size();
        if (size < GameModel.MAX_CARDS_ON_TABLE) {
            for (int i = 0; i < GameModel.MAX_CARDS_ON_TABLE - size; i++) {
                add(Box.createGlue());
            }
        }
    }

    /**
     * Attaches the game controller to the current GameView
     *
     * @param controller main.Game controller to attach.
     */
    public void attachListener(GameViewListener controller) {
        this.controller = controller;
    }


    /**
     * Called automatically when the parent Observer object notifies its
     * observers.
     *
     * @param o Observable object
     * @param arg Arguments from the observable.
     */
    public void update(Observable o, Object arg) {
        updateCardViews(gameModel.getCardsOnTable());
    }


    /**
     * Called when a CardView is clicked.
     */
    private class CardClickHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            CardView cardView = (CardView) e.getComponent();
            controller.cardClicked(cardView.getCardModel());
        }
    }
}
