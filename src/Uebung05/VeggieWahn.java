
public class VeggieWahn {

	public static long essen(int v, int g) {
		long[][] tmp = new long[v + 1][g + 1];

		return 2 * helper(v, g, tmp);
	}

	private static long helper(int v, int g, long[][] tmp) {
		if (tmp[v][g] != 0) {
			return tmp[v][g];
		}

		if (v == g || v == 1 || g == 1) {
			tmp[v][g] = 1;
		} else {
			tmp[v][g] = helper(v - 1, g - 1, tmp) + v * helper(v, g - 1, tmp);
		}
		return tmp[v][g];
	}

}
