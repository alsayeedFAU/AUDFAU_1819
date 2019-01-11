import java.util.LinkedList;
import java.util.Queue;

public class PresentAssigner {
	protected int[] assignment;
	protected int[][] values;
	protected double[] prices;

	public PresentAssigner(int[][] values) {
		this.values = values;
	}

	public static int[][] makeQuadratic(int[][] array) {
		int x = -1, y = 0;
		boolean b = false;

		if (array == null) {
			return null;
		}

		if (array.length > 0) {
			y = array.length;
			for (int i = 0; i < y; i++) {

				if (x != -1 && array[i] != null && array[i].length != x) {
					b = true;
				}

				if (array[i] != null && x < array[i].length) {
					x = array[i].length;
				}

			}
		}

		if (x == y && !b) {
			return array;
		}

		if (!b || x != y) {
			int[][] tmp = new int[x > y ? x : y][x > y ? x : y];
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null) {
					System.arraycopy(array[i], 0, tmp[i], 0, array[i].length);
				}
			}
			return tmp;
		}

		return null;
	}

	public Queue<Integer> initialAssignment() {
		Queue<Integer> q = new LinkedList<>();

		if (values == null) {
			return q;
		}

		int[][] tmp = makeQuadratic(values);
		assignment = new int[tmp.length];

		for (int i = 0; i < tmp.length; i++) {
			assignment[i] = i;
			int prev = -1;
			for (int j = 0; j < tmp[i].length; j++) {
				if (prev < tmp[i][j]) {
					prev = tmp[i][j];
				}
			}

			if (prev != tmp[i][i]) {
				q.add(i);
			}

		}

		return q;
	}

	public void singleAuctionPhase(int person, Queue<Integer> q) {
		if (q == null) {
			return;
		}

		if (q.size() > 0) {
			q.remove();
		}

		int prefAk = -1;
		int prefAk2 = -1;
		int pos = 0;
		int[][] val = makeQuadratic(values);

		for (int i = 0; i < val[person].length; i++) {
			if (prefAk < val[person][i]) {
				prefAk = val[person][i];
				pos = i;
			}
			if (prefAk2 < val[person][i] && val[person][i] != prefAk) {
				prefAk2 = val[person][i];
			}
		}

		if (prices != null && (prices[pos] - (double) prefAk) >= (1d / (double) val.length)) {
			if (q.size() > 0) {
				singleAuctionPhase(q.element(), q);
			}
			return;
		}
		for (int i = 0; i < assignment.length; i++) {
			if (assignment[i] == pos) {
				assignment[i] = assignment[person];
				assignment[person] = pos;
				if (!q.contains(i) && i != person) {
					q.add(i);
				}
			}
		}

		if (prices == null) {
			prices = new double[val.length];
		}
		prices[pos] = prefAk - prefAk2 + (1d / (double) assignment.length);

	}

	public int[] getAssignment() {
		Queue<Integer> q = initialAssignment();

		while (q.size() != 0) {
			singleAuctionPhase(q.element(), q);
		}

		return assignment;
	}

}
