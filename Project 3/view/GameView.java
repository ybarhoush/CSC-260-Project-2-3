package view;

import model.CardModel;
import model.GameModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * GameView.java
 * Represents the game screen that the player sees in terms of the cards
 * being displayed and what they are able to select.
 */
public class GameView extends JPanel implements Observer {

    private static final int GRID_COL = 8;
    private static final int GRID_ROW = 9;
    private static final int GRID_Y_SPACING = 20;
    private static final int GRID_X_SPACING = 20;
    private static final int GRID_OUTER_SPACING = 20;

    private GameModel gameModel;
    private Map<CardModel, CardView> cardViewMap;
    private GameViewListener controller;
    private boolean attachListeners;

    /**
     * Creates a new GameView
     *
     * @param gameModel GameModel to initialize from
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

    /**
     * Updates the cards being displayed to the player.
     *
     * @param cardModels list of Card objects
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
        if (size < GameModel.CARDS_ON_TABLE) {
            for (int i = 0; i < GameModel.CARDS_ON_TABLE - size; i++) {
                add(Box.createGlue());
            }
        }
    }

    /**
     * Listens to the clicks of the player to see if a card is selected.
     *
     * @param view CardView object
     */
    private void attachClickListeners(CardView view) {
        if (attachListeners) {
            view.addMouseListener(new CardClickHandler());
        }
    }

    /**
     * Attaches the proper controller to the gameView
     *
     * @param controller controller to attach.
     */
    public void attachListener(GameViewListener controller) {
        this.controller = controller;
    }

    /**
     * Called automatically when the parent Observer object notifies its
     * observers.
     *
     * @param o   Observable object
     * @param arg Arguments from the observable.
     */
    public void update(Observable o, Object arg) {
        updateCardViews(gameModel.getCardsOnTable());
    }

    /**
     * Called when a card is clicked on.
     */
    private class CardClickHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            CardView cardView = (CardView) e.getComponent();
            controller.cardClicked(cardView.getCardModel());
        }
    }
}