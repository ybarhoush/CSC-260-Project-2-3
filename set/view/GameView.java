package set.view;

import set.model.CardModel;
import set.model.GameModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * View class that renders the game board and the current 12/15 active cards.
 */
public class GameView extends JPanel implements Observer {

    private static final int GRID_COL = 3;
    private static final int GRID_ROW = 5;
    private static final int GRID_Y_SPACING = 30;
    private static final int GRID_X_SPACING = 30;
    private static final int GRID_OUTER_SPACING = 30;

    private GameModel gameModel;
    private Map<CardModel, CardView> cardViewMap;
    private GameViewListener controller;
    private boolean attachListeners;

    /**
     * Constructor for creating a new game view.
     *
     * @param gameModel GameModel to initialize from.
     */
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

    private void attachClickListeners(CardView view) {
        if (attachListeners) {
            view.addMouseListener(new CardClickHandler());
        }
    }

    /**
     * Attaches the game controller to the current GameView
     *
     * @param controller Game controller to attach.
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
     * Highlights each individual set with a new random color.
     *
     * @param set Single set.
     */
    public void highlightOneSet(List<CardModel> set) {
        unhighlightAllCards();

        Color color = Color.orange;

        for (int j = 0; j < GameModel.SET_NUM; j++) {
            CardModel model = set.get(j);
            CardView view = cardViewMap.get(model);

            view.setHighlightColor(color);
            view.highlight();
        }
    }

    private void unhighlightAllCards() {
        for (CardView cardView : cardViewMap.values()) {
            cardView.unHighlight();
        }
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