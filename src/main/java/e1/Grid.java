package e1;

public interface Grid {

    Pair<Integer, Integer> pawnPosition();

    Pair<Integer, Integer> knightPosition();

    boolean moveKnight(Pair<Integer, Integer> newPosition);
}
