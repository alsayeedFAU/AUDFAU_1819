import java.util.ArrayList;

public class Algebra {

	public static long[][] primfaktorzerlegung(long l) {
		if (l <= 1) {
			return new long[][] { new long[] {}, new long[] {} };
		}

		ArrayList<Long> primfaktor = new ArrayList<>();
		ArrayList<Long> potenz = new ArrayList<>();

		for (long teiler = 2; teiler <= l; teiler++) {
			if (l % teiler == 0) {
				if (primfaktor.contains(teiler)) {
					potenz.set(primfaktor.indexOf(teiler), potenz.get(primfaktor.indexOf(teiler)) + 1);
				} else {
					primfaktor.add(teiler);
					potenz.add(1L);
				}
				l = l / teiler;
				teiler = 2;

			}
		}
		long[][] tmp = new long[2][primfaktor.size()];
		for (int i = 0; i < primfaktor.size(); i++) {
			tmp[0][i] = primfaktor.get(i);
			tmp[1][i] = potenz.get(i);
		}
		return tmp;
	}

	public static long ggT(long[][] aPFZ, long[][] bPFZ) {
		long result = 1;
		for (int i = 0; i < aPFZ[1].length; i++)
			for (int j = 0; j < bPFZ[1].length; j++)
				if (aPFZ[0][i] == bPFZ[0][j]) {
					if (aPFZ[1][i] > bPFZ[1][j])
						result *= Math.pow(bPFZ[0][j], bPFZ[1][j]);
					else if (aPFZ[1][i] < bPFZ[1][j])
						result *= Math.pow(aPFZ[0][i], aPFZ[1][i]);
					else
						result *= Math.pow(aPFZ[0][i], aPFZ[1][i]);
				}
		return result;
	}

	public static long kgV(int a, int b) {
		return ((a * b) / ggT(primfaktorzerlegung(a), primfaktorzerlegung(b)));
	}

}
