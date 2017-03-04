package model;
import java.awt.*;
import java.util.*;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by David on 3/4/17.
 */
public class GameModelTest {
//    @org.junit.Test
//    public void test_CanDealSevenTwo() {
//        System.out.println ("\nTest if deck has 36 unique cards \n -------------------- ");
//        DeckModel g = new DeckModel();
//        assertEquals("The number of pairs is",0, g.halfDeckHasNoDuplicate());
//        assertTrue("Deck has 36 unique cards", g.halfDeckIsUnique(g.halfDeckHasNoDuplicate()));
//
//    }

    @org.junit.Test
    public void test_Length(){
        System.out.println("Test if deck has 36 pairs \n -------------------");
        DeckModel k = new DeckModel();
        assertEquals("The number of pairs in this deck of 72 cards is", 36, k.hasThirtySixPairs());
    }
}
