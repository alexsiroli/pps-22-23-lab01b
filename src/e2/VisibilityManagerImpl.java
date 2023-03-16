package e2;

import java.util.HashSet;
import java.util.Set;

public class VisibilityManagerImpl implements VisibilityManager {

    Board board;
    Set<Pair<Integer, Integer>> visiblePositions;

    public VisibilityManagerImpl(Board board) {
        this.board = board;
        this.visiblePositions = new HashSet<>();
    }

    @Override
    public boolean isVisible(Pair<Integer, Integer> pos) {
        return this.visiblePositions.contains(pos);
    }

    @Override
    public void setVisible(Pair<Integer, Integer> pos) {
        this.visiblePositions.add(pos);
    }
}
