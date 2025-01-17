package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridTest {

    private final static int GRID_SIZE = 3;
    private final static int PAWN_ROW = 0;
    private final static int PAWN_COLUMN = 0;
    private final static int KNIGHT_ROW = 2;
    private final static int KNIGHT_COLUMN = 1;
    private final static Pair<Integer, Integer> PAWN_POSITION = new Pair<Integer,Integer>(PAWN_ROW, PAWN_COLUMN);
    private final static Pair<Integer, Integer> KNIGHT_POSITION = new Pair<Integer,Integer>(KNIGHT_ROW, KNIGHT_COLUMN);
    private Grid gridWithFixedPosition;

    @BeforeEach
    public void initializeGrid() {
        this.gridWithFixedPosition = new GridImpl(
            GRID_SIZE,
            PAWN_POSITION,
            KNIGHT_POSITION
        );
    }
    

    @Test
    public void canCreateGridWithSize() {
    }

    @Test
    public void pawnPosition() {
        Pair<Integer, Integer> pawnPosition = new Pair<Integer,Integer>(PAWN_ROW, PAWN_COLUMN);
        assertEquals(pawnPosition, gridWithFixedPosition.pawnPosition());
    }

    @Test
    public void knightPosition() {
        Pair<Integer, Integer> knightPosition = new Pair<Integer,Integer>(KNIGHT_ROW, KNIGHT_COLUMN);
        assertEquals(knightPosition, gridWithFixedPosition.knightPosition());
    }

    @Test
    public void movingKnightOutOfBoundsThrowsException() {
        int newRowOutOfBound = 4;
        int newColumn = 2;
        Pair<Integer, Integer> newKnightPosition = new Pair<Integer,Integer>(newRowOutOfBound, newColumn);
        assertThrows(IndexOutOfBoundsException.class, () -> this.gridWithFixedPosition.moveKnight(newKnightPosition));
    }

    @Test
    public void movingKnightOnPawnReturnsTrue() {
        Pair<Integer, Integer> pawnPosition = new Pair<Integer,Integer>(PAWN_ROW, PAWN_COLUMN);
        assertTrue(this.gridWithFixedPosition.moveKnight(pawnPosition));
    }
}
