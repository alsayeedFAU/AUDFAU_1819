import java.util.Arrays;

public class CatHouse extends CatBase {
	private CatRoom[] crs = null;

	public CatHouse(int height, int width) {
		super(height, width);
	}

	public boolean canPlace(CatRoom r, int x, int y) {
		// passen die Koordinaten?
		if (x > width || y > height || x < 0 || y < 0 || r == null) {
			throw new IllegalArgumentException("Illegal argument");
		}

		// CatRoom zu hoch/breit?
		if (r.getHeight() > (height - y) || r.getWidth() > (width - x)) {
			throw new IllegalArgumentException("Illegal argument");
			// return false;
		}

		// Ueberscheidet es sich mit anderen Raeumen?

		for (int i = y; i < y + r.getHeight(); i++) {
			for (int j = x; j < x + r.getWidth(); j++) {
				if (getCatRoomAt(j, i) != null) {
					return false;
				}
			}
		}

		return true;// !belegt[y][x];
	}

	public void place(CatRoom r, int x, int y) {
		// passen die Koordinaten?
		if (x > width || y > height || x < 0 || y < 0 || r == null) {
			throw new IllegalArgumentException("Illegal argument");
		}

		if (r.getHeight() > (height - y) || r.getWidth() > (width - x)) {
			throw new IllegalArgumentException("Illegal argument");
		}

		if (crs == null) {
			r.setXY(x, y);
			crs = new CatRoom[] { r };
		} else {

			CatRoom rt = null;
			for (CatRoom cr : crs) {
				if (r.equals(cr)) {
					rt = cr;
				}
			}

			if (rt == null) {
				rt = r;
			}
			rt.setXY(x, y);
			CatRoom[] tmp = Arrays.copyOf(crs, crs.length + 1);
			tmp[tmp.length - 1] = rt;
			crs = tmp;

		}

	}

	public void remove(CatRoom r) {
		if (r == null || crs == null) {
			throw new IllegalArgumentException("Null");
		}
		for (int i = 0; i < crs.length; i++) {
			if (crs[i].equals(r)) {
				r.setXY(-1, -1);
				if (crs.length > 1) {
					CatRoom[] tmp = new CatRoom[crs.length - 1];
					if (i > 0) {
						System.arraycopy(crs, 0, tmp, 0, i);
					}
					System.arraycopy(crs, i + 1, tmp, i, tmp.length - i);
					crs = tmp;
					r.setXY(-1, -1);
					i = crs.length + 10;
				} else {
					crs = new CatRoom[0];
					i = 10;
				}
			}

		}
	}

	public CatRoom getCatRoomAt(int x, int y) {
		if (x > width || y > height || x < 0 || y < 0) {
			throw new IllegalArgumentException("Illegal argument");
		}
		if (crs == null) {
			return null;
		}

		for (CatRoom r : crs) {
			if (x < r.getX() + r.getWidth() && x >= r.getX() && y < r.getY() + r.getHeight() && y >= r.getY()) {
				return r;
			}
		}

		return null;
	}

	public boolean placeAll(CatRoom[] rs) {
		if (rs == null || rs.length == 0) {
			return true;
		}

		for (int pointer = 0; pointer < rs.length; pointer++) {
			CatRoom tmp = rs[pointer];
			rs[pointer] = rs[rs.length - 1];

			while (tmp.placeNextPossible(this)) {
				if (placeAll(Arrays.copyOf(rs, rs.length - 1))) {
					return true;
				}
			}

			remove(tmp);
			tmp.setXY(-1, -1);
			rs[rs.length - 1] = rs[pointer];
			rs[pointer] = tmp;

		}

		return false;
	}

}
