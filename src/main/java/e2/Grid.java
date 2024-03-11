package e2;

public interface Grid {

    boolean hasMine(Pair<Integer, Integer> position);

    int adjacentMines(Pair<Integer, Integer> position);

}
