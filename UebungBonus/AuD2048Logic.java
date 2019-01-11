/**
 * <p>
 * This class is the common base class for all variants of the 2048 game logic. For a general description of the game please refer to <a href="http://de.wikipedia.org/wiki/2048_(Computerspiel)">2048</a> at Wikipedia.
 * </p>
 * 
 * @author John Doe
 * @version 1.1, 12/06/18
 */
public abstract class AuD2048Logic {
	/** Denotes the number of rows (height) of the game board. */
	protected int rows;
	/** Denotes the number of columns (width) of the game board. */
	protected int cols;
	/**
	 * <p>
	 * The internal game board representation. The game board data is interpreted by the GUI as follows:
	 * </p>
	 * <ul>
	 * <li>a <b>negative</b> value indicates a <b>blocked</b> field</li>
	 * <li>a <b>value of 0</b> will show up in the GUI as an <b>empty</b> field</li>
	 * <li>a <b>positive</b> value will be displayed <b>itself</b> in the GUI</li>
	 * </ul>
	 */
	protected final long[][] gameBoard;
	/** Contains the <b>total sum</b> of <b>all</b> field <b>values</b> ever <b>merged</b> during one game run. */
	protected long score;

	public AuD2048Logic(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		gameBoard = new long[rows][cols];
	}

	/** Called by the GUI to display the game board. */
	public final long[][] getGameBoard() {
		return gameBoard;
	}

	/** Called by the GUI to display the score. */
	public final long getScore() {
		return score;
	}

	/** (Re)Initializes the game board, preparing it for a new game. */
	public abstract void startNewGame();

	/** Executes the move requested by the player according to the corresponding 2048 variant rules. */
	public abstract void move(Direction direction);

	/** Checks if the player has lost the game (i.e. no move is possible anymore). The game is over if all playable fields are non-empy (contain positive values) and no adjacent fields can be merged according to the corresponding 2048 variant rules. */
	public abstract boolean isGameOver();

	/** Checks if the player has won the game according to the corresponding 2048 variant rules. */
	public abstract boolean hasWinner();

	/** Called by the GUI to display the currently used logic. */
	@Override
	public final String toString() {
		return this.getClass().getName();
	}
}