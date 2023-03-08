package e1;

/**
 * Interface that manages the creation and movement of pawn and knight on a game board.
 */
public interface GameManager {

    /**
     * @return a cell in which the pawn can be placed.
     */
    Pair<Integer, Integer> generatePawn();

    /**
     * @param pawn position
     * @return a cell in which the knight can be placed.
     */
    Pair<Integer, Integer> generateKnight(Pair<Integer, Integer> pawn);

    /**
     * Method that verifies if the movement performed by the rider is valid.
     *
     * @param initialPosition of the knight
     * @param finalPosition of the knight
     * @return true if the move is valid
     */
    boolean validMovement(Pair<Integer, Integer> initialPosition, Pair<Integer, Integer> finalPosition);
}
