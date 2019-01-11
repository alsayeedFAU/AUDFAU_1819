public class RiddleSolver {
	public static boolean isSolution(RiddleNode[] path) {
		int tmpRes = path[0].value;
		for (int i = 1; i < path.length - 1; i += 2) {
			RiddleNode op = path[i];
			RiddleNode v = path[i + 1];
			switch (op.type) {
			case ADD:
				tmpRes += v.value;
				break;
			case SUB:
				tmpRes -= v.value;
				break;
			case MUL:
				tmpRes *= v.value;
				break;
			case DIV:
				if (tmpRes % v.value != 0) {
					return false;
				}
				tmpRes /= v.value;
				break;
			case EQ:
				if (tmpRes != v.value) {
					return false;
				}
				if (i < path.length - 2) {
					return false;
				}
				return true;
			default:
			}
		}
		return false;
	}

	// n == aktueller Knoten, p == Pfad zu n (exklusiv), s == Menge aller Pfade
	public static RiddleNode[][] allPaths(RiddleNode n, RiddleNode[] p, RiddleNode[][] s) {
		if (!n.visited) {
			n.visited = true;
			RiddleNode[] pn = appendNode(p, n); // Pfad ergaenzen
			if (n.result) { // Ende erreicht?
				s = storePath(pn, s);
			} else {
				for (RiddleNode r : n.getNeighbours()) {
					if (r != null) {
						s = allPaths(r, pn, s);
					}
				}
			}
			n.visited = false; // Backtrack
		}
		return s;
	}

	public static RiddleNode[] solve(RiddleNode start) {
		RiddleNode[][] s = allPaths(start, new RiddleNode[0], new RiddleNode[0][]);
		RiddleNode[] best = null;
		for (RiddleNode[] p : s) {
			boolean isSolution = isSolution(p);
			if (isSolution) {
				if (best == null || best.length > p.length) {
					best = p;
				}
			}
		}
		return best;
	}

	private static RiddleNode[] appendNode(RiddleNode[] p, RiddleNode n) {
		RiddleNode[] pn = java.util.Arrays.copyOf(p, p.length + 1);
		pn[p.length] = n;
		return pn;
	}

	private static RiddleNode[][] storePath(RiddleNode[] p, RiddleNode[][] s) {
		RiddleNode[][] sNew = java.util.Arrays.copyOf(s, s.length + 1);
		sNew[s.length] = java.util.Arrays.copyOf(p, p.length);
		return sNew;
	}
}
