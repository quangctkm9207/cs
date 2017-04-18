package datastructure.array;

/**
 * Created by user on 3/11/17.
 */
public class GameEntry {
    private String name;
    private int score;

    public GameEntry(String n, int s) {
        name = n;
        score = s;
    }

    public String getName(){
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + score + ")";
    }
}
