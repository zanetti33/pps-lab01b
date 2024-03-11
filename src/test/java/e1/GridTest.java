package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridTest {

    private final static int GRID_SIZE = 5;

    private Grid grid;

    @BeforeEach
    public void initializeGrid() {
        this.grid = new GridImpl(GRID_SIZE);
    }
    

    @Test
    public void canCreateGridWithSize() {

    }
}
