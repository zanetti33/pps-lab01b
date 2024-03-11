package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridTest {

    private static final int SIZE = 7;
    private static final int MINES_NUMBER = 8;
    private Grid grid;

    @BeforeEach
    public void initializeGrid() {
        this.grid = new GridImpl(SIZE, MINES_NUMBER);
    }

    @Test
    public void creatingGrid() {
    }

    @Test
    public void minesArePresentInGrid() {
        int mineCounter = 0;
        for (int x=0; x<=SIZE; x++) {
            for (int y=0; y<=SIZE; y++) {
                if (this.grid.hasMine(new Pair<Integer,Integer>(x, y))) {
                    mineCounter++;
                }
            }
        }
        assertEquals(MINES_NUMBER, mineCounter);
    }
}
