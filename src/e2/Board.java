package e2;

import e1.Pair;

/**
 * Interface of a game board, made up of any number of rows and
 * columns and on which it is possible to check if a given cell
 * belongs to it.
 */
public interface Board {

    /**
     * Method that checks if a cell is part of the board.
     *
     * @param row of cell
     * @param column of cell
     * @return true if the cell belongs to the board
     */
    boolean validPosition(int row, int column);

    /**
     * Method that checks if a cell is part of the board.
     *
     * @param cel to check
     * @return true if the cell belongs to the board
     */
    boolean validPosition(Pair<Integer, Integer> cel);

    /**
     * @return number of rows
     */
    int getRows();

    /**
     * @return number of columns
     */
    int getColumns();
}
