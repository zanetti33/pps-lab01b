package e1;

public class LogicsImpl implements Logics {

	private final Grid grid;
	 
    public LogicsImpl(int size){
    	this.grid = new GridImpl(size);
    }

	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
		this.grid = new GridImpl(size, pawnPosition, knightPosition);
	}
    
	@Override
	public boolean hit(int row, int column) {
		// Below a compact way to express allowed moves for the knight
		Pair<Integer, Integer> knightPosition = this.grid.knightPosition();
		int y = row-knightPosition.getY();
		int x = column-knightPosition.getX();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			return this.grid.moveKnight(new Pair<>(row, column));
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int column) {
		return this.grid.knightPosition().equals(new Pair<>(row, column));
	}

	@Override
	public boolean hasPawn(int row, int column) {
		return this.grid.pawnPosition().equals(new Pair<>(row, column));
	}
}
