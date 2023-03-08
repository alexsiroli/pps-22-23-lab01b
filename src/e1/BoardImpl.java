package e1;

public class BoardImpl implements Board {

    private final int rows;
    private final int columns;

    public BoardImpl(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public BoardImpl(int n) {
        this(n, n);
    }

    public boolean validPosition(int row, int column) {
        return (row>=0 && column>=0 &&
                row < this.rows && column < this.columns);
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }
}
