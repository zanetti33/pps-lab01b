package e2;

public interface Logics {

    boolean hasMine(Pair<Integer, Integer> position);

    boolean hit(Pair<Integer, Integer> position);

    boolean hasBeenDiscovered(Pair<Integer, Integer> position);

    int adjacentMines(Pair<Integer, Integer> position);
    
}
