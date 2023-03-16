package e2;

public interface Logics {

    boolean isThereVictory();

    void flag(Pair<Integer, Integer> pos);

    boolean hasFlag(Pair<Integer, Integer> pos);

    boolean hasNumber(Pair<Integer, Integer> pos);

    Integer getNumber(Pair<Integer, Integer> pos);

    boolean hasMine(Pair<Integer, Integer> pos);

    void showNumber(Pair<Integer, Integer> pos);
}
