package e1;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
public class LogicTest {

  private final static int GRID_SIZE = 5;
  private final static int SMALL_GRID_SIZE = 2;
  private Logics grid;

  @BeforeEach
  public void initializeGrid() {
    this.grid = new LogicsImpl(GRID_SIZE);
  }

  @Test
  public void createNewGrid() {
  }

  @Test
  public void thereIsOnlyOneKnightInTheGrid() {
    checkingFunctionOnAllTheGridReturnsOnlyOneTrue(this.grid::hasKnight);
  }

  @Test
  public void thereIsOnlyOnePawnInTheGrid() {
    checkingFunctionOnAllTheGridReturnsOnlyOneTrue(this.grid::hasPawn);
  }

  @Test
  public void knightAndPawnMustBeInDifferentCells() {
    assertNotEquals(knightPosition(), pawnPosition());
  }

  private void checkingFunctionOnAllTheGridReturnsOnlyOneTrue(BiFunction<Integer, Integer, Boolean> isHere) {
    int lowestIndex = 0;
    int numberOfItems = 0;
    int expectednumberOfItems = 1;
    for (int row = lowestIndex; row < GRID_SIZE; row++) {
      for (int column = lowestIndex; column < GRID_SIZE; column++) {
        if (isHere.apply(row, column)) {
          numberOfItems++;
        }
      }
    }
    assertEquals(expectednumberOfItems, numberOfItems);
  }

  @Test
  public void hasKnightOutOfTheGridGeneratesException() {
    callToFunctionOutOfGridReturnsFalse(this.grid::hasKnight);
  }

  @Test
  public void hitsOutOfTheGridGeneratesException() {
    callToFunctionOutOfGridReturnsFalse(this.grid::hasPawn);
  }

  private void callToFunctionOutOfGridReturnsFalse(BiFunction<Integer, Integer, Boolean> isHere) {
    int negativeIndex = -1;
    assertAll(
      () -> assertFalse(isHere.apply(GRID_SIZE, GRID_SIZE)),
      () -> assertFalse(isHere.apply(negativeIndex, GRID_SIZE)),
      () -> assertFalse(isHere.apply(negativeIndex, negativeIndex)),
      () -> assertFalse(isHere.apply(GRID_SIZE, negativeIndex))
    );
  }

  @Test
  public void checkThatKnightCannotMoveOutOfBounds() {
    // in this kind of grid (2x2) the knight must have all illegal moves
    this.grid = new LogicsImpl(SMALL_GRID_SIZE);
    String testName = "checkThatKnightCannotMoveOutOfBounds";
    Pair<Integer, Integer> currentKnightPosition = knightPosition();
    assertAll(
      testName,
      getAllPossibleKnightMoveDeltas().stream()
        .map(moveDelta ->
          () -> assertThrows(
            IndexOutOfBoundsException.class, 
            () -> this.grid.hit(
              currentKnightPosition.getY() + moveDelta.getY(),
              currentKnightPosition.getX() + moveDelta.getX()
            )
          )
        )
    );
  }

  private Pair<Integer, Integer> knightPosition() {
    return itemPosition(this.grid::hasKnight);
  }

  private Pair<Integer, Integer> pawnPosition() {
    return itemPosition(this.grid::hasPawn);
  }

  private Pair<Integer, Integer> itemPosition(BiFunction<Integer, Integer, Boolean> isHere) {
    int lowestIndex = 0;
    for (int row = lowestIndex; row < GRID_SIZE; row++) {
      for (int column = lowestIndex; column < GRID_SIZE; column++) {
        if (isHere.apply(row, column)) {
          return new Pair<Integer,Integer>(row, column);
        }
      }
    }
    throw new IllegalStateException();
  }

  private static List<Pair<Integer, Integer>> getAllPossibleKnightMoveDeltas() {
    int minimumValue = -2;
    int maximumValue = 3;
    int[] allPossibleMovementOnOneDirection = IntStream.range(minimumValue, maximumValue).toArray();
    List<Pair<Integer, Integer>> moves = new ArrayList<>();
    IntStream.of(allPossibleMovementOnOneDirection)
      .forEach(firstDimension -> moves.addAll(
        IntStream.of(allPossibleMovementOnOneDirection)
          .mapToObj(secondDimension -> new Pair<>(firstDimension, secondDimension))
          .filter(pair -> {
              if (pair.getX() != 0 && pair.getY() != 0 && (Math.abs(pair.getX()) + Math.abs(pair.getY())) == 3)
                return true;
              return false;
            })
          .toList()
        )
      );
    return moves;
  }

  @Test
  public void checkThatValidMoveActuallyMovesTheKnight() {
    int pawnRow = 1;
    int pawnColumn = 1;
    int knightRow = 2;
    int knightColumn = 1;
    int newKnightRow = 0;
    int newKnightColumn = 0;
    int logicSize = 3;
    Pair<Integer, Integer> pawnPosition = new Pair<Integer,Integer>(pawnRow, pawnColumn);
    Pair<Integer, Integer> knightPosition = new Pair<Integer,Integer>(knightRow, knightColumn);
    Logics logics = new LogicsImpl(logicSize, pawnPosition, knightPosition);
    logics.hit(newKnightRow, newKnightColumn);
    assertTrue(logics.hasKnight(newKnightRow, newKnightColumn));
  }

  @Test
  public void checkThatKnightHitOnPawnReturnsTrue() {
    int pawnRow = 0;
    int pawnColumn = 0;
    int knightRow = 2;
    int knightColumn = 1;
    int logicSize = 3;
    Pair<Integer, Integer> pawnPosition = new Pair<Integer,Integer>(pawnRow, pawnColumn);
    Pair<Integer, Integer> knightPosition = new Pair<Integer,Integer>(knightRow, knightColumn);
    Logics logics = new LogicsImpl(logicSize, pawnPosition, knightPosition);
    assertTrue(logics.hit(pawnRow, pawnColumn));
  }


}
