
public class AuD2048LogicEleven extends AuD2048LogicCommon {
	private int gamemode = 1;

	public AuD2048LogicEleven(int rows, int cols) {
		super(rows, cols);
	}

	@Override
	public void startNewGame() {
		super.startNewGame();
		super.warmup(gameBoard, super.startValues[gamemode]);
	}

	@Override
	public void move(Direction direction) {
		super.move(direction, gamemode);

	}

	@Override
	public boolean isGameOver() {
		return super.isGameOver();
	}

	@Override
	public boolean hasWinner() {
		return super.hasWinner(gamemode);
	}

}
