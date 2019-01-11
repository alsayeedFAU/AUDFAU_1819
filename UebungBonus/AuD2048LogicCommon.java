
public class AuD2048LogicCommon extends AuD2048Logic {

	protected final long[] target = new long[] { 2048, 11, 2584 };
	protected long[][] startValues = new long[][] { { 2, 4 }, { 1, 2 }, { 1, 2 } };

	public AuD2048LogicCommon(int rows, int cols) {
		super(rows, cols);
	}

	@Override
	public void startNewGame() {
		score = 0;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				gameBoard[i][j] = 0;
			}
		}
	}

	public void warmup(long[][] gameboard, long[] startvalues) {
		int randomX = (int) ((Math.random() * 100) % rows);
		int randomY = (int) ((Math.random() * 100) % cols);

		gameboard[randomY][randomX] = ranNumber(startvalues);

		while (gameboard[randomY][randomX] != 0) {
			randomX = (int) ((Math.random() * 100) % rows);
			randomY = (int) ((Math.random() * 100) % cols);
		}

		gameboard[randomY][randomX] = ranNumber(startvalues);

	}

	private long ranNumber(long[] l) {
		return Math.random() * 100 < 75 ? l[0] : l[1];
	}

	private long fib(long x) {
		return x == 1 || x == 0 ? 1 : fib(x - 1) + fib(x - 2);
	}

	private long getNextFib(long x) {
		for (long l = 1; l < 20; l++) {
			if (fib(l) == x) {
				return fib(l + 1);
			}
		}
		return 0;
	}

	private void next(int mode) {
		int freeFields = 0;

		for (int i = 1; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if(gameBoard[i][j] == 0) {
					freeFields++;
				}
			}
		}
		
		if(freeFields == 0) {
			return;
		}

		int randomX = (int) ((Math.random() * 100) % rows);
		int randomY = (int) ((Math.random() * 100) % cols);

		while (gameBoard[randomY][randomX] != 0) {
			randomX = (int) ((Math.random() * 100) % rows);
			randomY = (int) ((Math.random() * 100) % cols);
		}

		gameBoard[randomY][randomX] = ranNumber(startValues[mode]);
	}

	public void move(Direction direction, int mode) {
		boolean[][] gameB = new boolean[gameBoard.length][gameBoard[0].length];

		switch (direction) {
		case UP:
			for (int i = 1; i < cols; i++) {
				for (int j = 0; j < rows; j++) {

					// Zahl finden
					int y = i;
					int x = j;

					if (gameBoard[y][x] != 0) {
						// zahl so weit wie moeglich bewegen
						while (y > 0 && gameBoard[y - 1][x] == 0) {
							y--;
						}
						gameBoard[y][x] = gameBoard[i][x];
						if (i != y) {
							gameBoard[i][x] = 0;
						}

						if (y != 0) {
							// pruefen verschmelzung
							if (gameBoard[y][x] == gameBoard[y - 1][x] && !gameB[y - 1][x]) {
								// verschmelzen
								switch (mode) {
								case 0:
									gameBoard[y - 1][x] *= 2;
									break;
								case 1:
									gameBoard[y - 1][x] += 1;
									break;
								case 2:
									gameBoard[y - 1][x] = getNextFib(gameBoard[y][x]);
									break;
								}
								// aufraeumen
								gameB[y - 1][x] = true;
								score += gameBoard[y - 1][x];
								gameBoard[y][x] = 0;
							}

						}

					}

				}
			}

			next(mode);

			break;
		case DOWN:
			for (int i = cols - 1; i >= 0; i--) {
				for (int j = 0; j < rows; j++) {
					// Zahl finden
					int y = i;
					int x = j;

					if (gameBoard[y][x] != 0 && i < rows - 1) {
						// zahl so weit wie moeglich bewegen
						while (y < rows - 1 && gameBoard[y + 1][x] == 0) {
							y++;
						}
						gameBoard[y][x] = gameBoard[i][x];
						if (i != y) {
							gameBoard[i][x] = 0;
						}

						if (y != cols - 1) {
							// pruefen verschmelzung
							if (gameBoard[y][x] == gameBoard[y + 1][x] && !gameB[y + 1][x]) {
								// verschmelzen
								switch (mode) {
								case 0:
									gameBoard[y + 1][x] *= 2;
									break;
								case 1:
									gameBoard[y + 1][x] += 1;
									break;
								case 2:
									gameBoard[y + 1][x] = getNextFib(gameBoard[y][x]);
									break;
								}
								// aufraeumen
								gameB[y + 1][x] = true;
								score += gameBoard[y + 1][x];
								gameBoard[y][x] = 0;
							}

						}

					}

				}
			}

			next(mode);

			break;
		case LEFT:
			for (int i = 0; i < cols; i++) {
				for (int j = 1; j < rows; j++) {

					// Zahl finden
					int y = i;
					int x = j;

					if (gameBoard[y][x] != 0) {
						// zahl so weit wie moeglich bewegen
						while (x > 0 && gameBoard[y][x - 1] == 0) {
							x--;
						}
						gameBoard[y][x] = gameBoard[y][j];
						if (j != x) {
							gameBoard[y][j] = 0;
						}

						if (x != 0) {
							// pruefen verschmelzung
							if (gameBoard[y][x] == gameBoard[y][x - 1] && !gameB[y][x - 1]) {
								// verschmelzen
								switch (mode) {
								case 0:
									gameBoard[y][x - 1] *= 2;
									break;
								case 1:
									gameBoard[y][x - 1] += 1;
									break;
								case 2:
									gameBoard[y][x - 1] = getNextFib(gameBoard[y][x]);
									break;
								}
								// aufraeumen
								gameB[y][x - 1] = true;
								score += gameBoard[y][x - 1];
								gameBoard[y][x] = 0;
							}

						}

					}

				}
			}

			next(mode);

			break;
		case RIGHT:
			for (int i = 0; i < cols; i++) {
				for (int j = rows - 2; j >= 0; j--) {

					// Zahl finden
					int y = i;
					int x = j;

					if (gameBoard[y][x] != 0) {
						// zahl so weit wie moeglich bewegen
						while (x < rows - 1 && gameBoard[y][x + 1] == 0) {
							x++;
						}
						gameBoard[y][x] = gameBoard[y][j];
						if (j != x) {
							gameBoard[y][j] = 0;
						}

						if (x != rows - 1) {
							// pruefen verschmelzung
							if (gameBoard[y][x] == gameBoard[y][x + 1] && !gameB[y][x + 1]) {
								// verschmelzen
								switch (mode) {
								case 0:
									gameBoard[y][x + 1] *= 2;
									break;
								case 1:
									gameBoard[y][x + 1] += 1;
									break;
								case 2:
									gameBoard[y][x + 1] = getNextFib(gameBoard[y][x]);
									break;
								}
								// aufraeumen
								gameB[y][x + 1] = true;
								score += gameBoard[y][x + 1];
								gameBoard[y][x] = 0;
							}

						}

					}

				}
			}

			next(mode);
			break;
		}

	}

	@Override
	public boolean isGameOver() {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (gameBoard[i][j] == 0) {
					return false;
				}
			}
		}

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (!(i == cols - 1 && j == rows - 1)) {
					if (i == cols - 1) {
						if (gameBoard[i][j] == 0 || gameBoard[i][j] == gameBoard[i][j + 1]) {
							return false;
						}
					} else if (j == rows - 1) {
						if (gameBoard[i][j] == 0 || gameBoard[i][j] == gameBoard[i + 1][j]) {
							return false;
						}
					} else {
						if (gameBoard[i][j] == 0 || gameBoard[i][j] == gameBoard[i][j + 1]
								|| gameBoard[i][j] == gameBoard[i + 1][j]) {
							return false;
						}
					}

				}
			}
		}

		return true;

	}

	public boolean hasWinner(int z) {
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (gameBoard[i][j] == target[z]) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void move(Direction direction) {

	}

	@Override
	public boolean hasWinner() {
		System.out.println("2");
		return false;
	}

}
