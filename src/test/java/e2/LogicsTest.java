package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void countOfAdjacentMinesWithoutMines() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(0, 0);
        assertEquals(0, logics.adjacentMines(position));
    }

    @Test
    public void countOfAdjacentMinesWithMines() {
        int maxNumberOfMines = SIZE * SIZE;
        Logics logics = new LogicsImpl(SIZE, maxNumberOfMines);
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(0, 0);
        assertEquals(3, logics.adjacentMines(position));
    }

    @Test
    public void recoursivelyCellDisablingWithoutMines() {
        int numberOfCells = SIZE * SIZE;
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(0, 0);
        logics.hit(position);
        int discoveredCells = 0;
        for (int x=0; x<=SIZE; x++) {
            for (int y=0; y<=SIZE; y++) {
                if (logics.hasBeenDiscovered(new Pair<Integer,Integer>(x, y))) {
                    discoveredCells++;
                }
            }
        }
        assertEquals(numberOfCells, discoveredCells);
    }

    @Test
    public void settingFlags() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(0, 0);
        logics.switchFlag(position);
        assertTrue(logics.hasFlag(position));
    }

    @Test
    public void initialFlags() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(0, 0);
        assertFalse(logics.hasFlag(position));
    }

    @Test
    public void disablingFlag() {
        Logics logics = new LogicsImpl(SIZE, 0);
        Pair<Integer,Integer> position = new Pair<Integer,Integer>(0, 0);
        logics.switchFlag(position);
        logics.switchFlag(position);
        assertFalse(logics.hasFlag(position));
    }
}
