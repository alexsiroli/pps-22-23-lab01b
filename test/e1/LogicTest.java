package e1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    public static final int SIZE = 5;
    private final Logics randomLogics = new LogicsImpl(SIZE);

    @Test
    void checkUniquePawns(){
        int numberOfKnighst = 0;
        int numberOfPawns = 0;

        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                if (this.randomLogics.hasKnight(i,j))
                    numberOfKnighst++;
                if (this.randomLogics.hasPawn(i,j))
                    numberOfPawns++;
            }
        }

        assertEquals(1,numberOfKnighst);
        assertEquals(1, numberOfPawns);
    }

    @Test
    void checkValidPositions(){
        Pair<Integer, Integer> knightPosition = this.getKnightPosition();
        assertTrue(this.isValidPosition(knightPosition));
        Pair<Integer,Integer> pawnPosition = this.getPawnPosition();
        assertTrue(this.isValidPosition(pawnPosition));
    }

    @Test
    void checkDifferentKnightAndPawnPosition(){
        assertNotSame(this.getKnightPosition(), this.getPawnPosition());
    }

    @Test
    void checkMovement(){
        Pair<Integer,Integer> initialPosition = this.getKnightPosition();
        for (Pair<Integer, Integer> movement : this.getPossibleMovements(initialPosition)) {
            if (this.isValidPosition(movement)){
                this.randomLogics.hit(movement.getX(), movement.getY());
                assertNotSame(initialPosition, this.getKnightPosition());
                assertTrue(this.isValidPosition(this.getKnightPosition()));
                this.randomLogics.hit(initialPosition.getX(), initialPosition.getY());
            }
        }
    }

    @Test
    void checkPawnHitted(){
        Pair<Integer,Integer> pawnPosition = new Pair<>(2,4);
        Pair<Integer,Integer> knightPosition = new Pair<>(0,0);
        final Logics selectedLogic = new LogicsImpl(SIZE, pawnPosition, knightPosition);
        assertFalse(selectedLogic.hit(1,2));
        assertTrue(selectedLogic.hit(2,4));
    }

    private List<Pair<Integer, Integer>> getPossibleMovements(Pair<Integer, Integer> initialPosition) {
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        for (int i=-2; i<=2; i+=4) {
            for (int j = -1; j <= 1; j += 2) {
                list.add(new Pair<>(initialPosition.getX()+i, initialPosition.getY()+j));
                list.add(new Pair<>(initialPosition.getX()+j, initialPosition.getY()+i));
            }
        }
        return list;
    }

    private Pair<Integer, Integer> getKnightPosition() {
        return this.getPosition(t -> this.randomLogics.hasKnight(t.getX(), t.getY()));
    }

    private Pair<Integer, Integer> getPawnPosition() {
        return this.getPosition(t -> this.randomLogics.hasPawn(t.getX(), t.getY()));
    }

    private Pair<Integer, Integer> getPosition(Predicate<Pair<Integer, Integer>> predicate) {
        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                if (predicate.test(new Pair<>(i,j))){
                    return new Pair<>(i,j);
                }
            }
        }
        return null;
    }

    private boolean isValidPosition(Pair<Integer,Integer> position){
        return position.getX()>=0 && position.getX()<SIZE
                && position.getY()>=0 && position.getY() < SIZE;
    }
}
