package e2;

public class LogicsImpl implements Logics {

    private final Grid grid;

    public LogicsImpl(int size, int minesNumber) {
        this.grid = new GridImpl(size, minesNumber); 
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> position) {
        return this.grid.hasMine(position);
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        return this.hasMine(position);
    }

}
