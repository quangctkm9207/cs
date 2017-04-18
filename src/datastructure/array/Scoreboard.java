package datastructure.array;

/**
 * Created by user on 3/11/17.
 */
public class Scoreboard {
    private int numEntries = 0; //number of actual entries
    private GameEntry[] board; //array of game entries (name and scores)

    public Scoreboard(int capacity) {
        board = new GameEntry[capacity];
    }

    /**Attempts to add a new score to the collection (if it is high enough).*/
    public void add(GameEntry e) {
        int newScore = e.getScore();
        //is the new entry a really a high score
        if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
            if (numEntries < board.length)          //no score drops from the board
                numEntries ++;                      //so overall number increases

            //shift any lower score rightward to make room for the new entry
            int j = numEntries - 1;
            while (j > 0 && board[j-1].getScore() < newScore) {
                board[j] = board [j - 1];
                j--;
            }
            board[j] = e;
        }
    }

    /**Attempts to remove an existing score from the collection*/
    public GameEntry remove(int i) throws IndexOutOfBoundsException{
        if (i < 0 || i >= numEntries) {
            throw new IndexOutOfBoundsException("score index is invalid");
        }
        GameEntry temp = board[i]; //save the object to be removed
        numEntries --; //overall number decrease

        //Shifts all right items to the left from the index i + 1
        int j = i;
        while (j < numEntries) {
            board[j]  = board [j+ 1];
            j++;
        }

        board[j] = null; //Remove the last one to make it empty place

        return temp;
    }

}
