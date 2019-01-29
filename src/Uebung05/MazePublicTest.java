import static org.junit.Assert.*;
import org.junit.*;

public class MazePublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_Maze_isDeadEnd = "Maze.isDeadEnd";
	protected static final String EX_NAME_Maze_seekDeadEnd = "Maze.seekDeadEnd";
	protected static final String EX_NAME_Maze_solveMaze = "Maze.solveMaze";
	protected static final String EX_NAME_Maze_getSteps = "Maze.getSteps";
	// --------------------

	// ========== TEST-DATA ==========
	// ## = Wand, ~~=Durchgang
	// #######################
	// ~~_0,0_##_0,1_~~_0,2_##
	// ##~~~~~##~~~~~#########
	// ##_1,0_~~_1,1_~~_1,2_##
	// #########~~~~~##~~~~~##
	// ##_2,0_~~_2,1_##_2,2_~~ <= exit
	// #######################
	public static boolean[][][] simpleMaze() {
		boolean[][][] maze = new boolean[3][3][4];
		maze[0][0][MazeDirection.WEST.idx] = true; // entry
		maze[2][2][MazeDirection.EAST.idx] = true; // exit
		maze[0][1][MazeDirection.EAST.idx] = maze[0][2][MazeDirection.WEST.idx] = true;
		maze[0][0][MazeDirection.SOUTH.idx] = maze[1][0][MazeDirection.NORTH.idx] = true;
		maze[0][1][MazeDirection.SOUTH.idx] = maze[1][1][MazeDirection.NORTH.idx] = true;
		maze[1][0][MazeDirection.EAST.idx] = maze[1][1][MazeDirection.WEST.idx] = true;
		maze[1][1][MazeDirection.EAST.idx] = maze[1][2][MazeDirection.WEST.idx] = true;
		maze[1][1][MazeDirection.SOUTH.idx] = maze[2][1][MazeDirection.NORTH.idx] = true;
		maze[1][2][MazeDirection.SOUTH.idx] = maze[2][2][MazeDirection.NORTH.idx] = true;
		maze[2][0][MazeDirection.EAST.idx] = maze[2][1][MazeDirection.WEST.idx] = true;
		return maze;
	}

	// ========== PUBLIC TEST ==========
	@Test(timeout = 666)
	public void pubTest__isDeadEnd() {
		boolean[][][] maze = MazePublicTest.simpleMaze(), mazeOriginal = MazePublicTest.clone(maze);
		boolean[][] deadEnds = MazePublicTest.getdDeadEndsFor(maze), deadEndsOriginal = MazePublicTest.getdDeadEndsFor(maze);
		boolean actual;
		// --------------------
		actual = Maze.isDeadEnd(maze, deadEnds, 0, 2);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", mazeOriginal, maze);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", deadEndsOriginal, deadEnds);
		assertTrue(MazePublicTest.EX_NAME_Maze_isDeadEnd + " failed for (0,2).", actual);
		// --------------------
		actual = Maze.isDeadEnd(maze, deadEnds, 0, 1);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", mazeOriginal, maze);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", deadEndsOriginal, deadEnds);
		assertFalse(MazePublicTest.EX_NAME_Maze_isDeadEnd + " failed for (0,1).", actual);
		// --------------------
		deadEnds[0][2] = deadEndsOriginal[0][2] = true;
		actual = Maze.isDeadEnd(maze, deadEnds, 0, 1);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", mazeOriginal, maze);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", deadEndsOriginal, deadEnds);
		assertTrue(MazePublicTest.EX_NAME_Maze_isDeadEnd + " failed for (0,1).", actual);
	}

	@Test(timeout = 666)
	public void pubTest__seekDeadEnd() {
		boolean[][][] maze = MazePublicTest.simpleMaze(), mazeOriginal = MazePublicTest.clone(maze);
		boolean[][] deadEnds = MazePublicTest.getdDeadEndsFor(maze), deadEndsOriginal = MazePublicTest.getdDeadEndsFor(maze);
		int[] actuals;
		// --------------------
		actuals = Maze.seekDeadEnd(maze, deadEnds);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", mazeOriginal, maze);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", deadEndsOriginal, deadEnds);
		assertArrayEquals(MazePublicTest.EX_NAME_Maze_seekDeadEnd + " failed before setting deadEnds.", new int[] { 0, 2 }, actuals);
		// --------------------
		deadEnds[0][2] = deadEndsOriginal[0][2] = true;
		actuals = Maze.seekDeadEnd(maze, deadEnds);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", mazeOriginal, maze);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", deadEndsOriginal, deadEnds);
		assertArrayEquals(MazePublicTest.EX_NAME_Maze_seekDeadEnd + " failed after setting deadEnds.", new int[] { 0, 1 }, actuals);
	}

	@Test(timeout = 666)
	public void pubTest__solveMaze() {
		boolean[][][] maze = MazePublicTest.simpleMaze(), mazeOriginal = MazePublicTest.clone(maze);
		boolean[][] deadEndsActuals = MazePublicTest.getdDeadEndsFor(maze), deadEndsExpecteds = MazePublicTest.getdDeadEndsFor(maze);
		deadEndsExpecteds[0][2] = deadEndsExpecteds[0][1] = deadEndsExpecteds[2][0] = deadEndsExpecteds[2][1] = true;
		deadEndsActuals = Maze.solveMaze(maze, deadEndsActuals);
		assertArrayEquals("DO NOT MODIFY THE INPUT!", mazeOriginal, maze);
		assertArrayEquals(MazePublicTest.EX_NAME_Maze_solveMaze + " failed.", deadEndsExpecteds, deadEndsActuals);
	}

	@Test(timeout = 666)
	public void pubTest__getSteps() {
		boolean[][][] maze = MazePublicTest.simpleMaze(), mazeOriginal = MazePublicTest.clone(maze);
		boolean[][] deadEndsActuals = MazePublicTest.getdDeadEndsFor(maze), deadEndsExpected = MazePublicTest.getdDeadEndsFor(maze);
		int actual = Maze.getSteps(maze, deadEndsActuals);
		deadEndsExpected[0][2] = deadEndsExpected[0][1] = deadEndsExpected[2][0] = deadEndsExpected[2][1] = true;
		assertArrayEquals("DO NOT MODIFY THE INPUT!", mazeOriginal, maze);
		assertArrayEquals(MazePublicTest.EX_NAME_Maze_getSteps + " failed (solve).", deadEndsExpected, deadEndsActuals);
		assertEquals(MazePublicTest.EX_NAME_Maze_getSteps + " failed (steps).", 5, actual);
	}

	// ========== HELPER ==========
	protected static final boolean[][] getdDeadEndsFor(boolean[][][] maze) {
		if (maze == null) {
			return null;
		} else if (maze.length == 0 || maze[0] == null) {
			return new boolean[0][];
		} else {
			return new boolean[maze.length][maze[0].length];
		}
	}

	protected static final boolean[][][] clone(boolean[][][] in) {
		boolean[][][] out = null;
		if (in != null) {
			out = new boolean[in.length][][];
			for (int i = 0; i < in.length; i++) {
				if (in[i] != null) {
					out[i] = new boolean[in[i].length][];
					for (int j = 0; j < in[i].length; j++) {
						if (in[i][j] != null) {
							out[i][j] = new boolean[in[i][j].length];
							for (int k = 0; k < in[i][j].length; k++) {
								out[i][j][k] = in[i][j][k];
							}
						}
					}
				}
			}
		}
		return out;
	}
}