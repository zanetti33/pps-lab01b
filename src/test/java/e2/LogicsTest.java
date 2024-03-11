package e2;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void notHittingAMineReturnsFalse() {
        Logics logics = new LogicsImpl(SIZE, 0);
        assertFalse(logics.hit(new Pair<Integer,Integer>(0, 0)));
    }

    @Test
    public void mantainsHistoryOfHits() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(0, 0);
        logics.hit(position);
        assertTrue(logics.hasBeenDiscovered(position));
    }
}
