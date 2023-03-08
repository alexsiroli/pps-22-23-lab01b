package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    private final static int SIZE = 5;
    private static final int LAPS_NUMBER = 100;

    private final Board board = new BoardImpl(SIZE, SIZE);
    private final GameManager gameManager = new GameManagerImpl(this.board);

    @Test
    void generatePawn() {
        final var pawnPosition = this.gameManager.generatePawn();
        assertTrue(this.board.validPosition(pawnPosition));
    }

    @Test
    void generateKnight() {
        final var knightPosition = this.gameManager.generateKnight(new Pair<>(0,0));
        assertTrue(this.board.validPosition(knightPosition));
    }

    @Test
    void checkDifferentPositions() {
        final var pawnPosition = this.gameManager.generatePawn();
        final var knightPosition = this.gameManager.generateKnight(pawnPosition);
        assertNotSame(pawnPosition, knightPosition);
    }

    @Test
    void bruteForceCheck() {
        for (int i=0; i<LAPS_NUMBER; i++) {
            var pawnPosition = this.gameManager.generatePawn();
            assertTrue(this.board.validPosition(pawnPosition));
            var knightPosition = this.gameManager.generateKnight(pawnPosition);
            assertTrue(this.board.validPosition(knightPosition));
            assertNotSame(pawnPosition, knightPosition);
        }
    }

    @Test
    void checkValidMovement() {
        var initialPosition = new Pair<>(0,0);
        var finalPosition = new Pair<>(1,2);
        assertTrue(this.gameManager.validMovement(initialPosition, finalPosition));
        finalPosition = new Pair<>(2,1);
        assertTrue(this.gameManager.validMovement(initialPosition, finalPosition));
        finalPosition = new Pair<>(2,2);
        assertFalse(this.gameManager.validMovement(initialPosition, finalPosition));
    }
}
