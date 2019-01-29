
public class LSD {
	private final Direction dir;
	private final LSD lsd;

	public LSD(Direction dir) {
		this.dir = dir;
		this.lsd = null;
	}

	private LSD(LSD lsd) {
		this.dir = null;
		this.lsd = lsd;
	}

	public LSD step() {
		return new LSD(this);
	}

	public Colour getCol(Long x, Long y) {
		if (lsd == null) {
			return Colour.WHITE;
		}
		Colour colTemp = lsd.getCol(x, y);
		if (x == lsd.getX() && y == lsd.getY()) {
			colTemp = colTemp.flip();
		}
		return colTemp;
	}

	public Direction getDir() {
		if (dir != null) {
			return dir;
		}
		Direction dirTemp = lsd.getDir();

		return getCol(lsd.getX(), lsd.getY()) == Colour.WHITE ? dirTemp.turnCounterCW() : dirTemp.turnCW();
	}

	public Long getY() {
		if (lsd == null) {
			return 0L;
		}

		switch (getDir()) {
		case N:
			return 1 + lsd.getY();
		case S:
			return -1 + lsd.getY();

		default:
			return lsd.getY();
		}
	}

	public Long getX() {
		if (lsd == null) {
			return 0L;
		}

		switch (getDir()) {
		case E:
			return 1 + lsd.getX();
		case W:
			return -1 + lsd.getX();

		default:
			return lsd.getX();
		}
	}

}
