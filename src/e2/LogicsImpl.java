package e2;

public class LogicsImpl implements Logics {

    Board board;
    MineManager mineManager;
    //NumbersManager numbersManager = new NumbersManagerImpl();
    //VisibilityManager visibilityManager = new VisibilityManagerImpl();
    //FlagsManager flagsManager = new FlagsManagerImpl();

    public LogicsImpl(int size, int mines) {
        this.board = new BoardImpl(size, size);
        this.mineManager = new MineManagerImpl(this.board, mines);
    }

    @Override
    public boolean isThereVictory() {
        return false;
    }

    @Override
    public void flag(Pair<Integer, Integer> pos) {

    }

    @Override
    public boolean hasFlag(Pair<Integer, Integer> pos) {
        return false;
    }

    @Override
    public boolean hasNumber(Pair<Integer, Integer> pos) {
        return false;
    }

    @Override
    public Integer getNumber(Pair<Integer, Integer> pos) {
        return null;
    }

    @Override
    public boolean hasMine(Pair<Integer, Integer> pos) {
        return this.mineManager.hasMine(pos);
    }
}
