import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MazeGUI extends JPanel {
	public static void main(String[] args) {
		new MazeGUI(MazePublicTest.simpleMaze()).show();
	}

	private boolean[][][] maze;

	private MazeGUI(final boolean[][][] newMaze) {
		handleNewMaze(newMaze);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					handleNewMaze(MazeGenerator.generate(maze[0].length, maze.length));
				}
			}
		});
		addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				if (notches < 0 && maze.length > 3) { // up
					handleNewMaze(MazeGenerator.generate(maze[0].length - 1, maze.length - 1));
				} else if (notches > 0 && maze.length < 42) { // down
					handleNewMaze(MazeGenerator.generate(maze[0].length + 1, maze.length + 1));
				}
			}
		});
	}

	private void handleNewMaze(boolean[][][] maze) {
		this.maze = maze;
		setToolTipText("Left click for new random maze. Scroll wheel to change depth (now: " + maze[0].length + "x" + maze.length + ").");
		repaint();
	}

	@Override
	public void show() {
		JFrame frame = new JFrame("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(520, 520);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int offset = 10;
		int cellSizeW = (width - 2 * offset) / maze[0].length;
		int cellSizeH = (height - 2 * offset) / maze.length;
		int cellSize = cellSizeW < cellSizeH ? cellSizeW : cellSizeH;
		int xPos = offset;
		int yPos = offset;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		boolean[][] deadEnds = Maze.solveMaze(maze, new boolean[maze.length][maze[0].length]);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (deadEnds != null && deadEnds[i][j]) {
					g.setColor(Color.ORANGE);
					g.fillRect(xPos + 1, yPos + 1, cellSize, cellSize);
				}
				g.setColor(Color.BLACK);
				if (!maze[i][j][MazeDirection.NORTH.idx]) {
					g.drawLine(xPos, yPos, xPos + cellSize, yPos);
				}
				if (!maze[i][j][MazeDirection.EAST.idx]) {
					g.drawLine(xPos + cellSize, yPos, xPos + cellSize, yPos + cellSize);
				}
				if (!maze[i][j][MazeDirection.SOUTH.idx]) {
					g.drawLine(xPos, yPos + cellSize, xPos + cellSize, yPos + cellSize);
				}
				if (!maze[i][j][MazeDirection.WEST.idx]) {
					g.drawLine(xPos, yPos, xPos, yPos + cellSize);
				}
				xPos += cellSize;
			}
			xPos = offset;
			yPos += cellSize;
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(520, 520);
	}
}