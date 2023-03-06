package e1;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    public static final int SIZE = 5;
    private final Logics logic = new LogicsImpl(SIZE);

    @Test
    void testUniquePawns(){
        int numberOfKnighst = 0;
        int numberOfPawns = 0;

        for (int i=0; i<SIZE; i++){
            for (int j=0; j<SIZE; j++){
                if (this.logic.hasKnight(i,j)){
                    numberOfKnighst++;
                }
                if (this.logic.hasPawn(i,j)){
                    numberOfPawns++;
                }
            }
        }

        assertEquals(1,numberOfKnighst);
        assertEquals(1, numberOfPawns);
    }

    @Test
    void checkValidPositions(){
        Pair<Integer, Integer> knightPosition = this.getKnightPosition();
        Pair<Integer,Integer> pawnPosition = this.getPawnPosition();
        assertTrue(this.isValidPosition(knightPosition));
        assertTrue(this.isValidPosition(pawnPosition));
    }

    @Test
    void checkDifferentKnightAndPawnPosition(){
        assertTrue(this.getKnightPosition() != this.getPawnPosition());
    }

    @Test
    void checkMovement(){
        Pair<Integer,Integer> initialPosition = this.getKnightPosition();
        for (int i=-2; i<=2; i+=4){
            for (int j=-1; j<=1; j+=2){
                if (this.isValidPosition(new Pair<>(initialPosition.getX()+i,initialPosition.getY()+j))) {
                    this.logic.hit(initialPosition.getX()+i,initialPosition.getY()+j);
                    assertEquals(new Pair<>(initialPosition.getX()+i,initialPosition.getY()+j), this.getKnightPosition());
                    this.logic.hit(initialPosition.getX(), initialPosition.getY());
                }
                if (this.isValidPosition(new Pair<>(initialPosition.getX()+j,initialPosition.getY()+i))) {
                    this.logic.hit(initialPosition.getX()+j,initialPosition.getY()+i);
                    assertEquals(new Pair<>(initialPosition.getX()+j,initialPosition.getY()+i), this.getKnightPosition());
                    this.logic.hit(initialPosition.getX(), initialPosition.getY());
                }
            }
        }
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

    private Pair<Integer, Integer> getKnightPosition() {
        return this.getPosition(t -> this.logic.hasKnight(t.getX(), t.getY()));
    }

    private Pair<Integer, Integer> getPawnPosition() {
        return this.getPosition(t -> this.logic.hasPawn(t.getX(), t.getY()));
    }

    private boolean isValidPosition(Pair<Integer,Integer> position){
        if (position.getX()>=0 && position.getX()<SIZE)
            return (position.getY()>=0 && position.getY() < SIZE);
        return false;
    }
}
