package e2;

import java.util.HashSet;
import java.util.Set;

public class CellImpl implements Cell {

    private final Pair<Integer, Integer> position;

    public CellImpl(Pair<Integer, Integer> position) {
        this.position = position;
    }

    @Override
    public Set<Pair<Integer, Integer>> adjacents() {
        Set<Pair<Integer, Integer>> adjactents = new HashSet<>();
        for (int deltaX = -1; deltaX <= 1; deltaX++) {
            for (int deltaY = -1; deltaY <= 1; deltaY++) {
                if (deltaX != 0 || deltaY != 0) {
                    adjactents.add(new Pair<Integer,Integer>(
                        this.position.getX() + deltaX,
                        this.position.getY() + deltaY
                    ));
                }
            }
        }
        return adjactents;
    }

}
