package e2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class CellTest {

    @Test
    public void adjacentCells() {
        int x = 1;
        int y = 1;
        Set<Pair<Integer, Integer>> expectedPositions = Set.of(
            new Pair<>(0, 0),
            new Pair<>(0, 1),
            new Pair<>(1, 0),
            new Pair<>(2, 2),
            new Pair<>(2, 0),
            new Pair<>(0, 2),
            new Pair<>(1, 2),
            new Pair<>(2, 1)
        );
        Cell cell = new CellImpl(new Pair<Integer, Integer>(x, y));
        assertEquals(expectedPositions, cell.adjacents());
    }

}
