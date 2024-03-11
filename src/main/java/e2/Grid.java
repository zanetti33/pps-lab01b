package e2;

import java.util.Set;

public interface Grid {

    boolean hasMine(Pair<Integer, Integer> position);

    int adjacentMines(Pair<Integer, Integer> position);

    Set<Pair<Integer, Integer>> adjacentPositions(Pair<Integer, Integer> position);

}
