package e1;

import java.util.Random;

public class GameManagerImpl implements GameManager {

    private final Board board;
    private final Random random = new Random();

    public GameManagerImpl(int rows, int columns) {
        this.board = new BoardImpl(rows, columns);
    }

    public GameManagerImpl(int n) {
        this(n,n);
    }

    @Override
    public Pair<Integer, Integer> generatePawn() {
        return this.randomPosition();
    }

    @Override
    public Pair<Integer, Integer> generateKnight(Pair<Integer, Integer> pawn) {
        final var position = this.randomPosition();
        return pawn.equals(position) ? this.generateKnight(pawn) : position;
    }

    @Override
    public boolean validPosition(Pair<Integer, Integer> position) {
        return this.board.validPosition(position);
    }

    @Override
    public boolean validMovement(Pair<Integer, Integer> initialPosition,
                                 Pair<Integer, Integer> finalPosition) {
        int x = finalPosition.getX()-initialPosition.getX();
        int y = finalPosition.getY()-initialPosition.getY();
        return x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3 && this.validPosition(finalPosition);
    }

    private Pair<Integer, Integer> randomPosition() {
        return new Pair<>(this.random.nextInt(this.board.getRows()),
                this.random.nextInt(this.board.getColumns()));
    }
}
