public class MazeGenerator {
	private static final java.util.Random RANDOM = new java.util.Random();
	private static MazeDirection[] directions = MazeDirection.class.getEnumConstants();
	private static boolean[][][] maze;
	private static boolean[][] visited;

	public static boolean[][][] generate(int width, int height) {
		maze = new boolean[height][width][4];
		visited = new boolean[height][width];
		shuffle(directions);
		int entry = RANDOM.nextInt(4);
		int exit = RANDOM.nextInt(4);
		if (entry == exit) {
			exit = (exit + 1) % 4;
		}
		int yEntry = directions[entry] == MazeDirection.NORTH ? 0 : directions[entry] == MazeDirection.SOUTH ? height - 1 : (int) (Math.random() * height);
		int xEntry = directions[entry] == MazeDirection.WEST ? 0 : directions[entry] == MazeDirection.EAST ? width - 1 : (int) (Math.random() * width);
		int yExit = directions[exit] == MazeDirection.NORTH ? 0 : directions[exit] == MazeDirection.SOUTH ? height - 1 : (int) (Math.random() * height);
		int xExit = directions[exit] == MazeDirection.WEST ? 0 : directions[exit] == MazeDirection.EAST ? width - 1 : (int) (Math.random() * width);
		maze[yEntry][xEntry][directions[entry].idx] = true;
		maze[yExit][xExit][directions[exit].idx] = true;
		generateRecursive(yEntry, xEntry);
		return maze;
	}

	private static void generateRecursive(int yFrom, int xFrom) {
		visited[yFrom][xFrom] = true;
		shuffle(directions);
		for (MazeDirection d : directions) {
			switch (d) {
			case NORTH:
				step(yFrom, xFrom, -1, 0);
				break;
			case EAST:
				step(yFrom, xFrom, 0, 1);
				break;
			case SOUTH:
				step(yFrom, xFrom, 1, 0);
				break;
			case WEST:
				step(yFrom, xFrom, 0, -1);
				break;
			}
		}
	}

	private static void shuffle(MazeDirection[] feld) {
		int len = feld.length;
		for (int i = 0; i < feld.length; i++) {
			int pos = i + RANDOM.nextInt(len - i);
			MazeDirection tmp = feld[i];
			feld[i] = feld[pos];
			feld[pos] = tmp;
		}
	}

	private static void step(int yFrom, int xFrom, int yDelta, int xDelta) {
		int yTo = yFrom + yDelta;
		int xTo = xFrom + xDelta;
		if (yTo < 0 || yTo >= maze.length || xTo < 0 || xTo >= maze[yTo].length || visited[yTo][xTo]) {
			return;
		}
		removeWall(yFrom, xFrom, yTo, xTo);
		generateRecursive(yTo, xTo);
	}

	private static void removeWall(int yFrom, int xFrom, int yTo, int xTo) {
		if (yFrom == yTo && xFrom - xTo == -1) { // "to" is EAST of "from"
			maze[yFrom][xFrom][MazeDirection.EAST.idx] = true;
			maze[yTo][xTo][MazeDirection.WEST.idx] = true;
		} else if (yFrom == yTo && xFrom - xTo == 1) { // "to" is WEST of "from"
			maze[yFrom][xFrom][MazeDirection.WEST.idx] = true;
			maze[yTo][xTo][MazeDirection.EAST.idx] = true;
		} else if (xFrom == xTo && yFrom - yTo == -1) { // "to" is SOUTH of "from"
			maze[yFrom][xFrom][MazeDirection.SOUTH.idx] = true;
			maze[yTo][xTo][MazeDirection.NORTH.idx] = true;
		} else if (xFrom == xTo && yFrom - yTo == 1) { // "to" is NORTH of "from"
			maze[yFrom][xFrom][MazeDirection.NORTH.idx] = true;
			maze[yTo][xTo][MazeDirection.SOUTH.idx] = true;
		} else { // rooms must be adjacent to each other
			System.err.format("Cannot connect (%d,%d) and (%d,%d)", yFrom, xFrom, yTo, xTo);
			return;
		}
	}
}