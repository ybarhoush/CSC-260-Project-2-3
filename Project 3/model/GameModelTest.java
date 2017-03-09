package model;
import readAndWrite.ReadFromFile;
import view.UserID;

import java.awt.*;
import java.util.*;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by David on 3/4/17.
 */

/**
 * Tests the GameModel to make sure
 * the correct number of cards and pairs
 * are distributed.
 */
public class GameModelTest {
    @org.junit.Test

    /**
     * Checks that a new deck can deal 72 cards without
     * the incorrect number of duplicates (expects only
     * 2 cards to match).
     */
    public void test_CanDealSevenTwo() {
        System.out.println ("\nTest if deck has 36 unique cards \n -------------------- ");
        DeckModel g = new DeckModel();
        assertEquals("The number of pairs is",0, g.halfDeckHasNoDuplicate());
        assertTrue("Deck has 36 unique cards", g.halfDeckIsUnique());

    }

    @org.junit.Test

    /**
     * Creates a deck and checks the number of pairs in the deck to
     * confirm that the pair number is correct.
     */
    public void test_Length(){
        System.out.println("Test if deck has 36 pairs \n -------------------");
        DeckModel k = new DeckModel();
        assertEquals("The number of pairs in this deck of 72 cards is", 36, k.hasThirtySixPairs());
    }

//    @org.junit.Test
//    public void testReader(){
//        System.out.println("Test if reader can read properly \n --------------");
//        ReadFromFile checkIfPlayerExists = new ReadFromFile("Players/AllPlayers.txt");
//        ArrayList<String> allPlayers = checkIfPlayerExists.returnStrings();
//        assertEquals("the method works", true, allPlayers.contains("896"));
//
//    }
}
