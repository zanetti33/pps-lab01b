package e1;

import java.util.Random;

public class GridImpl implements Grid {

	private final Random random = new Random();
    private final int size;
    private final Pair<Integer, Integer> pawnPosition;
    private Pair<Integer, Integer> knightPosition;

    public GridImpl(int gridSize, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
        this.size = gridSize;
        checkPositionIsInsideGrid(knightPosition);
        checkPositionIsInsideGrid(pawnPosition);
        this.pawnPosition = pawnPosition;
        this.knightPosition = knightPosition;
    }

    private void checkPositionIsInsideGrid(Pair<Integer, Integer> position) {
        int y = position.getY();
        int x = position.getX();
		if (y<0 || x<0 || y >= this.size || x >= this.size) {
			throw new IndexOutOfBoundsException();
		}
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

    @Override
    public boolean moveKnight(Pair<Integer, Integer> newPosition) {
        checkPositionIsInsideGrid(newPosition);
        this.knightPosition = newPosition;
        return this.knightPosition.equals(this.pawnPosition);
    }

}
