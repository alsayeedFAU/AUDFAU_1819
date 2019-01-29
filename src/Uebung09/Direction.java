
public enum Direction {
	E, N, W, S;

	public Direction turnCounterCW() {
		switch (this) {
		case E:
			return N;

		case N:
			return W;

		case W:
			return S;

		case S:
			return E;
		}

		return null;
	}
	
	public Direction turnCW() {
		switch (this) {
		case E:
			return S;

		case N:
			return E;

		case W:
			return N;

		case S:
			return W;
		}

		return null;
	}

}
