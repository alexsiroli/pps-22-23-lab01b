package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    final static int SIZE = 5;
    Board boardImpl = new BoardImpl(SIZE, SIZE);

    @Test
    void checkSize() {
        int numberRows = this.boardImpl.getRows();
        int numberColumns = this.boardImpl.getColumns();
        assertEquals(SIZE, numberRows);
        assertEquals(SIZE, numberColumns);
    }

    @Test
    void checkValidPosition() {
        assertTrue(this.boardImpl.validPosition(0,0));
        assertTrue(this.boardImpl.validPosition(2,4));
        assertFalse(this.boardImpl.validPosition(SIZE,SIZE));
        assertFalse(this.boardImpl.validPosition(2,SIZE));
    }
}
