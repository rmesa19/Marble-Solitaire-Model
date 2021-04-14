import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class used to test MarbleSolitaireModelImpl Class. Verifying that game state is properly managed,
 * and all game actions are properly validated.
 */
public class MarbleSolitaireModelTest {

  @Test
  public void testMarbleSolitaireModelImplConstructor() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    MarbleSolitaireModel n = new MarbleSolitaireModelImpl(5);
    MarbleSolitaireModel o = new MarbleSolitaireModelImpl(3, 3);
    MarbleSolitaireModel p = new MarbleSolitaireModelImpl(5, 6, 5);
    assertEquals("    O O O    \n" +
            "    O O O    \n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O    \n" +
            "    O O O    ", m.getGameState());

    assertEquals("        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        ", n.getGameState());

    assertEquals("    O O O    \n" +
            "    O O O    \n" +
            "O O _ O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O    \n" +
            "    O O O    ", o.getGameState());

    assertEquals("        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O _ O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        ", p.getGameState());
  }

  @Test
  public void testInvalidConstruct() {
    try {
      MarbleSolitaireModel m = new MarbleSolitaireModelImpl(4);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "The arm thickness must be an odd number " +
              "and greater than 1.");
    }
    try {
      MarbleSolitaireModel n = new MarbleSolitaireModelImpl(1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "The arm thickness must be an odd number and " +
              "greater than 1.");
    }
    try {
      MarbleSolitaireModel o = new MarbleSolitaireModelImpl(-1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "The arm thickness must be an odd number and " +
              "greater than 1.");
    }
    try {
      MarbleSolitaireModel p = new MarbleSolitaireModelImpl(1, 1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid empty cell position (1,1)");
    }
    try {
      MarbleSolitaireModel q = new MarbleSolitaireModelImpl(-1, -1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid empty cell position (-1,-1)");
    }
    try {
      MarbleSolitaireModel r = new MarbleSolitaireModelImpl(9, -1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid empty cell position (9,-1)");
    }
    try {
      MarbleSolitaireModel r = new MarbleSolitaireModelImpl(1, 9, -1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "The arm thickness must be an odd number " +
              "and greater than 1.");
    }
    try {
      MarbleSolitaireModel r = new MarbleSolitaireModelImpl(3, 9, -1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid empty cell position (9,-1)");
    }
  }

  @Test
  public void testValidMove() {
    MarbleSolitaireModel n = new MarbleSolitaireModelImpl(5, 6, 5);
    n.move(6, 3, 6, 5);
    assertEquals("        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "O O O O O O O O O O O O O\n" +
            "O O _ _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        ", n.getGameState());

    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    m.move(4, 2, 4, 4);
    assertEquals("    O O O    \n" +
            "    O O O    \n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O    \n" +
            "    O O O    ", m.getGameState());

    m.move(4, 5, 4, 3);

    assertEquals("    O O O    \n" +
            "    O O O    \n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O O O    \n" +
            "    O O O    ", m.getGameState());

    m.move(6, 4, 4, 4);

    assertEquals("    O O O    \n" +
            "    O O O    \n" +
            "O O O O O O O\n" +
            "O _ O O _ O O\n" +
            "O O O _ O O O\n" +
            "    O _ O    \n" +
            "    O O O    ", m.getGameState());

    m.move(3, 4, 5, 4);

    assertEquals("    O O O    \n" +
            "    O O O    \n" +
            "O O O _ O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O _ O    \n" +
            "    O O O    ", m.getGameState());

  }

  @Test
  public void testInvalidMove() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    try {
      m.move(4, 1, 4, 4);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid move");
    }
    try {
      m.move(4, 1, 5, 4);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid move");
    }
    try {
      m.move(4, 1, -1, 1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid move");
    }
    try {
      m.move(4, -1, 0, -1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid move");
    }
    try {
      m.move(-4, 1, 0, -1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid move");
    }
    try {
      m.move(4, 1, 3, 1);
    } catch (IllegalArgumentException iae) {
      assertEquals(iae.getMessage(), "Invalid move");
    }
  }

  @Test
  public void isGameOver() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    assertFalse(m.isGameOver());
    m.move(4, 2, 4, 4);
    m.move(4, 5, 4, 3);
    m.move(4, 7, 4, 5);
    m.move(2, 4, 4, 4);
    m.move(5, 4, 3, 4);
    m.move(7, 4, 5, 4);
    assertTrue(m.isGameOver());

  }

  @Test
  public void getGameState() {
    MarbleSolitaireModel n = new MarbleSolitaireModelImpl(5, 6, 5);
    n.move(6, 3, 6, 5);
    assertEquals("        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "O O O O O O O O O O O O O\n" +
            "O O _ _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        \n" +
            "        O O O O O        ", n.getGameState());
  }

  @Test
  public void getScore() {
    MarbleSolitaireModel m = new MarbleSolitaireModelImpl();
    assertEquals(m.getScore(), 32);
    m.move(4, 2, 4, 4);
    assertEquals(m.getScore(), 31);
    m.move(4, 5, 4, 3);
    assertEquals(m.getScore(), 30);
    m.move(6, 4, 4, 4);
    assertEquals(m.getScore(), 29);
  }
}