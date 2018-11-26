
public class Maze {

	public static boolean isDeadEnd(boolean[][][] maze, boolean[][] deadEnds, int x, int y) {
		int counter = 0;
		if (!deadEnds[x][y]) {
			if (!maze[x][y][MazeDirection.NORTH.idx]) {
				counter += 1;
			} else if (x > 0) {
				if (deadEnds[x - 1][y])
					counter += 1;
			}
			if (!maze[x][y][MazeDirection.SOUTH.idx]) {
				counter += 1;
			} else if (x < maze.length - 1) {
				if (deadEnds[x + 1][y])
					counter += 1;
			}
			if (!maze[x][y][MazeDirection.WEST.idx]) {
				counter += 1;
			} else if (y > 0) {
				if (deadEnds[x][y - 1])
					counter += 1;
			}
			if (!maze[x][y][MazeDirection.EAST.idx]) {
				counter += 1;
			} else if (y < maze[0].length - 1) {
				if (deadEnds[x][y + 1])
					counter += 1;
			}
		}
		return counter >= 3 ? true : false;
	}

	public static int[] seekDeadEnd(boolean[][][] maze, boolean[][] deadEnds) {
		for (int y = 0; y < maze.length; y++) {
			for (int x = 0; x < maze[0].length; x++) {
				if (isDeadEnd(maze, deadEnds, y, x)) {
					return new int[] { y, x };
				}
			}
		}
		return null;
	}

	public static boolean[][] solveMaze(boolean[][][] maze, boolean[][] deadEnds) {
		int[] koords = seekDeadEnd(maze, deadEnds);
		if (koords == null) {
			//System.out.println("hello");
			return deadEnds;
		}

		deadEnds[koords[0]][koords[1]] = true;
		return solveMaze(maze, deadEnds);
	}

	public static int getSteps(boolean[][][] maze, boolean[][] deadEnds) {
		deadEnds = solveMaze(maze, deadEnds);
		int steps = 0;
		for (int y = 0; y < deadEnds.length; y++) {
			for (int x = 0; x < deadEnds[0].length; x++) {
				if (!deadEnds[y][x]) {
					steps += 1;
				}
			}
		}
		return steps;
	}

}
