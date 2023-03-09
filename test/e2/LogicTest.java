package e2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicTest {

    private static final int SIZE = 3;
    private static final int N_MINES = 7;
    private final Logics logics = new LogicsImpl(SIZE, N_MINES);

    @Test
    void checkMines() {
        int mines = 0;
        for (var elem : this.getAllPositions()) {
            mines = this.logics.hasMine(elem) ? mines+1 : mines;
        }
        assertEquals(N_MINES, mines);
    }

    private List<Pair<Integer, Integer>> getAllPositions() {
        final var list = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                list.add(new Pair<>(i, j));
            }
        }
        return list;
    }

}
