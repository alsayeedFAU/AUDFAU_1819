public class RiddleNode {
	public final RiddleType type;
	public final int value; // Zahlenwert, falls Knotentyp == VAL
	public final boolean result; // Endknoten?
	private final RiddleNode[] neighbours = new RiddleNode[4]; // direkte Nachbarknoten
	boolean visited = false;

	public RiddleNode(RiddleType type, int value, boolean result) {
		this.type = type;
		this.value = value;
		this.result = result;
	}

	public void setNeighbours(RiddleNode... neighbours) {
		if (neighbours == null) {
			throw new IllegalArgumentException("neighbours must not be null!");
		}
		if (neighbours.length > 4) {
			throw new IllegalArgumentException("Each node must have 2, 3 or 4 non-null neighbours - not more!");
		}
		int count = 0;
		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i] != null) {
				count++;
			}
			this.neighbours[i] = neighbours[i];
		}
		if (count < 2) {
			throw new IllegalArgumentException("Each node must have 2, 3 or 4 non-null neighbours - not less!");
		}
	}

	public RiddleNode[] getNeighbours() {
		return java.util.Arrays.copyOf(neighbours, neighbours.length);
	}

	@Override
	public String toString() {
		String v = "", r = "";
		if (type == RiddleType.VAL) {
			v = ":" + value;
		}
		if (result) {
			r = ":#";
		}
		return "[" + type + v + r + "]";
	}
}