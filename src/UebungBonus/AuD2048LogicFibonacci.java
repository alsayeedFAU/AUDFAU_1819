
public class AuD2048LogicFibonacci extends AuD2048LogicCommon {
	private int gamemode = 2;

	public AuD2048LogicFibonacci(int rows, int cols) {
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
