package e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MineManagerImpl implements MineManager {

    Board board;
    Set<Pair<Integer, Integer>> minesPositions;
    Random random = new Random();

    public MineManagerImpl(Board board, int mines) {
        this.board = board;
        this.minesPositions = new HashSet<>();
        this.generateRandomMines(mines);
    }

    private void generateRandomMines(int mines) {
        for (int i = 0; i < mines; i++) {
            this.minesPositions.add(this.getEmptyRandomPosition());
        }
    }

    private Pair<Integer, Integer> getEmptyRandomPosition() {
        var pos = this.randomPosition();
        return this.minesPositions.contains(pos) ? this.getEmptyRandomPosition() : pos;
    }

    private Pair<Integer, Integer> randomPosition() {
        return new Pair<>(this.random.nextInt(this.board.getRows()),
                this.random.nextInt(this.board.getColumns()));
    }

    public boolean hasMine(Pair<Integer, Integer> pos) {
        return this.minesPositions.contains(pos);
    }
}
