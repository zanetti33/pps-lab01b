package e1;

import java.util.Random;

public class GridImpl implements Grid {

	private final Random random = new Random();
    private final int size;
    private final Pair<Integer, Integer> pawnPosition;
    private Pair<Integer, Integer> knightPosition;

    public GridImpl(int gridSize, int pawnRow, int pawnColumn, int knightRow, int knightColumn) {
        this.size = gridSize;
        this.pawnPosition = new Pair<Integer,Integer>(pawnRow, pawnColumn);
        this.knightPosition = new Pair<Integer,Integer>(knightRow, knightColumn);
    }

    public GridImpl(int gridSize) {
        this.size = gridSize;
        this.pawnPosition = randomEmptyPosition();
        this.knightPosition = randomEmptyPosition();
    }
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawnPosition!=null && this.pawnPosition.equals(pos) ? randomEmptyPosition() : pos;
    }

    @Override
    public Pair<Integer, Integer> pawnPosition() {
        return this.pawnPosition;
    }

    @Override
    public Pair<Integer, Integer> knightPosition() {
        return this.knightPosition;
    }

}
