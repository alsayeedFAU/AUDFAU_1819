public enum MazeDirection {
	NORTH(0), EAST(1), SOUTH(2), WEST(3);

	public final int idx;

	private MazeDirection(int idx) {
		this.idx = idx;
	}
}