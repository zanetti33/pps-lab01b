package e2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LogicsTest {
    
    private static final int SIZE = 5;

    @Test
    public void hittingAMineReturnsTrue() {
        int maxNumberOfMines = SIZE * SIZE;
        Logics logics = new LogicsImpl(SIZE, maxNumberOfMines);
        assertTrue(logics.hit(new Pair<Integer,Integer>(0, 0)));
    }
}
