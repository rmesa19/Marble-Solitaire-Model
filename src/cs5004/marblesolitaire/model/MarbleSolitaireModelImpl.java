package cs5004.marblesolitaire.model;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Model class for MarbleSolitaire game.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  private Marble[][] board;
  private final int thickness;
  private final int squareDim;
  private final int total_Length;

  /**
   * Enum class used to represent Marble and empty slot on Marble Solitaire board.
   */
  public enum Marble {
    O, NONE;

    @Override
    public String toString() {
      String symbol;
      switch (this) {
        case O:
          symbol = "O";
          break;
        case NONE:
          symbol = "_";
          break;
        default:
          throw new IllegalArgumentException();
      }
      return symbol;
    }
  }

  /**
   * Constructor for Marble Solitaire that takes no parameters and places empty slot at the center
   * of the board.
   */
  public MarbleSolitaireModelImpl() {
    this.thickness = 3;
    this.squareDim = thickness - 1;
    this.total_Length = (2 * squareDim) + thickness;
    this.board = new Marble[total_Length][total_Length];
    for (int j = 0; j < total_Length; j++) {
      if (j >= squareDim && j < total_Length - squareDim) {
        for (int l = 0; l < total_Length; l++) {
          board[j][l] = Marble.O;
        }
      }
      for (int i = squareDim; i < total_Length - squareDim; i++) {
        board[j][i] = Marble.O;
      }
    }
    board[3][3] = Marble.NONE;
  }

  /**
   * Constructor for Marble Solitaire Model. Takes input to place the initial empty slot on the
   * board.
   *
   * @param sRow row where empty slot will be placed.
   * @param sCol column where empty slot will be placed.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) {
    this.thickness = 3;
    this.squareDim = thickness - 1;
    this.total_Length = (2 * squareDim) + thickness;
    this.board = new Marble[total_Length][total_Length];
    for (int j = 0; j < total_Length; j++) {
      if (j >= squareDim && j < total_Length - squareDim) {
        for (int l = 0; l < total_Length; l++) {
          board[j][l] = Marble.O;
        }
      }
      for (int i = squareDim; i < total_Length - squareDim; i++) {
        board[j][i] = Marble.O;
      }
    }
    if ((sRow > 7) || (sCol > 7) || (sRow < 1) || (sCol < 1)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    if (board[sRow - 1][sCol - 1] == null) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      board[sRow - 1][sCol - 1] = Marble.NONE;
    }
  }

  /**
   * Constructor for Marble Solitaire Model. Takes in board thickness as a parameter and places the
   * initial empty slot in the middle of the board.
   *
   * @param armThickness represents the arm thickness of the board.
   */
  public MarbleSolitaireModelImpl(int armThickness) {
    if (armThickness % 2 != 1 || armThickness == 1) {
      throw new IllegalArgumentException("The arm thickness " +
              "must be an odd number and greater than 1.");
    }
    this.thickness = armThickness;
    this.squareDim = thickness - 1;
    this.total_Length = (2 * squareDim) + thickness;
    this.board = new Marble[total_Length][total_Length];
    for (int j = 0; j < total_Length; j++) {
      if (j >= squareDim && j < total_Length - squareDim) {
        for (int l = 0; l < total_Length; l++) {
          board[j][l] = Marble.O;
        }
      }
      for (int i = squareDim; i < total_Length - squareDim; i++) {
        board[j][i] = Marble.O;
      }
    }
    board[(int) Math.floor(total_Length / 2.0)]
            [(int) Math.floor(total_Length / 2.0)] = Marble.NONE;
  }

  /**
   * Constructor for Marble Solitaire. Takes in Arm thickness, a row and column number parameters
   * and places the empty slot at position (sRow, sCol) on the board with thickness of thickness.
   *
   * @param armThickness How thick the board will be.
   * @param sRow         the row where the initial empty slot will be.
   * @param sCol         the column where the initial empty slot will be.
   */
  public MarbleSolitaireModelImpl(int armThickness, int sRow, int sCol) {
    if (armThickness % 2 != 1 || armThickness == 1) {
      throw new IllegalArgumentException("The arm thickness " +
              "must be an odd number and greater than 1.");
    }
    this.thickness = armThickness;
    this.squareDim = thickness - 1;
    this.total_Length = (2 * squareDim) + thickness;
    this.board = new Marble[total_Length][total_Length];
    for (int j = 0; j < total_Length; j++) {
      if (j >= squareDim && j < total_Length - squareDim) {
        for (int l = 0; l < total_Length; l++) {
          board[j][l] = Marble.O;
        }
      }
      for (int i = squareDim; i < total_Length - squareDim; i++) {
        board[j][i] = Marble.O;
      }
    }
    if ((sRow > total_Length) || (sCol > total_Length) || (sRow < 1) || (sCol < 1)) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    }

    if (board[sRow - 1][sCol - 1] == null) {
      throw new IllegalArgumentException("Invalid empty cell position (" + sRow + "," + sCol + ")");
    } else {
      board[sRow - 1][sCol - 1] = Marble.NONE;
    }
  }

  /**
   * Return the current game state, as a 2D array of Player. A {@code null} value in the grid
   * indicates an empty position on the board.
   *
   * @return the current game board
   */
  private Marble[][] getBoard() {
    Marble[][] copy = new Marble[total_Length][total_Length];
    for (int i = 0; i < copy.length; i++) {
      for (int j = 0; j < copy[i].length; j++) {
        copy[i][j] = board[i][j];
      }
    }
    return copy;
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int toRowConvert = toRow - 1;
    int toColumnConvert = toCol - 1;
    int fromRowConvert = fromRow - 1;
    int fromColumnConvert = fromCol - 1;
    int upperBounds = total_Length - 2;
    int lowerBounds = 3;


    if ((toRow > total_Length) || (toCol > total_Length) || (toRow < 1) || (toCol < 1)
            || (fromRow > total_Length) || (fromCol > total_Length) || (fromRow < 1) || (fromCol < 1)) {
      throw new IllegalArgumentException("Invalid move");
    }
    if (board[toRowConvert][toColumnConvert] == null
            || board[fromRowConvert][fromColumnConvert] == null) {
      throw new IllegalArgumentException("Invalid move");
    }

    if (fromCol <= upperBounds && fromCol + 2 == toCol && fromRow == toRow) {
      boolean canMoveRight = (board[fromRowConvert][fromColumnConvert] == Marble.O &&
              board[fromRowConvert][fromColumnConvert + 1] == Marble.O &&
              board[fromRowConvert][fromColumnConvert + 2] == Marble.NONE);
      if (canMoveRight) {
        board[fromRowConvert][fromColumnConvert] = Marble.NONE;
        board[fromRowConvert][fromColumnConvert + 1] = Marble.NONE;
        board[toRowConvert][toColumnConvert] = Marble.O;
        return;
      }
    }

    if (fromCol >= lowerBounds && fromCol - 2 == toCol && fromRow == toRow) {
      boolean canMoveLeft = (board[fromRowConvert][fromColumnConvert] == Marble.O &&
              board[fromRowConvert][fromColumnConvert - 1] == Marble.O &&
              board[fromRowConvert][fromColumnConvert - 2] == Marble.NONE);
      if (canMoveLeft) {
        board[fromRowConvert][fromColumnConvert] = Marble.NONE;
        board[fromRowConvert][fromColumnConvert - 1] = Marble.NONE;
        board[toRowConvert][toColumnConvert] = Marble.O;
        return;
      }
    }

    if (fromRow >= lowerBounds && fromRow - 2 == toRow && fromCol == toCol) {
      boolean canMoveUp = (board[fromRowConvert][fromColumnConvert] == Marble.O &&
              board[fromRowConvert - 1][fromColumnConvert] == Marble.O &&
              board[fromRowConvert - 2][fromColumnConvert] == Marble.NONE);
      if (canMoveUp) {
        board[fromRowConvert][fromColumnConvert] = Marble.NONE;
        board[fromRowConvert - 1][fromColumnConvert] = Marble.NONE;
        board[toRowConvert][toColumnConvert] = Marble.O;
        return;
      }
    }

    if (fromRow <= upperBounds && fromRow + 2 == toRow && fromCol == toCol) {
      boolean canMoveDown = (board[fromRowConvert][fromColumnConvert] == Marble.O &&
              board[fromRowConvert + 1][fromColumnConvert] == Marble.O &&
              board[fromRowConvert + 2][fromColumnConvert] == Marble.NONE);
      if (canMoveDown) {
        board[fromRowConvert][fromColumnConvert] = Marble.NONE;
        board[fromRowConvert + 1][fromColumnConvert] = Marble.NONE;
        board[toRowConvert][toColumnConvert] = Marble.O;
        return;
      }
    }

    throw new IllegalArgumentException("Invalid move");
  }

  /**
   * A helper method that returns a boolean whether or not a move is possible on the board.
   *
   * @return true if a move is possible, false otherwise.
   */
  private boolean canMove() {
    for (int i = 0; i < board.length; i++) {
      if (i < board.length - 3) {
        for (int m = 0; m < board.length; m++) {
          if (board[i][m] == Marble.O && board[i + 1][m] == Marble.O
                  && board[i + 2][m] == Marble.NONE) {
            return true;
          }
        }
      }
      if (i >= 2) {
        for (int n = 0; n < board.length; n++) {
          if (board[i][n] == Marble.O &&
                  board[i - 1][n] == Marble.O && board[i - 2][n] == Marble.NONE) {
            return true;
          }
        }
      }

      for (int j = 0; j <= board.length - 3; j++) {
        if (board[i][j] == Marble.O && board[i][j + 1] == Marble.O
                && board[i][j + 2] == Marble.NONE) {
          return true;
        }
      }

      for (int k = 2; k < board.length; k++) {
        if (board[i][k] == Marble.O && board[i][k - 1] == Marble.O
                && board[i][k - 2] == Marble.NONE) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean isGameOver() {
    return !canMove();
  }

  @Override
  public String getGameState() {
    return Arrays.stream(getBoard()).map(
            row -> "" + Arrays.stream(row).map(p -> p == null ? " " : p.toString()).
                    collect(Collectors.joining(" ")))
            .collect(Collectors.joining("\n"));
  }


  @Override
  public int getScore() {
    int score = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board.length; j++) {
        if (board[i][j] == Marble.O) {
          score = score + 1;
        }
      }
    }

    return score;
  }
}
