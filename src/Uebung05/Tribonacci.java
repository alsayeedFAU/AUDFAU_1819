import java.util.Arrays;

public class Tribonacci {

	public static long delannoyNaive(TriboCheck tc, int n, int k) {
		tc.checkTribo();
		if (n == 0 || k == 0) {
			return 1;
		}
		return delannoyNaive(tc, n, k - 1) + delannoyNaive(tc, n - 1, k - 1) + delannoyNaive(tc, n - 1, k);
	}

	public static long delannoyMem(TriboCheck tc, int n, int k, long[][] mem) {
		tc.checkTribo();
		if (mem == null) {
			mem = new long[n + 1][k + 1];
			for (int i = 0; i < mem.length; i++) {
				for (int j = 0; j < mem[0].length; j++) {
					if (i == 0 || j == 0) {
						mem[i][j] = 1;
					}
				}
			}
			return delannoyMem(tc, n, k, mem);
		}
		if (mem[n][k] != 0) {
			return mem[n][k];
		}

		mem[n][k] = delannoyMem(tc, n, k - 1, mem) + delannoyMem(tc, n - 1, k - 1, mem)
				+ delannoyMem(tc, n - 1, k, mem);

		return mem[n][k];
	}

	public static long delannoyDP(TriboCheck tc, int n, int k) {
		tc.checkTribo();
		long[][] mem = new long[n + 1][k + 1];

		for (int i = 0; i < mem.length; i++) {
			for (int j = 0; j < mem[0].length; j++) {
				if (i == 0 || j == 0) {
					mem[i][j] = 1;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				mem[i][j] = mem[i - 1][j] + mem[i - 1][j - 1] + mem[i][j - 1];
			}
		}
		return mem[n][k];
	}

}
