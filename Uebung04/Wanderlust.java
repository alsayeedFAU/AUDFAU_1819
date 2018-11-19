import java.util.Arrays;

public class Wanderlust {

	public static int solve(ReiseCheck rc, int[] values, int[][] next, int goal, int city, int[] path, int pathIdx) {
		rc.check();

		int[] newPath = new int[path.length];
		for (int i = 0; i < path.length; i++) {
			newPath[i] = path[i];
		}
		newPath[pathIdx] = city;

		int goaltmp = goal - values[city];
		if (goaltmp == 0) {
			rc.report(newPath);
			return 1;
		}

		int countSolution = 0;
		if (goal > 0) {
			for (int i : next[city]) {
				if (goaltmp > 0) {
					countSolution += solve(rc, values, next, goaltmp, i, newPath, pathIdx + 1);
				}
			}
		}
		return countSolution;

	}

	public static int solveMem(ReiseCheck rc, int[] values, int[][] next, int goal, int startcity, int[][] mem) {
		System.out.println("helllo");
		System.out.println(mem.length);
		System.out.println(mem[0].length);
		return 0;
	}

	public static int solveIt(ReiseCheck rc, int[] values, int[][] next, int goal, int startcity) {
		rc.check();

		int[][] mem = new int[next.length][goal];
		mem[startcity][values[startcity]] = 1;
		int solution = 0;
		boolean[] index = new boolean[next.length];
		index[startcity] = true;

		for (int i = 0; i < index.length; i++) {
			if (index[i]) {
				index[i] = false;
				for (int nex : next[i]) {
					for (int j = 0; j < goal; j++) {
						if (mem[i][j] != 0) {
							if (j + values[nex] < goal && mem[nex][j + values[nex]] == 0) {
								mem[nex][j + values[nex]] += 1;
								index[nex] = true;
							}

						}

					}

				}

				i = -1;
			}

		}

		for (int i = 0; i < values.length; i++) {
			for (int nex : next[i]) {
				if (goal - values[nex] > 0) {
					if (mem[i][goal - values[nex]] != 0) {
						solution += mem[i][goal - values[nex]];
					}
				}
			}
		}
		return solution;
	}

}
