package e1;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Board board;
	private final GameManager gameManager;

	public LogicsImpl(int size, Pair<Integer,Integer> pawnPosition,
			  Pair<Integer,Integer> knightPosition) {
		this.board = new BoardImpl(size, size);
		this.gameManager = new GameManagerImpl(this.board);
		this.pawn = pawnPosition;
		this.knight = knightPosition;
	}

	public LogicsImpl(int size) {
		this.board = new BoardImpl(size, size);
		this.gameManager = new GameManagerImpl(this.board);
		this.pawn = this.gameManager.generatePawn();
		this.knight = this.gameManager.generateKnight(this.pawn);
	}
    
	@Override
	public boolean hit(int row, int col) {
		final var pos = new Pair<>(row, col);
		if (!this.board.validPosition(pos))
			throw new IndexOutOfBoundsException();
		if (!this.gameManager.validMovement(this.knight, pos))
			return false;
		this.moveKnight(pos);
		return this.pawn.equals(this.knight);
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}

	private void moveKnight(Pair<Integer, Integer> pos){
		this.knight = pos;
	}
}
