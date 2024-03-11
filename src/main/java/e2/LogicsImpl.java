package e2;

import java.util.HashSet;
import java.util.Set;

public class LogicsImpl implements Logics {

    private final Grid grid;
    private final int numberOfEmptyCells;
    private final Set<Pair<Integer, Integer>> discoveredPositions = new HashSet<>();
    private final Set<Pair<Integer, Integer>> flagPositions = new HashSet<>();

    public LogicsImpl(int size, int minesNumber) {
        this.grid = new GridImpl(size, minesNumber); 
        this.numberOfEmptyCells = size * size - minesNumber;
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> position) {
        return this.grid.hasMine(position);
    }

    @Override
    public boolean hit(Pair<Integer, Integer> position) {
        Boolean firstTimeDiscovering = this.discoveredPositions.add(position);
        if (firstTimeDiscovering && this.adjacentMines(position) == 0) {
            this.grid.adjacentPositions(position).forEach(adjacentPosition -> this.hit(adjacentPosition));
        }
        return this.hasMine(position);
    }

    @Override
    public boolean hasBeenDiscovered(Pair<Integer, Integer> position) {
        return this.discoveredPositions.contains(position);
    }

    @Override
    public int adjacentMines(Pair<Integer, Integer> position) {
        return this.grid.adjacentMines(position);
    }

    @Override
    public boolean hasFlag(Pair<Integer, Integer> position) {
        return this.flagPositions.contains(position);
    }

    @Override
    public void switchFlag(Pair<Integer, Integer> position) {
        if (this.flagPositions.contains(position)) {
            this.flagPositions.remove(position);
        } else {
            this.flagPositions.add(position);
        }
    }

    @Override
    public boolean isWon() {
        return this.discoveredPositions.size() == this.numberOfEmptyCells;
    }

}
