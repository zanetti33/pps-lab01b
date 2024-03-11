package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridTest {

    private final static int GRID_SIZE = 3;
    private final static int PAWN_ROW = 0;
    private final static int PAWN_COLUMN = 0;
    private final static int KNIGHT_ROW = 2;
    private final static int KNIGHT_COLUMN = 1;

    private Grid grid;
    private Grid gridWithFixedPosition;

    @BeforeEach
    public void initializeGrid() {
        this.grid = new GridImpl(GRID_SIZE);
        this.gridWithFixedPosition = new GridImpl(
            GRID_SIZE,
            PAWN_ROW,
            PAWN_COLUMN,
            KNIGHT_ROW,
            KNIGHT_COLUMN
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
}
