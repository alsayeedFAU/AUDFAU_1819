

public class CatRoom extends CatBase {
	private int x = -1;
	private int y = -1;

	public CatRoom(int height, int width) {
		super(height, width);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean placeNextPossible(CatHouse ch) {
		if (x == -1 && y == -1) {
			for (int i = 0; i < ch.height - height + 1; i++) {
				for (int j = 0; j < ch.width - width + 1; j++) {
					if (ch.canPlace(this, j, i)) {
						ch.place(this, j, i);
						return true;
					}
				}
			}
		} else {
			int merkeX = x + 1;
			int merkeY = y;
			ch.remove(this);

			for (int i = merkeY; i < ch.height - height + 1; i++) {
				for (int j = merkeX; j < ch.width - width + 1; j++) {
					if (ch.canPlace(this, j, i)) {
						ch.place(this, j, i);
						return true;
					}
				}
				merkeX = 0;
			}

		}

		return false;
	}
}
