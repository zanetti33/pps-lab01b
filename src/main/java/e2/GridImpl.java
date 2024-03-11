package e2;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GridImpl implements Grid {

    private final int size;
    private final Set<Pair<Integer, Integer>> minePositions;

    public GridImpl(int size, int minesNumber) {
        this.size = size;
        this.minePositions = initializeMinePositions(minesNumber);
    }

    private Set<Pair<Integer, Integer>> initializeMinePositions(int minesNumber) {
        int numberOfCells = this.size * this.size;
        List<Integer> cellIndexes = IntStream.range(0, numberOfCells).boxed().collect(Collectors.toList());
        Collections.shuffle(cellIndexes);
        List<Integer> bombsIndexes = cellIndexes.subList(0, minesNumber);
        return convertIndexesToPositions(bombsIndexes);
    }

    private Set<Pair<Integer, Integer>> convertIndexesToPositions(List<Integer> bombsIndexes) {
        return bombsIndexes.stream()
            .map(index -> new Pair<>(
                index % this.size,
                index / this.size
            ))
            .collect(Collectors.toSet());
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> position) {
        return this.minePositions.contains(position);
    }

    @Override
    public int adjacentMines(Pair<Integer, Integer> position) {
        return (int) new CellImpl(position).adjacents().stream()
            .filter(adjacentPosition -> this.hasMine(adjacentPosition))
            .count();
    }

    @Override
    public Set<Pair<Integer, Integer>> adjacentPositions(Pair<Integer, Integer> position) {
        Cell cell = new CellImpl(position);
        return cell.adjacents().stream()
            .filter(adjacentPosition -> this.isInside(adjacentPosition.getX(), adjacentPosition.getY()))
            .collect(Collectors.toSet());
    }

    private boolean isInside(Integer x, Integer y) {
        return x >= 0 && y >= 0 && x < this.size && y < this.size;
    }

}
